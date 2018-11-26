package org.flyve.admin.dashboard.sms;

import android.Manifest;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import org.flyve.admin.dashboard.R;
import org.mobicents.protocols.ss7.map.api.smstpdu.SmsStatusReportTpdu;
import org.mobicents.protocols.ss7.map.api.smstpdu.SmsTpdu;
import org.mobicents.protocols.ss7.map.api.smstpdu.SmsTpduType;
import org.mobicents.protocols.ss7.map.api.smstpdu.Status;
import org.mobicents.protocols.ss7.map.smstpdu.SmsTpduImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class FragmentSMS extends Fragment {


    final byte[] payload = new byte[]{0x0A, 0x06, 0x03, (byte) 0xB0, (byte) 0xAF, (byte) 0x82, 0x03, 0x06, 0x6A, 0x00, 0x05};
    byte[] lastSendResultPDU = new byte[0];

    final String TAG = "Ping SMS";

    final String SENT = "pingsms.sent";
    final String DELIVER = "pingsms.deliver";

    final IntentFilter sentFilter = new IntentFilter(SENT);
    final IntentFilter deliveryFilter = new IntentFilter(DELIVER);
    final IntentFilter wapDeliveryFilter = new IntentFilter("android.provider.Telephony.WAP_PUSH_DELIVER");

    SharedPreferences preferences;
    public final static String PREF_LAST_NUMBER = "pref_last_number";
    public final static String PREF_HISTORY = "pref_history";
    public final static String PREF_RECEIVE_DATA_SMS = "pref_receive_data_sms";

    ArrayAdapter<String> historyAdapter;
    ArrayList<String> historyContent = new ArrayList<>();

    PendingIntent sentPI;
    PendingIntent deliveryPI;

    EditText phoneNumber;
    TextView statusText, resultText;
    ListView historyList;
    ImageButton resultPduDetails;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.activity_sms, container, false);

        phoneNumber = v.findViewById(R.id.phoneNumber);
        statusText = v.findViewById(R.id.sendStatus);
        resultText = v.findViewById(R.id.resultStatus);
        historyList = v.findViewById(R.id.historyList);
        resultPduDetails = v.findViewById(R.id.resultPduDetails);

        preferences = this.getActivity().getPreferences(Context.MODE_PRIVATE);
        phoneNumber.setText(preferences.getString(PREF_LAST_NUMBER, getString(R.string.phonenumber)));
        setHasOptionsMenu(true);

        v.findViewById(R.id.sendButton).setOnClickListener(e -> {
            final String phoneNum = phoneNumber.getText().toString();
            if (FragmentSMS.this.checkPermissions() && !TextUtils.isEmpty(phoneNum) && Patterns.PHONE.matcher(phoneNum).matches()) {
                resultText.setText(null);
                updateHistory(phoneNum);
                SmsManager.getDefault().sendDataMessage(phoneNum, null, (short) 9200, payload, sentPI, deliveryPI);
            }
        });

        resultPduDetails.setOnClickListener(e -> showPduInfoDialog());

        sentPI = PendingIntent.getBroadcast(getActivity(), 0x1337, new Intent(SENT), PendingIntent.FLAG_CANCEL_CURRENT);
        deliveryPI = PendingIntent.getBroadcast(getActivity(), 0x1337, new Intent(DELIVER), PendingIntent.FLAG_CANCEL_CURRENT);

        historyAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_2, android.R.id.text1);
        historyList.setAdapter(historyAdapter);
        updateHistory(null);

        historyList.setOnItemClickListener((parent, view, position, id) -> phoneNumber.setText(historyAdapter.getItem(position)));
        return v;
    }

    void updateHistory(String current) {
        if (current != null) {
            preferences.edit().putString(PREF_LAST_NUMBER, current).apply();
            preferences.edit().putString(PREF_HISTORY, preferences.getString(PREF_HISTORY, "").concat(current + ",")).apply();
        }

        historyContent.clear();
        historyContent.addAll(Arrays.asList(preferences.getString(PREF_HISTORY, "").split(",")));

        if (historyAdapter != null) {
            historyAdapter.clear();
            historyAdapter.addAll(historyContent);
            historyAdapter.notifyDataSetChanged();
        }
    }

    boolean checkPermissions() {
        List<String> missingPermissions = new ArrayList<>();

        int sendSmsPermission = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.SEND_SMS);
        int readPhonePermission = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_PHONE_STATE);
        int receiveSmsPermission = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.RECEIVE_SMS);

        if (sendSmsPermission != PackageManager.PERMISSION_GRANTED) {
            missingPermissions.add(Manifest.permission.SEND_SMS);
        }

        if (readPhonePermission != PackageManager.PERMISSION_GRANTED) {
            missingPermissions.add(Manifest.permission.READ_PHONE_STATE);
        }

        if (receiveSmsPermission != PackageManager.PERMISSION_GRANTED && preferences.getBoolean(PREF_RECEIVE_DATA_SMS, false)) {
            missingPermissions.add(Manifest.permission.RECEIVE_SMS);
        }

        if (!missingPermissions.isEmpty()) {
            ActivityCompat.requestPermissions(getActivity(), missingPermissions.toArray(new String[0]), 1);
            return false;
        }

        return true;
    }

    String getLogBytesHex(byte[] array) {
        StringBuilder sb = new StringBuilder();
        for (byte b : array) {
            sb.append(String.format("0x%02X ", b));
        }
        return sb.toString();
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().registerReceiver(br, sentFilter);
        getActivity().registerReceiver(br, deliveryFilter);
        getActivity().registerReceiver(br, wapDeliveryFilter);
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(br);
    }

    public void showPduInfoDialog() {
        if (lastSendResultPDU == null || lastSendResultPDU.length == 0) {
            resultPduDetails.setVisibility(View.INVISIBLE);
            return;
        }

        SmsTpdu parsedTpdu = getSmsTpdu(lastSendResultPDU);

        AlertDialog dialog = (new AlertDialog.Builder(getActivity()))
                .setTitle("Result PDU details")
                .setMessage(parsedTpdu != null ? parsedTpdu.toString() : "N/A")
                .setCancelable(true)
                .setNeutralButton("Close", (dialog1, which) -> dialog1.dismiss())
                .create();

        dialog.show();
    }

    @Nullable
    public SmsTpdu getSmsTpdu(byte[] pduBytes) {
        SmsTpdu result = null;
        try {
            result = SmsTpduImpl.createInstance(pduBytes, false, null);
        } catch (Exception e) {
            Log.d(TAG, "getSmsTpdu:1", e);
        }
        if (result == null) {
            try {
                byte[] pduWithoutSCA = Arrays.copyOfRange(pduBytes, pduBytes[0] + 1, pduBytes.length);
                result = SmsTpduImpl.createInstance(pduWithoutSCA, false, null);
            } catch (Exception e) {
                Log.d(TAG, "getSmsTpdu:2", e);
            }
        }
        return result;
    }

    BroadcastReceiver br = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e(TAG, "intent: " + ((intent == null || intent.getAction() == null) ? "null" : intent.getAction()));
            Log.e(TAG, "result: " + getResultCode());
            Log.e(TAG, "pdu (if any): " + ((intent != null && intent.hasExtra("pdu")) ? getLogBytesHex(intent.getByteArrayExtra("pdu")) : ""));

            if (intent == null) {
                return;
            }

            if (SENT.equalsIgnoreCase(intent.getAction())) {
                statusText.setText((getResultCode() == RESULT_OK ? R.string.sent : R.string.notsent));
                resultText.setText(null);
            } else if (DELIVER.equalsIgnoreCase(intent.getAction())) {
                boolean delivered = false;
                if (intent.hasExtra("pdu")) {
                    byte[] pdu = intent.getByteArrayExtra("pdu");
                    if (pdu != null && pdu.length > 1) {
                        lastSendResultPDU = pdu;
                        resultPduDetails.setVisibility(View.VISIBLE);
                        SmsTpdu parsedResultPdu = getSmsTpdu(pdu);
                        if (parsedResultPdu != null) {
                            Log.d(TAG, parsedResultPdu.toString());
                            delivered = parsedResultPdu.getSmsTpduType().equals(SmsTpduType.SMS_STATUS_REPORT) && ((SmsStatusReportTpdu) parsedResultPdu).getStatus().getCode() == Status.SMS_RECEIVED;
                        } else {
                            String resultPdu = getLogBytesHex(pdu).trim();
                            delivered = "00".equalsIgnoreCase(resultPdu.substring(resultPdu.length() - 2));
                        }
                    }
                }
                resultText.setText(delivered ? R.string.delivered : R.string.offline);
            }

        }
    };
}
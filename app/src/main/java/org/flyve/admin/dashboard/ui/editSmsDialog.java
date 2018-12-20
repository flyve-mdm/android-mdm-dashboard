package org.flyve.admin.dashboard.ui;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import org.flyve.admin.dashboard.R;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class editSmsDialog extends AppCompatDialogFragment {

    private EditText editNumberDialog;
    private EditText editIMEIDialog;
    private EditText editSimcardDialog;

    public int messagePosition;

    private DialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(getContext());
        lbm.registerReceiver(mMessageReceiver, new IntentFilter("passPosition"));

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_sms_change, null);

        builder.setView(view)
                .setTitle("Edit the parameters")
                .setPositiveButton(R.string.accept, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String numberDialog = editNumberDialog.getText().toString();
                        String imeiDialog = editIMEIDialog.getText().toString();
                        String simcardDialog = editSimcardDialog.getText().toString();


                        listener.applyTexts(messagePosition, numberDialog, imeiDialog, simcardDialog);

                        listener.onDialogPositiveClick(editSmsDialog.this);
                    }
                }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) { }
        });

        editNumberDialog = view.findViewById(R.id.editNumberDialog);
        editIMEIDialog = view.findViewById(R.id.editIMEIDialog);
        editSimcardDialog = view.findViewById(R.id.editSimCardDialog);


        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (DialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement DialogListener");
        }
    }

    public interface DialogListener{
        void applyTexts(int messagePosition, String numberDialog, String imeiDialog, String simcardDialog);
        void onDialogPositiveClick(DialogFragment dialogFragment);
    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            messagePosition = intent.getIntExtra("position", 0);

        }
    };

    public void onDestroy(){
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }
}

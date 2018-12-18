package org.flyve.admin.dashboard.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import org.flyve.admin.dashboard.R;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class editSmsDialog extends AppCompatDialogFragment {

    private EditText editNumberDialog;
    private EditText editIMEIDialog;
    private EditText editSimcarDialog;

    private DialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

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
                        String simcardDialog = editSimcarDialog.getText().toString();
                        listener.applyTexts(numberDialog, imeiDialog, simcardDialog);
                    }
                }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //TODO WHEN CLICK CANCEL

                }
        });

        editNumberDialog = view.findViewById(R.id.editNumberDialog);
        editIMEIDialog = view.findViewById(R.id.editIMEIDialog);
        editSimcarDialog = view.findViewById(R.id.editSimCardDialog);

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
        void applyTexts(String numberDialog, String imeiDialog, String simcardDialog);
    }
}

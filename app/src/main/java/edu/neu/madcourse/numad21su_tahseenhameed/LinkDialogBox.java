package edu.neu.madcourse.numad21su_tahseenhameed;

import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class LinkDialogBox extends AppCompatDialogFragment {
    private EditText editLinkName;
    private EditText editLinkURL;
    private DialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog, null);

        builder.setView(view)
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.onCancelClick(LinkDialogBox.this);
                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //String username = editLinkName.getText().toString();
                       // String password = editLinkURL.getText().toString();
                        listener.onOKClick(LinkDialogBox.this);
                    }
                });

        editLinkName = view.findViewById(R.id.edit_linkName);
        editLinkURL = view.findViewById(R.id.edit_url);

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (DialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement DialogListener");
        }
    }

    public interface DialogListener {
        void onOKClick(DialogFragment linkDialog);
        void onCancelClick(DialogFragment linkDialog);
    }
}

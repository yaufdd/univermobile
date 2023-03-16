package com.example.dialog;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

public class MyProgressDialogFragment extends DialogFragment {
    private ProgressDialog progressDialog;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Loding...");
        progressDialog.setMessage("Waiting...");
        progressDialog.setIndeterminate(true);
        progressDialog.setButton(Dialog.BUTTON_POSITIVE, "OK", (dialog, which) -> {
            ((MainActivity) getActivity()).onOkClicked();
        });
        return progressDialog;
    }
}

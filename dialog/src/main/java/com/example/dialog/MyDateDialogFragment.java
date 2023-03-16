package com.example.dialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

public class MyDateDialogFragment extends DialogFragment{
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new DatePickerDialog(getActivity(), (datePicker, year, month, day) -> {}, 0, 0, 0);
    }
}

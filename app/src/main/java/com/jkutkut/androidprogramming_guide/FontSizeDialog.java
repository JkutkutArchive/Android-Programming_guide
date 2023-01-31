package com.jkutkut.androidprogramming_guide;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FontSizeDialog extends DialogFragment {
    EditText etxtInput;

    @NotNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        MainActivity activity = (MainActivity) getActivity();
        assert activity != null;
        View v = activity.getLayoutInflater().inflate(R.layout.font_size_dialog, null);
        etxtInput = v.findViewById(R.id.etxtInput);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(v);

        builder.setTitle(getString(R.string.font_size_dialog_title));
        builder.setCancelable(false); // TODO
        builder.setPositiveButton(getText(R.string.confirm_txt), null);
        builder.setNegativeButton(getText(R.string.cancel_txt), null);

        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                this.onShow((AlertDialog) dialog);
            }

            public void onShow(AlertDialog dialog) {
                Button btn = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
                btn.setOnClickListener(v -> {
                    int input;
                    try {
                        input = Integer.parseInt(etxtInput.getText().toString());
                    }
                    catch (NumberFormatException e) {
                        etxtInput.setError(getString(R.string.font_size_dialog_error));
                        return;
                    }
                    if (input <= 0) { // EditText ensures natural number or 0
                        etxtInput.setError(getString(R.string.font_size_dialog_error));
                        return;
                    }
                    activity.setFontSize(input);
                    alertDialog.dismiss();
                });
            }
        });
        return alertDialog;
    }
}
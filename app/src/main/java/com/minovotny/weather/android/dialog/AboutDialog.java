package com.minovotny.weather.android.dialog;


import android.app.AlertDialog;
import android.app.Dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.minovotny.weather.android.R;

/**
 * Created by Miroslav Novotny on 18.06.2016.
 */
public class AboutDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.dialog_about_title);
        builder.setMessage(R.string.dialog_about_message);
        builder.setPositiveButton(R.string.dialog_about_btn_label, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return builder.create();
    }
}

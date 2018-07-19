package com.rajsuvariya.bakingapp.utils;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.rajsuvariya.bakingapp.R;

/**
 * Created by @raj on 06/06/18.
 */

public class DialogUtils {

    public static ProgressDialog mProgressDialog = null;

    public static AlertDialog alertDialog = null;

    public static void dialogBoxWithButtons(final Context context, String title, String message,
                                            String positiveButton, String negativeButton, boolean cancelable,
                                            DialogInterface.OnClickListener positiveCallback, DialogInterface.OnClickListener negativeCallback) {
        if (alertDialog!=null) {
            alertDialog.cancel();
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
                .setMessage(message)
                .setCancelable(cancelable)
                .setPositiveButton(positiveButton, positiveCallback)
                .setNegativeButton(negativeButton, negativeCallback);

        alertDialog = builder.create();
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
            }
        });
        alertDialog.show();
    }

    public static boolean isDialogShowing(){
        return mProgressDialog!=null;
    }
}
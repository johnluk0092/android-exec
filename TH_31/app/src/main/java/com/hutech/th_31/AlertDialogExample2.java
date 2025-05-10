package com.hutech.th_31;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

public class AlertDialogExample2 {

    public static void showAlertDialog(final Context context)  {
        final Drawable positiveIcon = context.getResources().getDrawable(R.drawable.img);
        final Drawable negativeIcon = context.getResources().getDrawable(R.drawable.img);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        // Set Title and Message:
        builder.setTitle("Confirmation").setMessage("Do you want to close this app?");

        //
        builder.setCancelable(true);
        builder.setIcon(R.drawable.icon_title);

        // Create "Yes" button with OnClickListener.
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(context,"You choose Yes button",
                        Toast.LENGTH_SHORT).show();
                Activity activity = (Activity) context;
                activity.finish();
            }
        });
        builder.setPositiveButtonIcon(positiveIcon);

        // Create "No" button with OnClickListener.
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(context,"You choose No button",
                        Toast.LENGTH_SHORT).show();
                //  Cancel
                dialog.cancel();
            }
        });
        builder.setNegativeButtonIcon(negativeIcon);

        // Create AlertDialog:
        AlertDialog alert = builder.create();
        alert.show();
    }
}


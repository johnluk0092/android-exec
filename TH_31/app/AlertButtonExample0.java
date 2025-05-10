package com.hutech.th_31;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

public class AlertDialogExample0 {

    public static void showAlertDialog(final Context context) {
        final Drawable positiveIcon = context.getResources().getDrawable(R.drawable.icon_positive);
        final Drawable negativeIcon = context.getResources().getDrawable(R.drawable.icon_negative);
        final Drawable neutralIcon = context.getResources().getDrawable(R.drawable.icon_neutral);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        // Set Title and Message:
        builder.setTitle("Title").setMessage("This is a message");

        //
        builder.setCancelable(true);
        builder.setIcon(R.drawable.icon_title);

        // Create "Positive" button with OnClickListener.
        builder.setPositiveButton("Positive", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(context, "You choose positive button",
                        Toast.LENGTH_SHORT).show();
            }
        });
        builder.setPositiveButtonIcon(positiveIcon);

        // Create "Negative" button with OnClickListener.
        builder.setNegativeButton("Negative", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(context, "You choose positive button",
                        Toast.LENGTH_SHORT).show();
                //  Cancel
                dialog.cancel();
            }
        });
        builder.setNegativeButtonIcon(negativeIcon);

        // Create "Neutral" button with OnClickListener.
        builder.setNeutralButton("Neutral", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //  Action for 'NO' Button
                Toast.makeText(context, "You choose neutral button",
                        Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNeutralButtonIcon(neutralIcon); // Not working!!!

        // Create AlertDialog:
        AlertDialog alert = builder.create();
        alert.show();
    }
}
package com.hutech.th_31;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

public class AlertDialogCustomTitleExample {

    public static void showAlertDialog(final Activity context)  {
        final Drawable positiveIcon = context.getResources().getDrawable(R.drawable.img);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        // Custom Title Area.
        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_custom_title, null);
        builder.setCustomTitle(view);

        // Message.
        builder.setMessage("This is AlertDialog with Custom Title Area");
        //
        builder.setCancelable(true);
        builder.setIcon(R.drawable.icon_title);

        // Create "OK" button with OnClickListener.
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        builder.setPositiveButtonIcon(positiveIcon);

        // Create AlertDialog:
        AlertDialog alert = builder.create();
        alert.show();
    }
}


package com.hutech.th_31;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

public class AlertDialogListExample {

    public static void showAlertDialog(final Activity activity)  {
        final Drawable negativeIcon = activity.getResources().getDrawable(R.drawable.img);

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        // Set Title.
        builder.setTitle("Select an Animal");

        // Add a list
        final String[] animals = {"Horse", "Cow", "Camel", "Sheep", "Goat"};
        builder.setItems(animals, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String animal = animals[which];

                dialog.dismiss(); // Close Dialog
                // Do some thing....
                // For example: Call method of MainActivity.
                Toast.makeText(activity,"You select: " + animal,
                        Toast.LENGTH_SHORT).show();
                // activity.someMethod(animal);
            }
        });

        //
        builder.setCancelable(true);
        builder.setIcon(R.drawable.icon_title);

        // Create "Cancel" button with OnClickListener.
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(activity,"You choose No button",
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


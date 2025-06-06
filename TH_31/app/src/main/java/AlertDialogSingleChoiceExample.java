package com.hutech.th_31;

import android.app.Activity;
import android.content.DialogInterface;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.util.HashSet;
import java.util.Set;

public class AlertDialogSingleChoiceExample {


    public static void showAlertDialog(final Activity activity)  {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        // Set Title.
        builder.setTitle("Select an Animal");

        // Add a list
        final String[] animals = {"Horse", "Cow", "Camel", "Sheep", "Goat"};

        int checkedItem = 3; // Sheep
        final Set<String> selectedItems = new HashSet<String>();
        selectedItems.add(animals[checkedItem]);

        builder.setSingleChoiceItems(animals, checkedItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do Something...
                selectedItems.clear();
                selectedItems.add(animals[which]);
            }
        });

        //
        builder.setCancelable(true);
        builder.setIcon(R.drawable.icon_title);

        // Create "Yes" button with OnClickListener.
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if(selectedItems.isEmpty()) {
                    return;
                }
                String animal = selectedItems.iterator().next();

                // Close Dialog
                dialog.dismiss();
                // Do something, for example: Call a method of Activity...
                Toast.makeText(activity,"You select " + animal,
                        Toast.LENGTH_SHORT).show();

            }
        });

        // Create "Cancel" button with OnClickListener.
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(activity,"You choose Cancel button",
                        Toast.LENGTH_SHORT).show();
                //  Cancel
                dialog.cancel();
            }
        });

        // Create AlertDialog:
        AlertDialog alert = builder.create();
        alert.show();
    }

}

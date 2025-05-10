package com.hutech.th_31;

import android.app.Activity;
import android.content.DialogInterface;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

public class AlertDialogMultiChoiceExample {


    public static void showAlertDialog(final Activity activity)  {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        // Set Title.
        builder.setTitle("Select an Animal");

        // Add a list
        final String[] animals = {"Horse", "Cow", "Camel", "Sheep", "Goat"};

        final boolean[] checkedInfos = new boolean[]{false, false, false, true, false}; // Sheep


        builder.setMultiChoiceItems(animals, checkedInfos, new DialogInterface.OnMultiChoiceClickListener()  {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                checkedInfos[which] = isChecked;
            }
        });

        //
        builder.setCancelable(true);
        builder.setIcon(R.drawable.icon_title);

        // Create "Yes" button with OnClickListener.
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // Close Dialog
                dialog.dismiss();

                String s= null;
                for(int i=0; i< animals.length;i++)  {
                    if(checkedInfos[i]) {
                        if(s == null)  {
                            s = animals[i];
                        } else {
                            s+= ", " + animals[i];
                        }
                    }
                }
                s = s == null? "":s;

                // Do something, for example: Call a method of Activity...
                Toast.makeText(activity,"You select " + s,
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

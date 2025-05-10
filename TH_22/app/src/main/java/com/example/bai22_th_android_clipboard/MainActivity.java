package com.example.bai22_th_android_clipboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "AndroidExample";

    private ClipboardManager clipboardManager;

    private Button buttonCopy;
    private Button buttonGo;

    private RadioButton radioButtonCry;
    private RadioButton radioButtonFeelGood;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
        this.clipboardManager = (ClipboardManager) this.getSystemService(Context.CLIPBOARD_SERVICE);

        this.radioButtonCry = (RadioButton) this.findViewById(R.id.radioButton_cry);
        this.radioButtonFeelGood = (RadioButton) this.findViewById(R.id.radioButton_feel_good);

        this.buttonCopy = (Button) this.findViewById(R.id.button_copy);
        this.buttonGo = (Button) this.findViewById(R.id.button_go);

        this.buttonCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doCopy();
            }
        });

        this.buttonGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToImageActivity();
            }
        });
    }


    private void doCopy()  {
        int resId = R.drawable.icon_cry;

        if(this.radioButtonCry.isChecked()) {
            resId = R.drawable.icon_cry;
        } else if(this.radioButtonFeelGood.isChecked()) {
            resId = R.drawable.icon_feel_good;
        }

        Resources resources = this.getResources();

        // android.resource://org.o7planning.clipboarduriexample/drawable/icon_cry
        String path = ContentResolver.SCHEME_ANDROID_RESOURCE //  android.resource
                + "://" + resources.getResourcePackageName(resId) // org.o7planning.clipboarduriexample
                + '/' + resources.getResourceTypeName(resId)  // drawable
                + '/' + resources.getResourceEntryName(resId); // icon_cry

        Log.i(LOG_TAG, path);

        Uri uriCopy = Uri.parse(path);

        // ClipData (Uri)
        ClipData clipData = ClipData.newRawUri("Some label", uriCopy);

        // Copy ClipData to Clipboard.
        this.clipboardManager.setPrimaryClip(clipData);
    }

    private void goToImageActivity() {
        // Create an Intent:
        Intent myIntent = new Intent(this, ImageActivity.class);

        // Extra data:
        myIntent.putExtra("text1", "This is text1 sent from MainActivity at " + new Date());
        myIntent.putExtra("text2", "This is text2 sent from MainActivity at " + new Date());

        // Start ImageActivity
        this.startActivity(myIntent);
    }
}

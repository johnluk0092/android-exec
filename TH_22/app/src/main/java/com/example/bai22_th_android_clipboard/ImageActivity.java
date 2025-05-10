package com.example.bai22_th_android_clipboard;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ImageActivity  extends AppCompatActivity {

    private static final String LOG_TAG = "AndroidExample";

    private ClipboardManager clipboardManager;

    private ImageView imageView;
    private Button buttonBack;
    private Button buttonPaste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        //
        this.clipboardManager = (ClipboardManager) this.getSystemService(Context.CLIPBOARD_SERVICE);

        //
        this.imageView = (ImageView) this.findViewById(R.id.imageView);

        this.buttonPaste = (Button) this.findViewById(R.id.button_paste);
        this.buttonPaste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doPaste();
            }
        });


        this.buttonBack = (Button) this.findViewById(R.id.button_back);
        this.buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doBack();
            }
        });
    }



    private void doPaste()  {
        Intent intent= this.getIntent();

        ClipData primaryClipData = this.clipboardManager.getPrimaryClip();

        if(primaryClipData == null)  {
            this.imageView.setImageURI(null);
            return;
        }
        ClipData.Item item = primaryClipData.getItemAt(0);
        Uri uri = item.getUri();
        this.imageView.setImageURI(uri);
    }
    // Back to MainActivity.
    private void doBack()  {
        this.finish();
    }
}

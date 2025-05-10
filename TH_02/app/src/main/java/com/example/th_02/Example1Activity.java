package com.example.th_02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Example1Activity extends AppCompatActivity {

    private Button buttonClickMe;
    private Button buttonBack;
    private TextView textView1;
    private TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example1);

        // Find TextView by its ID
        this.textView1  = (TextView)this.findViewById(R.id.textView1);

        // Find TextView by its ID
        this.textView2 = (TextView)this.findViewById(R.id.textView2);

        this.buttonClickMe = (Button)this.findViewById(R.id.button_clickMe);

        this.buttonBack = (Button)this.findViewById(R.id.button_back);

        // Get the intent sent from MainActivity.
        Intent intent = getIntent();

        // Parameter in Intent, sent from MainActivity
        String value1 = intent.getStringExtra("text1");

        // Parameter in Intent, sent from MainActivity
        String value2 = intent.getStringExtra("text2");

        this.textView1.setText(value1);
        this.textView2.setText(value2);

        // When user click "Click me" button.
        this.buttonClickMe.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                textView2.setText("You click button");
            }
        });

        // When user long click "Click me" button.
        this.buttonClickMe.setOnLongClickListener(new Button.OnLongClickListener() {


            // return true if the callback consumed the long click, false otherwise.
            @Override
            public boolean onLongClick(View v) {
                textView2.setText("You long click button");
                return true;
            }
        });

        // When user click "Back" button.
        this.buttonBack.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Back to previous Activity.
                Example1Activity.this.finish();
            }
        });

    }
}

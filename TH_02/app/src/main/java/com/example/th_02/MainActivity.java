package com.example.th_02;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find Button by its ID
        this.button1 = (Button) this.findViewById(R.id.button1);

        // Find button by its ID
        this.button2 = (Button) this.findViewById(R.id.button2);

        // Find button by its ID.
        this.button3 = (Button) this.findViewById(R.id.button3);

        // Find button by its ID.
        this.button4 = (Button) this.findViewById(R.id.button4);

        // Find button by its ID.
        this.button5 = (Button) this.findViewById(R.id.button5);

        // Called when the user clicks the button1.
        button1.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Create a Intent:
                // (This object contains content that will be sent to Example1Activity).
                Intent myIntent = new Intent(MainActivity.this, Example1Activity.class);

                // Put parameters
                myIntent.putExtra("text1", "This is text1 sent from MainActivity at " + new Date());
                myIntent.putExtra("text2", "This is text2 sent from MainActivity at " + new Date());

                // Start Example1Activity.
                MainActivity.this.startActivity(myIntent);
            }
        });

        // Called when the user clicks the button2.
        button2.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Create a Intent:
                // (This object contains content that will be sent to Example2Activity).
                Intent myIntent = new Intent(MainActivity.this, Example2Activity.class);

                // Start Example2Activity.
                MainActivity.this.startActivity(myIntent);
            }
        });

        // Called when the user clicks the button3.
        button3.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Create a Intent:
                // (This object contains content that will be sent to Example3Activity).
                Intent myIntent = new Intent(MainActivity.this, Example3Activity.class);


                MainActivity.this.startActivity(myIntent);
            }
        });

        button4.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Create a Intent:
                // (This object contains content that will be sent to Example4Activity).
                Intent myIntent = new Intent(MainActivity.this, Example4Activity.class);

                // Start Example4Activity.
                MainActivity.this.startActivity(myIntent);
            }
        });

        button5.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Create a Intent:
                // (This object contains content that will be sent to Example5Activity).
                Intent myIntent = new Intent(MainActivity.this, Example5Activity.class);

                // Start Example5Activity.
                MainActivity.this.startActivity(myIntent);
            }
        });
    }
}

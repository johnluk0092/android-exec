package com.example.bai17_thandroid_toast;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnDefault, btnTopLeft, btnCenter, btnCustom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDefault = findViewById(R.id.btnDefault);
        btnTopLeft = findViewById(R.id.btnTopLeft);
        btnCenter = findViewById(R.id.btnCenter);
        btnCustom = findViewById(R.id.btnCustom);

        btnDefault.setOnClickListener(v ->
                Toast.makeText(MainActivity.this, "Default Toast", Toast.LENGTH_SHORT).show()
        );

        btnTopLeft.setOnClickListener(v -> {
            Toast toast = Toast.makeText(MainActivity.this, "Top Left Toast", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP | Gravity.LEFT, 50, 100);
            toast.show();
        });

        btnCenter.setOnClickListener(v -> {
            Toast toast = Toast.makeText(MainActivity.this, "Center Toast", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        });

        btnCustom.setOnClickListener(v -> {
            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.custom_toast_layout, findViewById(R.id.custom_toast_container));

            Toast customToast = new Toast(getApplicationContext());
            customToast.setDuration(Toast.LENGTH_LONG);
            customToast.setView(layout);
            customToast.show();
        });
    }
}
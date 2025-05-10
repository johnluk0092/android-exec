package com.example.bai29_th_android_seekbar;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SeekBar seekBar;
    private TextView textView;
    private Button buttonDecrease;
    private Button buttonIncrease;

    private static final int DELTA_VALUE = 5;
    private static final String LOGTAG = "SeekBarDemo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = findViewById(R.id.seekBar);
        textView = findViewById(R.id.textView);
        buttonDecrease = findViewById(R.id.button_decrease);
        buttonIncrease = findViewById(R.id.button_increase);

        seekBar.setMax(100);
        seekBar.setProgress(15);

        textView.setText("Progress: " + seekBar.getProgress() + "/" + seekBar.getMax());

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                progress = progressValue;
                textView.setText("Progress: " + progressValue + "/" + seekBar.getMax());
                Log.i(LOGTAG, "Changing seekbar's progress");
                Toast.makeText(getApplicationContext(), "Changing seekbar's progress", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.i(LOGTAG, "Started tracking seekbar");
                Toast.makeText(getApplicationContext(), "Started tracking seekbar", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textView.setText("Progress: " + progress + "/" + seekBar.getMax());
                Log.i(LOGTAG, "Stopped tracking seekbar");
                Toast.makeText(getApplicationContext(), "Stopped tracking seekbar", Toast.LENGTH_SHORT).show();
            }
        });

        buttonDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decreaseProgress();
            }
        });

        buttonIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increaseProgress();
            }
        });
    }

    private void decreaseProgress() {
        int progress = seekBar.getProgress();
        seekBar.setProgress(Math.max(progress - DELTA_VALUE, 0));
        textView.setText("Progress: " + seekBar.getProgress() + "/" + seekBar.getMax());
    }

    private void increaseProgress() {
        int progress = seekBar.getProgress();
        if (progress + DELTA_VALUE > seekBar.getMax()) {
            seekBar.setProgress(0);
        } else {
            seekBar.setProgress(progress + DELTA_VALUE);
        }
        textView.setText("Progress: " + seekBar.getProgress() + "/" + seekBar.getMax());
    }
}
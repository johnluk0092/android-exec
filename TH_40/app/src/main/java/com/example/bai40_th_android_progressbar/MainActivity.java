package com.example.bai40_th_android_progressbar;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button buttonStart1, buttonStart2;
    private ProgressBar progressBar1, progressBar2;
    private TextView textViewInfo1, textViewInfo2;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar1 = findViewById(R.id.progressBar1);
        progressBar2 = findViewById(R.id.progressBar2);
        progressBar2.setIndeterminate(false);
        textViewInfo1 = findViewById(R.id.textView_info1);
        textViewInfo2 = findViewById(R.id.textView_info2);
        buttonStart1 = findViewById(R.id.button_start1);
        buttonStart2 = findViewById(R.id.button_start2);

        buttonStart1.setOnClickListener(v -> doStartProgressBar1());
        buttonStart2.setOnClickListener(v -> doStartProgressBar2());
    }

    private void doStartProgressBar1() {
        final int MAX = 110;
        progressBar1.setMax(MAX);

        Thread thread = new Thread(() -> {
            handler.post(() -> buttonStart1.setEnabled(false));

            for (int i = 0; i < MAX; i++) {
                final int progress = i + 1;
                SystemClock.sleep(20); // Mô phỏng thời gian xử lý

                handler.post(() -> {
                    progressBar1.setProgress(progress);
                    int percent = (progress * 100) / MAX;
                    textViewInfo1.setText("Percent: " + percent + " %");
                    if (progress == MAX) {
                        textViewInfo1.setText("Completed!");
                        buttonStart1.setEnabled(true);
                    }
                });
            }
        });
        thread.start();
    }

    private void doStartProgressBar2() {
        progressBar2.setIndeterminate(true);

        Thread thread = new Thread(() -> {
            handler.post(() -> {
                textViewInfo2.setText("Working...");
                buttonStart2.setEnabled(false);
            });

            SystemClock.sleep(5000); // Mô phỏng thời gian xử lý

            progressBar2.setIndeterminate(false);
            progressBar2.setMax(1);
            progressBar2.setProgress(1);

            handler.post(() -> {
                textViewInfo2.setText("Completed!");
                buttonStart2.setEnabled(true);
            });
        });
        thread.start();
    }
}
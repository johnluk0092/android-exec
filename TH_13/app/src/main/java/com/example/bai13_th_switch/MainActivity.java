package com.example.bai13_th_switch;

import android.os.Bundle;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.CompoundButton;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Switch switch61, switch62, switch63, switch64;
    private TextView textView61, textView62, textView63, textView64;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ các Switch và TextView từ XML
        switch61 = findViewById(R.id.switch61);
        switch62 = findViewById(R.id.switch62);
        switch63 = findViewById(R.id.switch63);
        switch64 = findViewById(R.id.switch64);

        textView61 = findViewById(R.id.textView61);
        textView62 = findViewById(R.id.textView62);
        textView63 = findViewById(R.id.textView63);
        textView64 = findViewById(R.id.textView64);

        // Thiết lập sự kiện cho từng Switch
        setupSwitchListener(switch61, textView61);
        setupSwitchListener(switch62, textView62);
        setupSwitchListener(switch63, textView63);
        setupSwitchListener(switch64, textView64);
    }

    // Phương thức chung để xử lý sự kiện chuyển đổi trạng thái Switch
    private void setupSwitchListener(Switch switchWidget, TextView textView) {
        switchWidget.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    textView.setText(buttonView.getText() + " is ON");
                } else {
                    textView.setText(buttonView.getText() + " is OFF");
                }
            }
        });
    }
}
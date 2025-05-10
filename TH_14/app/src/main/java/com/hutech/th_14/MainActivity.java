package com.hutech.th_14;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Thiết lập listener cho insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            // Lấy các insets cho system bars (thanh trạng thái và thanh điều hướng)
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            // Cập nhật padding của view để tránh bị che khuất bởi các system bars
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            // Trả về insets mà không tiêu thụ (consume)
            return insets;
        });
    }
}

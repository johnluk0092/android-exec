package com.hutech.th_41;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;  // Đặt trong lớp MainActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Button để mở DetailActivity
        Button btnOpenDetail = findViewById(R.id.btnOpenDetail);
        btnOpenDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Tạo Explicit Intent để chuyển sang Activity khác
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);

                // Truyền dữ liệu qua Intent
                intent.putExtra("message", "Hello from MainActivity!");

                // Bắt đầu Activity
                startActivity(intent);
            }
        });

        // Button để chia sẻ văn bản
        Button btnShare = findViewById(R.id.btnShare);
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Tạo Implicit Intent để chia sẻ văn bản
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "This is the text to share!");

                // Kiểm tra xem có ứng dụng nào có thể xử lý Intent này không
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        // Button để mở DetailActivity và nhận kết quả
        Button btnStartForResult = findViewById(R.id.btnStartForResult);
        btnStartForResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                startActivityForResult(intent, REQUEST_CODE);  // Gọi Activity với kết quả trả về
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            String result = data.getStringExtra("result");
            Toast.makeText(this, "Result: " + result, Toast.LENGTH_LONG).show();
        }
    }
}

package com.hutech.th_41;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Nhận dữ liệu từ Intent
        Intent intent = getIntent();
        String message = intent.getStringExtra("message");

        // Hiển thị dữ liệu trong TextView
        TextView tvMessage = findViewById(R.id.tvMessage);
        tvMessage.setText(message);

        // Button trả lại kết quả cho MainActivity
        Button btnReturnResult = findViewById(R.id.btnReturnResult);
        btnReturnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("result", "This is the result from DetailActivity!");
                setResult(RESULT_OK, resultIntent);  // Trả kết quả cho Activity gọi
                finish();  // Đóng Activity này
            }
        });
    }
}

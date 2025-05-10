package com.example.bai21_th_android_textwatcher;



import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextWatcher;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.editText = (EditText) this.findViewById(R.id.editText_birthDay);

        // Create TextWatcher:
        TextWatcher textWatcher = new DateFormatTextWatcher(this.editText);
        this.editText.addTextChangedListener(textWatcher);

    }
}

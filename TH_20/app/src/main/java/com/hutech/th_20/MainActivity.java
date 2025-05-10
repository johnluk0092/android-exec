package com.hutech.th_20;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Thiết lập các kiểm tra cho các trường
        this.setupFloatingLabelError();
    }

    private void setupFloatingLabelError() {
        // TextInputLayout cho Username
        final TextInputLayout textInputLayoutUserName = findViewById(R.id.textInputLayout_user_name);
        final TextInputLayout textInputLayoutPassword = findViewById(R.id.textInputLayout_password);
        final TextInputLayout textInputLayoutNewUserName = findViewById(R.id.textInputLayout);

        // TextWatcher cho trường Username
        textInputLayoutUserName.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence text, int start, int count, int after) {
                if (text.length() == 0) {
                    textInputLayoutUserName.setError("Username is required");
                    textInputLayoutUserName.setErrorEnabled(true);
                } else if (text.length() < 5) {
                    textInputLayoutUserName.setError("Username must be at least 5 characters");
                    textInputLayoutUserName.setErrorEnabled(true);
                } else {
                    textInputLayoutUserName.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        // TextWatcher cho trường Password
        textInputLayoutPassword.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence text, int start, int count, int after) {
                if (text.length() == 0) {
                    textInputLayoutPassword.setError("Password is required");
                    textInputLayoutPassword.setErrorEnabled(true);
                } else if (text.length() < 6) {
                    textInputLayoutPassword.setError("Password must be at least 6 characters");
                    textInputLayoutPassword.setErrorEnabled(true);
                } else {
                    textInputLayoutPassword.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        // TextWatcher cho trường New Username
        textInputLayoutNewUserName.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence text, int start, int count, int after) {
                if (text.length() == 0) {
                    textInputLayoutNewUserName.setError("New Username is required");
                    textInputLayoutNewUserName.setErrorEnabled(true);
                } else if (text.length() < 5) {
                    textInputLayoutNewUserName.setError("New Username must be at least 5 characters");
                    textInputLayoutNewUserName.setErrorEnabled(true);
                } else {
                    textInputLayoutNewUserName.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
}


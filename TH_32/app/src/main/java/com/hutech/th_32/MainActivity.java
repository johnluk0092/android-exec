package com.hutech.th_32;

import android.os.Bundle;
import android.text.Editable;
import android.text.method.CharacterPickerDialog;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "AndroidExample";
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.editText = this.findViewById(R.id.editText);

        // Hiển thị bàn phím khi EditText được focus
        editText.requestFocus();
        editText.post(() -> {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
            }
        });

        this.editText.setOnKeyListener((v, keyCode, event) -> handleOnKey(keyCode, event));
    }

    private boolean handleOnKey(int keyCode, KeyEvent event) {
        // Chỉ xử lý khi phím được nhả ra
        if (event.getAction() != KeyEvent.ACTION_UP) {
            return false;
        }

        char base = (char) event.getUnicodeChar();
        Log.i(LOG_TAG, "keyCode: " + keyCode + ", Base character: " + base);

        final String accentedString = AccentedStringUtils.getAccentedString(base);
        if (accentedString == null) {
            // Không có ký tự đặc biệt, cho phép xử lý bàn phím bình thường
            return false;
        }

        final Editable editable = editText.getText();

        // Hiển thị hộp thoại chọn ký tự đặc biệt
        CharacterPickerDialog dialog = new CharacterPickerDialog(this, new View(this), editable, accentedString, false) {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                char selectedChar = accentedString.charAt(position);

                // Chèn ký tự đặc biệt vào vị trí con trỏ
                int start = editText.getSelectionStart();
                int end = editText.getSelectionEnd();
                editable.replace(start, end, String.valueOf(selectedChar));

                super.onItemClick(parent, view, position, id);
            }
        };
        dialog.show();

        // Đã xử lý sự kiện
        return true;
    }
}

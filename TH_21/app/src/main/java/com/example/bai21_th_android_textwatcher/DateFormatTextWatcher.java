package com.example.bai21_th_android_textwatcher;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import java.util.Calendar;

public class DateFormatTextWatcher implements TextWatcher {
    private static final String DDMMYYYY = "DDMMYYYY";
    private static final String SEPARATOR = "/";
    private final Calendar calendar = Calendar.getInstance();
    private String currentText = "";
    private EditText editText;

    public DateFormatTextWatcher(EditText editText) {
        this.editText = editText;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (!s.toString().equals(this.currentText)) {
            String newTextClean = s.toString().replaceAll("[^\\d.]|\\.", "");
            String currentTextClean = this.currentText.replaceAll("[^\\d.]|\\.", "");
            int newTextLength = newTextClean.length();
            int selectionIndex = newTextLength;

            for (int i = 2; i <= newTextLength && i < 6; i += 2) {
                selectionIndex++;
            }

            if (newTextClean.equals(currentTextClean)) {
                selectionIndex--;
            }

            if (newTextClean.length() < 8) {
                newTextClean = newTextClean + DDMMYYYY.substring(newTextClean.length());
            } else {
                int day = Integer.parseInt(newTextClean.substring(0, 2));
                int month = Integer.parseInt(newTextClean.substring(2, 4));
                int year = Integer.parseInt(newTextClean.substring(4, 8));

                month = Math.max(1, Math.min(month, 12));
                calendar.set(Calendar.MONTH, month - 1);
                year = Math.max(1900, Math.min(year, 2100));
                calendar.set(Calendar.YEAR, year);
                day = Math.min(day, calendar.getActualMaximum(Calendar.DATE));

                newTextClean = String.format("%02d%02d%02d", day, month, year);
            }

            newTextClean = String.format("%s%s%s%s%s",
                    newTextClean.substring(0, 2), SEPARATOR,
                    newTextClean.substring(2, 4), SEPARATOR,
                    newTextClean.substring(4, 8));

            selectionIndex = Math.min(selectionIndex, newTextClean.length());
            this.currentText = newTextClean;
            this.editText.setText(this.currentText);
            this.editText.setSelection(selectionIndex);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
    }
}

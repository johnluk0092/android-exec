package com.hutech.th_19;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editTextTest;
    private TextView textViewInfo;

    private Button button_selectAll;
    private Button button_setSelection1;
    private Button button_setSelection2;
    private Button button_extendSelection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.editTextTest = (EditText) this.findViewById(R.id.editText_test);
        this.textViewInfo = (TextView) this.findViewById(R.id.textView_info);

        this.button_selectAll = (Button) this.findViewById(R.id.button_selectAll);
        this.button_setSelection1 = (Button) this.findViewById(R.id.button_setSelection1);
        this.button_setSelection2 = (Button) this.findViewById(R.id.button_setSelection2);
        this.button_extendSelection = (Button) this.findViewById(R.id.button_extendSelection);

        // Focus
        this.editTextTest.requestFocus();

        this.button_selectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectAllHandler();
            }
        });

        this.button_setSelection1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelection1Handler();
            }
        });

        this.button_setSelection2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelection2Handler();
            }
        });

        this.button_extendSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                extendSelectionHandler();
            }
        });
    }

    // Usage of: editText.selectALl()
    private void selectAllHandler() {
        this.editTextTest.selectAll();
        this.textViewInfo.setText("selectAll()");
    }

    // Usage of: editText.setSelection(int index)
    private void setSelection1Handler() {
        this.editTextTest.setSelection(3);
        this.textViewInfo.setText("setSelection(3)");
    }

    // Usage of: editText.setSelection(int start, int stop)
    private void setSelection2Handler() {
        this.editTextTest.setSelection(2, 7);
        this.textViewInfo.setText("setSelection(2, 7)");
    }

    // Usage of: editText.extendSelection(int index)
    private void extendSelectionHandler() {
        this.editTextTest.extendSelection(5);

        int selectionStart = this.editTextTest.getSelectionStart();
        this.textViewInfo.setText("selectionStart = " + selectionStart+ " --> extendSelection(5)");
    }

}

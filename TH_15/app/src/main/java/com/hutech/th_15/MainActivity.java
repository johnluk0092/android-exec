package com.hutech.th_15;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private CheckBox checkBoxCheckAll;
    private CheckBox checkBoxCcpp;
    private CheckBox checkBoxCsharp;
    private CheckBox checkBoxJava;

    private Button buttonShowResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.checkBoxCheckAll = (CheckBox) this.findViewById(R.id.checkBox_checkAll);
        this.checkBoxCcpp = (CheckBox) this.findViewById(R.id.checkBox_ccpp);
        this.checkBoxCsharp = (CheckBox) this.findViewById(R.id.checkBox_csharp);
        this.checkBoxJava = (CheckBox) this.findViewById(R.id.checkBox_java);

        this.buttonShowResult = (Button) this.findViewById(R.id.button_showResult);

        this.buttonShowResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showResult();
            }
        });

        this.checkBoxCheckAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkAllCheckedChange(isChecked);
            }
        });
    }

    private void showResult()  {
        String message = null;
        if(this.checkBoxCcpp.isChecked()) {
            message =  this.checkBoxCcpp.getText().toString();
        }
        if(this.checkBoxCsharp.isChecked()) {
            if(message== null)  {
                message =  this.checkBoxCsharp.getText().toString();
            } else {
                message += ", " + this.checkBoxCsharp.getText().toString();
            }
        }
        if(this.checkBoxJava.isChecked()) {
            if(message== null)  {
                message =  this.checkBoxJava.getText().toString();
            } else {
                message += ", " + this.checkBoxJava.getText().toString();
            }
        }
        message = message == null? "You select nothing": "You select: " + message;
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    // When "Check All" change state.
    private void checkAllCheckedChange(boolean isChecked)  {
        this.checkBoxCsharp.setChecked(isChecked);
        this.checkBoxCcpp.setChecked(isChecked);
        this.checkBoxJava.setChecked(isChecked);
    }
}

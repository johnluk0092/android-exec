package com.hutech.th_11;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNumber1;
    private EditText editTextNumber2;
    private Button buttonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.editTextNumber1 = (EditText) this.findViewById(R.id.editText_number1);
        this.editTextNumber2 = (EditText) this.findViewById(R.id.editText_number2);

        this.buttonAdd = (Button) this.findViewById(R.id.button_add);

        this.buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add2Number();
            }
        });
    }


    private void add2Number()  {
        String str1 = this.editTextNumber1.getText().toString();
        String str2 = this.editTextNumber2.getText().toString();
        try {
            double value1 = Double.parseDouble(str1);
            double value2 = Double.parseDouble(str2);

            double result = value1 + value2;

            Toast.makeText(this, "Result: " + result, Toast.LENGTH_SHORT).show();
        } catch(Exception e)  {
            Toast.makeText(this, "Error: "+ e, Toast.LENGTH_SHORT).show();
        }
    }
}

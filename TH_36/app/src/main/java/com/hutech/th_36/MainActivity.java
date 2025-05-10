package com.hutech.th_36;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private EditText editTextTime;
    private Button buttonTime;
    private CheckBox checkBoxIsSpinnerMode;
    private CheckBox checkBoxIs24HView;

    private int lastSelectedHour = -1;
    private int lastSelectedMinute = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.editTextTime = (EditText) this.findViewById(R.id.editText_time);
        this.buttonTime = (Button) this.findViewById(R.id.button_time);
        this.checkBoxIsSpinnerMode = (CheckBox) this.findViewById(R.id.checkBox_isSpinnerMode);
        this.checkBoxIs24HView = (CheckBox) this.findViewById(R.id.checkBox_is24HView);

        this.buttonTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSelectTime();
            }
        });
    }


    private void buttonSelectTime()  {
        if(this.lastSelectedHour == -1)  {
            // Get Current Time
            final Calendar c = Calendar.getInstance();
            this.lastSelectedHour = c.get(Calendar.HOUR_OF_DAY);
            this.lastSelectedMinute = c.get(Calendar.MINUTE);
        }
        final boolean is24HView = this.checkBoxIs24HView.isChecked();
        final boolean isSpinnerMode = this.checkBoxIsSpinnerMode.isChecked();

        // Time Set Listener.
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                editTextTime.setText(hourOfDay + ":" + minute );
                lastSelectedHour = hourOfDay;
                lastSelectedMinute = minute;
            }
        };

        // Create TimePickerDialog:
        TimePickerDialog timePickerDialog = null;

        // TimePicker in Spinner Mode:
        if(isSpinnerMode)  {
            timePickerDialog = new TimePickerDialog(this,
                    android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                    timeSetListener, lastSelectedHour, lastSelectedMinute, is24HView);
        }
        // TimePicker in Clock Mode (Default):
        else  {
            timePickerDialog = new TimePickerDialog(this,
                    timeSetListener, lastSelectedHour, lastSelectedMinute, is24HView);
        }

        // Show
        timePickerDialog.show();
    }

}

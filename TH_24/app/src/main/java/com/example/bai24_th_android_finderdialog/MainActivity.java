package com.example.bai24_th_android_finderdialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int MY_REQUEST_CODE_PERMISSION = 1000;
    private static final String LOG_TAG = "AndroidExample";

    private Button buttonSearch;
    private EditText editTextSearch;
    private CheckBox checkBoxIsRegex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.editTextSearch = findViewById(R.id.editText_search);
        this.checkBoxIsRegex = findViewById(R.id.checkBox_isRegex);
        this.buttonSearch = findViewById(R.id.button_search);

        this.buttonSearch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                askPermissionAndSearchFile();
            }
        });
    }

    private void askPermissionAndSearchFile() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            int permission = ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE);

            if (permission != PackageManager.PERMISSION_GRANTED) {
                this.requestPermissions(
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_REQUEST_CODE_PERMISSION
                );
                return;
            }
        }
        this.doSearchFile();
    }

    private void doSearchFile() {
        FileFinder fileSelector = new FileFinder(true);
        String searchText = this.editTextSearch.getText().toString();
        boolean isRegex = this.checkBoxIsRegex.isChecked();

        List<File> resultList;
        if (isRegex) {
            resultList = fileSelector.findInSDCardByRegex(searchText);
        } else {
            resultList = fileSelector.findInSDCardByKeyword(searchText);
        }

        ResultDialog dialog = new ResultDialog(this, resultList, new OnFileSelectListener() {
            @Override
            public void onSelect(File file) {
                Toast.makeText(MainActivity.this, "Path: " + file.getAbsolutePath(), Toast.LENGTH_LONG).show();
            }
        });
        dialog.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == MY_REQUEST_CODE_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.i(LOG_TAG,"Permission granted!");
                Toast.makeText(this, "Permission granted!", Toast.LENGTH_SHORT).show();
                this.doSearchFile();
            } else {
                Log.i(LOG_TAG,"Permission denied!");
                Toast.makeText(this, "Permission denied!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
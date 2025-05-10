package edu.hutech.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import edu.hutech.sqlitedemo.bean.Note;

public class AddEditNoteActivity extends AppCompatActivity {
    private static final int MODE_CREATE = 1;
    private static final int MODE_EDIT = 2;
    private EditText textTitle, textContent;
    private Button buttonSave, buttonCancel;
    private Note note;
    private int mode;
    private boolean needRefresh = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_note);

        textTitle = findViewById(R.id.editText_note_title);
        textContent = findViewById(R.id.editText_note_content);
        buttonSave = findViewById(R.id.button_save);
        buttonCancel = findViewById(R.id.button_cancel);

        note = (Note) getIntent().getSerializableExtra("note");
        mode = (note == null) ? MODE_CREATE : MODE_EDIT;
        if (mode == MODE_EDIT) {
            textTitle.setText(note.getNoteTitle());
            textContent.setText(note.getNoteContent());
        }

        buttonSave.setOnClickListener(v -> saveNote());
        buttonCancel.setOnClickListener(v -> finish());
    }

    private void saveNote() {
        String title = textTitle.getText().toString().trim();
        String content = textContent.getText().toString().trim();
        if (title.isEmpty() || content.isEmpty()) {
            Toast.makeText(this, "Please enter title & content", Toast.LENGTH_LONG).show();
            return;
        }
        MyDatabaseHelper db = new MyDatabaseHelper(this);
        if (mode == MODE_CREATE) {
            db.addNote(new Note(title, content));
        } else {
            note.setNoteTitle(title);
            note.setNoteContent(content);
            db.updateNote(note);
        }
        needRefresh = true;
        finish();
    }

    @Override
    public void finish() {
        Intent data = new Intent();
        data.putExtra("needRefresh", needRefresh);
        setResult(Activity.RESULT_OK, data);
        super.finish();
    }
}

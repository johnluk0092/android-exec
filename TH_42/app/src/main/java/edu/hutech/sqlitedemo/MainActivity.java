package edu.hutech.sqlitedemo;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.List;
import edu.hutech.sqlitedemo.bean.Note;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter<Note> adapter;
    private static final int REQUEST_CODE = 1000;
    private static final int MENU_VIEW = 1, MENU_CREATE = 2, MENU_EDIT = 3, MENU_DELETE = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        MyDatabaseHelper db = new MyDatabaseHelper(this);
        db.createDefaultNotesIfNeed();
        List<Note> notes = db.getAllNotes();

        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, notes);
        listView.setAdapter(adapter);
        registerForContextMenu(listView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo info) {
        super.onCreateContextMenu(menu, v, info);
        menu.setHeaderTitle("Actions");
        menu.add(0, MENU_VIEW, 0, "View Note");
        menu.add(0, MENU_CREATE, 1, "Create Note");
        menu.add(0, MENU_EDIT, 2, "Edit Note");
        menu.add(0, MENU_DELETE, 3, "Delete Note");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Note note = adapter.getItem(info.position);
        switch (item.getItemId()) {
            case MENU_VIEW:
                Toast.makeText(this, note.getNoteContent(), Toast.LENGTH_LONG).show();
                return true;
            case MENU_CREATE:
                startActivityForResult(
                        new Intent(this, AddEditNoteActivity.class), REQUEST_CODE);
                return true;
            case MENU_EDIT:
                Intent i = new Intent(this, AddEditNoteActivity.class);
                i.putExtra("note", note);
                startActivityForResult(i, REQUEST_CODE);
                return true;
            case MENU_DELETE:
                new AlertDialog.Builder(this)
                        .setMessage("Delete '" + note.getNoteTitle() + "'? ")
                        .setPositiveButton("Yes", (d, w) -> deleteNote(note))
                        .setNegativeButton("No", null)
                        .show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void deleteNote(Note note) {
        new MyDatabaseHelper(this).deleteNote(note);
        adapter.remove(note);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK
                && data.getBooleanExtra("needRefresh", false)) {
            adapter.clear();
            adapter.addAll(new MyDatabaseHelper(this).getAllNotes());
            adapter.notifyDataSetChanged();
        }
    }
}

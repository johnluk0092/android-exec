package edu.hutech.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;
import edu.hutech.sqlitedemo.bean.Note;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Note_Manager";
    private static final String TABLE_NOTE = "Note";

    private static final String COLUMN_ID = "Note_Id";
    private static final String COLUMN_TITLE = "Note_Title";
    private static final String COLUMN_CONTENT = "Note_Content";

    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String script = "CREATE TABLE " + TABLE_NOTE + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY, "
                + COLUMN_TITLE + " TEXT, "
                + COLUMN_CONTENT + " TEXT)";
        db.execSQL(script);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTE);
        onCreate(db);
    }

    // Create
    public void addNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, note.getNoteTitle());
        values.put(COLUMN_CONTENT, note.getNoteContent());
        db.insert(TABLE_NOTE, null, values);
        db.close();
    }

    // Read single
    public Note getNote(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NOTE,
                new String[]{COLUMN_ID, COLUMN_TITLE, COLUMN_CONTENT},
                COLUMN_ID + "=?", new String[]{String.valueOf(id)},
                null, null, null);
        if (cursor != null) cursor.moveToFirst();

        Note note = new Note(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2)
        );
        cursor.close();
        return note;
    }

    // Read all
    public List<Note> getAllNotes() {
        List<Note> notes = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NOTE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Note note = new Note(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2)
                );
                notes.add(note);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return notes;
    }

    // Update
    public int updateNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, note.getNoteTitle());
        values.put(COLUMN_CONTENT, note.getNoteContent());
        return db.update(TABLE_NOTE, values,
                COLUMN_ID + " = ?", new String[]{String.valueOf(note.getNoteId())});
    }

    // Delete
    public void deleteNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NOTE,
                COLUMN_ID + " = ?", new String[]{String.valueOf(note.getNoteId())});
        db.close();
    }

    // Count
    public int getNotesCount() {
        String countQuery = "SELECT * FROM " + TABLE_NOTE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    // Default notes
    public void createDefaultNotesIfNeed() {
        if (getNotesCount() == 0) {
            addNote(new Note("Firstly see Android ListView", "See Android ListView Example in o7planning.org"));
            addNote(new Note("Learning Android SQLite", "See Android SQLite Example in o7planning.org"));
        }
    }
}
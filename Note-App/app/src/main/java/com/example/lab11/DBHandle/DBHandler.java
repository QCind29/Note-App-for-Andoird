package com.example.lab11.DBHandle;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.lab11.Adapter.NoteAdapter;
import com.example.lab11.Model.Note;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class DBHandler extends SQLiteOpenHelper {
    private static final String DB_NAME = "notedb";

    // below int is our database version
    private static final int DB_VERSION = 2;

    // below variable is for our table name.
    private static final String TABLE_NAME = "mynote2";

    // below variable is for our id column.
    private static final String ID_COL = "id_note";

    // below variable is for our course name column
    private static final String NAME_COL = "title_note";

    // below variable id for our course duration column.
    private static final String DURATION_COL = "content_note";
    //private static final String DATE_COL = "date_note";
    private static final String NOTE_COL = "string_note";



    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }




    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + DURATION_COL + " TEXT,"
                + NOTE_COL + " TEXT)";

        db.execSQL(query);
    }
    public void deleteNote(int noteID) {

        // on below line we are creating
        // a variable to write our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are calling a method to delete our
        // course and we are comparing it with our course name.
        db.delete(TABLE_NAME, "id_note=?", new String[]{String.valueOf(noteID)});
        db.close();
    }
    public void updateNote(String originalTitle,String Title, String Content){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put(NAME_COL, Title);
        values.put(DURATION_COL, Content);

        db.update(TABLE_NAME, values, "id_note=?", new String[]{originalTitle});
        db.close();

    }
    public void addNewNote(String noteTitle, String noteContent, String noteDate) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();



        // on below line we are passing all values
        // along with its key and value pair.
        values.put(NOTE_COL, noteDate);
        values.put(NAME_COL, noteTitle);
        values.put(DURATION_COL, noteContent);




        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME,null, values);
        // at last we are closing our
        // database after adding database.
        db.close();
    }
    public ArrayList<Note> readNote() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorNote = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        // on below line we are creating a new array list.
        ArrayList<Note> NoteModalArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorNote.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                NoteModalArrayList.add(new Note(cursorNote.getString(0),
                        cursorNote.getString(1),
                        cursorNote.getString(2),
                        cursorNote.getString(3)));

            } while (cursorNote.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorNote.close();
        return NoteModalArrayList;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
}

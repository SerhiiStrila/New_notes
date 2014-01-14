package com.example.Notes;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;

public class Baza extends SQLiteOpenHelper {

    static final String dbName = "notes_base.db";      // name of our database

    public final Context mContext;
    private Baza myDB;
    private SQLiteDatabase sqldb;
    private ListAdapter adapter;

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + Note.Notes.TABLE_NAME +
            " (" + Note.Notes._ID + " INTEGER PRIMARY KEY, " +
            Note.Notes.colNote + " TEXT );";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + Note.Notes.TABLE_NAME + ";";


    Baza(Context context) {
        super(context, dbName, null, 1);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int db1, int db2) {
        // Удаляем предыдущую таблицу при апгрейде
        db.execSQL(SQL_DELETE_ENTRIES);
        // Создаём новый экземпляр таблицы
        onCreate(db);
    }
    public SQLiteDatabase openDB() {
        myDB = new Baza(mContext);
        sqldb = myDB.getWritableDatabase();
        return sqldb;
    }

    public void closeDB(){
        sqldb.close();
        myDB.close();
    }

    public ListAdapter readDB() {
        openDB();
        String[] columns = {Note.Notes._ID,"Note"};
        Cursor c = sqldb.query(Note.Notes.TABLE_NAME,columns,null,null,null,null,"Note");
        String[] from = new String[]{"Note"};
        int[] to = {android.R.id.text1};
        adapter = new SimpleCursorAdapter(mContext,android.R.layout.simple_list_item_2,c,from,to,0);
        return(adapter);
    }



}


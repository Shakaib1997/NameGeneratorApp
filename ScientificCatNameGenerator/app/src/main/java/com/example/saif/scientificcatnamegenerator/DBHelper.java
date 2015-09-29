package com.example.saif.scientificcatnamegenerator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;


public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "catNames.db";
    private static final String NAMES_TABLE_NAME = "names";
    private static final String NAMES_COLUMN_ID = "_id";
    private static final String NAMES_COLUMN_NAME = "name";
    private static final Integer DATABASE_VERSION = 2;
    public DBHelper (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table "+NAMES_TABLE_NAME+ "("+NAMES_COLUMN_ID+" integer primary key autoincrement, "+NAMES_COLUMN_NAME+" VARCHAR(100) );");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS names");
        onCreate(db);
    }

    public boolean insertName (String name ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAMES_COLUMN_NAME, name);
        db.insert(NAMES_TABLE_NAME, null, contentValues);
        return true;
    }

    public static void backUpData() throws IOException {
        String inFileName = "/data/data/com.example.saif.scientificcatnamegenerator/databases/catNames.db";
        File dbFile = new File (inFileName);
        FileInputStream fis = new FileInputStream(dbFile);

        String outFileName = Environment.getExternalStorageDirectory()+"/catNames.sqlite";

        OutputStream output = new FileOutputStream(outFileName);

        byte[] buffer = new byte[1024];
        int length;
        while((length = fis.read(buffer))>0){
            output.write(buffer, 0, length);
        }

        output.flush();
        output.close();
        fis.close();
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " +NAMES_TABLE_NAME, null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getWritableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db,NAMES_TABLE_NAME );
        return numRows;
    }

    public Integer deleteName (Integer id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("names", "id=?", new String [] {Integer.toString(id)});
    }

    public ArrayList<String> getAllNames(){
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from names", null);
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(NAMES_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }

}

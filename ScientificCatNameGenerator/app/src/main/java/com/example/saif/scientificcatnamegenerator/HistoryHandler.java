package com.example.saif.scientificcatnamegenerator;

import android.content.Context;
import android.database.Cursor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;


public class HistoryHandler {
    DBHelper mydb;
    Cursor cursor;
    private static final int numberOfNames = 25;
    public HistoryHandler(Context context){


        mydb = new DBHelper(context);

        try{mydb.backUpData();} catch(IOException e){}

    }
    public String[] getNames (){
        cursor =mydb.getData();
        int index = 0;
        String[] nameArray = new String[numberOfNames];
        cursor.moveToLast();

        do{
            if(index>= numberOfNames){
                break;
            }
            nameArray[index]=cursor.getString(1);
            index++;
        }
        while(cursor.moveToPrevious() );


        return nameArray;
    }

    public void insertName(String name){
    mydb.insertName(name);
    }
}

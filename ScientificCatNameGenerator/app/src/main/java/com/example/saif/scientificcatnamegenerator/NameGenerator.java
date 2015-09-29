package com.example.saif.scientificcatnamegenerator;


import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class NameGenerator {
    static String[] nameArray;
    public NameGenerator(Context context, String fileName){
        BufferedReader fileReader = null;
        BufferedReader lineReader = null;

        try{
            lineReader= new BufferedReader(new InputStreamReader(context.getAssets().open(fileName)));
            int lines = 0;
            String readLine;
            while(lineReader.readLine() != null)lines++;
            lineReader.close();
            nameArray = new String[lines];

            fileReader = new BufferedReader(new InputStreamReader(context.getAssets().open(fileName)));
            lines = 0;
            readLine = fileReader.readLine();
            while(readLine != null){
                nameArray[lines]=readLine;
                lines++;
                readLine = fileReader.readLine();
            }
        }catch(IOException e){}

    }
    public String generateName(){
        Random numberGenerator = new Random();
        int index = numberGenerator.nextInt(nameArray.length);
        return nameArray[index];

    }
}

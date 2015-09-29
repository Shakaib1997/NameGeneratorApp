package com.example.saif.scientificcatnamegenerator;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class QuestionHandler {
    private String fileName;
    private String[] questionArray = new String[15];

    public QuestionHandler(String fileName, Context context ){
        this.fileName = fileName;
        BufferedReader reader = null;

        try{
            reader = new BufferedReader(new InputStreamReader(context.getAssets().open(this.fileName)));
            String readLine;

            int counter = 0;
            readLine = reader.readLine();
            while(readLine != null){
                if(readLine.startsWith("~")){
                    questionArray[counter] = readLine.substring(1,readLine.length());
                    counter++;
                }

                readLine = reader.readLine();



            }

        } catch (IOException e){

        } finally {
            try{
                reader.close();
            } catch(IOException e){

            }
        }
    }

    public String getQuestionAt(int questionLocation){
        return questionArray[questionLocation];
    }
}

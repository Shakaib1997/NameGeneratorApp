package com.example.saif.scientificcatnamegenerator;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class AnswerHandler {
    private String fileName;
    private String[][] answerArray = new String[15][4];

    public AnswerHandler(String fileName, Context context){
        this.fileName = fileName;

        BufferedReader reader = null;

        try{
            reader = new BufferedReader(new InputStreamReader(context.getAssets().open(this.fileName)));

            String readLine;

            int questionNumber = 0;
            int answerNumber = 0;

            readLine = reader.readLine();

            while(readLine != null){
                if(readLine.startsWith("~")){
                    answerNumber = 0;
                    while(answerNumber < 4){
                        readLine = reader.readLine();
                        answerArray[questionNumber][answerNumber] = readLine.substring(2,readLine.length());

                        answerNumber++;
                    }
                    questionNumber++;
                }
                readLine = reader.readLine();
            }
        }catch(IOException e){}

    }
    public String returnAnswer(int questionNumber, int answerNumber){
        return answerArray[questionNumber][answerNumber];
    }
}

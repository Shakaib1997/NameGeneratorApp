package com.example.saif.scientificcatnamegenerator;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Saif on 02/09/2015.
 */
public class ToastMaker {
    private final int duration = Toast.LENGTH_SHORT;

    ToastMaker(String message, Context context){
        Toast.makeText(context,message,duration).show();
    }
}

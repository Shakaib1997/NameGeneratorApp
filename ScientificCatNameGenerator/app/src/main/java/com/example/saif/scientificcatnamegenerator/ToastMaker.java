package com.example.saif.scientificcatnamegenerator;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Saif on 02/09/2015.
 */
public class ToastMaker {
    private final int duration = Toast.LENGTH_SHORT;
    private Context mContext;
    ToastMaker(Context context){
        this.mContext = context;
    }

    public void makeToast (String message) {
        Toast.makeText(mContext, message, duration).show();
    }
}

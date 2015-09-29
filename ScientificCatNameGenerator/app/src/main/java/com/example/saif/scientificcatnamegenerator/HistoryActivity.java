package com.example.saif.scientificcatnamegenerator;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;

public class HistoryActivity extends ActionBarActivity {
    private Context context;
    private HistoryHandler handler;

    @Bind(R.id.history_back_button) Button backButton;
    @Bind(R.id.name_text) TextView nameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);
        context = getApplicationContext();

        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(HistoryActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        handler = new HistoryHandler(context);
        nameText.setText(namesToString());
    }

    private String namesToString (){
        StringBuffer textViewString = new StringBuffer();
        String[] nameArray;
        nameArray = handler.getNames();
        int index = 0;
        while (index < nameArray.length){
            if(nameArray[index] != null) {
                textViewString.append(nameArray[index] + "\n");
            }
            index++;
        }
        return textViewString.toString();
    }

}

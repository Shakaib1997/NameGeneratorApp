package com.example.saif.scientificcatnamegenerator;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends ActionBarActivity {

    @Bind(R.id.generate_button) Button generateButton;
    @Bind(R.id.history_button) Button historyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Question_Activity.class);
                startActivity(intent);
            }
        });

        historyButton.setOnClickListener(new View.OnClickListener(){
            @Override
        public void onClick (View v){
                Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(intent);
            }
        });





    }



}

package com.example.saif.scientificcatnamegenerator;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import butterknife.Bind;
import butterknife.ButterKnife;


public class Question_Activity extends ActionBarActivity {
    private PagerAdapter mPagerAdapter;
    private static ArrayList<String> questions;
    public static final int NUMBER_OF_QUESTIONS = 15;


    private String name;

    private Context context;
    private int[] answerArray = new int[15];
    int questionNumber = 0;

    private QuestionHandler questionGetter;
    private AnswerHandler answerGetter;

    //dialog screen

    @Bind(R.id.back_button) Button mBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_);
        ButterKnife.bind(this);
        /// HERE IS WHERE WE START THE VIEWPAGER

        initialisePaging();

        //context = getApplicationContext();
        context = this;
        ///the thing that puts the generated names in history

        //my two fonts



        mBackButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Question_Activity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initialisePaging(){
        List<Fragment> fragments = new Vector<Fragment>();
        for(int i=0;i<NUMBER_OF_QUESTIONS;i++){
            fragments.add(Fragment.instantiate(this,QuestionFragment.class.getName()));
        }

        mPagerAdapter = new QuestionPagerAdapter(this.getSupportFragmentManager(),fragments);
        ViewPager pager = (ViewPager)findViewById(R.id.questionViewPager);


        pager.setAdapter(mPagerAdapter);

    }

    }



package com.example.saif.scientificcatnamegenerator;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class QuestionFragment extends Fragment {

    @Bind (R.id.generate_button) Button mGenerateButton;
    @Bind (R.id.question_text_view) TextView mQuestionText;
    @Bind (R.id.rb_answer1) RadioButton mAnswerOne;
    @Bind (R.id.rb_answer2) RadioButton mAnswerTwo;
    @Bind (R.id.rb_answer3) RadioButton mAnswerThree;
    @Bind (R.id.rb_answer4) RadioButton mAnswerFour;

    private Button mExitButton;
    private Button mLikeNameButton;
    private TextView mNamePhraseDisplay;
    private TextView mNameDisplay;
    private String name;
    private HistoryHandler history ;
    private QuestionHandler questionGetter;
    private AnswerHandler answerGetter;

    private int mPosition = 0;

    private ToastMaker mToastMaker;
    public static QuestionFragment instance(int position) {
        QuestionFragment fragment1 = new QuestionFragment();
        fragment1.setPosition(position);
        return fragment1;
    }

    public void setPosition(int position) {
        mPosition = position;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ){
        final Context context;
        if(container == null){
            return null;
        }



        View questionView =inflater.inflate(R.layout.question_layout, container, false);
        context = questionView.getContext();
        ButterKnife.bind(this, questionView);

        mToastMaker = new ToastMaker(context);

        questionGetter = new QuestionHandler("cat_questions.txt", context);
        answerGetter = new AnswerHandler("cat_questions.txt", context);
        history = new HistoryHandler(context);

        mQuestionText.setText(questionGetter.getQuestionAt(mPosition));
        mAnswerOne.setText(answerGetter.returnAnswer(mPosition,0));
        mAnswerTwo.setText(answerGetter.returnAnswer(mPosition,1));
        mAnswerThree.setText(answerGetter.returnAnswer(mPosition,2));
        mAnswerFour.setText(answerGetter.returnAnswer(mPosition,3));

        if(mPosition == 14){
            mGenerateButton.setVisibility(View.VISIBLE);
        }
        else{
            mGenerateButton.setVisibility(View.GONE);
        }

        mGenerateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final NameGenerator generator = new NameGenerator(context, "cat_names.txt");
                name = generator.generateName();

                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.customdialog_layout);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);

                Typeface orangeJuiceFont = Typeface.createFromAsset(context.getAssets(), "orange juice 2.0.ttf");
                Typeface colorsOfAutumnFont = Typeface.createFromAsset(context.getAssets(),"Colors Of Autumn.ttf");

                mNamePhraseDisplay=(TextView)dialog.findViewById(R.id.generated_name_phrase);
                mNamePhraseDisplay.setTypeface(orangeJuiceFont);

                mNameDisplay=(TextView)dialog.findViewById(R.id.generated_name);
                mNameDisplay.setTypeface(colorsOfAutumnFont);
                mNameDisplay.setText(name);

                mExitButton=(Button)dialog.findViewById(R.id.exit_button);
                mExitButton.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        dialog.cancel();
                    }
                });

                mLikeNameButton=(Button)dialog.findViewById(R.id.like_name_button);
                mLikeNameButton.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        history.insertName(name);
                        mToastMaker.makeToast(name +" has been added to your liked names page!");
                        dialog.cancel();
                    }
                });

                dialog.show();

            }
        });
        return questionView;
    }
}

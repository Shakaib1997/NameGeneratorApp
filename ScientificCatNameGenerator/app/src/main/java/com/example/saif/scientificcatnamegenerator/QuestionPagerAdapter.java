package com.example.saif.scientificcatnamegenerator;



import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;

import java.util.List;

public class QuestionPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;
    public QuestionPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments=fragments;
    }

    @Override
    public Fragment getItem(int position){
        Fragment fragment = QuestionFragment.instance(position);
        return fragment;
    }

    @Override
    public int getCount(){
        return Question_Activity.NUMBER_OF_QUESTIONS;
    }
}


package com.example.pc.campusapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class BusinessPagerAdapter extends FragmentPagerAdapter {
    public static final int NUMBER_OF_PAGES = 4;

    public BusinessPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return (new BusinessScheduleFragment());
        }
        else if(position == 1 ){
            return (new BusinessGradeFragment());
        }
        else if(position == 2){
            return (new BusinessAttendanceFragment());
        }
        else return (new BusinessCalcFragment());
    }

    @Override
    public int getCount() {
        return NUMBER_OF_PAGES;
    }
}

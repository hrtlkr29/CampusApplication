package com.example.pc.campusapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class RobotPagerAdapter extends FragmentPagerAdapter {
    public static final int NUMBER_OF_PAGES = 3;

    public RobotPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return (new RobotDecisionFragment());
        }
        else if(position == 1 ){
            return (new RobotParticipantFragment());
        }
        else {
            return (new RobotEventFragment());
        }
    }

    @Override
    public int getCount() {
        return NUMBER_OF_PAGES;
    }
}

package com.example.pc.campusapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {
    public static final int NUMBER_OF_PAGES = 4;

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return (new AcademicFragment());
        }
        else if(position == 1 ){
            return (new EventFragment());
        }
        else if(position == 2){
            return (new SportFragment());
        }
        else return (new SettingFragment());
    }

    @Override
    public int getCount() {
        return NUMBER_OF_PAGES;
    }
}

package com.example.pc.campusapplication;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class MarathonActivity extends AppCompatActivity implements View.OnClickListener,ViewPager.OnPageChangeListener {
    ViewPager viewPager;
    MarathonPagerAdapter SPPagerAdapter;
    ImageButton btnSchedule, btnGrade, btnAttendance, btnCalc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sport_register);
        btnGrade = findViewById(R.id.btnGrade);
        btnAttendance = findViewById(R.id.btnAttendance);
        btnCalc = findViewById(R.id.btnCalc);

        btnGrade.setOnClickListener(this);
        btnAttendance.setOnClickListener(this);
        btnCalc.setOnClickListener(this);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        SPPagerAdapter = new MarathonPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(SPPagerAdapter);

        viewPager.addOnPageChangeListener(this);
        onPageSelected(0);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.btnGrade){
            viewPager.setCurrentItem(0);
        }
        else if(id == R.id.btnAttendance){
            viewPager.setCurrentItem(1);
        }
        else viewPager.setCurrentItem(2);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position == 0) {
            btnGrade.setBackground(getDrawable(R.drawable.ico_grade_red));
            btnAttendance.setBackground(getDrawable(R.drawable.ico_sport_attendance_black));
            btnCalc.setBackground(getDrawable(R.drawable.ico_sport_event_black));
        } else if (position == 1) {
            btnGrade.setBackground(getDrawable(R.drawable.ico_grade_black));
            btnAttendance.setBackground(getDrawable(R.drawable.ico_sport_attendance_red));
            btnCalc.setBackground(getDrawable(R.drawable.ico_sport_event_black));
        } else if (position == 2) {
            btnGrade.setBackground(getDrawable(R.drawable.ico_grade_black));
            btnAttendance.setBackground(getDrawable(R.drawable.ico_sport_attendance_black));
            btnCalc.setBackground(getDrawable(R.drawable.ico_sport_event_red));
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}


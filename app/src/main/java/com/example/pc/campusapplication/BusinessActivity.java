package com.example.pc.campusapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;

public class BusinessActivity extends AppCompatActivity implements View.OnClickListener,ViewPager.OnPageChangeListener {
    ViewPager viewPager;
    BusinessPagerAdapter BAPagerAdapter;
    ImageButton btnSchedule, btnGrade, btnAttendance, btnCalc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_administration);
        btnSchedule = findViewById(R.id.btnSchedule);
//        btnGrade = findViewById(R.id.btnGrade);
//        btnAttendance = findViewById(R.id.btnAttendance);
        btnCalc = findViewById(R.id.btnCalc);

        btnSchedule.setOnClickListener(this);
//        btnGrade.setOnClickListener(this);
//        btnAttendance.setOnClickListener(this);
        btnCalc.setOnClickListener(this);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        BAPagerAdapter = new BusinessPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(BAPagerAdapter);

        viewPager.addOnPageChangeListener(this);
        onPageSelected(0);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.btnSchedule){
            viewPager.setCurrentItem(0);
        }
        else if(id == R.id.btnGrade){
            viewPager.setCurrentItem(1);
        }
        else if(id == R.id.btnAttendance){
            viewPager.setCurrentItem(2);
        }
        else viewPager.setCurrentItem(3);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if(position == 0){
            btnSchedule.setBackground(getDrawable(R.drawable.ico_class_schedule_red));
//            btnGrade.setBackground(getDrawable(R.drawable.ico_grade_black));
//            btnAttendance.setBackground(getDrawable(R.drawable.ico_attendance_black));
            btnCalc.setBackground(getDrawable(R.drawable.ico_calc_black));
        }
//        else if(position == 1){
//            btnSchedule.setBackground(getDrawable(R.drawable.ico_class_schedule_black));
//            btnGrade.setBackground(getDrawable(R.drawable.ico_grade_red));
//            btnAttendance.setBackground(getDrawable(R.drawable.ico_attendance_black));
//            btnCalc.setBackground(getDrawable(R.drawable.ico_calc_black));
//        }
//        else if(position == 2){
//            btnSchedule.setBackground(getDrawable(R.drawable.ico_class_schedule_black));
//            btnGrade.setBackground(getDrawable(R.drawable.ico_grade_black));
//            btnAttendance.setBackground(getDrawable(R.drawable.ico_attendance_red));
//            btnCalc.setBackground(getDrawable(R.drawable.ico_calc_black));
//        }
        else if(position == 1){
            btnSchedule.setBackground(getDrawable(R.drawable.ico_class_schedule_black));
//            btnGrade.setBackground(getDrawable(R.drawable.ico_grade_black));
//            btnAttendance.setBackground(getDrawable(R.drawable.ico_attendance_black));
            btnCalc.setBackground(getDrawable(R.drawable.ico_calc_red));
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}

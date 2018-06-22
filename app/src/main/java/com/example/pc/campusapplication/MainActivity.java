package com.example.pc.campusapplication;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {
    ViewPager viewPager;
    PagerAdapter pagerAdapter;
    ImageButton btnAcademic, btnEvent, btnSport, btnSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAcademic = findViewById(R.id.btnAcademic);
        btnEvent = findViewById(R.id.btnEvent);
        btnSport = findViewById(R.id.btnSport);
        btnSetting = findViewById(R.id.btnSetting);

        btnAcademic.setOnClickListener(this);
        btnEvent.setOnClickListener(this);
        btnSport.setOnClickListener(this);
        btnSetting.setOnClickListener(this);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        viewPager.addOnPageChangeListener(this);
        onPageSelected(0);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.btnAcademic){
            viewPager.setCurrentItem(0);
        }
        else if(id == R.id.btnEvent){
            viewPager.setCurrentItem(1);
        }
        else if(id == R.id.btnSport){
            viewPager.setCurrentItem(2);
        }
        else viewPager.setCurrentItem(3);
    }

    private void performSignOut() {
        FirebaseAuth.getInstance().signOut();
        finish();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if(position == 0){
            btnAcademic.setBackground(getDrawable(R.drawable.ico_academic_red));
            btnEvent.setBackground(getDrawable(R.drawable.ico_event_black));
            btnSport.setBackground(getDrawable(R.drawable.ico_sport_black));
            btnSetting.setBackground(getDrawable(R.drawable.ico_setting_black));
        }
        else if(position == 1){
            btnAcademic.setBackground(getDrawable(R.drawable.ico_academic_black));
            btnEvent.setBackground(getDrawable(R.drawable.ico_event_red));
            btnSport.setBackground(getDrawable(R.drawable.ico_sport_black));
            btnSetting.setBackground(getDrawable(R.drawable.ico_setting_black));
        }
        else if(position == 2){
            btnAcademic.setBackground(getDrawable(R.drawable.ico_academic_black));
            btnEvent.setBackground(getDrawable(R.drawable.ico_event_black));
            btnSport.setBackground(getDrawable(R.drawable.ico_sport_red));
            btnSetting.setBackground(getDrawable(R.drawable.ico_setting_black));
        }
        else if(position == 3){
            btnAcademic.setBackground(getDrawable(R.drawable.ico_academic_black));
            btnEvent.setBackground(getDrawable(R.drawable.ico_event_black));
            btnSport.setBackground(getDrawable(R.drawable.ico_sport_black));
            btnSetting.setBackground(getDrawable(R.drawable.ico_setting_red));
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}

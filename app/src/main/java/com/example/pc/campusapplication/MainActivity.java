package com.example.pc.campusapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {
    ViewPager viewPager;
    MenuPagerAdapter menuPagerAdapter;
    ImageButton btnAcademic, btnEvent, btnSport, btnSetting;
    EventFragment eventFragment;
    EventDetailFragment eventDetailFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eventFragment = new EventFragment();
        eventDetailFragment = new EventDetailFragment();
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
        menuPagerAdapter = new MenuPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(menuPagerAdapter);
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
            showFragment(eventFragment,eventDetailFragment);
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

    public void showFragment(Fragment fragmentToShow, Fragment fragmentToHide) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (fragmentToShow.isAdded()) {
            transaction.show(fragmentToShow);
        } else {
            transaction.add(R.id.viewPager, fragmentToShow);
        }

        if (fragmentToHide.isAdded()) {
            transaction.hide(fragmentToHide);

        }
        transaction.commit();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

//    @Override
//    public void onListEventClicked(Event selectedEvent) {
//        startActivity(intent);
//        eventDetailFragment.setEvent(selectedEvent);
//    }
}

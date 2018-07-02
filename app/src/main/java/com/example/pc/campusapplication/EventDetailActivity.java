package com.example.pc.campusapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class EventDetailActivity extends AppCompatActivity {
    private TextView txtName, txtPlace, txtTime, txtDate, txtDescription;
    private ImageView thumbnail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        txtName = findViewById(R.id.tvEventDetailName);
        txtPlace = findViewById(R.id.tvEventDetailPlace);
        txtTime = findViewById(R.id.tvEventDetailTime);
        txtDate = findViewById(R.id.tvEventDetailDate);
        txtDescription = findViewById(R.id.tvEventDetailDescription);
        thumbnail = findViewById(R.id.detailThumbnail);


        Intent intent = getIntent();
//        intent.putExtra("eventName",event.getName());
//        intent.putExtra("eventTime",event.getTime());
//        intent.putExtra("eventPlace",event.getAddress());
//        intent.putExtra("eventDate",event.getDate());
//        intent.putExtra("eventDescription",event.getDescription());
//        intent.putExtra("eventImageURL",event.getImageUri());

        String name = intent.getStringExtra("eventName");
        String date = intent.getStringExtra("eventDate");
        String time = intent.getStringExtra("eventTime");
        String address = intent.getStringExtra("eventPlace");
        String description = intent.getStringExtra("eventDescription");
        String imageURL = intent.getStringExtra("eventImageURL");

        txtName.setText("Name: " + name);
        txtPlace.setText("Place: " + address);
        txtTime.setText("Time: " + time);
        txtDate.setText("Date: " + date);
        txtDescription.setText("Description: " + description);
        GlideApp.with(this)
                .load(imageURL)
                .into(thumbnail);
    }
}

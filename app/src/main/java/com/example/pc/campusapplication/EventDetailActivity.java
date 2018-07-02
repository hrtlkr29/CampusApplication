package com.example.pc.campusapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class EventDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView txtName, txtPlace, txtTime, txtDate, txtDescription;
    private ImageView thumbnail;
    Button btnJoinEvent, btnMessage;
    String name, date, time, address, description, imageURL, id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        txtDate = findViewById(R.id.tvEventDetailDate);
        txtName = findViewById(R.id.tvEventDetailName);
        txtPlace = findViewById(R.id.tvEventDetailPlace);
        txtTime = findViewById(R.id.tvEventDetailTime);
        txtDescription = findViewById(R.id.tvEventDetailDescription);
        thumbnail = findViewById(R.id.detailThumbnail);
        btnJoinEvent = findViewById(R.id.btnJoinEvent);
        btnMessage = findViewById(R.id.btnMessage);

        Intent intent = getIntent();


         name = intent.getStringExtra("eventName");
         date = intent.getStringExtra("eventDate");
         time = intent.getStringExtra("eventTime");
         address = intent.getStringExtra("eventPlace");
         description = intent.getStringExtra("eventDescription");
         imageURL = intent.getStringExtra("eventImageURL");
         id = intent.getStringExtra("eventID");

        txtName.setText(name);
        txtPlace.setText(address);
        txtTime.setText(time);
        txtDate.setText(date);
        txtDescription.setText(description);
        GlideApp.with(this)
                .load(imageURL)
                .override(400,200)
                .fitCenter()
                .centerCrop()
                .into(thumbnail);

        btnMessage.setOnClickListener(this);
        btnJoinEvent.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btnJoinEvent ){
//            joinEvent();
        }
        if(id == R.id.btnMessage){
            goToMessages();
        }
    }

    private void goToMessages(){
        Intent intent = new Intent(EventDetailActivity.this, MessageActivity.class);
        intent.putExtra("eventID",this.id);
        startActivity(intent);
    }
}

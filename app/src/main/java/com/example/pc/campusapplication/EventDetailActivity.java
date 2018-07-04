package com.example.pc.campusapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EventDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView txtName, txtPlace, txtTime, txtDate, txtDescription, txtParticipants;
    private ImageView thumbnail;
    Button btnJoinEvent, btnMessage;
    String name, date, time, address, description, imageURL, eventId, uid, participants;
    FirebaseAuth auth;
    DatabaseReference db;
//    Boolean isJoined;
    int participantCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        txtDate = findViewById(R.id.tvEventDetailDate);
        txtName = findViewById(R.id.tvEventDetailName);
        txtPlace = findViewById(R.id.tvEventDetailPlace);
        txtTime = findViewById(R.id.tvEventDetailTime);
        txtDescription = findViewById(R.id.tvEventDetailDescription);
        txtParticipants = findViewById(R.id.tvEventDetailParticipants);
        thumbnail = findViewById(R.id.detailThumbnail);
        btnJoinEvent = findViewById(R.id.btnJoinEvent);
        btnMessage = findViewById(R.id.btnMessage);
//        isJoined = false;
        auth = FirebaseAuth.getInstance();
        uid = auth.getCurrentUser().getUid();
        db = FirebaseDatabase.getInstance().getReference();
        Intent intent = getIntent();
        participantCount = 0;
        name = intent.getStringExtra("eventName");
        date = intent.getStringExtra("eventDate");
        time = intent.getStringExtra("eventTime");
        address = intent.getStringExtra("eventPlace");
        description = intent.getStringExtra("eventDescription");
        imageURL = intent.getStringExtra("eventImageURL");
        eventId = intent.getStringExtra("eventID");

        btnMessage.setOnClickListener(this);
        btnJoinEvent.setOnClickListener(this);
        txtParticipants.setOnClickListener(this);
        txtPlace.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        DatabaseReference participantsRef = db.child("events").child(eventId).child("participants");
        participantsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                participantCount = 0;
                for(DataSnapshot child : dataSnapshot.getChildren()){
                    participantCount++;
                    txtParticipants.setText(participantCount + " have joined this event");
                    User user = child.getValue(User.class);
                    boolean isJoined = user.getUid().equals(uid);
                    if(isJoined == true){
                        btnJoinEvent.setBackgroundColor(getResources().getColor(R.color.grey));
                        btnJoinEvent.setEnabled(false);
                    }
                }


            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
        txtName.setText(name);
        txtPlace.setText(address);
        txtTime.setText(time);
        txtDate.setText(date);
        txtDescription.setText(description);
        txtParticipants.setText(participantCount + " have joined this event");
        GlideApp.with(this)
                .load(imageURL)
                .override(400, 200)
                .fitCenter()
                .centerCrop()
                .into(thumbnail);

    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btnJoinEvent) {
            joinEvent();
        }
        if (id == R.id.btnMessage) {
            goToMessages();
        }
        if (id == R.id.tvEventDetailParticipants){
            goToParticipants();
        }
        if (id == R.id.tvEventDetailPlace){
            goToMap();
        }
    }

    private void goToMessages() {
        Intent intent = new Intent(EventDetailActivity.this, MessageActivity.class);
        intent.putExtra("eventID", this.eventId);
        startActivity(intent);
    }

    private void goToMap(){
        Intent intent = new Intent(EventDetailActivity.this,MapActivity.class);
        intent.putExtra("eventAddress",this.address);
        startActivity(intent);
    }

    private void goToParticipants(){
        Intent intent = new Intent(EventDetailActivity.this, EventParticipantActivity.class);
        intent.putExtra("eventID", this.eventId);
        startActivity(intent);
    }

    private void joinEvent() {
        DatabaseReference userRef = db.child("users").child(uid);
        final Participant participant = new Participant();
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                DatabaseReference participantInfoRef = db.child("events").child(eventId).child("participants").push();
                participantInfoRef.setValue(user);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
        btnJoinEvent.setEnabled(false);
        btnJoinEvent.setBackgroundColor(getResources().getColor(R.color.grey));
    }
}

package com.example.pc.campusapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RobotEventParticipantActivity extends AppCompatActivity {
    private ListView lvParticipants;
    ArrayList<Participant> participants;
    DatabaseReference db;
    String eventId;
    DatabaseReference participantRef;
    ParticipantAdapter adapter;
    FirebaseAuth auth;
    String uid;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_participant);
        db = FirebaseDatabase.getInstance().getReference();
        lvParticipants = findViewById(R.id.lvParticipants);
        participants = new ArrayList<>();
        Intent intent = getIntent();
        eventId = intent.getStringExtra("eventID");
        Log.i("eventId",eventId);
        auth = FirebaseAuth.getInstance();
        uid = auth.getCurrentUser().getUid();
        participantRef = db.child("sport").child("robot").child("event").child(eventId).child("participants");
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                participants.clear();
                for(DataSnapshot child : dataSnapshot.getChildren()){
                    Participant p = child.getValue(Participant.class);
                    participants.add(p);
                }
                adapter = new ParticipantAdapter(RobotEventParticipantActivity.this, R.layout.participant_row,participants);
                lvParticipants.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        participantRef.addValueEventListener(listener);
    }
}

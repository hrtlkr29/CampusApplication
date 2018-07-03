package com.example.pc.campusapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MarathonParticipantFrament extends Fragment {
    View v;
    App app;
    DatabaseReference db;
    DatabaseReference participantref;
    ListView lstSport_User;
    ArrayList<User> users;
    String name;
    SportParticipantAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sp_participant,null);
        db = FirebaseDatabase.getInstance().getReference();
        lstSport_User = (ListView) rootView.findViewById(R.id.lstSport_User);
        loadUser();
        return  rootView;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
    private void loadUser(){
        users = new ArrayList<>();
        participantref = db.child("sport").child("Marathon").child("Participants");
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                users.clear();
                for(DataSnapshot child : dataSnapshot.getChildren()) {
                    User u = child.getValue(User.class);
                    users.add(u);
                }
                adapter = new SportParticipantAdapter(getContext(), R.layout.sport_participant_row,users);
                lstSport_User.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        participantref.addValueEventListener(valueEventListener);
    }

}

package com.example.pc.campusapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EventFragment extends Fragment  {
    ListView lvEvents;
    ArrayList<Event> events;
    EventAdapter adapter;
    EventCallback eventCallback;
    Button btnAddEvent;
    ProgressDialog progressDialog;
    DatabaseReference db;
    DatabaseReference eventRef;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_event,null);
        btnAddEvent = rootView.findViewById(R.id.btnAddEvent);
        db = FirebaseDatabase.getInstance().getReference();
        lvEvents = rootView.findViewById(R.id.lvEvent);
        progressDialog = new ProgressDialog(this.getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        ButtonHandler handler = new ButtonHandler();
        btnAddEvent.setOnClickListener(handler);
        loadEvents();
        return rootView;
    }

//    private void goToMessage(){
//        Intent intent = new Intent(this.getActivity(),MessageActivity.class);
//        startActivity(intent);
//    }
    class ButtonHandler implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            if(view.getId() == R.id.btnAddEvent){
                goToAddEvent();
            }
        }
    }
    private void goToAddEvent(){
        Intent intent = new Intent(this.getActivity(),AddEventActivity.class);
        startActivity(intent);
    }

    private void loadEvents(){
        progressDialog.show();
        events = new ArrayList<>();
        eventRef = db.child("events");
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                events.clear();
                for(DataSnapshot child : dataSnapshot.getChildren()){
                    Event e = child.getValue(Event.class);
                    events.add(e);
                }
                adapter = new EventAdapter(getContext(), R.layout.event_row,events);
                lvEvents.setAdapter(adapter);
                progressDialog.hide();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        eventRef.addValueEventListener(listener);
    }

}

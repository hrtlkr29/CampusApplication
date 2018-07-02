package com.example.pc.campusapplication;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodSession;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EventFragment extends Fragment implements AdapterView.OnItemClickListener {
    ListView lvEvents;
    ArrayList<Event> events;
    EventAdapter adapter;
    Button btnAddEvent;
    ProgressDialog progressDialog;
    DatabaseReference db;
    DatabaseReference eventRef;
    SharedEventModel model;
    EventListCallback eventListCallback;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = ViewModelProviders.of(getActivity()).get(SharedEventModel.class);
        progressDialog = new ProgressDialog(this.getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_event,null);
        btnAddEvent = rootView.findViewById(R.id.btnAddEvent);
        db = FirebaseDatabase.getInstance().getReference();
        lvEvents = rootView.findViewById(R.id.lvEvent);
        ButtonHandler handler = new ButtonHandler();
        btnAddEvent.setOnClickListener(handler);
        loadEvents();
        lvEvents.setOnItemClickListener(this);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Event event = this.events.get(i);
        Intent intent = new Intent(this.getActivity(),EventDetailActivity.class);
        intent.putExtra("eventName",event.getName());
        intent.putExtra("eventTime",event.getTime());
        intent.putExtra("eventPlace",event.getAddress());
        intent.putExtra("eventDate",event.getDate());
        intent.putExtra("eventDescription",event.getDescription());
        intent.putExtra("eventImageURL",event.getImageUri());
        intent.putExtra("eventID",event.getId());
        startActivity(intent);
//        eventListCallback.onListEventClicked(event);
    }

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

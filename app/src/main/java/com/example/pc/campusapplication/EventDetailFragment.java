package com.example.pc.campusapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class EventDetailFragment extends Fragment {
    private TextView txtName, txtPlace, txtTime, txtDate, txtDescription;
    private ImageView thumbnail;
    private MainActivity host;
    private Event currentEvent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.activity_event_detail,null);
        txtName = rootView.findViewById(R.id.tvEventDetailName);
        txtPlace = rootView.findViewById(R.id.tvEventDetailPlace);
        txtTime = rootView.findViewById(R.id.tvEventDetailTime);
        txtDate = rootView.findViewById(R.id.tvEventDetailDate);
        txtDescription = rootView.findViewById(R.id.tvEventDetailDescription);
        thumbnail = rootView.findViewById(R.id.detailThumbnail);



        txtName.setText("Name: " + currentEvent.getName());
        txtPlace.setText("Place: " + currentEvent.getAddress());
        txtTime.setText("Time: " + currentEvent.getTime());
        txtDate.setText("Date: " + currentEvent.getDate());
        txtDescription.setText("Description: " + currentEvent.getDescription());
        GlideApp.with(this.getContext())
                .load(currentEvent.getThumbnail())
                .into(thumbnail);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        host = (MainActivity) getActivity();
    }

    public void setEvent(Event selectedEvent){
        this.currentEvent = selectedEvent;
    }
}

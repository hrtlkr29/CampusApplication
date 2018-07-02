package com.example.pc.campusapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ParticipantAdapter extends ArrayAdapter<Participant> {
    private Context context;
    private Integer resource;
    private ArrayList<Participant> participants;

    public ParticipantAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Participant> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.participants = objects;
    }

    @Override
    public int getCount() {
        return participants.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ParticipantHolder holder;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(resource,parent,false);
            holder = new ParticipantHolder();
            holder.fullName = convertView.findViewById(R.id.txt_participant_name);
            convertView.setTag(holder);
        }else{
            holder = (ParticipantHolder) convertView.getTag();
        }
        Participant p = participants.get(position);
        holder.fullName.setText(p.getFirstName());
        return convertView;
    }

    class ParticipantHolder{
        private TextView fullName;
    }
}

package com.example.pc.campusapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class EventAdapter extends ArrayAdapter<Event> {
    private Context context;
    private int resource;
    private ArrayList<Event> events;
    ViewHolder viewHolder;

    public EventAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Event> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.events = objects;
    }

    @Override
    public int getCount() {
        return events.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(resource,parent,false);
            viewHolder.tvEventName = (TextView) convertView.findViewById(R.id.tvEventName);
            viewHolder.tvEventTime = (TextView) convertView.findViewById(R.id.tvEventTime);
            viewHolder.tvEventPlace = (TextView) convertView.findViewById(R.id.tvEventPlace);
            viewHolder.ivEventImage = (ImageView) convertView.findViewById(R.id.ivEventImage);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Event event = this.events.get(position);
        viewHolder.tvEventName.setText(event.getName());
        viewHolder.tvEventTime.setText(event.getTime());
        viewHolder.tvEventPlace.setText(event.getAddress());
        GlideApp.with(this.getContext())
                .load(event.getThumbnail())
                .into(viewHolder.ivEventImage);
        return convertView;
    }

    static class ViewHolder{
        TextView tvEventName;
        TextView tvEventPlace;
        TextView tvEventTime;
        ImageView ivEventImage;
    }
}

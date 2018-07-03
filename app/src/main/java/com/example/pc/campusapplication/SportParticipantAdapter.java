package com.example.pc.campusapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.*;

public class SportParticipantAdapter extends ArrayAdapter<User> {
    private  Context context;
    private  int resource;
    private  ArrayList<User> users;
    private  ViewHolder holder;
    public SportParticipantAdapter(@NonNull Context context, int resource, @NonNull ArrayList<User> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.users = objects;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        if(convertView ==null){
            holder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(resource,parent,false);
            holder.tvUser = (TextView) convertView.findViewById(R.id.tvUser);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        User name = this.users.get(position);
        holder.tvUser.setText(name.getEmail());

        return convertView;
    }

    static class ViewHolder {
        TextView tvUser;
    }
}

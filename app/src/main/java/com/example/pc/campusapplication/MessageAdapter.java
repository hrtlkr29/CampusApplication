package com.example.pc.campusapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends ArrayAdapter<Message> {
    Context context;
    int res;
    ArrayList<Message> messages;
    public MessageAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Message> objects) {
        super(context, resource, objects);
        this.context = context;
        this.res = resource;
        this.messages = objects;
    }

    @Override
    public int getCount() {
        return this.messages.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(res,parent,false);
            holder = new ViewHolder();
            holder.username = convertView.findViewById(R.id.tvUsername);
            holder.text = convertView.findViewById(R.id.txtMessRow);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        Message m = messages.get(position);
        holder.username.setText(m.getUsername());
        holder.text.setText(m.getText());
        return convertView;
    }


    class ViewHolder{
        TextView username;
        TextView text;
    }
}

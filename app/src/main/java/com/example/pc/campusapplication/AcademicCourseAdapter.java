package com.example.pc.campusapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

public class AcademicCourseAdapter extends ArrayAdapter<AcademicCourse> {
    private Context context;
    private Integer resource;
    private ArrayList<AcademicCourse> courses;
    public AcademicCourseAdapter(@NonNull Context context, int resource, @NonNull ArrayList<AcademicCourse> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.courses = objects;
    }

    @Override
    public int getCount() {
        return courses.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        CourseHolder holder;
        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(resource, null);
            holder = new CourseHolder();
            holder.imageView = convertView.findViewById(R.id.academic_cell_imgView);
            convertView.setTag(holder);
        }
        else{
            holder = (CourseHolder) convertView.getTag();
        }
        AcademicCourse c = courses.get(position);
        holder.imageView.setImageResource(c.getImageView());
        return convertView;
    }

    class CourseHolder{
        private ImageView imageView;
    }
}

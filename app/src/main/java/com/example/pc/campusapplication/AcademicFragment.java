package com.example.pc.campusapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class AcademicFragment extends Fragment implements AdapterView.OnItemClickListener {
    ListView listCourse;
    ArrayList<AcademicCourse> courses;
    AcademicCourseAdapter adapter;
    CourseListCallback courseListCallback;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_academic,null);
        courses = new ArrayList<>();
        listCourse = rootView.findViewById(R.id.listAcademic);
        AcademicCourse business = new AcademicCourse(R.drawable.img_ba);
        AcademicCourse computerScience = new AcademicCourse(R.drawable.img_cs);
        courses.add(business);
        courses.add(computerScience);
        return rootView;
    }

    public void setCallback(CourseListCallback callback){
        this.courseListCallback = callback;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new AcademicCourseAdapter(getActivity(),R.layout.academic_cell,this.courses);
        listCourse.setAdapter(adapter);
        listCourse.setOnItemClickListener(this);

    }

    private void goToBAScreen(){
        Intent intent = new Intent(this.getActivity(),BusinessActivity.class);
        startActivity(intent);
    }

    private void goToCSScreen(){
        Intent intent = new Intent(this.getActivity(),ComputerScienceActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (i == 0 ){
            goToBAScreen();
        }
        if(i == 1){
            goToCSScreen();
        }
    }
}

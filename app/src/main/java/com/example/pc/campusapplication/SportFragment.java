package com.example.pc.campusapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SportFragment extends Fragment{
    RecyclerView mRecycleview;
    View v;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ImageButton rImage;
    RecyclerView.LayoutManager mLayoutManager;
//    ProgressDialog progress;


    //set View layout
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sport,container,false);
        return rootView;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.v = view;
        init();
//        progress = new ProgressDialog(getActivity());
//        progress.setTitle("Loading");
//        progress.setMessage("Syncing");
//        progress.setCancelable(false);
//        progress.show();
        loaddata();
    }

    private void loaddata(){
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("sport");
    }

    private void init(){
        mRecycleview = (RecyclerView) v.findViewById(R.id.recycleview);
        mRecycleview.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecycleview.setLayoutManager(mLayoutManager);
    }

    //load data to recycler view on start
    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<SportModel, SportViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<SportModel, SportViewHolder>(
                        SportModel.class,
                        R.layout.sport_row,
                        SportViewHolder.class,
                        databaseReference) {

                    @Override
                    protected void populateViewHolder(SportViewHolder viewHolder, SportModel model, int position) {
                        viewHolder.setDetail(getActivity().getApplicationContext(), model.getTitle(), model.getImage(), model.getdescription());
                    }
                };
        mRecycleview.setAdapter(firebaseRecyclerAdapter);
    }

}

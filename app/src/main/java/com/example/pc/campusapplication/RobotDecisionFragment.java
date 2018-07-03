package com.example.pc.campusapplication;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class RobotDecisionFragment extends Fragment implements  View.OnClickListener{
    Button btnJoin, btnLeave;
    String uid;
    View v;
    App app;
    FirebaseAuth auth;
    DatabaseReference db;
    EditText txtEmail;
    TextView rTitle,rdescription;
    ImageView rImage;
    DatabaseReference ref;
    String title, description, image;
    int participantCount;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sp_decision,null);
        participantCount = 0;
        //firebase
        auth =  FirebaseAuth.getInstance();
        uid = auth.getCurrentUser().getUid();
        db = FirebaseDatabase.getInstance().getReference();
        //get view
        txtEmail = (EditText) rootView.findViewById(R.id.txtEmail);
        rTitle = (TextView) rootView.findViewById(R.id.rTitle);
        rdescription = (TextView) rootView.findViewById(R.id.rdescription);
        rImage = (ImageView) rootView.findViewById(R.id.rImage);
        btnJoin = (Button) rootView.findViewById(R.id.btnJoin);

        //pass intent from SportViewHolder
        Intent intent = getActivity().getIntent();
        title = intent.getStringExtra("title");
        description = intent.getStringExtra("des");
        rTitle.setText(title);
        rdescription.setText(description);
        rImage.setImageResource(R.drawable.img_robot);

        btnJoin.setOnClickListener(this);

        return  rootView;
    }
    @Override
    public void onStart() {
        super.onStart();
        DatabaseReference participantsRef = db.child("sport").child("robot").child("Participants");
        participantsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                participantCount = 0;
                for(DataSnapshot child : dataSnapshot.getChildren()){
//                    participantCount++;
                    User user = child.getValue(User.class);
                    boolean isJoined = user.getUid().equals(uid);
                    if(isJoined == true){
                        btnJoin.setBackgroundColor(getResources().getColor(R.color.grey));
                        btnJoin.setEnabled(false);
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.v = view;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.btnJoin){
            writeUserData();

        }
    }


    @Override
    public void onActivityCreated (Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }


    private void writeUserData() {
        DatabaseReference userRef = db.child("users").child(uid);
        final Participant participant = new Participant();
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                DatabaseReference participantInfoRef =
                        db.child("sport")
                                .child("robot")
                                .child("Participants").push();
                participantInfoRef.setValue(user);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
        btnJoin.setEnabled(false);
        btnJoin.setBackgroundColor(getResources().getColor(R.color.grey));
    }
    }


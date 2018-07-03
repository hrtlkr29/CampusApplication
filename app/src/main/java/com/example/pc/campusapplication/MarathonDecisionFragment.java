package com.example.pc.campusapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import static android.support.constraint.Constraints.TAG;

public class MarathonDecisionFragment extends Fragment implements  View.OnClickListener{
    Button btnJoin, btnLeave;
    String uid;
    View v;
    App app;
    FirebaseAuth auth;
    DatabaseReference db;
    EditText txtEmail;
    DatabaseReference ref;
    String title, description, image;
    TextView rTitle,rdescription;
    ImageView rImage;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sp_decision,null);

        return  rootView;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.v = view;
        auth =  FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance().getReference();

        txtEmail = (EditText) view.findViewById(R.id.txtEmail);
        rTitle = (TextView) view.findViewById(R.id.rTitle);
        rdescription = (TextView) view.findViewById(R.id.rdescription);
        rImage = (ImageView) view.findViewById(R.id.rImage);
        btnJoin = (Button) view.findViewById(R.id.btnJoin);
        uid = auth.getCurrentUser().getUid();
        btnJoin.setEnabled(true);
        btnJoin.setOnClickListener(this);
        Intent intent = getActivity().getIntent();
        title = intent.getStringExtra("title");
        description = intent.getStringExtra("des");
        rTitle.setText(title);
        rImage.setImageResource(R.drawable.img_mara);
        rdescription.setText(description);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.btnJoin){
            writeUserData();
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        DatabaseReference participantsRef = db.child("sport").child("Marathon").child("Participants");
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

    private void writeUserData() {
        DatabaseReference userRef = db.child("users").child(uid);
        final Participant participant = new Participant();
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                DatabaseReference participantInfoRef =
                                db.child("sport")
                                .child("Marathon")
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
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        uid = user.getUid();
//        String name = user.getEmail();
//        User user_new = new User(name);
//        DatabaseReference ref = db.child("sport").child("Marathon").child("Participants").push();
//        ref.setValue(user_new);
//        getActivity().finish();


    }


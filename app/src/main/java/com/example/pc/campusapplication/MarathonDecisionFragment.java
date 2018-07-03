package com.example.pc.campusapplication;

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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
        btnJoin = (Button) view.findViewById(R.id.btnJoin);

        btnJoin.setEnabled(true);
        btnJoin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.btnJoin){
            writeUserData(uid);

            btnJoin.setEnabled(false);
            btnJoin.setBackgroundColor(getResources().getColor(R.color.grey));
        }
    }


    private void writeUserData(String uid) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        String name = user.getEmail();
        User user_new = new User(name);
        DatabaseReference ref = db.child("sport").child("Marathon").child("Participants").push();
        ref.setValue(user_new);
//        getActivity().finish();


    }
}

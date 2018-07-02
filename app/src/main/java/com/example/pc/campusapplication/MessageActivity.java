package com.example.pc.campusapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MessageActivity extends AppCompatActivity implements View.OnClickListener{
    EditText txtMess;
    ListView lvMessages;
    Button btnSend;
    FirebaseAuth auth;
    DatabaseReference db;
    String uid;
    ArrayList<Message> messages;
    MessageAdapter adapter;
    DatabaseReference messRef;
    DatabaseReference user;
    ProgressDialog progressDialog;
    String eventID;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        auth = FirebaseAuth.getInstance();
        uid = auth.getCurrentUser().getUid();
        db = FirebaseDatabase.getInstance().getReference();

        Intent intent = getIntent();
        eventID = intent.getStringExtra("eventID");
        txtMess = findViewById(R.id.txtMess);
        lvMessages = findViewById(R.id.lstMess);
        btnSend = findViewById(R.id.btnSend);
        btnSend.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);

        loadMessages();
    }

    private void loadMessages(){
        progressDialog.show();
        messages = new ArrayList<>();
        messRef = db.child("events").child(eventID).child("messages");
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                messages.clear();
                for(DataSnapshot child : dataSnapshot.getChildren()){
                    Message m = child.getValue(Message.class);
                    messages.add(m);
                }
                adapter = new MessageAdapter(MessageActivity.this, R.layout.message_row,messages);
                lvMessages.setAdapter(adapter);
                progressDialog.hide();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        messRef.addValueEventListener(listener);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void addNewMessage(){
        final String getMessage = txtMess.getText().toString();
        String username;
        if (TextUtils.isEmpty(getMessage)) {
            Toast.makeText(this, "Your message is empty", Toast.LENGTH_SHORT).show();
        }
        else{
            //get username
            user = db.child("users").child(uid);
            user.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    User user = dataSnapshot.getValue(User.class);
                    String firstname = user.getFirstName();
                    App.hideKeyboard(MessageActivity.this);
                    Message message = new Message(firstname.toString(),getMessage);
                    DatabaseReference ref = db.child("events").child(eventID).child("messages").push();
                    ref.setValue(message);
                    txtMess.setText("");
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btnSend){
            addNewMessage();
        }
    }
}

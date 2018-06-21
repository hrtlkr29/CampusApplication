package com.example.pc.campusapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnMessage, btnSignout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnMessage = findViewById(R.id.btnMessage);
        btnSignout = findViewById(R.id.btnSignOut);
        btnMessage.setOnClickListener(this);
        btnSignout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.btnSignOut){
            performSignOut();
        }
        else if(id == R.id.btnMessage){
            Intent intent = new Intent(this,MessageActivity.class);
            startActivity(intent);
        }
    }

    private void performSignOut() {
        FirebaseAuth.getInstance().signOut();
        finish();
    }
}

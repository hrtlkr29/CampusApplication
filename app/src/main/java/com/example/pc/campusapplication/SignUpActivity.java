package com.example.pc.campusapplication;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    EditText txtEmail, txtPass, txtRePass, txtFirst, txtLast;
    Button btnRegister;
    App app;
    FirebaseAuth auth;
    DatabaseReference db;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        app = (App) getApplication();
        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance().getReference();

        txtFirst = findViewById(R.id.txtFName);
        txtLast = findViewById(R.id.txtLName);
        txtEmail = findViewById(R.id.txtEmail);
        txtPass = findViewById(R.id.txtPass);
        txtRePass = findViewById(R.id.txtRePass);
        btnRegister = findViewById(R.id.btnLogin);
        btnRegister.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnLogin) {
            progressDialog.show();
            performRegister();
        }
    }

    private void performRegister() {
        final String email = txtEmail.getText().toString().trim();
        String pass = txtPass.getText().toString().trim();
        String repass = txtRePass.getText().toString();
        String firstName = txtFirst.getText().toString().trim();
        String lastName = txtLast.getText().toString().trim();

        if(email.isEmpty() || pass.isEmpty() || repass.isEmpty() || firstName.isEmpty() || lastName.isEmpty()){
            Toast.makeText(app, "Please fill all information", Toast.LENGTH_SHORT).show();
            progressDialog.hide();
        }

        if (verifyEmailAndPassword(email, pass, repass)) {

            Task<AuthResult> task = auth.createUserWithEmailAndPassword(email, pass);
            task.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        String uid = auth.getCurrentUser().getUid();
                        Toast.makeText(SignUpActivity.this, "Register successfully", Toast.LENGTH_SHORT).show();
                        writeUserData(uid, email);
                        progressDialog.hide();
                    }
                }
            });
            task.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.hide();
                    Toast.makeText(app, "Sign up failed!", Toast.LENGTH_SHORT).show();
                    Log.i("Register failed: ",e.toString());
                }
            });
        }
    }

    private boolean verifyEmailAndPassword(String email, String pass, String repass) {
        if (!email.contains("@")) {
            Toast.makeText(this, "Email is invalid", Toast.LENGTH_SHORT).show();
            progressDialog.hide();
            return false;
        }

        if (!pass.equals(repass)) {
            Toast.makeText(this, "Password is not match", Toast.LENGTH_SHORT).show();
            progressDialog.hide();
            return false;
        }

        return true;
    }

    private void writeUserData(String uid, String email) {
        String firstName = txtFirst.getText().toString().trim();
        String lastName = txtLast.getText().toString().trim();
        String token = app.loadPref(App.FCM_TOKEN);
        User user = new User(uid, email, firstName, lastName, token);
        db.child("users").child(uid).setValue(user);
        finish(); // back to sign in activity
        // go to main activity
    }
}

package com.example.pc.campusapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {
    EditText txtEmail, txtPass;
    Button btnSignup, btnSignin, btnForgot;
    FirebaseAuth auth;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        auth = FirebaseAuth.getInstance();
        txtEmail = findViewById(R.id.txtEmail);
        txtPass = findViewById(R.id.txtPass);
        btnSignin = findViewById(R.id.btnLogin);
        btnSignup = findViewById(R.id.btnRegister);
        btnForgot = findViewById(R.id.btn_forgot);
        btnSignin.setOnClickListener(this);
        btnSignup.setOnClickListener(this);
        btnForgot.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser u = auth.getCurrentUser();
        if(u != null){
            goToMainScreen();
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.btnLogin){
            progressDialog.show();
            performLogin();
        }
        else if(id == R.id.btnRegister){
            goToRegister();
        }
        else if(id == R.id.btn_forgot){
            goToForgot();
        }
    }

    private void performLogin(){
        String email = txtEmail.getText().toString();
        String password = txtPass.getText().toString();
        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Please enter your email and password", Toast.LENGTH_SHORT).show();
            progressDialog.hide();
        }
        else{
            Task<AuthResult> task = auth.signInWithEmailAndPassword(email,password);
            task.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(SignInActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();
                        progressDialog.hide();
                        goToMainScreen();
                    }
                    else{
                        progressDialog.hide();
                        Toast.makeText(SignInActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    private void goToMainScreen(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    private void goToForgot(){
        Intent intent = new Intent(this,ResetPasswordActivity.class);
        startActivity(intent);
    }

    private void goToRegister(){
        Intent intent = new Intent(this,SignUpActivity.class);
        startActivity(intent);
    }
}

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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class ChangePasswordActivity extends AppCompatActivity implements View.OnClickListener {
    EditText txtPass, txtRepass, txtOldPass;
    Button btnChangePass;
    FirebaseUser user;
    String userEmail;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        user = FirebaseAuth.getInstance().getCurrentUser();
        txtPass = findViewById(R.id.txtNewPass);
        txtRepass = findViewById(R.id.txtRePass);
        txtOldPass = findViewById(R.id.txtOldPass);
        btnChangePass = findViewById(R.id.btnChangePass);
        userEmail = user.getEmail();
        btnChangePass.setOnClickListener(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btnChangePass){
            changePass();
        }
    }
    private void performSignOut() {
        FirebaseAuth.getInstance().signOut();
    }

    private void changePass(){
        progressDialog.show();
        String oldPassword =txtOldPass.getText().toString().trim();
        final String newPassword = txtPass.getText().toString().trim();
        String retypeNewPassword = txtRepass.getText().toString();

        if(oldPassword.isEmpty() || newPassword.isEmpty() || retypeNewPassword.isEmpty()){
            Toast.makeText(this, "Please fill out all the fields", Toast.LENGTH_SHORT).show();
            progressDialog.hide();
            return;
        }
        if(!newPassword.equals(retypeNewPassword)){
            Toast.makeText(this, "Make sure your retype password is correct!", Toast.LENGTH_SHORT).show();
            progressDialog.hide();
            return;
        }
        else{
            if(newPassword.equals(retypeNewPassword)){
                AuthCredential credential = EmailAuthProvider
                        .getCredential(userEmail,oldPassword);
                user.reauthenticate(credential)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    user.updatePassword(newPassword)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        Toast.makeText(ChangePasswordActivity.this, "Password update successful", Toast.LENGTH_SHORT).show();
                                                        progressDialog.hide();
                                                        finish();
                                                    }
                                                    else{
                                                        Toast.makeText(ChangePasswordActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                                        progressDialog.hide();
                                                        finish();
                                                    }
                                                }
                                            });
                                }
                                else{
                                    Toast.makeText(ChangePasswordActivity.this, "Make sure your current password is corret!", Toast.LENGTH_SHORT).show();
                                    progressDialog.hide();
                                    return;
                                }
                            }
                        });
            }
        }
    }
}

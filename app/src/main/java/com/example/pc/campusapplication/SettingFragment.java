package com.example.pc.campusapplication;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class SettingFragment extends android.support.v4.app.Fragment {
    Button btnLogout, btnChangePassword;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_setting,null);
        btnLogout = rootView.findViewById(R.id.btnLogout);
        btnChangePassword = rootView.findViewById(R.id.btnChangePass);
        ButtonHandler btnHandler =  new ButtonHandler();
        btnLogout.setOnClickListener(btnHandler);
        btnChangePassword.setOnClickListener(btnHandler);
        return rootView;
    }

    private void performSignOut() {
        FirebaseAuth.getInstance().signOut();
    }

    private void goToSignIn(){
        Intent intent = new Intent(this.getActivity(),SignInActivity.class);
        startActivity(intent);
    }

    private void gotoChangePass(){
        Intent intent = new Intent(this.getActivity(),ChangePasswordActivity.class);
        startActivity(intent);
    }
    class ButtonHandler implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            if(view.getId() == R.id.btnLogout){
                performSignOut();
                goToSignIn();
            }
            if(view.getId() == R.id.btnChangePass){
                gotoChangePass();
            }
        }
    }

}

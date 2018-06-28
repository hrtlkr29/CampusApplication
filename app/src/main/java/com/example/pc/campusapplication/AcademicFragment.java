package com.example.pc.campusapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

public class AcademicFragment extends Fragment {

    ImageButton btnCS, btnBA;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_academic,null);
        btnBA = rootView.findViewById(R.id.btnBA);
        btnCS = rootView.findViewById(R.id.btnCS);
        ButtonHandler buttonHandler = new ButtonHandler();
        btnBA.setOnClickListener(buttonHandler);
        btnCS.setOnClickListener(buttonHandler);
        return rootView;
    }

    private class ButtonHandler implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            int id = view.getId();
            if(id == R.id.btnCS){
                goToCSScreen();
            }
            else if(id == R.id.btnBA){
                goToBAScreen();
            }
        }
    }

    private void goToBAScreen(){
        Intent intent = new Intent(this.getActivity(),BusinessActivity.class);
        startActivity(intent);
    }

    private void goToCSScreen(){
        Intent intent = new Intent(this.getActivity(),ComputerScienceActivity.class);
        startActivity(intent);
    }
}

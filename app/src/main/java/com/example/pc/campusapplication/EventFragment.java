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

public class EventFragment extends Fragment  {
    Button btnMessage;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_event,null);
        btnMessage = rootView.findViewById(R.id.btnMessage);
        ButtonHandler btnHandler = new ButtonHandler();
        btnMessage.setOnClickListener(btnHandler);
        return rootView;
    }

    private void goToMessage(){
        Intent intent = new Intent(this.getActivity(),MessageActivity.class);
        startActivity(intent);
    }
    class ButtonHandler implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            if(view.getId() == R.id.btnMessage){
                goToMessage();
            }
        }
    }
}

package com.example.pc.campusapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    TextView tvMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        tvMessage = (TextView) findViewById(R.id.tvMessage);

        tvMessage.setText(
                getIntent().getStringExtra("key_1")
                        + "\n" +
                        getIntent().getStringExtra("key_2")
        );
    }
}

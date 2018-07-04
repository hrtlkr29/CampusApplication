package com.example.pc.campusapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class ComputerScienceCalcFragment extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    Spinner spnIntake;
    EditText txtCredit;
    TextView txtFee;
    Button btnGenerate;
    double baseFee;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseFee = 2000;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ba_calc,null);
        spnIntake = rootView.findViewById(R.id.spnIntake);
        txtFee = rootView.findViewById(R.id.txtFee);
        txtCredit = rootView.findViewById(R.id.txtChooseCredit);
        btnGenerate = rootView.findViewById(R.id.btnCalculate);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(),
                R.array.intake_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnIntake.setAdapter(adapter);
        spnIntake.setOnItemSelectedListener(this);
        btnGenerate.setOnClickListener(this);
        return  rootView;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (i == 0){
            baseFee = 2.0;
        }
        else if(i == 1){
            baseFee = 2.1;
        }
        else if(i == 2){
            baseFee = 2.2;
        }
        else if(i == 3){
            baseFee = 2.3;
        }
        else if(i == 4){
            baseFee = 2.4;
        }
        else if(i == 5){
            baseFee = 2.5;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnCalculate){
            int credit = Integer.parseInt(txtCredit.getText().toString().trim());
            double fee = credit * baseFee;
            txtFee.setText("Your tuition fee this semester is " + String.valueOf(fee) + "(Millions VND)");
        }
    }
}

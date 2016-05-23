package com.example.pc.medproject.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pc.medproject.R;

import java.util.ArrayList;


public class DiabeticFragment extends Fragment{

    public DiabeticFragment() {
        // Required empty public constructor
    }

    EditText glucose;
    TextView t;
    CheckBox boxBefore;
    CheckBox boxAfter;
    Button buttonAdd;
    Button buttonSetTime;
    ArrayList<String> time;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.diabetic_fragment, container, false);        // Inflate the layout for this fragment

        glucose = (EditText) view.findViewById(R.id.editText);
        buttonAdd = (Button) view.findViewById(R.id.button);
        buttonSetTime = (Button) view.findViewById(R.id.buttonSetTime);
        boxAfter = (CheckBox) view.findViewById(R.id.checkBox2);
        boxBefore = (CheckBox) view.findViewById(R.id.checkBox);

        buttonSetTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                buttonClicked(v);
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                save(v);
            }
        });

        return view;
    }



    public void buttonClicked(View v) {

        DialogFragment picker = new TimeDialogFragment();
        picker.setTargetFragment(this, 1);
        picker.show(this.getFragmentManager(), "datePicker");
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == 1){
            switch (requestCode){
                case 1:
                    time = data.getStringArrayListExtra("key_time");
                    Log.d("time ", time.get(0));
                    break;
            }
        }
    }

    public void save(View v){
        Log.d("czas " + time.get(0), " ciśnienie " + glucose.getText() + " " + boxAfter.isChecked() + " " + boxBefore.isChecked());
    }
}


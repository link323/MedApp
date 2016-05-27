package com.example.pc.medproject.views;

import android.app.Dialog;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.pc.medproject.DataBaseAdapter;
import com.example.pc.medproject.DialogHelper;
import com.example.pc.medproject.R;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by PC on 25.04.2016.
 */
public class BloodPressureFragment extends Fragment{

    public BloodPressureFragment() {
        // Required empty public constructor
    }

    EditText skurczowe;
    EditText rozkurczowe;
    TextView t;
    Button buttonAdd;
    Button buttonSetTime;
    ArrayList<String> time = new ArrayList<>();
    DataBaseAdapter db;
    DialogHelper helper = new DialogHelper();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.pressure_fragment, container, false);
        skurczowe = (EditText) view.findViewById(R.id.editText2);
        rozkurczowe = (EditText) view.findViewById(R.id.editText3);
        buttonAdd = (Button) view.findViewById(R.id.button);
        buttonSetTime = (Button) view.findViewById(R.id.buttonSetTime);
        t = (TextView) view.findViewById(R.id.textView);
        time = helper.returnDefaultTime(time);
        db = new DataBaseAdapter(getContext());
        db = db.open();

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
        Log.d("czas "+time.get(0), " ciśnienie "+ skurczowe.getText() + " " + rozkurczowe.getText());
        if (skurczowe.getText().toString().equals("") || rozkurczowe.getText().toString().equals("") || skurczowe.getText().toString().equals(null) || rozkurczowe.getText().toString().equals(null)) {
            Toast.makeText(v.getContext(), "Uzupełnij wszystkie pola!", Toast.LENGTH_LONG).show();
            return;
        }
        else if (Integer.parseInt(skurczowe.getText().toString()) < Integer.parseInt(rozkurczowe.getText().toString())) {
            Toast.makeText(v.getContext(), "Wartości ciśnienia wpisane zostały w złej kolejności!", Toast.LENGTH_LONG).show();
        }

        else{
            db.insertEntryToBloodTable(Integer.parseInt(skurczowe.getText().toString()), Integer.parseInt(rozkurczowe.getText().toString()), time.get(0));
            Toast.makeText(v.getContext(), "Zapisano nowy wynik!", Toast.LENGTH_LONG).show();
        }
    }
}

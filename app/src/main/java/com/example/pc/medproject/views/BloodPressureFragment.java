package com.example.pc.medproject.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.pc.medproject.DataBaseAdapter;
import com.example.pc.medproject.DateTime;
import com.example.pc.medproject.InputAnalyzer;
import com.example.pc.medproject.MySQLTaskPressure;
import com.example.pc.medproject.R;
import java.util.ArrayList;

/**
 * Created by PC on 25.04.2016.
 */
public class BloodPressureFragment extends Fragment{

    public BloodPressureFragment() {
        // Required empty public constructor
    }
    EditText skurczowe, rozkurczowe, comment;
    Button buttonAdd, buttonSetTime;
    ArrayList<String> time = new ArrayList<>();
    DataBaseAdapter db;
    DateTime dateTime = new DateTime();
    SharedPreferences preferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pressure_fragment, container, false);
        skurczowe = (EditText) view.findViewById(R.id.editText2);
        rozkurczowe = (EditText) view.findViewById(R.id.editText3);
        comment = (EditText) view.findViewById(R.id.editText4);
        buttonAdd = (Button) view.findViewById(R.id.button);
        buttonSetTime = (Button) view.findViewById(R.id.buttonSetTime);
        time.add(dateTime.findActualDate());
        db = new DataBaseAdapter(getContext());
        db = db.open();

        preferences = PreferenceManager.getDefaultSharedPreferences(view.getContext());

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

        String pesel = preferences.getString("pesel", "");
        Log.d("pesel ", pesel);

        if (skurczowe.getText().toString().equals("") || rozkurczowe.getText().toString().equals("") ||
                skurczowe.getText().toString().equals(null) || rozkurczowe.getText().toString().equals(null)) {
            Toast.makeText(v.getContext(), "Uzupełnij wszystkie pola!", Toast.LENGTH_LONG).show();
            return;
        }
        else if (Integer.parseInt(skurczowe.getText().toString()) < Integer.parseInt(rozkurczowe.getText().toString())) {
            Toast.makeText(v.getContext(), "Wartości ciśnienia wpisane zostały w złej kolejności!", Toast.LENGTH_LONG).show();
        }

        else{
            InputAnalyzer analyzer = new InputAnalyzer(Integer.parseInt(skurczowe.getText().toString()), Integer.parseInt(rozkurczowe.getText().toString()));
            if(analyzer.checkPressureInput(v.getContext())){
                db.insertEntryToBloodTable(pesel, Integer.parseInt(skurczowe.getText().toString()),
                        Integer.parseInt(rozkurczowe.getText().toString()), time.get(0), comment.getText().toString());
                Toast.makeText(v.getContext(), "Zapisano nowy wynik!", Toast.LENGTH_LONG).show();

                MySQLTaskPressure mySQLTaskPressure = new MySQLTaskPressure(getContext(), pesel,
                        skurczowe.getText().toString(), rozkurczowe.getText().toString(),  time.get(0), comment.getText().toString());
                mySQLTaskPressure.execute(pesel, pesel, skurczowe.getText().toString(),
                        rozkurczowe.getText().toString(),  time.get(0), comment.getText().toString());
            }
        }
    }
}
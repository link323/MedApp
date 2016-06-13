package com.example.pc.medproject.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pc.medproject.DataBaseAdapter;
import com.example.pc.medproject.DateTime;
import com.example.pc.medproject.InputAnalyzer;
import com.example.pc.medproject.MySQLTaskDiabetic;
import com.example.pc.medproject.R;

import java.util.ArrayList;

public class DiabeticFragment extends Fragment{

    public DiabeticFragment() {
        // Required empty public constructor
    }

    EditText glucose, comment;
    CheckBox boxBefore, boxAfter;
    Button buttonAdd, buttonSetTime;
    ArrayList<String> time = new ArrayList<>();
    DataBaseAdapter db;
    DateTime dateTime = new DateTime();
    SharedPreferences preferences;

    // url to create new product
    private static String url_create_product = "http://api.androidhive.info/android_connect/create_product.php";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.diabetic_fragment, container, false);

        glucose = (EditText) view.findViewById(R.id.editText);
        comment = (EditText) view.findViewById(R.id.editText4);
        buttonAdd = (Button) view.findViewById(R.id.button);
        buttonSetTime = (Button) view.findViewById(R.id.buttonSetTime);
        boxAfter = (CheckBox) view.findViewById(R.id.checkBox2);
        boxBefore = (CheckBox) view.findViewById(R.id.checkBox);
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

        MySQLTaskDiabetic mySQLTaskDiabetic = new MySQLTaskDiabetic(getContext(), pesel, glucose.getText().toString(), time.get(0), "true", comment.getText().toString());
        Log.d("czas " + time.get(0), " ciśnienie " + glucose.getText() + " " + boxAfter.isChecked() + " " + boxBefore.isChecked() + " "+ comment.getText().toString());
        if (glucose.getText().toString().equals("") || glucose.getText().toString().equals(" ") || glucose.getText().toString().equals(null)) {
            Toast.makeText(v.getContext(), "Uzupełnij wynik", Toast.LENGTH_LONG).show();
            return;
        }
        else if (((boxAfter.isChecked() == false) && (boxBefore.isChecked() == false)) || ((boxAfter.isChecked() == true) && (boxBefore.isChecked() == true))) {
            Toast.makeText(v.getContext(), "Wybierz jedną z opcji: PRZED albo PO POSIŁKU", Toast.LENGTH_LONG).show();
            return;
        }
        else{
            InputAnalyzer analyzer = new InputAnalyzer(Integer.parseInt(glucose.getText().toString()), boxBefore.isChecked());
            if(analyzer.checkDiabeticInput(v.getContext()) && boxBefore.isChecked() == true) {
                db.insertEntryToDiabeticTable(pesel, Integer.parseInt(glucose.getText().toString()), time.get(0), true, comment.getText().toString());
                mySQLTaskDiabetic.execute(pesel, glucose.getText().toString(), time.get(0), "true", comment.getText().toString());
            }else if(analyzer.checkDiabeticInput(v.getContext()) && boxBefore.isChecked() == false){
                db.insertEntryToDiabeticTable(pesel, Integer.parseInt(glucose.getText().toString()), time.get(0), false, comment.getText().toString());
                mySQLTaskDiabetic.execute(pesel, glucose.getText().toString(), time.get(0), "false", comment.getText().toString());
            }
            Toast.makeText(v.getContext(), "Zapisano nowy wynik!", Toast.LENGTH_LONG).show();
        }
    }
}
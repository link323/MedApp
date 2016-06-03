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
import android.widget.Toast;

import com.example.pc.medproject.DataBaseAdapter;
import com.example.pc.medproject.DialogHelper;
import com.example.pc.medproject.MySQLTask;
import com.example.pc.medproject.R;

import junit.framework.Assert;

import java.util.ArrayList;

public class DiabeticFragment extends Fragment{

    public DiabeticFragment() {
        // Required empty public constructor
    }

    EditText glucose;
    CheckBox boxBefore, boxAfter;
    Button buttonAdd, buttonSetTime;
    ArrayList<String> time = new ArrayList<>();
    DataBaseAdapter db;
    DialogHelper helper = new DialogHelper();

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
        buttonAdd = (Button) view.findViewById(R.id.button);
        buttonSetTime = (Button) view.findViewById(R.id.buttonSetTime);
        boxAfter = (CheckBox) view.findViewById(R.id.checkBox2);
        boxBefore = (CheckBox) view.findViewById(R.id.checkBox);
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
        Log.d("czas " + time.get(0), " ciśnienie " + glucose.getText() + " " + boxAfter.isChecked() + " " + boxBefore.isChecked());
        if (glucose.getText().toString().equals("") || glucose.getText().toString().equals(" ") || glucose.getText().toString().equals(null)) {
            Toast.makeText(v.getContext(), "Uzupełnij wynik", Toast.LENGTH_LONG).show();
            return;
        }
        else if (((boxAfter.isChecked() == false) && (boxBefore.isChecked() == false)) || ((boxAfter.isChecked() == true) && (boxBefore.isChecked() == true))) {
            Toast.makeText(v.getContext(), "Wybierz jedną z opcji: PRZED albo PO POSIŁKU", Toast.LENGTH_LONG).show();
            return;
        }
        else{
            if(boxBefore.isChecked() == true) {
                db.insertEntryToDiabeticTable(Integer.parseInt(glucose.getText().toString()), time.get(0), true);
            }else {
                db.insertEntryToDiabeticTable(Integer.parseInt(glucose.getText().toString()), time.get(0), false);
            }
            Toast.makeText(v.getContext(), "Zapisano nowy wynik!", Toast.LENGTH_LONG).show();
            MySQLTask mySQLTask = new MySQLTask(getContext(), "900923836291", glucose.getText().toString(), time.get(0), "true");
            mySQLTask.execute("900923836291", glucose.getText().toString(), time.get(0), "true");        }
    }
}
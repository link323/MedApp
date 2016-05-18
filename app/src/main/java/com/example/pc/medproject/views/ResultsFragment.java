package com.example.pc.medproject.views;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pc.medproject.R;

import java.util.List;

/**
 * Created by PC on 25.04.2016.
 */
public class ResultsFragment extends Fragment{
    public ResultsFragment() {
        // Required empty public constructor
    }
    SensorManager manager;
    TextView tv;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        tv = (TextView) findViewById(R.id.textView);
//        tv.setText("");
//
//        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
//        List<Sensor> s = manager.getSensorList(Sensor.TYPE_ALL);
//        for (int x = 0; x < s.size(); x++) {
//            tv.setText(tv.getText() + "\n" + s.get(x).getName());
//        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.result_fragment, container, false);
    }
    public void setText(String string){
        TextView view = (TextView) getView().findViewById(R.id.textView);
        view.setText("blabla");
    }
}

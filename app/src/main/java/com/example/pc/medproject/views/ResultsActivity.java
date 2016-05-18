package com.example.pc.medproject.views;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import com.example.pc.medproject.R;

import java.util.List;

/**
 * Created by PC on 04.04.2016.
 */
public class ResultsActivity extends Activity {

    SensorManager manager;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.diabetic_fragment);

        tv = (TextView) findViewById(R.id.textView);
        tv.setText("");

        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> s = manager.getSensorList(Sensor.TYPE_ALL);
        for (int x = 0; x < s.size(); x++) {
            tv.setText(tv.getText() + "\n" + s.get(x).getName());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

package com.example.pc.medproject;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

/**
 * Created by PC on 14.03.2016.
 */
public class DiabeticActivity extends Activity {

    SensorManager manager;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.diabetic);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

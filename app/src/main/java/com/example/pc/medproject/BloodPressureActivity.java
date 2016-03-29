package com.example.pc.medproject;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by PC on 14.03.2016.
 */
public class BloodPressureActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pressure);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

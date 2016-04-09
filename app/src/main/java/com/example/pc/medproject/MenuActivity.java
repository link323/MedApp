package com.example.pc.medproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by PC on 13.03.2016.
 */
public class MenuActivity extends Activity {

    Button diabeticButton, presureButton, resultsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        diabeticButton = (Button) findViewById(R.id.diabeticButton);
        presureButton = (Button) findViewById(R.id.pressureButton);
        resultsButton = (Button) findViewById(R.id.resultsButton);

        diabeticButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intentDiabetic = new Intent(getApplicationContext(), DiabeticActivity.class);
                startActivity(intentDiabetic);
            }
        });
        presureButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intentPressure = new Intent(getApplicationContext(), BloodPressureActivity.class);
                startActivity(intentPressure);
            }
        });
        resultsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intentResults = new Intent(getApplicationContext(), ResultsActivity.class);
                startActivity(intentResults);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close The Database
    }
}

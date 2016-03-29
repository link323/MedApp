package com.example.pc.medproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by PC on 13.03.2016.
 */
public class MenuActivity extends Activity {

    TextView diabeticTextView, pressureTextView;
    Button diabeticButton, presureButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

//        diabeticTextView=(TextView)findViewById(R.id.diabeticText);
//        diabeticTextView.setText("Zmierz poziom cukru we krwi");
//        pressureTextView.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);

//        pressureTextView=(TextView)findViewById(R.id.pressureText);
//        pressureTextView.setText("Zmierz poziom ciśnienia krwi");
//        pressureTextView.setGravity(Gravity.CENTER);

        diabeticButton=(Button)findViewById(R.id.diabeticButton);
        presureButton=(Button)findViewById(R.id.pressureButton);
        // Set OnClick Listener on SignUp button
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
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close The Database
    }
}

package com.example.pc.medproject.views;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pc.medproject.DataBaseAdapter;
import com.example.pc.medproject.R;

public class HomeActivity extends Activity
{
	Button btnSignUp;
	DataBaseAdapter dataBaseAdapter;
	SharedPreferences preferences;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		dataBaseAdapter =new DataBaseAdapter(this);
		dataBaseAdapter = dataBaseAdapter.open();
		preferences = PreferenceManager.getDefaultSharedPreferences(this);

        final  EditText editTextUserName=(EditText)findViewById(R.id.editTextUserNameToLogin);
        final  EditText editTextPassword=(EditText)findViewById(R.id.editTextPasswordToLogin);

		btnSignUp=(Button)findViewById(R.id.buttonSignUp);

        Button btnSignIn=(Button)findViewById(R.id.buttonSignIn);

        btnSignIn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String userName = editTextUserName.getText().toString();
                String password = editTextPassword.getText().toString();

				SharedPreferences.Editor editor = preferences.edit();
				editor.putString("Name",userName);
				editor.apply();

                String storedPassword = dataBaseAdapter.getSinlgeEntry(userName);

                if (password.equals(storedPassword)) {
                    Toast.makeText(HomeActivity.this, "Congrats: Login Successfull", Toast.LENGTH_LONG).show();
                    Intent intentMenu = new Intent(getApplicationContext(), MenuTabsActivity.class);
                    startActivity(intentMenu);
                } else {
                    Toast.makeText(HomeActivity.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				Intent intentSignUP=new Intent(getApplicationContext(),SignUPActivity.class);
				startActivity(intentSignUP);
			}
		});
    }

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// Close The Database
		dataBaseAdapter.close();
	}
}
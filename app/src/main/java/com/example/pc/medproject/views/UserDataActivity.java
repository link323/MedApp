package com.example.pc.medproject.views;

import android.app.Activity;
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

/**
 * Created by PC on 18.05.2016.
 */
public class UserDataActivity extends Activity {

    EditText editTextUserFirstName, editTextUserLastName, getEditTextPesel;
    Button btnSave;
    SharedPreferences preferences;

    DataBaseAdapter dataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_data);

        dataBaseAdapter = new DataBaseAdapter(this);
        dataBaseAdapter = dataBaseAdapter.open();

        editTextUserFirstName = (EditText) findViewById(R.id.editTextUserFirstName);
        editTextUserLastName = (EditText) findViewById(R.id.editTextUserLastName);
        getEditTextPesel = (EditText) findViewById(R.id.editTextPesel);
        btnSave = (Button) findViewById(R.id.buttonSave);
        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        btnSave.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String login = preferences.getString("Name", "");
                Log.d("login ", login);

                String name = editTextUserFirstName.getText().toString();
                String lastname = editTextUserLastName.getText().toString();
                String pesel = getEditTextPesel.getText().toString();

                // check if any of the fields are vaccant
                if (name.equals("") || lastname.equals("")|| getEditTextPesel.equals("")) {
                    Toast.makeText(getApplicationContext(), "Uzupełnij wszystkie pola", Toast.LENGTH_LONG).show();
                    return;
                }
                else {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("pesel",pesel);
                    editor.apply();
                    // Save the Data in Database
                    dataBaseAdapter.insertEntryUsersDataTable(login, name, lastname, pesel);
                    Toast.makeText(getApplicationContext(), "Zapisano!", Toast.LENGTH_LONG).show();
                    Intent intentHome = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intentHome);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        dataBaseAdapter.close();
    }
}

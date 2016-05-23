package com.example.pc.medproject.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

    DataBaseAdapter dataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_data);

        dataBaseAdapter = new DataBaseAdapter(this);
        dataBaseAdapter = dataBaseAdapter.open();

        // Get Refferences of Views
        editTextUserFirstName = (EditText) findViewById(R.id.editTextUserFirstName);
        editTextUserLastName = (EditText) findViewById(R.id.editTextUserLastName);
        getEditTextPesel = (EditText) findViewById(R.id.editTextPesel);
        btnSave = (Button) findViewById(R.id.buttonSave);

        btnSave.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub

                String name = editTextUserFirstName.getText().toString();
                String lastname = editTextUserLastName.getText().toString();
                String pesel = getEditTextPesel.getText().toString();

                // check if any of the fields are vaccant
                if (name.equals("") || lastname.equals("")|| getEditTextPesel.equals("")) {
                    Toast.makeText(getApplicationContext(), "Uzupełnij wszystkie pola", Toast.LENGTH_LONG).show();
                    return;
                }
                else {
                    // Save the Data in Database
                    dataBaseAdapter.insertEntryUsersDataTable(name, lastname, pesel);
                    Toast.makeText(getApplicationContext(), "Zapisano!", Toast.LENGTH_LONG).show();
                    Intent intentHome = new Intent(getApplicationContext(), MenuTabsActivity.class);
                    startActivity(intentHome);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

        dataBaseAdapter.close();
    }
}

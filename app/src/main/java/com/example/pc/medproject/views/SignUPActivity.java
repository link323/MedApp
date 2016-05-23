package com.example.pc.medproject.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pc.medproject.DataBaseAdapter;
import com.example.pc.medproject.R;

public class SignUPActivity extends Activity {
    EditText editTextUserName, editTextPassword, editTextConfirmPassword, editTextPesel;
    Button btnCreateAccount;
    TextView textView;

    DataBaseAdapter dataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        dataBaseAdapter = new DataBaseAdapter(this);
        dataBaseAdapter = dataBaseAdapter.open();

        // Get Refferences of Views
        editTextUserName = (EditText) findViewById(R.id.editTextUserName);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextConfirmPassword = (EditText) findViewById(R.id.editTextConfirmPassword);
        editTextPesel = (EditText) findViewById(R.id.editTextPesel);
        btnCreateAccount = (Button) findViewById(R.id.buttonCreateAccount);
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub

                String userName = editTextUserName.getText().toString();
                String password = editTextPassword.getText().toString();
                String confirmPassword = editTextConfirmPassword.getText().toString();
                Log.d("register",editTextPesel.getText().toString());
                long pesel = Long.parseLong(editTextPesel.getText().toString());

                // check if any of the fields are vaccant
                if (userName.equals("") || password.equals("")|| confirmPassword.equals("")) {
                    Toast.makeText(getApplicationContext(), "Uzupełnij wszystkie pola", Toast.LENGTH_LONG).show();
                    return;
                }
                // check if both password matches
                if (!password.equals(confirmPassword)) {
                    Toast.makeText(getApplicationContext(), "Podane hasła są od siebie różne", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    // Save the Data in Database
                    dataBaseAdapter.insertEntryUsersTable(userName, password);
                    Toast.makeText(getApplicationContext(), "Gratulacje! Utworzono nowe konto", Toast.LENGTH_LONG).show();
                    Intent intentHome = new Intent(getApplicationContext(), HomeActivity.class);
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

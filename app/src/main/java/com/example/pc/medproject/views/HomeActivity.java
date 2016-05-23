package com.example.pc.medproject.views;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pc.medproject.DataBaseAdapter;
import com.example.pc.medproject.R;

public class HomeActivity extends Activity
{
	Button btnSignIn,btnSignUp;
	DataBaseAdapter dataBaseAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		// create a instance of SQLite Database
		dataBaseAdapter =new DataBaseAdapter(this);
		dataBaseAdapter = dataBaseAdapter.open();

        final  EditText editTextUserName=(EditText)findViewById(R.id.editTextUserNameToLogin);
        final  EditText editTextPassword=(EditText)findViewById(R.id.editTextPasswordToLogin);

		// Get The Refference Of Buttons
//		btnSignIn=(Button)findViewById(R.id.buttonSignIN);
		btnSignUp=(Button)findViewById(R.id.buttonSignUp);

		// Set OnClick Listener on SignUp button
//		btnSignUp.setOnClickListener(new View.OnClickListener() {
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//
//				/// Create Intent for SignUpActivity  and Start The Activity
//				Intent intentSignUP=new Intent(getApplicationContext(),SignUPActivity.class);
//				startActivity(intentSignUP);
//			}
//		});
        Button btnSignIn=(Button)findViewById(R.id.buttonSignIn);

        // Set On ClickListener
        btnSignIn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // get The User name and Password
                String userName = editTextUserName.getText().toString();
                String password = editTextPassword.getText().toString();

                // fetch the Password form database for respective user name
                String storedPassword = dataBaseAdapter.getSinlgeEntry(userName);

                // check if the Stored password matches with  Password entered by user
                if (password.equals(storedPassword)) {
                    Toast.makeText(HomeActivity.this, "Congrats: Login Successfull", Toast.LENGTH_LONG).show();
                    /// Create Intent for SignUpActivity  and Start The Activity
                    Intent intentMenu = new Intent(getApplicationContext(), MenuTabsActivity.class);
                    startActivity(intentMenu);
                } else {
                    Toast.makeText(HomeActivity.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub

				/// Create Intent for SignUpActivity  and Start The Activity
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



/*package com.example.pc.medproject.views;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pc.medproject.DataBaseAdapter;
import com.example.pc.medproject.R;

public class HomeActivity extends Activity 
{
	Button btnSignIn,btnSignUp;
	DataBaseAdapter dataBaseAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
	     super.onCreate(savedInstanceState);
	     setContentView(R.layout.main);
	     
	     // create a instance of SQLite Database
	     dataBaseAdapter =new DataBaseAdapter(this);
	     dataBaseAdapter = dataBaseAdapter.open();
	     
	     // Get The Refference Of Buttons
	     btnSignIn=(Button)findViewById(R.id.buttonSignIN);
	     btnSignUp=(Button)findViewById(R.id.buttonSignUP);
			
	    // Set OnClick Listener on SignUp button 
	    btnSignUp.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			/// Create Intent for SignUpActivity  and Start The Activity
			Intent intentSignUP=new Intent(getApplicationContext(),SignUPActivity.class);
			startActivity(intentSignUP);
			}
		});
	}
	// Methos to handleClick Event of Sign In Button
	public void signIn(View V)
	   {
			final Dialog dialog = new Dialog(HomeActivity.this);
			dialog.setContentView(R.layout.login);
		    dialog.setTitle("Login");
	
		    // get the Refferences of views
		    final  EditText editTextUserName=(EditText)dialog.findViewById(R.id.editTextUserNameToLogin);
		    final  EditText editTextPassword=(EditText)dialog.findViewById(R.id.editTextPasswordToLogin);
		    
			Button btnSignIn=(Button)dialog.findViewById(R.id.buttonSignIn);
				
			// Set On ClickListener
			btnSignIn.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					// get The User name and Password
					String userName=editTextUserName.getText().toString();
					String password=editTextPassword.getText().toString();
					
					// fetch the Password form database for respective user name
					String storedPassword= dataBaseAdapter.getSinlgeEntry(userName);
					
					// check if the Stored password matches with  Password entered by user
					if(password.equals(storedPassword))
					{
						Toast.makeText(HomeActivity.this, "Congrats: Login Successfull", Toast.LENGTH_LONG).show();
						dialog.dismiss();
						/// Create Intent for SignUpActivity  and Start The Activity
						Intent intentMenu=new Intent(getApplicationContext(),MenuTabsActivity.class);
						startActivity(intentMenu);
					}
					else
					{
						Toast.makeText(HomeActivity.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
					}
				}
			});
			
			dialog.show();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	    // Close The Database
		dataBaseAdapter.close();
	}
}*/
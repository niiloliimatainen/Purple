package com.example.purple;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

//Registration screen

public class Activity4 extends AppCompatActivity {
    private EditText getFirstname, getLastname, getEmail, getPhoneNumber, getPassword, getPassword2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);

        getFirstname = findViewById(R.id.fname);
        getLastname = findViewById(R.id.lname);
        getEmail = findViewById(R.id.email);
        getPhoneNumber = findViewById(R.id.pnumber);
        getPassword = findViewById(R.id.password);
        getPassword2 = findViewById(R.id.password2);

    }
}

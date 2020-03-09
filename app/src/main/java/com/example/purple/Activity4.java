package com.example.purple;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

//Registration screen

public class Activity4 extends AppCompatActivity {
    private EditText getFirstname, getLastname, getEmail, getPassword, getPassword2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);
    }
}

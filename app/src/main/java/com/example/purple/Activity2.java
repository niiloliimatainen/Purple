package com.example.purple;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class Activity2 extends AppCompatActivity {
    private EditText getUsername;
    private EditText getPassword;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        getUsername = findViewById(R.id.username);
        getPassword = findViewById(R.id.password);


    }
}

package com.example.purple;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

// Logging in page

public class Activity2 extends AppCompatActivity {
    private EditText getUsername, getPassword;
    private String username, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        getUsername = findViewById(R.id.username);
        getPassword = findViewById(R.id.email);

    }


    public void submitLoginInfo(View v) {
        username = getUsername.getText().toString();
        password = getPassword.getText().toString();

        Intent intent = new Intent(this, Activity3.class);

        startActivity(intent);
    }

    public void doRegistration(View v) {
        Intent intent2 = new Intent(this, Activity4.class);
        startActivity(intent2);
    }


}

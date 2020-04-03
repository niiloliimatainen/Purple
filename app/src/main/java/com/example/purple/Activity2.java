package com.example.purple;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

// Logging in page

public class Activity2 extends AppCompatActivity {
    private EditText getEmail, getPassword;
    private String email, password;
    private Bank bank = Bank.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        getEmail = findViewById(R.id.email);
        getPassword = findViewById(R.id.password);

    }


    public void submitLoginInfo(View v) {
        email = getEmail.getText().toString();
        password = getPassword.getText().toString();

        if ((email.isEmpty()) || (password.isEmpty())) {
            Toast.makeText(getApplicationContext(), "Invalid Email address or Password!", Toast.LENGTH_SHORT).show();

        } else {
            if (bank.login(email, password,this) == 1) {
                Toast.makeText(getApplicationContext(), "Logged in!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, Activity3.class);
                startActivity(intent);
            } else {
                getEmail.setText("");
                getPassword.setText("");
                Toast.makeText(getApplicationContext(), "Invalid Email address or Password!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void doRegistration(View v) {
        Intent intent2 = new Intent(this, Activity4.class);
        startActivity(intent2);
    }


}

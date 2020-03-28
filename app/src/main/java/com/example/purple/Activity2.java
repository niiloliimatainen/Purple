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
            Toast.makeText(getApplicationContext(), "Invalid username or password. Try again!", Toast.LENGTH_LONG).show();

        } else {
            Intent intent = new Intent(this, Activity3.class);
            startActivity(intent);
    }
}

    public void doRegistration(View v) {
        Intent intent2 = new Intent(this, Activity4.class);
        startActivity(intent2);
    }


}

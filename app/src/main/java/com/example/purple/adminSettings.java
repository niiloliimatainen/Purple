package com.example.purple;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class adminSettings extends AppCompatActivity {
    private Bank bank = Bank.getInstance();
    private StringBuilder sb = new StringBuilder();
    private EditText choice;
    private ArrayList<String> userList = bank.getAllUsers();
    private String userValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_settings);
        TextView users = findViewById(R.id.users);
        choice = findViewById(R.id.choice);
        bank.resetCurrentUser();

        for (int i = 0; i < userList.size(); i++) {
            sb.append(userList.get(i) + " \n");
        }
        users.setText(sb.toString());
    }


    public void getChoice(View v) {
        if (choice.getText() == null) {
            Toast.makeText(getApplicationContext(), "Invalid value!", Toast.LENGTH_SHORT).show();
        } else {
            userValue = choice.getText().toString();
            int finalValue = Integer.parseInt(userValue);
            for (int i = 0; i < userList.size(); i++) {
                if ((i + 1) == finalValue) {
                    bank.setCurrenUser(i + 1);
                }
            }
            Toast.makeText(getApplicationContext(), "Return to profile to change account you want to modify!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(adminSettings.this, main_one.class);
            startActivity(intent);
        }
    }


    public void returnButton(View v) {
        Intent intent = new Intent(adminSettings.this, main_one.class);
        startActivity(intent);
    }


    public void deleteAll(View v) {
        bank.deleteAll();
        Toast.makeText(getApplicationContext(), "BOOM! All accounts deleted", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(adminSettings.this, main_one.class);
        startActivity(intent);
    }


    public void deleteUser(View v) {
        if (choice.getText() == null) {
            Toast.makeText(getApplicationContext(), "Invalid value!", Toast.LENGTH_SHORT).show();
        } else {
            userValue = choice.getText().toString();
            int finalValue = Integer.parseInt(userValue);
        }
    }



}
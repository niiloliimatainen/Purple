package com.example.purple;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
    private ArrayList<String> userList;
    private String userValue;
    private TextView users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_settings);
        users = findViewById(R.id.users);
        choice = findViewById(R.id.choice);
        bank.resetCurrentUser();
        userList = bank.getAllUsers();
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
                if (i == finalValue) {
                    bank.setCurrenUser(i);
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
        AlertDialog.Builder dialog= new AlertDialog.Builder(this);
        ArrayList<String> accountList = bank.getAccounts();
        dialog.setTitle("Are you sure?");

        dialog.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                bank.deleteAll();
                Toast.makeText(getApplicationContext(), "BOOM! All users deleted", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(adminSettings.this, main_one.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });

        dialog.setPositiveButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

            dialog.show();
    }


    public void deleteUser(View v) {
        if (choice.getText() == null) {
            Toast.makeText(getApplicationContext(), "Invalid value!", Toast.LENGTH_SHORT).show();
        } else {
            AlertDialog.Builder dialog= new AlertDialog.Builder(this);
            ArrayList<String> accountList = bank.getAccounts();
            dialog.setTitle("Are you sure?");

            dialog.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    userValue = choice.getText().toString();
                    int finalValue = Integer.parseInt(userValue);
                    bank.deleteUser(finalValue);
                    Toast.makeText(getApplicationContext(), "User deleted!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(adminSettings.this, main_one.class);
                    startActivity(intent);
                    dialog.dismiss();
                }
            });

            dialog.setPositiveButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            dialog.show();

        }
    }
}
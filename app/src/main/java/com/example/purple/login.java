package com.example.purple;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

// Logging in page

public class login extends AppCompatActivity {
    private EditText getEmail, getPassword;
    private String email, password;
    private Bank bank = Bank.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        getEmail = findViewById(R.id.email);
        getPassword = findViewById(R.id.password);

    }


    public void submitLoginInfo(View v) throws Exception {
        email = getEmail.getText().toString();
        password = getPassword.getText().toString();
        //numberHandler.tester();
        if ((email.isEmpty()) || (password.isEmpty())){
            Toast.makeText(getApplicationContext(), "Invalid Email address or Password!", Toast.LENGTH_SHORT).show();

        } else {
            if (bank.login(email, password,this) == 1) {
                confirmationPopup();

            } else {
                getEmail.setText("");
                getPassword.setText("");
                Toast.makeText(getApplicationContext(), "Invalid Email address or Password!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void doRegistration(View v) {
        Intent intent2 = new Intent(this, register.class);
        startActivity(intent2);
    }

    public void confirmationPopup(){
        final String code = numberHandler.setVerificationNumber();
         AlertDialog.Builder dialog= new AlertDialog.Builder(this);
        dialog.setTitle("Confirmation");
        dialog.setMessage("Input the given code below\nCode: "+ code);
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        dialog.setView(input);

        dialog.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(code.equals(input.getText().toString())) {
                    if (bank.isUserAdmin()) {
                        Toast.makeText(getApplicationContext(), "You're logged in as admin!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Confirmation ok, logging in...", Toast.LENGTH_SHORT).show();
                    }

                    dialog.dismiss();
                    Intent intent;
                    intent = new Intent(login.this, main_one.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(), "Incorrect code, try again!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.show();
    }
}

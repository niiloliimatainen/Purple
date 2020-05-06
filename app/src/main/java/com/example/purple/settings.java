package com.example.purple;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class settings extends AppCompatActivity {
    private EditText editFirstName, editLastName, editEmail, editPhoneNumber, editPassword, confirmPassword;
    private String firstName, lastName, email, phoneNumber, password;
    private Bank bank = Bank.getInstance();
    private Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        editFirstName = findViewById(R.id.fname);
        editLastName = findViewById(R.id.lname);
        editEmail = findViewById(R.id.email);
        editPhoneNumber = findViewById(R.id.pnumber);
        editPassword = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.password2);

        firstName = bank.getUserInfo(1);
        lastName = bank.getUserInfo(2);
        email = bank.getUserInfo(3);
        phoneNumber = bank.getUserInfo(4);
        password = bank.getUserInfo(5);


        editFirstName.setText(firstName);
        editLastName.setText(lastName);
        editEmail.setText(email);
        editPhoneNumber.setText(phoneNumber);

    }


    public void editInfo(View v) {
        Intent intent = new Intent(settings.this, main_one.class);
        boolean ok = true;

        if ((firstName.equals(editFirstName.getText().toString())) && (lastName.equals(editLastName.getText().toString())) && (email.equals(editEmail.getText().toString())) && (phoneNumber.equals(editPhoneNumber.getText().toString())) && editPassword.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "No changes were made!", Toast.LENGTH_SHORT).show();
            startActivity(intent);

        } else {
            if (!(firstName.equals(editFirstName.getText().toString()))) {
                firstName = editFirstName.getText().toString();
                bank.editUserInfo(firstName, 1, context);
            }

            if (!(lastName.equals(editLastName.getText().toString()))) {
                lastName = editLastName.getText().toString();
                bank.editUserInfo(lastName, 2, context);
            }

            if (!(email.equals(editEmail.getText().toString()))) {
                email = editEmail.getText().toString();
                bank.editUserInfo(email, 3, context);
            }

            if (!(phoneNumber.equals(editPhoneNumber.getText().toString()))) {
                phoneNumber = editPhoneNumber.getText().toString();
                bank.editUserInfo(phoneNumber, 4, context);
            }

            if ((!(editPassword.getText().toString().isEmpty())) || (!(confirmPassword.getText().toString().isEmpty()))) {
                password = editPassword.getText().toString();
                ok = false;
                System.out.println(ok);

                Pattern pattern1 = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
                Pattern pattern2 = Pattern.compile("[a-z0-9]");
                Pattern pattern3 = Pattern.compile("[A-Z]");
                Pattern pattern4 = Pattern.compile("[a-z]");
                Matcher matcher1 = pattern1.matcher(password);
                Matcher matcher2 = pattern2.matcher(password);
                Matcher matcher3 = pattern3.matcher(password);
                Matcher matcher4 = pattern4.matcher(password);
                boolean flag1 = matcher1.find();
                boolean flag2 = matcher2.find();
                boolean flag3 = matcher3.find();
                boolean flag4 = matcher4.find();

                if (password.length() < 12) {
                    Toast.makeText(getApplicationContext(), "Password has to be at least 12 characters!", Toast.LENGTH_SHORT).show();
                } else if (flag1 && flag2 && flag3 && flag4) {
                    if ((confirmPassword.getText().toString()).equals(password)) {
                        //Tämä on vittu kohta
                        bank.editUserInfo(password, 5, context);
                        ok = true;
                    } else {
                        Toast.makeText(getApplicationContext(), "Passwords don't match!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Password must at least contain number, capital letter, small letter and special character!", Toast.LENGTH_SHORT).show();
                }
            }
            if (ok == true) {
                Toast.makeText(getApplicationContext(), "Changes were made!", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        }
    }
}

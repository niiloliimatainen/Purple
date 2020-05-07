package com.example.purple;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//Registration screen

public class register extends AppCompatActivity {
    private EditText getFirstname, getLastname, getEmail, getPhoneNumber, getPassword, getPassword2;
    private String firstName, lastName, email, phoneNumber, password1, password2;
    private boolean ok = false;
    private Bank bank = Bank.getInstance();
    private numberHandler nh = new numberHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        getFirstname = findViewById(R.id.fname);
        getLastname = findViewById(R.id.lname);
        getEmail = findViewById(R.id.email);
        getPhoneNumber = findViewById(R.id.pnumber);
        getPassword = findViewById(R.id.password);
        getPassword2 = findViewById(R.id.password2);

    }


    public void registeration(View v) {
        firstName = getFirstname.getText().toString();
        lastName = getLastname.getText().toString();
        email = getEmail.getText().toString();
        phoneNumber = getPhoneNumber.getText().toString();
        password1 = getPassword.getText().toString();
        password2 = getPassword2.getText().toString();

        Pattern pattern1 = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Pattern pattern2 = Pattern.compile("[a-z0-9]");
        Pattern pattern3 = Pattern.compile("[A-Z]");
        Pattern pattern4 = Pattern.compile("[a-z]");
        Matcher matcher1 = pattern1.matcher(password1);
        Matcher matcher2 = pattern2.matcher(password1);
        Matcher matcher3 = pattern3.matcher(password1);
        Matcher matcher4 = pattern4.matcher(password1);
        boolean flag1 = matcher1.find();
        boolean flag2 = matcher2.find();
        boolean flag3 = matcher3.find();
        boolean flag4 = matcher4.find();


        //tässä viel vähän säätämistä ja tarkistamista ton salasanan checkin kanssa//
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || phoneNumber.isEmpty() || password1.isEmpty() || password2.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Fill all of the user information first!", Toast.LENGTH_SHORT).show();

        }else if (password1.length() < 12) {
            Toast.makeText(getApplicationContext(), "Password has to be at least 12 characters!", Toast.LENGTH_SHORT).show();

        } else if (flag1 && flag2 && flag3 && flag4) {
            if (password2.equals(password1)) {

                ok = true;
            } else {
                Toast.makeText(getApplicationContext(), "Passwords don't match!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Password must at least contain number, capital letter, small letter and special character!", Toast.LENGTH_SHORT).show();
        }

        if (ok) {
            String hashedPw;
            String tempSalt;
            byte[] salt = nh.getSalt();
            tempSalt = Arrays.toString(salt);
            hashedPw = nh.hasher(password1, tempSalt.getBytes());

            int newUser = bank.addUser(firstName, lastName, email, phoneNumber, hashedPw, tempSalt, this);

            if (newUser == 1) {
                Toast.makeText(getApplicationContext(), "Registration complete!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, login.class);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "You already have an account!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

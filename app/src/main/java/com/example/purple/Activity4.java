package com.example.purple;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//Registration screen

public class Activity4 extends AppCompatActivity {
    private EditText getFirstname, getLastname, getEmail, getPhoneNumber, getPassword, getPassword2;
    private String firstName, lastName, email, phoneNumber, password1, password2;
    private boolean ok = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);
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

        Pattern pattern1 = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Pattern pattern2 = Pattern.compile("[a-z0-9]");
        Pattern pattern3 = Pattern.compile("[A-Z]");
        Matcher matcher1 = pattern1.matcher(password1);
        Matcher matcher2 = pattern2.matcher(password1);
        Matcher matcher3 = pattern3.matcher(password1);
        boolean flag1 = matcher1.find();
        boolean flag2 = matcher2.find();
        boolean flag3 = matcher3.find();
        System.out.println(flag1 +" " + flag2 +" "+ flag3);

        //tässä viel vähän säätämistä ja tarkistamista ton salasanan checkin kanssa//



        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || phoneNumber.isEmpty() || password1.isEmpty() || password2.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Fill all of the user information first!", Toast.LENGTH_LONG).show();
        } else if (password1.length() < 12) {
            Toast.makeText(getApplicationContext(), "Password has to be at least 12 characters!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Password has to contain number, small and capital character and special character!", Toast.LENGTH_LONG).show();
        }
        if (flag1 && flag2 && flag3) {
            Toast.makeText(getApplicationContext(), "Password ok!", Toast.LENGTH_LONG).show();
            ok = true;
        } else {
            ok = false;
        }
        password2 = getPassword2.getText().toString();
        if (password2.equals(password1)) {
            //tähän voisi laittaa sellasen hienon vihreen valon et näkee niitten salasanojen olevan ok//
            ok = true;
        } else {
            Toast.makeText(getApplicationContext(), "Passwords don't match!", Toast.LENGTH_LONG).show();
            ok = false;
        }
        if (ok) {
            Intent intent = new Intent(this, Activity2.class);
            startActivity(intent);
        }
    }
}

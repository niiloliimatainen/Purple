package com.example.purple;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class bankStatement1 extends AppCompatActivity {
    private Bank bank = Bank.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bank_statement1);

        TextView statement = findViewById(R.id.statement);





    }
}

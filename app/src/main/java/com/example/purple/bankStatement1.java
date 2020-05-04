package com.example.purple;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class bankStatement1 extends AppCompatActivity {
    private Bank bank = Bank.getInstance();
    private StringBuilder sb = new StringBuilder();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bank_statement1);

        TextView statement = findViewById(R.id.statement);
        TextView title = findViewById(R.id.title);
        title.setText(bank.getAccountNumber(1));
        ArrayList<String> list = databaseConnector.readBankStatement(this, bank.getAccountNumber(1));

        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i) + " \n");
        }

        statement.setText(sb.toString());
    }
}

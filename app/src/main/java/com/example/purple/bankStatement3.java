package com.example.purple;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class bankStatement3 extends AppCompatActivity {

    private Bank bank = Bank.getInstance();
    private StringBuilder sb = new StringBuilder();
    private Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bank_statement3);

        TextView statement = findViewById(R.id.statement);
        TextView title = findViewById(R.id.title);
        title.setText(bank.getAccountNumber(3));
        ArrayList<String> list = databaseConnector.readBankStatement(this, bank.getAccountNumber(3));

        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i) + " \n");
        }

        statement.setText(sb.toString());
    }

    public void editAccount(View v) {
        Intent intent = new Intent(bankStatement3.this, accountSettings.class);
        Bundle b = new Bundle();
        b.putInt("key", 3);
        intent.putExtras(b);
        startActivity(intent);
        finish();
    }





    public void deleteAccount(View v) {
        bank.deleteAccount(3, context);
        Toast.makeText(getApplicationContext(), "Account deleted!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(bankStatement3.this, main_one.class);
        startActivity(intent);
    }


    public void returnButton(View v) {
        Intent intent = new Intent(bankStatement3.this, main_one.class);
        startActivity(intent);
    }



}

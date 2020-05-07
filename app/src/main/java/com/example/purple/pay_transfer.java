package com.example.purple;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class pay_transfer extends AppCompatActivity {
    private Bank bank = Bank.getInstance();
    private EditText inputAccountToPay, payAmount;
    private Context context = this;
    int flag = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_transfer);
        payAmount = findViewById(R.id.payamount);
        inputAccountToPay = findViewById(R.id.inputAccountToPay);

        ImageButton showInputButton = findViewById(R.id.payment);
        ImageButton showSpinnerButton = findViewById(R.id.selfTransfer);
        final TextView paymentInfo = findViewById(R.id.paymentInfoTW);
        final Spinner chooseAccToPay = findViewById(R.id.chooseAccToPay);


        showInputButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputAccountToPay.setVisibility(View.VISIBLE);
                chooseAccToPay.setVisibility(View.GONE);
                paymentInfo.setVisibility(View.VISIBLE);
                paymentInfo.setText("Input the account number you want to make payment");
                flag = 2;
            }
        });
        showSpinnerButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                inputAccountToPay.setVisibility(View.GONE);
                chooseAccToPay.setVisibility(View.VISIBLE);
                paymentInfo.setVisibility(View.VISIBLE);
                paymentInfo.setText("Choose the account you want to transfer to");
                flag = 1;
            }
        });

        Spinner chooseAcc = findViewById(R.id.chooseAcc);
        ArrayList<String> accountList = bank.getAccounts();
        for (int i = 0; accountList.size() > i; i++) {
           accountList.set(i, accountList.get(i) + " " + bank.getAccountsMoneyAmount(i + 1) + "€");

        }
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, accountList);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chooseAcc.setAdapter(spinnerArrayAdapter);
        chooseAccToPay.setAdapter(spinnerArrayAdapter);
    }


    public void returnMethod(View v){
        Intent intent = new Intent(pay_transfer.this, main_one.class);
        startActivity(intent);
    }


    public void makeTransaction(View v){
        Spinner chooseAcc = findViewById(R.id.chooseAcc);
        Spinner chooseAccToPay = findViewById(R.id.chooseAccToPay);
        chooseAcc.getSelectedItemPosition();
        chooseAccToPay.getSelectedItemPosition();
        double amount = Double.parseDouble(payAmount.getText().toString());
        if (flag == 1){
            if(bank.selfTransfer(chooseAcc.getSelectedItemPosition() + 1, chooseAccToPay.getSelectedItemPosition() + 1, amount, context) == 1) {
                Toast.makeText(getApplicationContext(), "Payment completed!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(pay_transfer.this, main_one.class);
                startActivity(intent);
            } else if(bank.selfTransfer(chooseAcc.getSelectedItemPosition() + 1, chooseAccToPay.getSelectedItemPosition() + 1, amount, context) == 2) {
                Toast.makeText(getApplicationContext(), "You're trying to pay with savings account!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Not enough money!", Toast.LENGTH_SHORT).show();
            }


        }else if(flag == 2){
            if (bank.transferMoney(inputAccountToPay.getText().toString(), chooseAcc.getSelectedItemPosition() + 1, amount, context) == 1) {
                Toast.makeText(getApplicationContext(), "Payment completed!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(pay_transfer.this, main_one.class);
                startActivity(intent);

            } else if (bank.transferMoney(inputAccountToPay.getText().toString(), chooseAcc.getSelectedItemPosition() + 1, amount, context) == 2){
                Toast.makeText(getApplicationContext(), "You're trying to pay with savings account!", Toast.LENGTH_SHORT).show();

            } else if (bank.transferMoney(inputAccountToPay.getText().toString(), chooseAcc.getSelectedItemPosition() + 1, amount, context) == 3) {
                Toast.makeText(getApplicationContext(), "Not enough money!", Toast.LENGTH_SHORT).show();

            }else if (bank.transferMoney(inputAccountToPay.getText().toString(), chooseAcc.getSelectedItemPosition() + 1, amount, context) == 4) {
                Toast.makeText(getApplicationContext(), "Invalid receiving account!", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getApplicationContext(), "Choose an account first!", Toast.LENGTH_SHORT).show();
        }
    }
}





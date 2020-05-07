package com.example.purple;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

// view after user clicks add card button in main screen

public class addCardActivity extends AppCompatActivity {
    private Bank bank = Bank.getInstance();
    private EditText creditInput;
    private boolean isCredit = false;
    private int accIndex = 0;
    private Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);


        creditInput = findViewById(R.id.creditInput);
        final ImageButton debit = findViewById(R.id.debitB);
        final ImageButton credit = findViewById(R.id.creditB);
        final ImageButton addCard = findViewById(R.id.addCardB);
        final ImageButton setCredit = findViewById(R.id.setCreditLimit);
        final Button acc1B = findViewById(R.id.acc1B);
        final Button acc2B = findViewById(R.id.acc2B);
        final Button acc3B = findViewById(R.id.acc3B);

        acc1B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                credit.setVisibility(View.VISIBLE);
                debit.setVisibility(View.VISIBLE);
                accIndex = 1;
            }
        });
        acc2B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                credit.setVisibility(View.VISIBLE);
                debit.setVisibility(View.VISIBLE);
                accIndex = 2;
            }
        });
        acc3B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                credit.setVisibility(View.VISIBLE);
                debit.setVisibility(View.VISIBLE);
                accIndex = 3;
            }
        });
       debit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(accIndex == 0){
                   Toast.makeText(getApplicationContext(), "Choose account first!", Toast.LENGTH_SHORT).show();
               }else {
                   creditInput.setVisibility(View.GONE);
                   setCredit.setVisibility(View.GONE);
                   addCard.setVisibility(View.VISIBLE);
                   isCredit = false;
               }
           }
       });
       credit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(accIndex == 0){
                   Toast.makeText(getApplicationContext(), "Choose account first!", Toast.LENGTH_SHORT).show();
               }else {
                   creditInput.setVisibility(View.VISIBLE);
                   addCard.setVisibility(View.GONE);
                   setCredit.setVisibility(View.VISIBLE);
                   isCredit = true;
               }
           }
       });
       setCredit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               addCard.setVisibility(View.VISIBLE);
           }
       });

        int counter = 0;
        String account1 = null, account2 = null, account3 = null;
        ArrayList<String> accountList = bank.getAccounts();

        for(int i = 0; accountList.size()>i;i++){
            counter += 1;
            if(bank.getAccountsPayPossibility(i + 1).equals("Regular")){

                if (counter == 1) {
                    account1 = (i + 1) + "." + accountList.get(i);
                    acc1B.setVisibility(View.VISIBLE);
                    acc1B.setText(account1);

                } else if (counter == 2) {
                    account2 = (i + 1) + "." + accountList.get(i);
                    acc2B.setVisibility(View.VISIBLE);
                    acc2B.setText(account2);

                } else if (counter == 3) {
                    account3 = (i + 1) + "." + accountList.get(i);
                    acc3B.setVisibility(View.VISIBLE);
                    acc3B.setText(account3);
                }
            }
        }
    }
    // method to add card with account type/amount check
    public void addCard(View v){
        Intent intent = new Intent(addCardActivity.this, main_one.class );
        if (isCredit){
            if (creditInput.getText() == null || creditInput.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(), "Incorrect amount!", Toast.LENGTH_SHORT).show();
            } else {
                Double creditLimit = Double.parseDouble(creditInput.getText().toString());
                System.out.println(accIndex + "credit");
                if (bank.addCreditCard(accIndex, creditLimit, context)) {
                    Toast.makeText(getApplicationContext(), "New card added!", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "You already have card in this account!", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
            }

        }else if (!isCredit){
            System.out.println(accIndex + "debit");
            if(bank.addCard(accIndex, context)) {
                Toast.makeText(getApplicationContext(), "New card added!", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }else{
                Toast.makeText(getApplicationContext(), "You already have card in this account!", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
                }


    }



}

package com.example.purple;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.content.DialogInterface;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;


public class main_one extends AppCompatActivity {
    private GestureDetectorCompat gesture;
    private TextView moneyAmount, accounts, accountCounter;
    private Bank bank = Bank.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_one);
        gesture = new GestureDetectorCompat(this, new LearnGesture());
        moneyAmount = findViewById(R.id.allmoney);
        accounts = findViewById(R.id.accounts);
        accountCounter = findViewById(R.id.accountCounter);


    }

    //Ehkä viel se countteri
    protected void onStart() {
        super.onStart();
        getDelegate().onStart();
        ArrayList<String> accountList = bank.getAccounts();
        if (accountList.isEmpty()) {
            moneyAmount.setText("--");
        } else {
            moneyAmount.setText(bank.getMoneyAmount() + "€");
            String text = "";
            int counter = 0;
            for (int i = 0; accountList.size() > i; i++) {
                text = text + ("\n" + accountList.get(i) + " " + bank.getAccountsMoneyAmount(i) + "€");
                counter += 1;
            }
            accountCounter.setText(counter + "/3");
            accounts.setText(text);
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.gesture.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    class LearnGesture extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2, float x1, float y1){
            if(event1.getX() > event2.getX()){
                Intent intent1 = new Intent(main_one.this, main_two.class);
                finish();
                startActivity(intent1);
            }else if(event2.getX() < event1.getX()){

            }
            return true;
        }
    }


    public void addAccountPopup(View v){
        AlertDialog.Builder dialog= new AlertDialog.Builder(this);
        dialog.setTitle("Choose the account type");

        dialog.setNeutralButton("Regular", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //TÄHÄN SE AKKOUNTTIJUTTU
                if (bank.addAccount(1)) {
                    ArrayList<String> accountList = bank.getAccounts();
                    Toast.makeText(getApplicationContext(), "New account created!", Toast.LENGTH_SHORT).show();
                    moneyAmount.setText(String.format(Locale.GERMANY, "%.2f€", bank.getMoneyAmount()));
                    String text = "";
                    int counter = 0;
                    for (int i = 0; accountList.size() > i; i++) {
                        text = text + ("\n" + accountList.get(i));
                        counter += 1;
                    }
                    accounts.setText(text);
                    accountCounter.setText(counter + "/3");
                } else {
                    Toast.makeText(getApplicationContext(), "Max limit of accounts!", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });



        dialog.setPositiveButton("Savings", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which){
                //TÄHÄN SE AKKOUNTTIJUTTU
                if (bank.addAccount(0)) {
                    ArrayList<String> accountList = bank.getAccounts();
                    Toast.makeText(getApplicationContext(), "New account created!", Toast.LENGTH_SHORT).show();
                    moneyAmount.setText(String.format(Locale.GERMANY, "%.2f€", bank.getMoneyAmount()));
                    String text = "";
                    int counter = 0;
                    for (int i = 0; accountList.size() > i; i++) {
                        text = text + ("\n" + accountList.get(i));
                        counter += 1;
                    }
                    accounts.setText(text);
                    accountCounter.setText(counter + "/3");
                } else {
                    Toast.makeText(getApplicationContext(), "Max limit of accounts!", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }



    public void addCardPopup(View v){
        AlertDialog.Builder dialog= new AlertDialog.Builder(this);
        dialog.setTitle("Choose the card type");

        dialog.setNeutralButton("Debit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //TÄHÄN SE AKKOUNTTIJUTTU
                Toast.makeText(getApplicationContext(), "New card added!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        dialog.setPositiveButton("Credit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which){
                //TÄHÄN SE AKKOUNTTIJUTTU TAAS
                Toast.makeText(getApplicationContext(), "New card added!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void payButton(View v){
        Intent intent = new Intent(main_one.this, pay_transfer.class);
        startActivity(intent);
    }


    public void addMoney(View v) {
        ArrayList<String> accountList = bank.getAccounts();
        bank.addMoney(1, 200);
        moneyAmount.setText(bank.getMoneyAmount() + "€");
        String text = "";
        for (int i = 0; accountList.size() > i; i++) {
            text = text + ("\n" + accountList.get(i) + " " + bank.getAccountsMoneyAmount(i) + "€");
        }
        accounts.setText(text);
    }



}




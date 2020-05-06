package com.example.purple;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class cardSettings extends AppCompatActivity {
    private int card;
    private TextView title, raiselimit, isCredit, PIN, creditLimit;
    private Bank bank = Bank.getInstance();
    private EditText getRaiseLimit;
    private Context context = this;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_settings);

        title = findViewById(R.id.title);
        raiselimit = findViewById(R.id.raiselimit);
        isCredit = findViewById(R.id.isCredit);
        PIN = findViewById(R.id.pin);
        creditLimit = findViewById(R.id.creditlimit);


        Bundle b = getIntent().getExtras();
        if (b != null) {
            card = b.getInt("key");
            if (card == 1) {
                title.setText("Card " + 1 + ": " + bank.getCardNumber(1));
                raiselimit.setText("Current raiselimit: " + bank.getCardRaiseLimit(1));
                isCredit.setText(("Current type: " + bank.isCardCreditCard(1)));
                PIN.setText("Current PIN-Code: " + bank.getCardPin(1));
                creditLimit.setText("(only credit cards)Current creditlimit: " + bank.getCreditLimit(1));


            } else if (card == 2) {
                title.setText("Card " + 2 + ": " + bank.getCardNumber(2));;
                raiselimit.setText("Current raiselimit: " + bank.getCardRaiseLimit(2));
                isCredit.setText(("Current type: " + bank.isCardCreditCard(2)));
                PIN.setText("Current PIN-Code: " + bank.getCardPin(2));
                creditLimit.setText("(only credit cards)Current creditlimit: " + bank.getCreditLimit(2));

            } else if (card == 3) {
                title.setText("Card " + 3 + ": " + bank.getCardNumber(3));;
                raiselimit.setText("Current raiselimit: " + bank.getCardRaiseLimit(3));
                isCredit.setText(("Current type: " + bank.isCardCreditCard(3)));
                PIN.setText("Current PIN-Code: " + bank.getCardPin(3));
                creditLimit.setText("(only credit cards)Current creditlimit: " + bank.getCreditLimit(3));

            }
        }
    }


    public void returnButton(View v) {
        Intent intent = new Intent(cardSettings.this, main_one.class);
        startActivity(intent);
    }




}

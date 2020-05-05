package com.example.purple;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.content.DialogInterface;
import android.text.InputType;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;


public class main_one extends AppCompatActivity {
    private GestureDetectorCompat gesture;
    private TextView moneyAmount, accounts, accountCounter, cards;
    private Bank bank = Bank.getInstance();
    private double creditLimit;
    private String accToAddCard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_one);
        gesture = new GestureDetectorCompat(this, new LearnGesture());
        moneyAmount = findViewById(R.id.allmoney);
        accounts = findViewById(R.id.accounts);
        accountCounter = findViewById(R.id.accountCounter);
        cards = findViewById(R.id.cards);

    }


    protected void onStart() {
        super.onStart();
        getDelegate().onStart();
        ArrayList<String> accountList = bank.getAccounts();
        ArrayList<String> cardsList = bank.getCards();

        if (accountList.isEmpty()) {
            moneyAmount.setText("--");
        } else {
            moneyAmount.setText(bank.getMoneyAmount() + "€");
            String text = "";
            String cardText = "";
            int counter = 0;

            for (int i = 0; accountList.size() > i; i++) {
                text = text + ("\n" + (i + 1) + ". " + bank.getAccountsPayPossibility(i + 1) + " | " + accountList.get(i) + " | " + bank.getAccountsMoneyAmount(i + 1) + "€");
                counter += 1;
            }
            accountCounter.setText(counter + "/3");
            accounts.setText(text);

            for(int x = 0; cardsList.size() > x; x++){
                cardText = cardText + ("\n" + (x + 1) + "." + bank.isCardCreditCard(x) + " | " + cardsList.get(x));
            }
            cards.setText(cardText);
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

                if (bank.addAccount(1)) {
                    ArrayList<String> accountList = bank.getAccounts();
                    Toast.makeText(getApplicationContext(), "New account created!", Toast.LENGTH_SHORT).show();
                    moneyAmount.setText(String.format(Locale.GERMANY, "%.2f€", bank.getMoneyAmount()));
                    String text = "";
                    int counter = 0;
                    for (int i = 0; accountList.size() > i; i++) {
                        text = text + ("\n" + (i + 1) + ". " + bank.getAccountsPayPossibility(i + 1) + " | " + accountList.get(i) + " | " + bank.getAccountsMoneyAmount(i + 1) + "€");
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

                if (bank.addAccount(0)) {
                    ArrayList<String> accountList = bank.getAccounts();
                    Toast.makeText(getApplicationContext(), "New account created!", Toast.LENGTH_SHORT).show();
                    moneyAmount.setText(String.format(Locale.GERMANY, "%.2f€", bank.getMoneyAmount()));
                    String text = "";
                    int counter = 0;
                    for (int i = 0; accountList.size() > i; i++) {
                        text = text + ("\n" + bank.getAccountsPayPossibility(i + 1) + " | " + accountList.get(i) + " | " + bank.getAccountsMoneyAmount(i + 1) + "€");
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

    public void alreadyCardToast(){
        Toast.makeText(getApplicationContext(), "You already have card in this account!", Toast.LENGTH_SHORT).show();
    }

    public void addCardPopup(int index){
        AlertDialog.Builder dialog= new AlertDialog.Builder(this);
        dialog.setTitle("Choose the card type");
        //en keksiny kätevämpää tapaa saada toi indexi noihi sisää ku pysty vaihtaa innerclassin muuttujaa
        if (index == 1) {
            dialog.setNeutralButton("Debit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(bank.addCard(1)) {
                        Toast.makeText(getApplicationContext(), "New card added!", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }else{
                        dialog.dismiss();
                        alreadyCardToast();
                    }
                }
            });
        }
        dialog.setPositiveButton("Credit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which){
                creditPopup(1);
                dialog.dismiss();
            }
        });
        dialog.show();

        if (index == 2) {
            dialog.setNeutralButton("Debit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(bank.addCard(2)) {
                        Toast.makeText(getApplicationContext(), "New card added!", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }else{
                        dialog.dismiss();
                        alreadyCardToast();
                    }
                }
            });
        }
        dialog.setPositiveButton("Credit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which){
                creditPopup(2);
                dialog.dismiss();
            }
        });
        dialog.show();

        if (index == 3) {
            dialog.setNeutralButton("Debit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(bank.addCard(3)) {
                        Toast.makeText(getApplicationContext(), "New card added!", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }else{
                        dialog.dismiss();
                        alreadyCardToast();
                    }
                }
            });
        }
        dialog.setPositiveButton("Credit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which){
                creditPopup(3);
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    public void whichAccountToAddPopup(View v){
        int counter = 0;
        String account1 = null, account2 = null, account3 = null;
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        ArrayList<String> accountList = bank.getAccounts();

        for(int i = 0; accountList.size()>i;i++){
            counter += 1;
            if(bank.getAccountsPayPossibility(i + 1).equals("Regular")){

                if (counter == 1) {
                    account1 = (i + 1) + "." + accountList.get(i);

                } else if (counter == 2) {
                    account2 = (i + 1) + "." + accountList.get(i);

                } else if (counter == 3) {
                    account3 = (i + 1) + "." + accountList.get(i);

                }
            }
        }

        if(counter==0){
            dialog.setTitle("No accounts!");
            dialog.setMessage("You need to create account first!");
            dialog.setNegativeButton("Cancel",new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }

        dialog.setTitle("Choose an account you want to add card");
        dialog.setPositiveButton(account1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                addCardPopup(1);
                dialog.dismiss();
            }
        });

        if((counter==1)){
            dialog.show();
        }

        dialog.setNeutralButton(account2, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which){
                addCardPopup(2);
                dialog.dismiss();
            }
        });

        if(counter==2){
            dialog.show();
        }

        dialog.setNegativeButton(account3, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                addCardPopup(3);
                dialog.dismiss();
            }
        });
        if(counter==3){
            dialog.show();
        }
    }


    private void creditPopup(int index){
        AlertDialog.Builder dialog= new AlertDialog.Builder(this);
        creditLimit = 1;
        dialog.setTitle("Creditcard");
        dialog.setMessage("Input the limit for your creditcard");
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        dialog.setView(input);
        if (index == 1) {
            dialog.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (input.getText() == null || input.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Incorrect amount!", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    } else {
                        creditLimit = Double.parseDouble(input.getText().toString());
                        if(bank.addCreditCard(1, creditLimit)) {
                            Toast.makeText(getApplicationContext(), "New card added!", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }else{
                            alreadyCardToast();
                            dialog.dismiss();
                        }
                    }
                }
            });
            dialog.show();
        }
        if (index == 2) {
            dialog.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (input.getText() == null || input.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Incorrect amount!", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    } else {
                        creditLimit = Double.parseDouble(input.getText().toString());
                        if(bank.addCreditCard(2, creditLimit)) {
                            Toast.makeText(getApplicationContext(), "New card added!", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }else{
                            alreadyCardToast();
                            dialog.dismiss();
                        }
                    }
                }
            });
            dialog.show();
        }
        if (index == 3) {
            dialog.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (input.getText() == null || input.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Incorrect amount!", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    } else {
                        creditLimit = Double.parseDouble(input.getText().toString());
                        if(bank.addCreditCard(3, creditLimit)) {
                            Toast.makeText(getApplicationContext(), "New card added!", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }else{
                            alreadyCardToast();
                            dialog.dismiss();
                        }
                    }
                }
            });
            dialog.show();
        }
    }

    public void payButton(View v){
        Intent intent = new Intent(main_one.this, pay_transfer.class);
        startActivity(intent);
    }


    public void addMoney(View v) {

        AlertDialog.Builder dialog= new AlertDialog.Builder(this);
        ArrayList<String> accountList = bank.getAccounts();
        dialog.setTitle("Add money");
        dialog.setMessage("Input the amount you want to add\n");
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        dialog.setView(input);

        dialog.setPositiveButton("Add to account 1.", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(input.getText() == null || input.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),  "Incorrect amount!", Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                }else {
                    double money = Double.parseDouble(input.getText().toString());
                    ArrayList<String> accountList = bank.getAccounts();
                    bank.addMoney(1, money);
                    moneyAmount.setText(bank.getMoneyAmount() + "€");
                    String text = "";
                    for (int i = 0; accountList.size() > i; i++) {
                        text = text + ("\n" + (i + 1) + ". " + bank.getAccountsPayPossibility(i + 1) + " | " + accountList.get(i) + " | " + bank.getAccountsMoneyAmount(i + 1) + "€");
                    }
                    accounts.setText(text);
                    Toast.makeText(getApplicationContext(), money + "€ added to account!", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }
            }
        });
        if (accountList.size() == 1) {
            dialog.show();
        }

        dialog.setNegativeButton("Add to account 2.", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(input.getText() == null || input.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),  "Incorrect amount!", Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                }else {
                    double money = Double.parseDouble(input.getText().toString());
                    ArrayList<String> accountList = bank.getAccounts();
                    bank.addMoney(2, money);
                    moneyAmount.setText(bank.getMoneyAmount() + "€");
                    String text = "";

                    for (int i = 0; accountList.size() > i; i++) {
                        text = text + ("\n" + (i + 1) + ". " + bank.getAccountsPayPossibility(i + 1) + " | " + accountList.get(i) + " | " + bank.getAccountsMoneyAmount(i + 1) + "€");
                    }

                    accounts.setText(text);
                    Toast.makeText(getApplicationContext(), money + "€ added to account!", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }
            }
        });

        if (accountList.size() == 2) {
            dialog.show();
        }

        dialog.setNeutralButton("Add to account 3.", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(input.getText() == null || input.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),  "Incorrect amount!", Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                }else {
                    double money = Double.parseDouble(input.getText().toString());
                    ArrayList<String> accountList = bank.getAccounts();
                    bank.addMoney(3, money);
                    moneyAmount.setText(bank.getMoneyAmount() + "€");
                    String text = "";

                    for (int i = 0; accountList.size() > i; i++) {
                        text = text + ("\n" + (i + 1) + ". " + bank.getAccountsPayPossibility(i + 1) + " | " + accountList.get(i) + " | " + bank.getAccountsMoneyAmount(i + 1) + "€");
                    }

                    accounts.setText(text);
                    Toast.makeText(getApplicationContext(), money + "€ added to account!", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }
            }
        });

        if (accountList.size() == 3) {
            dialog.show();
        }
    }


    public void showBankStatement(View v)   {
        AlertDialog.Builder dialog= new AlertDialog.Builder(this);
        ArrayList<String> accountList = bank.getAccounts();
        dialog.setTitle("Choose account");

        dialog.setPositiveButton("Bank statement of Account 1.", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(main_one.this, bankStatement1.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });

        if (accountList.size() == 1) {
            dialog.show();
        }

        dialog.setNegativeButton("Bank statement of Account 2.", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(main_one.this, bankStatement2.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });

        if (accountList.size() == 2) {
            dialog.show();
        }

        dialog.setNeutralButton("Bank statement of Account 3.", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(main_one.this, bankStatement3.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });
        
        if (accountList.size() == 3) {
            dialog.show();
        }
    }


    public void getToSettings(View v) {
        Intent intent = new Intent(main_one.this, settings.class);
        startActivity(intent);
    }

}




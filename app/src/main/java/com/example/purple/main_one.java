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
    private TextView moneyAmount, accounts, accountCounter;
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


    }

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
                text = text + ("\n" + (i + 1) + ". " + bank.getAccountsPayPossibility(i + 1) + " | " + accountList.get(i) + " | " + bank.getAccountsMoneyAmount(i + 1) + "€");
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



    public void addCardPopup(final String accNumber){
        AlertDialog.Builder dialog= new AlertDialog.Builder(this);
        dialog.setTitle("Choose the card type");

        dialog.setNeutralButton("Debit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(getApplicationContext(), "New card added!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        dialog.setPositiveButton("Credit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which){
                //creditkortti lisätään eri metodissa
                creditPopup(accNumber);
                dialog.dismiss();

            }
        });
        dialog.show();
    }

    public void whichAccountToAddPopup(View v){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        ArrayList<String> accountList = bank.getAccounts();
        final ArrayList<String> accountsToAddCard = new ArrayList<>();
        for(int i = 0; accountList.size()>i;i++){
            bank.getAccountsPayPossibility(i);
            if(bank.getAccountsPayPossibility(i).equals("Regular")){
                accountsToAddCard.add(accountList.get(i));
            }
        }
        if(accountsToAddCard.size()==0){
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
        dialog.setPositiveButton("1. Account", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                addCardPopup(accountsToAddCard.get(0));
                dialog.dismiss();
            }
        });
        if(accountsToAddCard.size()==1){
            dialog.show();
        }
        dialog.setNeutralButton("2. Account", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which){
                accToAddCard = accountsToAddCard.get(1);
                addCardPopup(accountsToAddCard.get(1));
                dialog.dismiss();
            }
        });
        if(accountsToAddCard.size()==2){
            dialog.show();
        }
        dialog.setNegativeButton("3. Account", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                addCardPopup(accountsToAddCard.get(2));
                dialog.dismiss();
            }
        });
        if(accountsToAddCard.size()==3){
            dialog.show();
        }

    }

    private void creditPopup(String accountNumber){
        AlertDialog.Builder dialog= new AlertDialog.Builder(this);
        creditLimit = 1;
        dialog.setTitle("Creditcard");
        dialog.setMessage("Input the limit for your creditcard");
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        dialog.setView(input);

        dialog.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which){
                creditLimit = Double.parseDouble(input.getText().toString());

                //accountin lisäys creditlimitillä

                Toast.makeText(getApplicationContext(), "New card added, with credit limit of: " + creditLimit + "€", Toast.LENGTH_LONG).show();
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
                if(input.getText() == null){
                    Toast.makeText(getApplicationContext(),  "Incorrect amount!", Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                }
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
        });
        if (accountList.size() == 1) {
            dialog.show();
        }
        dialog.setNegativeButton("Add to account 2.", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(input.getText() == null){
                    Toast.makeText(getApplicationContext(),  "Incorrect amount!", Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                }
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
        });
        if (accountList.size() == 2) {
            dialog.show();
        }
        dialog.setNeutralButton("Add to account 3.", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(input.getText() == null){
                    Toast.makeText(getApplicationContext(),  "Incorrect amount!", Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                }
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
        });
        if (accountList.size() == 3) {
            dialog.show();
        }

    }


    public void showBankStatement(View v) {
        AlertDialog.Builder dialog= new AlertDialog.Builder(this);
        ArrayList<String> accountList = bank.getAccounts();
        dialog.setTitle("Choose account");


        dialog.setNeutralButton("Account 1.", new DialogInterface.OnClickListener() {
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
        dialog.setNegativeButton("Account 2.", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });
        if (accountList.size() == 2) {
            dialog.show();
        }

        dialog.setPositiveButton("Account 3.", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });
        if (accountList.size() == 3) {
            dialog.show();
        }
    }



}




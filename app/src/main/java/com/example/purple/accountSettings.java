package com.example.purple;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

// the view where user can edit profile

public class accountSettings extends AppCompatActivity {
    private TextView title;
    private int account;
    private Bank bank = Bank.getInstance();
    private EditText type;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_settings);
        title = findViewById(R.id.title);
        type = findViewById(R.id.type);
        Bundle b = getIntent().getExtras();
        if (b != null) {
            account = b.getInt("key");
            if (account == 1) {
                title.setText(bank.getAccountNumber(1));
                type.setText(bank.getAccountsPayPossibility(1));
            } else if (account == 2) {
                title.setText(bank.getAccountNumber(2));
                type.setText(bank.getAccountsPayPossibility(2));
            } else if (account == 3) {
                title.setText(bank.getAccountNumber(3));
                type.setText(bank.getAccountsPayPossibility(3));
            }
        }
    }

// on click this method will update accounts type to regular or savings
    public void editAccount(View v) {
        if (!(type.getText().toString().isEmpty())) {
            if (type.getText().toString().equals("Savings") && (!(type.getText().toString().equals(bank.getAccountsPayPossibility(account))))) {
                AlertDialog.Builder dialog= new AlertDialog.Builder(this);
                dialog.setTitle("Are you sure? You delete the account's possible card also.");

                dialog.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        bank.editAccount(account, 0, context);
                        bank.deleteCard(account, context);
                        Toast.makeText(getApplicationContext(), "Account changed!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(accountSettings.this, main_one.class);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });

                dialog.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                dialog.show();


            } else if (type.getText().toString().equals("Regular") && (!(type.getText().toString().equals(bank.getAccountsPayPossibility(account))))) {
                AlertDialog.Builder dialog= new AlertDialog.Builder(this);
                dialog.setTitle("Are you sure?");

                dialog.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.out.println(account);
                        bank.editAccount(account, 1, context);
                        Toast.makeText(getApplicationContext(), "Account changed!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(accountSettings.this, main_one.class);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });
                dialog.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            } else {
                Toast.makeText(getApplicationContext(), "No changes were made!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(accountSettings.this, main_one.class);
                startActivity(intent);
            }
        } else {
            Toast.makeText(getApplicationContext(), "Write down the type first!", Toast.LENGTH_LONG).show();
        }
    }


    public void returnButton(View v) {
        Intent intent = new Intent(accountSettings.this, main_one.class);
        startActivity(intent);
    }
}

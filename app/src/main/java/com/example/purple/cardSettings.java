package com.example.purple;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

// main one will pass to this activity a bundle which contains card information
public class cardSettings extends AppCompatActivity {
    private int card;
    private TextView title, raiselimit, isCredit, PIN, creditLimit;
    private Bank bank = Bank.getInstance();
    private EditText getRaiseLimit, getPin, getType, getCreditLimit;
    private Context context = this;
    private boolean countryChangesMade = false;

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
        getRaiseLimit = findViewById(R.id.changeraiselimit);
        getPin = findViewById(R.id.changePin);
        getType = findViewById(R.id.changecredit);
        getCreditLimit = findViewById(R.id.changecreditlimit);



        Bundle b = getIntent().getExtras();
        if (b != null) {
            card = b.getInt("key");
            title.setText("Card " + card + ": " + bank.getCardNumber(card));
            raiselimit.setText("Current raiselimit: " + bank.getCardRaiseLimit(card) + "€");
            isCredit.setText(("Current type: " + bank.isCardCreditCard(card)));
            PIN.setText("Current PIN-Code: " + bank.getCardPin(card));
            creditLimit.setText("Current creditlimit: " + bank.getCreditLimit(card) + "€");
        }
        Spinner currentCountries = findViewById(R.id.currentCountries);
        Spinner allCountries = findViewById(R.id.allCountries);

        ArrayAdapter<String> currentCountryArray = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, bank.getCardObj(card).getAreaToUseList());
        currentCountryArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        currentCountries.setAdapter(currentCountryArray);

        ArrayAdapter<String> countriesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, bank.getCardObj(card).getCountryArray());
        countriesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        allCountries.setAdapter(countriesAdapter);


    }

    public void editCard(View v) {
        Intent intent = new Intent(cardSettings.this, main_one.class);
        String raiseLimit, PIN, type, creditLimit;
        boolean ok = true;
        raiseLimit = getRaiseLimit.getText().toString();
        PIN = getPin.getText().toString();
        type = getType.getText().toString();
        creditLimit = getCreditLimit.getText().toString();

        if((raiseLimit.isEmpty()) && (PIN.isEmpty()) && (type.isEmpty()) && (creditLimit.isEmpty()) && (!countryChangesMade)) {
            Toast.makeText(getApplicationContext(), "No changes were made!", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        } else {
            if (!(raiseLimit.isEmpty())) {
                int finalValue = Integer.parseInt(raiseLimit);
                bank.editCard(finalValue, 1, card, context);

            }
            if (!(PIN.isEmpty())) {
                if (PIN.length() == 4) {
                    int finalValue = Integer.parseInt(PIN);
                    bank.editCard(finalValue, 2, card, context);
                } else {
                    ok = false;
                }
            }
            if (!(type.isEmpty())) {
                if (type.equals("Credit")) {
                    int finalValue = 1;
                    bank.editCard(finalValue, 3, card, context);
                } else if (type.equals("Debit")) {
                    int finalValue = 0;
                    bank.editCard(finalValue, 3, card, context);
                }
            }
            if (!(creditLimit.isEmpty())) {
                if (type.equals("Credit")) {
                    int finalValue = Integer.parseInt(creditLimit);
                    bank.editCard(finalValue, 4, card, context);
                } else if ((bank.isCardCreditCard(card).equals("Credit")) && (!(type.equals("Debit")))) {
                    int finalValue = Integer.parseInt(creditLimit);
                    bank.editCard(finalValue, 4, card, context);
                } else {
                    ok = false;
                }
            }
            if (ok || countryChangesMade) {
                Toast.makeText(getApplicationContext(), "Changes were made!", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Oops! Wrong values!", Toast.LENGTH_SHORT).show();
            }

        }
    }

    public void addCountry(View v){
        Spinner allCountries = findViewById(R.id.allCountries);
        bank.getCardObj(card).addCountry(allCountries.getSelectedItemPosition());
        countryChangesMade = true;
        ArrayAdapter<String> countriesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, bank.getCardObj(card).getCountryArray());
        countriesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        allCountries.setAdapter(countriesAdapter);
    }

    public void removeCountry(View v){
        Spinner currentCountries = findViewById(R.id.currentCountries);
        bank.getCardObj(card).removeCountry(currentCountries.getSelectedItemPosition());
        countryChangesMade = true;
        ArrayAdapter<String> currentCountryArray = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, bank.getCardObj(card).getAreaToUseList());
        currentCountryArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        currentCountries.setAdapter(currentCountryArray);
    }

    public void returnButton(View v) {
        Intent intent = new Intent(cardSettings.this, main_one.class);
        startActivity(intent);
    }




}

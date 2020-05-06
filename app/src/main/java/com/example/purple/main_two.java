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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;



public class main_two extends AppCompatActivity {
    private Bank bank = Bank.getInstance();
    private ArrayList<Card> cardsList = new ArrayList<>();
    private ArrayList<String> chooseCardList = new ArrayList<>();
    private String[] countryArray = new String[]{"United States", "Finland", "Norway", "Sweden", "Denmark", "Canada", "United Kingdom", "Switzerland", "Germany"};
    private GestureDetectorCompat gesture;
    private EditText amountInput = findViewById(R.id.amountInput);
    private Card cardToUse;
    private double amountToDialog = 0;
    private Spinner chooseCardSpinner = findViewById(R.id.chooseCardSpinner);
    private Spinner chooseCountry = findViewById(R.id.chooseCountry);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_two);
        gesture = new GestureDetectorCompat(this, new LearnGesture());



// Card objects need to be fetched to another list to get strings for arrayadapter (chooseCardList)
        for(int i=0; 3 > i; i++){
            if(bank.getCardObj(i+1) != null){
                cardsList.add(bank.getCardObj(i + 1));
                chooseCardList.add(bank.getCardObj(i + 1).isCreditCard() + " | " + bank.getCardObj(i + 1).cardNumber);
            }
        }

        ArrayAdapter<String> cardArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, chooseCardList);
        cardArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chooseCardSpinner.setAdapter(cardArrayAdapter);


        ArrayAdapter<String> countryArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, countryArray);
        countryArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chooseCountry.setAdapter(countryArrayAdapter);
    }

    public void cardPayment(View v) {

        cardToUse = cardsList.get(chooseCardSpinner.getSelectedItemPosition());
        String country = chooseCountry.getSelectedItem().toString();
        ArrayList<String> cardArea = cardToUse.getAreaToUseList();

        boolean countryOk = false;
        if (amountInput.getText() == null || amountInput.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Incorrect amount!", Toast.LENGTH_SHORT).show();
        } else {
            amountToDialog = Double.parseDouble(amountInput.getText().toString());
            for (int i = 0; 8 > i; i++) {
                if (country.equals(cardArea.get(i))) {
                    countryOk = true;
                    break;
                }
            }
            if (countryOk) {
                creditOrDebit(2);
            }else {
                Toast.makeText(getApplicationContext(), "You cannot use your card in this country!\nCheck card restrictions.", Toast.LENGTH_SHORT).show();
            }
        }

    }
    public void withDraw(View v) {
        Spinner chooseCountry = findViewById(R.id.chooseCountry);
        String country = chooseCountry.getSelectedItem().toString();
        ArrayList<String> cardArea = cardToUse.getAreaToUseList();
        boolean countryOk = false;
        if (amountInput.getText() == null || amountInput.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Incorrect amount!", Toast.LENGTH_SHORT).show();
        } else {
            amountToDialog = Double.parseDouble(amountInput.getText().toString());
            for (int i = 0; 8 > i; i++) {
                if (country.equals(cardArea.get(i))) {
                    countryOk = true;
                    break;
                }
            }
            if (countryOk) {
                creditOrDebit(1);
            } else {
                Toast.makeText(getApplicationContext(), "You cannot use your card in this country!\nCheck card restrictions.", Toast.LENGTH_SHORT).show();
            }
        }
    }
        public void creditOrDebit (int flag) {
            //flag determines whether its a payment or withdraw
            //withdraw
            if (flag == 1){
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setTitle("Choose the payment method");

                dialog.setNegativeButton("Debit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (cardToUse.payment(amountToDialog) == 1) {
                            Toast.makeText(getApplicationContext(), "Money withdrawn, spend it wisely!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Card declined! Get a job..", Toast.LENGTH_SHORT).show();
                        }

                        dialog.dismiss();
                    }
                });

                dialog.setPositiveButton("Credit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (cardToUse.creditPayment(amountToDialog) == 1) {
                            Toast.makeText(getApplicationContext(), "Credit money withdrawn, make it rain!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Card declined! Get a job..", Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                    }
                });
                dialog.show();
                //card payment
            }else{
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setTitle("Choose the payment method");

                dialog.setNeutralButton("Debit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (cardToUse.payment(amountToDialog) == 1) {
                            Toast.makeText(getApplicationContext(), "Payment has been made! Have fun with your" + amountToDialog + "€ breadtoaster!", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getApplicationContext(), "Card declined! WELL THAT'S EMBARRASSING..", Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                    }
                });


                dialog.setPositiveButton("Credit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (cardToUse.creditPayment((amountToDialog)) == 1) {
                            Toast.makeText(getApplicationContext(), "Your credit card was charged! Have fun with your" + amountToDialog + "€ breadtoaster!", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getApplicationContext(), "Card declined! WELL THAT'S EMBARRASSING..", Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }

        }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gesture.onTouchEvent(event);
        return super.onTouchEvent(event);

    }
    class LearnGesture extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2, float x1, float y1) {
            if (event2.getX() > event1.getX()) {
                Intent intent1 = new Intent(main_two.this, main_one.class);
                finish();
                startActivity(intent1);
            }
            return true;
        }
    }

}
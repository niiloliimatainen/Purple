package com.example.purple;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.text.InputType;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;



public class main_two extends AppCompatActivity {
    private Bank bank = Bank.getInstance();
    private ArrayList<String> chooseCardList = new ArrayList<>();
    private String[] countryArray = new String[]{"United States", "Finland", "Norway", "Sweden", "Denmark", "Canada", "United Kingdom", "Switzerland", "Germany"};
    private GestureDetectorCompat gesture;
    private int cardToUse = 0;
    private double amountToDialog = 0;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_two);
        gesture = new GestureDetectorCompat(this, new LearnGesture());
        Spinner chooseCardSpinner = findViewById(R.id.chooseCardSpinner);
        Spinner chooseCountry = findViewById(R.id.chooseCountry);
        ImageButton pay = findViewById(R.id.payWithCard);
        ImageButton withdraw = findViewById(R.id.withDrawButton);

        for(int i=0; 3 > i; i++){
            if(bank.getCardObj(i+1) != null){
                chooseCardList.add(i+1 + "." + bank.isCardCreditCard(i + 1) + " | " + bank.getCardObj(i + 1).getCardNumber());
            }
        }
        if(chooseCardList == null || chooseCardList.isEmpty()){
            chooseCardSpinner.setVisibility(View.GONE);
            pay.setVisibility(View.GONE);
            withdraw.setVisibility(View.GONE);
        }

        ArrayAdapter<String> cardArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, chooseCardList);
        cardArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chooseCardSpinner.setAdapter(cardArrayAdapter);

        ArrayAdapter<String> countryArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, countryArray);
        countryArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chooseCountry.setAdapter(countryArrayAdapter);
    }

    public void cardPayment(View v) {
        Spinner chooseCardSpinner = findViewById(R.id.chooseCardSpinner);
        Spinner chooseCountry = findViewById(R.id.chooseCountry);
        EditText amountInput = findViewById(R.id.amountInput);
        cardToUse = 0;
        cardToUse = chooseCardSpinner.getSelectedItemPosition() + 1;
        String country = chooseCountry.getSelectedItem().toString();
        ArrayList<String> cardArea = bank.getCardObj(cardToUse).getAreaToUseList();

        boolean countryOk = false;
        if (amountInput.getText() == null || amountInput.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Incorrect amount!", Toast.LENGTH_SHORT).show();
        } else {
            amountToDialog = Double.parseDouble(amountInput.getText().toString());
            for (int i = 0; bank.getCardObj(cardToUse).getAreaToUseList().size() > i; i++) {
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
        Spinner chooseCardSpinner = findViewById(R.id.chooseCardSpinner);
        EditText amountInput = findViewById(R.id.amountInput);
        Spinner chooseCountry = findViewById(R.id.chooseCountry);
        cardToUse = 0;
        cardToUse = chooseCardSpinner.getSelectedItemPosition() + 1;
        String country = chooseCountry.getSelectedItem().toString();
        ArrayList<String> cardArea = bank.getCardObj(cardToUse).getAreaToUseList();
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
            //when this executes, two flags will be sent to pincodepopup. First Credit/debit, then pay/withdraw!
//withdraw
            if (flag == 1){
                    AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                    dialog.setTitle("Choose the payment method");

                    dialog.setNegativeButton("Debit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            pinCodePopup(false, false);
                            dialog.dismiss();
                        }
                });
                if(!bank.getCardObj(cardToUse).isCreditCard()) {
                    dialog.show();
                }else {
                    dialog.setPositiveButton("Credit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            pinCodePopup(false, true);
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                }
//card payment
            }else{
                // is debitCard
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setTitle("Choose the payment method");

                dialog.setNeutralButton("Debit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        pinCodePopup(true, false);
                        dialog.dismiss();
                    }
                });
                if(!bank.getCardObj(cardToUse).isCreditCard()) {
                    dialog.show();
                }else {
                    //is creditcard
                    dialog.setPositiveButton("Credit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            pinCodePopup(true, true);
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                }
            }

        }


    public void pinCodePopup(final boolean isPayment, final boolean isCredit){
        AlertDialog.Builder dialog= new AlertDialog.Builder(this);
        Spinner chooseCardSpinner = findViewById(R.id.chooseCardSpinner);
        dialog.setTitle("Payment terminal");
        dialog.setMessage("Input card pin");
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        dialog.setView(input);
        cardToUse = 0;
        cardToUse = chooseCardSpinner.getSelectedItemPosition() + 1;

        dialog.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (input.getText() == null || input.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Incorrect amount!", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                } else {
                    int inputPin = Integer.parseInt(input.getText().toString());
                    if(bank.getCardObj(cardToUse).testPin(inputPin)) {
                        if(isPayment) {
                            //Payment
                            if(isCredit) {
                                //Using credit
                                if (bank.getCardObj(cardToUse).creditPayment(amountToDialog) == 1) {
                                    bank.saveCredit(bank.getCardObj(cardToUse).getAccount(), context);
                                    Toast.makeText(getApplicationContext(), "Your credit card was charged! Have fun with your" + amountToDialog + "€ breadtoaster!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Card declined! WELL THAT'S EMBARRASSING..", Toast.LENGTH_SHORT).show();
                                }
                                //using debit
                            }else{
                                if (bank.cardTransaction(bank.getCardObj(cardToUse).getAccount(), amountToDialog, true, context) == 1) {
                                    Toast.makeText(getApplicationContext(), "Payment has been made! Have fun with your" + amountToDialog + "€ breadtoaster!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Card declined! WELL THAT'S EMBARRASSING..", Toast.LENGTH_SHORT).show();
                                }

                            }
                        }else{
                            if(isCredit){
                                //using credit for withdrawal
                                if (bank.getCardObj(cardToUse).creditPayment(amountToDialog) == 1) {
                                    bank.saveCredit(bank.getCardObj(cardToUse).getAccount(), context);
                                    Toast.makeText(getApplicationContext(), "Credit money withdrawn, make it rain!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Card declined! Get a job..", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                //using debit for withdrawal
                                if ((bank.cardTransaction(bank.getCardObj(cardToUse).getAccount(), amountToDialog, false, context) == 1) && (bank.getCardObj(cardToUse).creditPayment(amountToDialog)) == 1) {
                                    Toast.makeText(getApplicationContext(), "Money withdrawn, spend it wisely!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Card declined! Get a job..", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                        dialog.dismiss();
                    }else {
                        Toast.makeText(getApplicationContext(), "Incorrect pin code, try again!", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }
            }
        });
        dialog.show();
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


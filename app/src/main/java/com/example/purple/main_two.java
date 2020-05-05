package com.example.purple;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

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
import java.util.Arrays;


public class main_two extends AppCompatActivity {
    private Bank bank = Bank.getInstance();
    ArrayList<String> cardsList = bank.getCards();
    private String[] countryArray = new String[]{"United States", "Finland", "Norway", "Sweden", "Denmark", "Canada", "United Kingdom", "Switzerland", "Germany"};
    private GestureDetectorCompat gesture;
    private EditText amountInput = findViewById(R.id.amountInput);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_two);
        gesture = new GestureDetectorCompat(this, new main_one.LearnGesture());
        Spinner chooseCard = findViewById(R.id.chooseCard);
        Spinner chooseCountry = findViewById(R.id.chooseCountry);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cardsList);
        ArrayAdapter<String> spinnerArrayAdapter_2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, countryArray);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chooseCard.setAdapter(spinnerArrayAdapter);
        chooseCountry.setAdapter(spinnerArrayAdapter_2);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gesture.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    public void cardPayment(View v){
        Spinner chooseCountry = findViewById(R.id.chooseCountry);
        String country = chooseCountry.getSelectedItem().toString();
        Card cardToUse = (Card) chooseCountry.getSelectedItem();
        ArrayList <String> areaToUse = cardToUse.getAreaToUseList();

        if (amountInput.getText() == null || amountInput.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Incorrect amount!", Toast.LENGTH_SHORT).show();
        } else {
            Double creditLimit = Double.parseDouble(amountInput.getText().toString());
            for (int i = 0; 8 > i; i++) {
                if (country.equals(areaToUse.get(i))) {

                }else{
                    Toast.makeText(getApplicationContext(), "You can not use your card in this country!", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void withDraw(View v){
        ArrayList <String> areaToUse = cardToUse.getAreaToUseList();        }
        String country = chooseCountry.getSelectedItem().toString();

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
package com.example.purple;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;


public class main_one extends AppCompatActivity {
    private GestureDetectorCompat gesture;
    private TextView moneyAmount;
    private Bank bank = Bank.getInstance();
    private int allMoney = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_one);
        gesture = new GestureDetectorCompat(this, new LearnGesture());
        moneyAmount = findViewById(R.id.allmoney);

        ArrayList<Account> accountList = bank.getAccounts();
        if (accountList == null) {
            moneyAmount.setText("--");
        } else {
            moneyAmount.setText(String.format(Locale.GERMANY, "%.2f", bank.getMoneyAmount()));
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




}

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
import android.widget.Spinner;


public class main_two extends AppCompatActivity {
    private Bank bank = Bank.getInstance();
    private GestureDetectorCompat gesture;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_two);
        gesture = new GestureDetectorCompat(this, new LearnGesture());

        //tässä alustetaan spinneri korteilla

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gesture.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    public void cardPayment(View v){
        Spinner chooseCountry = findViewById(R.id.chooseCountry);
        String country = chooseCountry.getSelectedItem().toString();
    }

    public void withDraw(View v){
        Spinner chooseCountry = findViewById(R.id.chooseCountry);
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
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
        Button acc1Button = findViewById(R.id.acc1Button);
        Button acc2Button = findViewById(R.id.acc2Button);

        //tässä alustetaan spinnerit listoilla
        // maaspinnerii se listeneri joka ottaa nykysen tilan

        final Spinner choosecard = findViewById(R.id.chooseCard);
        // haetaan nappia painamalla eri tilien listat näkyviin spinneriin

        acc1Button.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                choosecard.setAdapter();
              }
          });
        acc2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosecard.setAdapter();
            }
        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gesture.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    public void cardPayment(){

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
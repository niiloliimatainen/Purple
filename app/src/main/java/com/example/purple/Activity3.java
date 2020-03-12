package com.example.purple;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.content.Intent;
import android.os.Bundle;


public class Activity3 extends AppCompatActivity {
    private GestureDetectorCompat gesture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_3);
        gesture = new GestureDetectorCompat(this, new LearnGesture());

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
                Intent intent1 = new Intent(Activity3.this, activity_3_1.class);
                finish();
                startActivity(intent1);
            }else if(event2.getX() < event1.getX()){

            }
            return true;
        }
    }




}

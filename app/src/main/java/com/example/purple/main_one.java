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
import android.widget.Toast;


public class main_one extends AppCompatActivity {
    private GestureDetectorCompat gesture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_one);
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
                Toast.makeText(getApplicationContext(), "New account created!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        dialog.setNeutralButton("Savings", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which){
                //TÄHÄN SE AKKOUNTTIJUTTU TAAS
                    Toast.makeText(getApplicationContext(), "New account created!", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
            }
        });
        dialog.show();
    }


}

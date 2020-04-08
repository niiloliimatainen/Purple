package com.example.purple.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;

public class Activity2_1 extends AppCompatActivity {
PopupWindow popupWindow;
ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        popupWindow = new PopupWindow(this);
        layout = new ConstraintLayout(this);


    }

}

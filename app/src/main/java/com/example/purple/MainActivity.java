package com.example.purple;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

//Starting page

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.start);
    }


    public void openActivity2(View v) {
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
    }
    public void tester(View v){
        Intent intent;
        intent = new Intent(MainActivity.this, main_one.class);
        startActivity(intent);
    }


}




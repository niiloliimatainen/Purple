package com.example.purple;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class pay_transfer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_transfer);

        Button showInputButton = findViewById(R.id.payment);
        Button showSpinnerButton = findViewById(R.id.selfTransfer);
        final View inputAccountToPay = findViewById(R.id.inputAccountToPay);
        final View chooseAccToPay = findViewById(R.id.chooseAccToPay);
        final TextView paymentInfo = findViewById(R.id.paymentInfoTW);
        final View payButton = findViewById(R.id.payButton);
        Spinner chooseAcc = findViewById(R.id.chooseAcc);

        // molemmat spinnerit täytetään user->getAccounts liststa tässä!!

        // tähän metodi joka odottaa kunnes maksutili ja maksettava tili valittu ja senjälkeen paybutton.setVisibility(View.VISIBLE);

        showInputButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputAccountToPay.setVisibility(View.VISIBLE);
                chooseAccToPay.setVisibility(View.GONE);
                paymentInfo.setVisibility(View.VISIBLE);
                paymentInfo.setText("Input the account number you want to make payment");
            }
        });
        showSpinnerButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                inputAccountToPay.setVisibility(View.GONE);
                chooseAccToPay.setVisibility(View.VISIBLE);
                paymentInfo.setVisibility(View.VISIBLE);
                paymentInfo.setText("Choose the account you want to transfer to");
            }
        });






    }

    public void returnMethod(View v){
        Intent intent = new Intent(pay_transfer.this, main_one.class);
        startActivity(intent);
    }

}





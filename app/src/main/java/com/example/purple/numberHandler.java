package com.example.purple;


import java.util.Random;

//Only class methods
public class numberHandler {

    public static String setVerificationNumber(){
        Random random = new Random();
        int number = random.nextInt(899999) + 100000;
        String code = String.valueOf(number);
        return code;
    }

}

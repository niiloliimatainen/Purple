package com.example.purple;


import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Random;

//Only class methods
public class numberHandler {

    //Constructor is private to make sure this class cannot be instantiated
    private numberHandler() {}

    public static String setVerificationNumber(){
        Random random = new Random();
        int number = random.nextInt(899999) + 100000;
        String code = String.valueOf(number);
        return code;
    }
    public static String getHashedPassword(String password){

        return hashedPw;
    }


    public static String setAccountNumber() {
        //Country code, IBAN-code and bank identifier
        String account = "FI 86 433";
        Random random = new Random();
        int number = random.nextInt(89999) + 10000;
        int number2 = random.nextInt(89999) + 10000;
        account = account + String.valueOf(number) + String.valueOf(number2);
        return account;
    }

}

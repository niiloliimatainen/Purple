package com.example.purple;
import java.security.MessageDigest;
import java.util.Random;


public class numberHandler {

    public numberHandler() {}

    public String setVerificationNumber(){
        Random random = new Random();
        int number = random.nextInt(899999) + 100000;
        String code = String.valueOf(number);
        return code;
    }

    public byte[] getSalt(){
        Random random = new Random();
        byte[] salt= new byte[150];
        random.nextBytes(salt);
        return salt;
    }

    public String hasher(String password, byte[] salt){
        String hashed = "";
        try{
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            messageDigest.update(salt);
            byte[] bytes = messageDigest.digest(password.getBytes());
            hashed = toHex(bytes);
        }catch (Exception e){
        }
        return hashed;

    }


    public String toHex(byte[] bytes) {
        StringBuilder pwString = new StringBuilder();
        for (byte b: bytes) {
            pwString.append(String.format("%02x", b));
        }
        return pwString.toString();
    }



    public String setAccountNumber() {
        //Country code, IBAN-code and bank identifier
        String account = "FI86 433";
        Random random = new Random();
        int number = random.nextInt(89999) + 10000;
        int number2 = random.nextInt(89999) + 10000;
        account = account + number + number2;
        return account;
    }


    public String setCardNumber() {
        Random random = new Random();
        int number = random.nextInt(8999) + 1000;
        int number2 = random.nextInt(8999) + 1000;
        int number3 = random.nextInt(8999) + 1000;
        int number4 = random.nextInt(8999) + 1000;

        String cardNumber = number + " " + number2 + " " + number3 + " " + number4;
        return cardNumber;
    }


    public int setCVC() {
        Random random = new Random();
        int CVC = random.nextInt(899) + 100;
        System.out.println(CVC);
        return CVC;
    }


    public int setPIN() {
        Random random = new Random();
        int PIN = random.nextInt(8999) + 1000;
        System.out.println(PIN);
        return PIN;
    }


}

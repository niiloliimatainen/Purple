package com.example.purple;


import android.provider.ContactsContract;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

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
    public static SecretKeySpec getKey(String password) throws Exception {
        final MessageDigest digest = MessageDigest.getInstance("SHA-512");
        byte[] bytes = password.getBytes(StandardCharsets.UTF_8);
        digest.update(bytes);
        byte[] key = digest.digest();
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        return secretKeySpec;
    }

    public static String encrypter(String password) throws Exception{
        SecretKeySpec key = getKey(password);
        Cipher c = Cipher.getInstance("AES");
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encrypted = c.doFinal(password.getBytes());
        return encrypted.toString();


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

package com.example.purple;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;


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

   // https://howtodoinjava.com/security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/ //

    /*public static String enCrypter(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        int iterations = 10000;
        char[] chars = password.toCharArray();
        byte[] salt = getSalt();

        PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        SecretKey key = secretKeyFactory.generateSecret(spec);
        byte[] hashedpw = secretKeyFactory.generateSecret(spec).getEncoded();
        return iterations + ":"+ toHex(salt) + ":" + toHex(hashedpw);

    }
    private static String toHex(byte[] array) throws NoSuchAlgorithmException
    {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if(paddingLength > 0)
        {
            return String.format("%0"  +paddingLength + "d", 0) + hex;
        }else{
            return hex;
        }
    }
    private static byte [] getSalt() throws NoSuchAlgorithmException {
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        return salt;
    }
     public  static void tester() throws Exception {
        String testPW = "ASDasd123%";
        String encoded = enCrypter(testPW);
        System.out.println("TESTISALASANA ON: " + encoded);

    }
*/

    public static String setAccountNumber() {
        //Country code, IBAN-code and bank identifier
        String account = "FI86 433";
        Random random = new Random();
        int number = random.nextInt(89999) + 10000;
        int number2 = random.nextInt(89999) + 10000;
        account = account + number + number2;
        return account;
    }


    public static String setCardNumber() {
        Random random = new Random();
        int number = random.nextInt(8999) + 1000;
        int number2 = random.nextInt(8999) + 1000;
        int number3 = random.nextInt(8999) + 1000;
        int number4 = random.nextInt(8999) + 1000;

        String cardNumber = number + " " + number2 + " " + number3 + " " + number4;
        return cardNumber;
    }


    public static int setCVC() {
        Random random = new Random();
        int CVC = random.nextInt(899) + 100;
        System.out.println(CVC);
        return CVC;
    }


    public static int setPIN() {
        Random random = new Random();
        int PIN = random.nextInt(8999) + 1000;
        System.out.println(PIN);
        return PIN;
    }


}

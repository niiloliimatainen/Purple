package com.example.purple;

import org.mindrot.jbcrypt.BCrypt;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Random;

//Only class methods
public class numberHandler {
    private numberHandler(){

    }
    public static String setVerificationNumber(){
        Random random = new Random();
        int number = random.nextInt(899999) + 100000;
        String code = String.valueOf(number);
        return code;
    }
    public static String getHashedPassword(String password){
        String hashedPw = BCrypt.hashpw
        return hashedPw;
    }

}

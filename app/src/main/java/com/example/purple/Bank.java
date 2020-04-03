package com.example.purple;

import android.content.Context;

import java.util.ArrayList;

public class Bank {
    private static Bank bank = new Bank();
    private int counter = 0;

    private Bank() {
       String BIC = "BOFAAFIHH";
        String name = "Bank of Finland";
    }

    public static Bank getInstance() {
        return bank;
    }


    public void addUser(String fname, String lname, String email, String pnumber, String pword, Context context) {
            System.out.println("Haloo");
            User user = new regularUser(fname, lname, email, pnumber, pword);
            databaseConnector.writeToFile(context, user);
            //admini viel
    }


    public int currentUser(String email, Context context) {
        ArrayList<regularUser> userList = databaseConnector.readFromFile(context);

        for (int i = 0; i < userList.size(); i++) {
            if (email.equals(userList.get(i).getUserEmail())) {
                counter = i;
                return 1;
            }
        }
        return 0;
    }


    public void addAccount() {

    }



}

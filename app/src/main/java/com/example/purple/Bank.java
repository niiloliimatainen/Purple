package com.example.purple;

import java.util.ArrayList;

public class Bank {
    private static Bank bank = new Bank();
    private ArrayList<User> userList = new ArrayList<User>();
    private int counter = 0;

    private Bank() {
       String BIC = "BOFAAFIHH";
        String name = "Bank of Finland";
    }

    public static Bank getInstance() {
        return bank;
    }


    public void addUser(String fname, String lname, String email, String pnumber, String pword) {
            User user = new regularUser(fname, lname, email, pword, pnumber);
            userList.add(user);
            //admini viel
    }


    public void currentUser(String email) {
        for (int i = 0; i < userList.size(); i++) {
            if (email.equals(userList.get(i).getUserEmail())) {
                counter = i;
                break;
            }
        }

    }





    public void addAccount() {}



}

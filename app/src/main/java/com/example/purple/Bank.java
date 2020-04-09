package com.example.purple;

import android.content.Context;
import java.util.ArrayList;

public class Bank {
    private static Bank bank = new Bank();
    private ArrayList<regularUser> userList = new ArrayList<regularUser>();
    private int currentUser;

    private Bank() {
       String BIC = "BOFAAFIHH";
       String name = "Bank of Finland";
    }

    public static Bank getInstance() {
        return bank;
    }


    public void addUser(String fname, String lname, String email, String pnumber, String pword, Context context) {
            regularUser user = new regularUser(fname, lname, email, pnumber, pword);
            userList.add(user);
            databaseConnector.writeToFile(context, userList);
            //admini viel
    }


    public int login(String email, String password, Context context) {
        userList = databaseConnector.readFromFile(context);

        for (int i = 0; i < userList.size(); i++) {
            if (email.equals(userList.get(i).getUserEmail()) && (password.equals(userList.get(i).getUserPassword()))) {
                currentUser = i;
                return 1;
            }
        }
        return 0;
    }


    public void addAccount(Context context) {
        userList.get(currentUser).addAccount();
        databaseConnector.writeToFile(context, userList);
    }



}

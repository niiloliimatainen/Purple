package com.example.purple;

import android.content.Context;
import java.util.ArrayList;

public class Bank {
    private static Bank bank = new Bank();
    private ArrayList<regularUser> userList = new ArrayList<>();
    private int currentUser;
    private Context context;

    private Bank() {
       String BIC = "BOFAAFIHH";
       String bankName = "Bank of Finland";
    }

    public static Bank getInstance() {
        return bank;
    }


    public int addUser(String fname, String lname, String email, String pnumber, String pword, Context Context) {
            context = Context;
            regularUser user = new regularUser(fname, lname, email, pnumber, pword);
            for (int i = 0; i < userList.size(); i++) {
                if (email.equals(userList.get(i).getUserEmail())) {
                    return 0;
                }
            }

            userList.add(user);
            databaseConnector.writeToFile(context, userList);
            return 1;
            //admini viel
    }


    public int login(String email, String password, Context Context) {
        context = Context;
        userList = databaseConnector.readFromFile(context);
        for (int i = 0; i < userList.size(); i++) {
            if (email.equals(userList.get(i).getUserEmail()) && (password.equals(userList.get(i).getUserPassword()))) {
                currentUser = i;
                return 1;
            }
        }
        return 0;
    }


    public void addAccount(int flag) {
        userList.get(currentUser).addAccount(flag);
        databaseConnector.writeToFile(context, userList);
    }


    public void addMoney(int flag, double money) {
        userList.get(currentUser).addMoney(flag, money);
        databaseConnector.writeToFile(context, userList);
    }


    public void selfTransfer(int pay, int receive, double money) {
        userList.get(currentUser).selfTransfer(pay, receive, money);
        databaseConnector.writeToFile(context, userList);
    }


    public ArrayList<Account> getAccounts() {
        ArrayList<Account> accountList = userList.get(currentUser).getAccounts();
        return accountList;
    }


    public double getMoneyAmount() {
        double money = userList.get(currentUser).getMoneyAmount();
        return money;
    }

}

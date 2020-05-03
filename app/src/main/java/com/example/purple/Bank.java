package com.example.purple;

import android.content.Context;
import java.util.ArrayList;

public class Bank {
    private static Bank bank = new Bank();
    private ArrayList<User> userList = new ArrayList<>();
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
            User user = new User(fname, lname, email, pnumber, pword);
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
        userList.clear();
        userList = databaseConnector.readFromFile(context);
        for (int i = 0; i < userList.size(); i++) {
            if (email.equals(userList.get(i).getUserEmail()) && (password.equals(userList.get(i).getUserPassword()))) {
                currentUser = i;
                return 1;
            }
        }
        return 0;
    }


    public boolean addAccount(int flag) {
        boolean ok = userList.get(currentUser).addAccount(flag);
        if (ok) {
            System.out.println(userList.get(currentUser).counter);
            databaseConnector.writeToFile(context, userList);
            return true;
        } else {
            return false;
        }

    }


    public void addMoney(int index, double money) {
        userList.get(currentUser).addMoney(index, money);
        databaseConnector.writeToFile(context, userList);
    }


    public int selfTransfer(int pay, int receive, double money) {
        if (userList.get(currentUser).selfTransfer(pay, receive, money) == 1) {
            databaseConnector.writeToFile(context, userList);
            return 1;
        }
        return 0;
    }


    public int transferMoney(String receivingAcc, int payAccount, double money) {
        ArrayList<String> list;
        int flag;
        for (int i = 0; i < userList.size(); i++) {
            list = userList.get(i).getAccounts();
            for (int x = 0; x < list.size(); x++) {
                System.out.println(receivingAcc + " " + list.get(x));
                if (receivingAcc.equals(list.get(x))) {
                    if ((flag = userList.get(currentUser).transferMoney(payAccount, money)) == 1) {
                        userList.get(i).addMoney(x + 1, money);
                        databaseConnector.writeToFile(context, userList);
                        return 1;
                    } else if ((flag = userList.get(currentUser).transferMoney(payAccount, money)) == 0){
                        return 2;
                    } else {
                        return 3;
                    }

                }
            }
        }
        return 4;
    }


    public ArrayList<String> getAccounts() {
        ArrayList<String> accountList = userList.get(currentUser).getAccounts();
        return accountList;
    }


    public double getMoneyAmount() {
        double money = userList.get(currentUser).getMoneyAmount();
        return money;
    }


    public double getAccountsMoneyAmount(int index) {
       double money = userList.get(currentUser).getAccountsMoneyAmount(index);
        return money;
    }


    public String getAccountsPayPossibility(int index) {
        int payPossibility;
        if ((payPossibility = userList.get(currentUser).getAccountsPayPossibility(index)) == 1) {
            return "Regular";
        } else {
            return "Savings";
        }
    }

}

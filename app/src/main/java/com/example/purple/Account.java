package com.example.purple;


public class Account {
    private String accountNumber;
    private double money = 0;
    private int payPossibility;

    public Account(String account, int flag) {
        accountNumber = account;
        payPossibility = flag;
    }


    public void addMoney(double x) {
        money += x;
    }


    public int transferMoney(double x) {
        if (money < x) {
            return 0;
        }
        money -= x;
        return 1;
    }


    public double getMoneyAmount() {
        return money;
    }


    public void getAccountInfo() {
    }


    public String getAccountNumber() {
        return accountNumber;
    }


    public int getPayPossibility() {
        return payPossibility;
    }
}












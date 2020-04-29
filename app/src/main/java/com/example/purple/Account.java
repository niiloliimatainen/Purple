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


    public void transferMoney(double x) {
        money -= x;
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












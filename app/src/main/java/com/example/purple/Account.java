package com.example.purple;

// account class with only simple methods
public class Account {
    private String accountNumber;
    private double money = 0;
    private int payPossibility, cards = 0;

    public Account(String account, int flag) {
        accountNumber = account;
        payPossibility = flag;
    }


    public void addCard() {
        cards += 1;
    }


    public int getCards() {
        return cards;
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


    public void editAccount(int hasPayPossibility) {
        payPossibility = hasPayPossibility;
    }


    public String getAccountNumber() {
        return accountNumber;
    }


// to determine if account is regular or savings
    public int getPayPossibility() {
        return payPossibility;
    }

}












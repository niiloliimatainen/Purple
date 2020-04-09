package com.example.purple;

public abstract class Account {
    protected String account;
    protected int money = 0;

    public Account(String account) {
        this.account = account;
    }


    public abstract void addMoney(int x);
    public abstract void transferMoney(int x);
    public abstract void getAccountInfo();
    public abstract String getAccountNumber();
}


class regularAccount extends Account {

    public regularAccount(String account) {
        super(account);
    }


    public void addMoney(int x) {
        money += x;
    }

    public void transferMoney(int x) {
        if ((money -= x) < 0) {
            money += x;
            System.out.println("Not enough money.");
        }
    }


    public void getAccountInfo() {
    }

    public String getAccountNumber() {
        return account;
    }
}


class creditAccount extends Account {

    int credit;

    public creditAccount(String account, int x) {
        super(account);
        credit = x;
    }


    public void addMoney(int x) {
        money += x;

    }


    public void transferMoney(int x) {
        if ((money -= x) < 0 - credit) {
            money += x;
            System.out.println("Not enough credit.");
        } else {
            if (money < 0) {
                credit -= money;
            }
        }
    }


    public void getAccountInfo() {
    }


    public String getAccountNumber() {
        return account;
    }
}









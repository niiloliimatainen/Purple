package com.example.purple;

public abstract class Account {
    protected int money;
    protected String account;

    public Account(String account, int money) {
        this.money = money;
        this.account = account;
    }


    public abstract void addMoney(int x);
    public abstract void getMoney(int x);
    public abstract void getInfo();
    public abstract String getAccountNumber();
}



class Regular extends Account {

    public Regular(String account, int money) {
        super(account, money);
    }


    public void addMoney(int x) {
        money += x;
    }

    public void getMoney(int x) {
        if ((money -= x) < 0) {
            money += x;
            System.out.println("Not enough money.");
        }
    }

    public void getInfo() {
        System.out.println("Account number: " + account + " Amount of money: " + money);
    }

    public String getAccountNumber() {
        return account;
    }
}


class Credit extends Account {

    int credit;

    public Credit(String account, int money, int x) {
        super(account, money);
        credit = x;
    }


    public void addMoney(int x) {
        money += x;

    }

    public void getMoney(int x) {
        if ((money -= x) < 0 - credit) {
            money += x;
            System.out.println("Not enough credit.");
        } else {
            if (money < 0) {
                credit -= money;
            }
        }
    }

    public void getInfo() {
        System.out.println("Account number: " + account + " Amount of money: " + money + " Credit limit: " + credit);
    }

    public String getAccountNumber() {
        return account;
    }
}










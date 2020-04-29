package com.example.purple;

//Class for creating user. Abstract class shows the structure of user classes.
import java.util.ArrayList;

public abstract class User {
    protected String firstName, lastName, email, phoneNumber, password;

    public User(String fname, String lname,String email, String pnumber, String pword) {
        this.firstName = fname;
        this.lastName = lname;
        this.email = email;
        this.phoneNumber = pnumber;
        this.password = pword;

    }

    public abstract String getUserEmail();
    public abstract String getUserPassword();
    public abstract void addAccount(int flag);
    public abstract void addMoney(int flag, double money);
    public abstract void selfTransfer(int pay, int receive, double money);
    public abstract void delAccount();
    public abstract ArrayList<Account> getAccounts();
    public abstract void editUser();
    public abstract double getMoneyAmount();
}


class regularUser extends User {
    private ArrayList<Account> accounts = new ArrayList<Account>();

    public regularUser(String fname, String lname,String email, String pnumber, String pword) {
        super(fname, lname, email, pnumber, pword);

    }


    @Override
    public String getUserEmail() {
        return email;
    }


    @Override
    public String getUserPassword() {
        return password;
    }


    @Override
    public void addAccount(int flag) {
        ArrayList<Account> initializer = new ArrayList<Account>();
        if (accounts == null) {
            String accountNumber = numberHandler.setAccountNumber();
            Account account = new Account(accountNumber, flag);
            initializer.add(account);
        } else {
            initializer = accounts;
            String accountNumber = numberHandler.setAccountNumber();
            Account account = new Account(accountNumber, flag);
            initializer.add(account);
        }
        accounts = initializer;
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println(accounts.get(i).getAccountNumber());
        }
    }


    @Override
    public double getMoneyAmount() {
        double money = 0;
        for (int i = 0; i < accounts.size(); i++) {
               money += accounts.get(i).getMoneyAmount();
        }
        return money;
    }

    @Override
    public void addMoney(int index, double money) {
        accounts.get(index).addMoney(money);
    }


    @Override
    public void selfTransfer(int pay, int receive, double money) {

    }


    @Override
    public void editUser() {

    }


    @Override
    public ArrayList<Account> getAccounts() {
        return accounts;
    }


    @Override
    public void delAccount() {

    }
}


class Admin extends User {

    public Admin(String fname, String lname,String email, String pnumber, String pword) {
        super(fname, lname, email, pnumber, pword);
    }


    @Override
    public String getUserEmail() {
        return email;
    }


    @Override
    public String getUserPassword() {
        return password;
    }


    @Override
    public void addAccount(int flag) {

    }


    @Override
    public void selfTransfer(int pay, int receive, double money) {

    }

    @Override
    public void editUser() {

    }


    @Override
    public ArrayList<Account> getAccounts() {
        return null;
    }


    @Override
    public double getMoneyAmount() {
        return 0;
    }

    @Override
    public void delAccount() {

    }

    @Override
    public void addMoney(int flag, double money) {
    }

    //All methods below this apply to all users

    public void delAnyAccount() {

    }


    public void getAnyAccount() {

    }

    public void changeAnyUserInfo() {

    }

}

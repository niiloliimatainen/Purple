package com.example.purple;

//Class for creating user. Abstract class shows the structure of user classes.
import java.util.ArrayList;

public abstract class User {
    protected String firstName, lastName, email, phoneNumber, password;
    protected Account account1, account2, account3;
    protected int counter = 0;


    public User(String fname, String lname,String email, String pnumber, String pword) {
        this.firstName = fname;
        this.lastName = lname;
        this.email = email;
        this.phoneNumber = pnumber;
        this.password = pword;

    }

    public abstract String getUserEmail();
    public abstract String getUserPassword();
    public abstract boolean addAccount(int flag);
    public abstract void addMoney(int flag, double money);
    public abstract void selfTransfer(int pay, int receive, double money);
    public abstract void delAccount();
    public abstract ArrayList<String> getAccounts();
    public abstract void editUser();
    public abstract double getMoneyAmount();
}


class regularUser extends User {

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
    public boolean addAccount(int flag) {
       if (this.counter == 0) {
           String accountNumber = numberHandler.setAccountNumber();
           this.account1 = new Account(accountNumber, flag);
           this.counter += 1;
           System.out.println(this.counter);
       } else if (this.counter == 1) {
           String accountNumber = numberHandler.setAccountNumber();
           this.account2 = new Account(accountNumber, flag);
           this.counter += 1;

       } else if (this.counter == 2) {
           String accountNumber = numberHandler.setAccountNumber();
           this.account3 = new Account(accountNumber, flag);
           this.counter += 1;
       } else {
           return false;
       }
       return true;
    }


    @Override
    public double getMoneyAmount() {
        double money = 0;

        if (this.counter == 1) {
            money += this.account1.getMoneyAmount();
        } else if (this.counter == 2) {
            money += this.account1.getMoneyAmount() + this.account2.getMoneyAmount();
        } else if (this.counter == 3) {
            money += this.account1.getMoneyAmount() + this.account2.getMoneyAmount() + this.account3.getMoneyAmount();
        }
        return money;
    }

    @Override
    public void addMoney(int index, double money) {
    }


    @Override
    public void selfTransfer(int pay, int receive, double money) {

    }


    @Override
    public void editUser() {

    }


    @Override
    public ArrayList<String> getAccounts() {
        ArrayList<String> list = new ArrayList<>();
        if (this.counter == 1) {
            list.add(this.account1.getAccountNumber());
        } else if (this.counter == 2) {
            list.add(this.account1.getAccountNumber());
            list.add(this.account2.getAccountNumber());
        } else if (this.counter == 3) {
            list.add(this.account1.getAccountNumber());
            list.add(this.account2.getAccountNumber());
            list.add(this.account3.getAccountNumber());
        }
        return list;
    }


    @Override
    public void delAccount() {

    }
}

/*
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
*/
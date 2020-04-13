package com.example.purple;

//Class for creating user. Abstract class shows the structure of user classes.

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
    public abstract void changeUserInfo();
    public abstract void addAccount();
    public abstract void delAccount();
    public abstract void getAccount();
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
    public void addAccount() {
        String accountNumber = numberHandler.setAccountNumber();
        Account account = new regularAccount(accountNumber);
    }


    @Override
    public void changeUserInfo() {

    }


    @Override
    public void getAccount() {

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
    public void addAccount() {

    }


    @Override
    public void changeUserInfo() {

    }


    @Override
    public void getAccount() {

    }


    @Override
    public void delAccount() {

    }

    //All methods below this apply to all users

    public void delAnyAccount() {

    }


    public void getAnyAccount() {

    }

    public void changeAnyUserInfo() {

    }

}

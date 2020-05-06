package com.example.purple;

//joo

import java.util.ArrayList;




public class Card {
    private String cardNumber, country = "Finland";
    private int PIN, CVC, account, raiseLimit = 500;
    private double creditLimit;
    private boolean isCredit;
    private String[] countryArray = new String[]{"Finland","United States", "Norway", "Sweden", "Denmark", "Canada", "United Kingdom", "Switzerland", "Germany"};
    private ArrayList<String> areaToUseList;


    public Card(String cardNumber, int PIN, int CVC, int account, boolean isCredit, double creditLimit) {
        this.cardNumber = cardNumber;
        this.PIN = PIN;
        this.CVC = CVC;
        this.isCredit = isCredit;
        this.creditLimit = creditLimit;
        this.account = account;
    }

    public String getCardNumber(){
        return cardNumber;
    }

    public int getCardRaiseLimit() {
        return raiseLimit;
    }

    public boolean testPin(int pin){
        if(pin == PIN){
            return true;
        }else{
            return false;
        }
    }

    public int getAccount(){
    return account;
}

    public int getCardPin() {
        return PIN;
    }

    public double getCardCreditLimit() {
        return creditLimit;
    }


    public ArrayList<String> getAreaToUseList(){
        if(areaToUseList == null){
            areaToUseList = new ArrayList<>();
            String defaultCountry = "Finland";
            areaToUseList.add(defaultCountry);
        }
        return areaToUseList;
    }

    public boolean isCreditCard(){return isCredit;}

    public int raiseMoney(double money) {
        if (raiseLimit < money) {
            return 0;
        } else {
            return 1;
        }
    }



    public int creditPayment(double money) {
        if(isCredit) {
            if (money <= creditLimit) {
                creditLimit -= money;
                return 1;
            }
        }
        return 0;
    }
}






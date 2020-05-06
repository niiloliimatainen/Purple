package com.example.purple;

//joo

import java.util.ArrayList;
import java.util.Arrays;

public class Card {
    private String cardNumber, country = "Finland";
    private int PIN, CVC, account, raiseLimit = 500;
    private boolean isCredit;
    private double creditLimit;
    private Bank bank = Bank.getInstance();
    private String[] countryArray = new String[]{"Finland","United States", "Norway", "Sweden", "Denmark", "Canada", "United Kingdom", "Switzerland", "Germany"};
    private ArrayList<String> areaToUseList;

    public Card(String cardNumber, int PIN, int CVC, int account, boolean isCredit, double creditLimit) {
        this.cardNumber = cardNumber;
        this.PIN = PIN;
        this.CVC = CVC;
        this.account = account;
        this.isCredit = isCredit;
        this.creditLimit = creditLimit;

    }

    public String getCardNumber(){
        return cardNumber;
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
            if (bank.cardPayment(account, money) == 1) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    public boolean testPin(int pin){
        return pin == PIN;
    }

    public int payment(double money) {

        if (bank.cardPayment(account, money) == 1) {
            return 1;
        } else {
            return 0;
        }
    }


    public void changeCardInfo() {

    }

    public int creditPayment(double money) {
        if(isCredit) {
            if (money <= creditLimit) {
                bank.saveCredit(account);
                creditLimit -= money;
                return 1;
            }
        }
        return 0;
    }
}






package com.example.purple;

//joo

import java.util.ArrayList;
import java.util.Arrays;

public class Card {
    private String cardNumber;
    private String[] countryArray = new String[]{"United States", "Finland", "Norway", "Sweden", "Denmark", "Canada", "United Kingdom", "Switzerland", "Germany"};
    private int PIN, CVC, raiseLimit = 500;
    private boolean isCredit;
    private Account account;
    private String[] defaultArea = new String[]{"Finland"};
    private ArrayList<String> areaToUseList;

    public Card(String cardNumber, int PIN, int CVC, Account account) {
        this.cardNumber = cardNumber;
        this.PIN = PIN;
        this.CVC = CVC;
        this.account = account;
        this.isCredit = false;
        this.areaToUseList = (ArrayList<String>) Arrays.asList(defaultArea);
    }

    public String getCardNumber(){
        return cardNumber;
    }

    public ArrayList<String> getAreaToUseList(){
        return areaToUseList;

    }


    public boolean isCreditCard(){return isCredit;}

    public int raiseMoney(double money) {
        if (raiseLimit < money) {
            return 0;
        } else {
            if (account.transferMoney(money) == 1) {
                return 1;
            } else {
                return 0;
            }
        }
    }


    public int payment(double money) {
        if (account.transferMoney(money) == 1) {
            return 1;
        } else {
            return 0;
        }
    }


    public void changeCardInfo() {

    }
}



class creditCard extends Card {
    double creditLimit;


    public creditCard(String cardNumber, int PIN, int CVC, double creditLimit, Account account) {
        super(cardNumber, PIN, CVC, account);
        this.creditLimit = creditLimit;
        this.isCredit = true;
    }


    public int creditPayment(double money) {
        if (money <= creditLimit) {
            creditLimit -= money;
            return 1;
        }
        return 0;
    }

}





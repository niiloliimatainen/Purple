package com.example.purple;

//joo

public class Card {
    protected String cardNumber, country;
    protected int year, raiseLimit = 500;
    protected Account account;

    public Card(String cardNumber, String country, int year, Account account) {
        this.cardNumber = cardNumber;
        this.country = country;
        this.year = year;
        this.account = account;
    }


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

    public creditCard(String cardNumber, String country, int year, double creditLimit, Account account) {
        super(cardNumber, country, year, account);
        creditLimit = creditLimit;
    }


    public int creditPayment(double money) {
        if (money <= creditLimit) {
            creditLimit -= money;
            return 1;
        }
        return 0;
    }

}





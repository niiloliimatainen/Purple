package com.example.purple;

//joo

public class Card {
    protected String cardNumber, country = "Finland";
    protected int PIN, CVC, raiseLimit = 500;
    protected Account account;

    public Card(String cardNumber, int PIN, int CVC, Account account) {
        this.cardNumber = cardNumber;
        this.PIN = PIN;
        this.CVC = CVC;
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

    public creditCard(String cardNumber, int PIN, int CVC, double creditLimit, Account account) {
        super(cardNumber, PIN, CVC, account);
        this.creditLimit = creditLimit;
    }


    public int creditPayment(double money) {
        if (money <= creditLimit) {
            creditLimit -= money;
            return 1;
        }
        return 0;
    }

}





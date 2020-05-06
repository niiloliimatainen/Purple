package com.example.purple;

//joo

public class Card {
    private String cardNumber, country = "Finland";
    private int PIN, CVC, raiseLimit = 500;
    private double creditLimit;
    private boolean isCredit;


    public Card(String cardNumber, int PIN, int CVC, boolean isCredit, double creditLimit) {
        this.cardNumber = cardNumber;
        this.PIN = PIN;
        this.CVC = CVC;
        this.isCredit = isCredit;
        this.creditLimit = creditLimit;
    }

    public String getCardNumber(){
        System.out.println("Minua kysyttiin getcardnumber card123");
        return cardNumber;
    }

    public int getCardRaiseLimit() {
        return raiseLimit;
    }


    public int getCardPin() {
        return PIN;
    }


    public double getCardCreditLimit() {
        return creditLimit;
    }


    public boolean isCreditCard(){return isCredit;}


    public void editCard(int change, int flag) {
        if (flag == 1) {
            raiseLimit = change;
        } else if (flag == 2) {
            PIN = change;
        } else if (flag == 3) {
            if (change == 1) {
                isCredit = true;
            } else {
                isCredit = false;
            }
        } else if (flag == 4) {
            creditLimit = change;
        }
    }



    /*public void editCard(String change, int flag) {
        if (flag == 1) {
            raiseLimit = Integer(
            lastName = change;
        } else if (flag == 3) {
            email = change;
        } else if (flag == 4) {
            phoneNumber = change;
        } else {
            password = change;
        }
    }

    /*public int raiseMoney(double money) {
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
*/

}




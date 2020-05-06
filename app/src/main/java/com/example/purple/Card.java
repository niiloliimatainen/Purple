package com.example.purple;

//joo

public class Card {
    protected String cardNumber, country = "Finland";
    protected int PIN, CVC, raiseLimit = 500;
    protected boolean isCredit;

    public Card(String cardNumber, int PIN, int CVC) {
        this.cardNumber = cardNumber;
        this.PIN = PIN;
        this.CVC = CVC;
        this.isCredit = false;
    }

    public String getCardNumber(){
        System.out.println("Minua kysyttiin getcardnumber card123");
        return cardNumber;
    }

    public boolean isCreditCard(){return isCredit;}

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




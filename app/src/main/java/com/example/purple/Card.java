package com.example.purple;

//joo

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Card {
    protected String cardNumber;
    protected String[] countryArray = new String[]{"United States", "Finland", "Norway", "Sweden", "Denmark", "Canada", "United Kingdom", "Switzerland", "Germany"};
    protected int PIN, CVC, raiseLimit = 500;
    protected boolean isCredit;
    protected Account account;
    protected String[] defaultArea = new String[]{"Finland"};
    protected ArrayList<String> areaToUseList;


    public Card(String cardNumber, int PIN, int CVC, Account account) {
        this.cardNumber = cardNumber;
        this.PIN = PIN;
        this.CVC = CVC;
        this.account = account;
        this.isCredit = false;
        this.areaToUseList = (ArrayList<String>) Arrays.asList(defaultArea);
    }

        String getCardNumber(){
            return cardNumber;
        }

        int payment(double money) {
            if (account.transferMoney(money) == 1) {
                return 1;
            } else {
                return 0;
            }
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

        public boolean isCreditCard() {
            return isCredit;
        }

        public abstract void changeCardInfo();

        public abstract ArrayList<String> getAreaToUseList();

        public abstract int raiseCreditMoney(double money);




    public abstract int creditPayment(double money);
}

    class debitCard extends Card {
        public debitCard(String cardNumber, int PIN, int CVC, Account account) {
            super(cardNumber, PIN, CVC, account);
            this.isCredit = false;
        }


        public void changeCardInfo() {

        }

        @Override
        public ArrayList<String> getAreaToUseList() {return areaToUseList; }

        @Override
        public int creditPayment(double money) {return 0;}

        @Override
        public int raiseCreditMoney(double money) {return 0;}
    }



    class creditCard extends Card {
        private double creditLimit;

        public creditCard(String cardNumber, int PIN, int CVC, double creditLimit, Account account) {
            super(cardNumber, PIN, CVC, account);
            this.creditLimit = creditLimit;
            this.isCredit = true;
        }


        @Override
        public ArrayList<String> getAreaToUseList() {
            return areaToUseList;
        }


        @Override
        public int creditPayment(double money) {
            if (money <= creditLimit) {
                creditLimit -= money;
                return 1;
            }
            return 0;
        }


        public int raiseCreditMoney(double money) {
            if (raiseLimit < money) {
                return 0;
            } else {
                if (money <= creditLimit) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }

        public boolean isCreditCard() {
            return false;
        }

        public void changeCardInfo() {

        }
    }


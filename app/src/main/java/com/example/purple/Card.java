package com.example.purple;
import java.util.ArrayList;
//joo


public class Card {
    private String cardNumber;
    private int PIN, CVC, account, raiseLimit = 500;
    private double creditLimit;
    private boolean isCredit;
    private String[] countryArray = new String[]{"Finland", "United States", "Norway", "Sweden", "Denmark", "Canada", "United Kingdom", "Switzerland", "Germany"};
    private ArrayList<String> areaToUseList;


    public Card(String cardNumber, int PIN, int CVC, int account, boolean isCredit, double creditLimit) {
        this.cardNumber = cardNumber;
        this.PIN = PIN;
        this.CVC = CVC;
        this.isCredit = isCredit;
        this.creditLimit = creditLimit;
        this.account = account;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public int getCardRaiseLimit() {
        return raiseLimit;
    }

    public boolean testPin(int pin) {
        if (pin == PIN) {
            return true;
        } else {
            return false;
        }
    }

    public String[] getCountryArray(){
        return countryArray;
    }

    public void addCountry(int index){
        boolean flag = true;
        for(int i = 0; i < areaToUseList.size(); i++){
           if(areaToUseList.get(i).equals(countryArray[index])){
               flag = false;
           }
        }if(flag){
            areaToUseList.add(countryArray[index]);
        }
    }
    public void removeCountry(int index){
        if(areaToUseList.isEmpty()){
            areaToUseList.add("Finland");
        }else {
            areaToUseList.remove(index);
        }
    }

    public int getAccount() {
        return account;
    }

    public int getCardPin() {
        return PIN;
    }


    public double getCardCreditLimit() {
        return creditLimit;
    }

    public int creditPayment(double money){
        if(creditLimit >= money){
            creditLimit -= money;
            return 1;
        }else {
            return 0;
        }
    }

    public ArrayList<String> getAreaToUseList() {
        if (areaToUseList == null) {
            areaToUseList = new ArrayList<>();
            String defaultCountry = "Finland";
            areaToUseList.add(defaultCountry);
        }
        return areaToUseList;
    }

    public boolean isCreditCard() {
        return isCredit;
    }

    public int raiseMoney(double money) {
        if (raiseLimit < money) {
            return 0;
        } else {
            return 1;
        }
    }

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

}




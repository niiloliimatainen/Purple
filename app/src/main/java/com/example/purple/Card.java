package com.example.purple;

public abstract class Card {
    protected String account, cardNumber, country;
    protected int year, raiseLimit;

    public Card(String cardNumber, String account, String country, int year, int raiseLimit) {
        this.cardNumber = cardNumber;
        this.country = country;
        this.year = year;
    }

    public abstract void raiseMoney();
    public abstract void payment();
    public abstract void changeCardInfo();
}

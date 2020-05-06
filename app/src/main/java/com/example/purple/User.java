package com.example.purple;

//Class for creating user. Abstract class shows the structure of user classes.
import java.lang.reflect.Array;
import java.util.ArrayList;

public class User {
    protected String firstName, lastName, email, phoneNumber, password;
    protected Account account1, account2, account3;
    protected  Card card1, card2, card3;
    protected int accCounter = 0, cardCounter = 0;

    public User(String fname, String lname,String email, String pnumber, String pword) {
        this.firstName = fname;
        this.lastName = lname;
        this.email = email;
        this.phoneNumber = pnumber;
        this.password = pword;
    }


    public String getUserEmail() {
        return email;
    }



    public String getUserPassword() {
        return password;
    }



    public boolean addAccount(int payPossibility, String accountNumber) {
        if (accCounter == 0) {
            account1 = new Account(accountNumber, payPossibility);
            accCounter += 1;
            System.out.println(accCounter);
        } else if (accCounter == 1) {
            account2 = new Account(accountNumber, payPossibility);
            accCounter += 1;

        } else if (accCounter == 2) {
            account3 = new Account(accountNumber, payPossibility);
            accCounter += 1;
        } else {
            return false;
        }
        return true;
    }



    public boolean addCard(int index, String cardNumber, int CVC, int PIN) {
        if (index == 1) {
            if (account1.getCards() == 0) {
                account1.addCard();
                card1 = new debitCard(cardNumber, PIN, CVC, account1);
                cardCounter += 1;
                return true;
            }

        } else if (index == 2) {
            if (account2.getCards() == 0) {
                account2.addCard();
                card2 = new debitCard(cardNumber, PIN, CVC, account2);
                cardCounter += 1;
                return true;
            }

        } else if (index == 3) {
            if (account3.getCards() == 0) {
                account3.addCard();
                card3 = new debitCard(cardNumber, PIN, CVC, account3);
                cardCounter += 1;
                return true;
            }
        }
        return false;
    }


    public boolean addCreditCard(int index, String cardNumber, int CVC, int PIN, double creditLimit) {
        if (index == 1) {
            if (account1.getCards() == 0) {
                account1.addCard();
                card1 = new creditCard(cardNumber, PIN, CVC, creditLimit, account1);
                cardCounter +=1;
                return true;
            }

        } else if (index == 2) {
            if (account2.getCards() == 0) {
                account2.addCard();
                card2 = new creditCard(cardNumber, PIN, CVC, creditLimit, account2);
                cardCounter +=1;
                return true;
            }

        } else if (index == 3) {
            if (account3.getCards() == 0) {
                account3.addCard();
                card3 = new creditCard(cardNumber, PIN, CVC, creditLimit, account3);
                cardCounter +=1;
                return true;
            }
        }
        return false;
    }


    public double getMoneyAmount() {
        double money = 0;

        if (accCounter == 1) {
            money += account1.getMoneyAmount();
        } else if (accCounter == 2) {
            money += account1.getMoneyAmount() + account2.getMoneyAmount();
        } else if (accCounter == 3) {
            money += account1.getMoneyAmount() + account2.getMoneyAmount() + account3.getMoneyAmount();
        }
        return money;
    }


    public void addMoney(int index, double money) {
        if (index == 1) {
            account1.addMoney(money);
        } else if (index == 2) {
            account2.addMoney(money);
        } else if (index == 3) {
            account3.addMoney(money);
        }
    }


    public int transferMoney(int index, double money) {
        if (index == 1) {
            if (account1.getPayPossibility() == 0) {
                return 0;
            }
            if (account1.transferMoney(money) == 1) {
                return 1;
            }
        } else if (index == 2) {
            if (account2.getPayPossibility() == 0) {
                return 0;
            }
            if (account2.transferMoney(money) == 1) {
                return 1;
            }
        } else if (index == 3) {
            if (account3.getPayPossibility() == 0) {
                return 0;
            }
            if (account3.transferMoney(money) == 1) {
                return 1;
            }
        }
        return 2;
    }


    public int selfTransfer(int pay, int receive, double money) {
        int flag = 0;
        if (pay == 1) {
            if (account1.transferMoney(money) == 1) {
                flag = 1;
            }
        } else if (pay == 2) {
            if (account2.transferMoney(money) == 1) {
                flag = 1;
            }
        } else if (pay == 3) {
            if (account3.transferMoney(money) == 1) {
                flag = 1;
            }
        }
        if (receive == 1) {
            account1.addMoney(money);
        } else if (receive == 2) {
            account2.addMoney(money);
        } else if (receive == 3) {
            account3.addMoney(money);
        }
        return flag;
    }


    public void editUserInfo(String change, int flag) {
        if (flag == 1) {
            firstName = change;
        } else if (flag == 2) {
            lastName = change;
        } else if (flag == 3) {
            email = change;
        } else if (flag == 4) {
           phoneNumber = change;
        } else {
            password = change;
        }

    }

    public Card getCardObject(int index){
        if((index == 1) && (card1 != null)) {
            return card1;
        } else if ((index == 2) && (card2 != null)) {
            return card2;
        } else if ((index == 3) && (card3 != null)) {
            return card3;
        }
        return null;
    }

    public ArrayList<String> getAccounts() {
        ArrayList<String> list = new ArrayList<>();
        if (accCounter == 1) {
            list.add(account1.getAccountNumber());
        } else if (accCounter == 2) {
            list.add(account1.getAccountNumber());
            list.add(account2.getAccountNumber());
        } else if (accCounter == 3) {
            list.add(account1.getAccountNumber());
            list.add(account2.getAccountNumber());
            list.add(account3.getAccountNumber());
        }
        return list;
    }


    public String getCardNumber (int index) {
        String cardNumber = "";
        if((index == 1) && (card1 != null)) {
            cardNumber = card1.getCardNumber();
        } else if ((index == 2) && (card2 != null)) {
            cardNumber = card2.getCardNumber();
        } else if ((index == 3) && (card3 != null)) {
            cardNumber = card3.getCardNumber();
        }
        return cardNumber;
    }


    public void deleteAccount(int index) {
        if (index == 1) {
            account1 = null;
            account1 = account2;
            account2 = account3;
            account3 = null;
        } else if (index == 2) {
            account2 = null;
            account2 = account3;
            account3 = null;
        } else if (index == 3) {
            account3 = null;
        }
        accCounter -= 1;
    }


    public double getAccountsMoneyAmount(int index) {
        double money = 0;
        if (index == 1) {
            money = account1.getMoneyAmount();
        } else if (index == 2) {
            money = account2.getMoneyAmount();
        } else if (index == 3) {
            money = account3.getMoneyAmount();
        }
        return money;
    }


    public boolean isCardCreditCard(int index){
        boolean isCredit = true;
        if((index == 1) && (card1 != null)){
            isCredit = card1.isCreditCard();
        }else if ((index == 2) && (card2 != null)){
            isCredit = card2.isCreditCard();
        }else if ((index == 3) && (card3 != null)){
            isCredit = card3.isCreditCard();
        }
        return isCredit;
    }


    public int getAccountsPayPossibility(int index) {
        int payPossibility = 0;
        if (index == 1) {
            payPossibility = account1.getPayPossibility();
        } else if (index == 2) {
            payPossibility = account2.getPayPossibility();
        } else if (index == 3) {
            payPossibility = account3.getPayPossibility();
        }
        return payPossibility;
    }


    public String getAccountNumber (int index) {
        String accountNumber = "";
        if (index == 1) {
            accountNumber = account1.getAccountNumber();
        } else if (index == 2) {
            accountNumber = account2.getAccountNumber();
        } else if (index == 3) {
            accountNumber = account3.getAccountNumber();
        }
        return accountNumber;
    }


    public String getName() {
        String name = firstName + " " + lastName;
        return name;
    }


    public String getUserInfo(int flag) {
        if (flag == 1) {
            return firstName;
        } else if (flag == 2) {
            return lastName;
        } else if (flag == 3) {
            return email;
        } else if (flag == 4) {
            return phoneNumber;
        } else {
            return password;
        }
    }


    public void deleteCard(int index) {
        if (index == 1) {
            card1 = null;
            card1 = card2;
            card2 = card3;
            card3 = null;
        } else if (index == 2) {
            card2 = null;
            card2 = card3;
            card3 = null;
        } else if (index == 3) {
            card3 = null;
        }
        cardCounter -= 1;
    }

}



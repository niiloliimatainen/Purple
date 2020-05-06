package com.example.purple;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Bank {
    private static Bank bank = new Bank();
    private ArrayList<User> userList = new ArrayList<>();
    private int currentUser;
    private Context context;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");


    private Bank() {
       String BIC = "BOFAAFIHH";
       String bankName = "Bank of Finland";
    }

    public static Bank getInstance() {
        return bank;
    }


    public int addUser(String fname, String lname, String email, String pnumber, String pword, Context Context) {
            context = Context;
            User user = new User(fname, lname, email, pnumber, pword);
            for (int i = 0; i < userList.size(); i++) {
                if (email.equals(userList.get(i).getUserEmail())) {
                    return 0;
                }
            }

            userList.add(user);
            databaseConnector.writeToFile(context, userList);
            return 1;
            //admini viel
    }


    public int login(String email, String password, Context Context) {
        context = Context;
        userList.clear();
        userList = databaseConnector.readFromFile(context);
        for (int i = 0; i < userList.size(); i++) {
            if (email.equals(userList.get(i).getUserEmail()) && (password.equals(userList.get(i).getUserPassword()))) {
                currentUser = i;
                return 1;
            }
        }
        return 0;
    }


    public boolean addAccount(int flag) {
        String accountNumber = numberHandler.setAccountNumber();
        if (userList.get(currentUser).addAccount(flag, accountNumber)) {
            databaseConnector.writeToFile(context, userList);
            return true;
        } else {
            return false;
        }
    }


    public void addMoney(int index, double money) {
        Date date = new Date();
        String accountNumber = userList.get(currentUser).getAccountNumber(index);
        String transaction = String.format("%s        %s         +%s",sdf.format(date), userList.get(currentUser).getName(), money);
        System.out.println(transaction);
        userList.get(currentUser).addMoney(index, money);
        databaseConnector.writeToFile(context, userList);
        databaseConnector.saveBankStatement(context, accountNumber, transaction);
    }


    public int selfTransfer(int pay, int receive, double money) {
        Date date = new Date();
        String accountNumber1 = userList.get(currentUser).getAccountNumber(pay);
        String accountNumber2 = userList.get(currentUser).getAccountNumber(receive);
        String transaction1 = String.format("%s        %s         +%s",sdf.format(date), userList.get(currentUser).getName(), money);
        String transaction2 = String.format("%s        %s         -%s",sdf.format(date), userList.get(currentUser).getName(), money);

        if (userList.get(currentUser).selfTransfer(pay, receive, money) == 1) {
            databaseConnector.writeToFile(context, userList);
            databaseConnector.saveBankStatement(context, accountNumber1, transaction1);
            databaseConnector.saveBankStatement(context, accountNumber2, transaction2);
            return 1;
        }
        return 0;
    }


    public int transferMoney(String receivingAcc, int payAccount, double money) {
        Date date = new Date();
        String accountNumber = userList.get(currentUser).getAccountNumber(payAccount);
        ArrayList<String> list;

        for (int i = 0; i < userList.size(); i++) {
            list = userList.get(i).getAccounts();
            for (int x = 0; x < list.size(); x++) {
                System.out.println(receivingAcc + " " + list.get(x));
                if (receivingAcc.equals(list.get(x))) {
                    if ((userList.get(currentUser).transferMoney(payAccount, money)) == 1) {
                        userList.get(i).addMoney(x + 1, money);
                        String transaction1 = String.format("%s        %s         +%s",sdf.format(date), userList.get(currentUser).getName(), money);
                        String transaction2 = String.format("%s        %s         -%s",sdf.format(date), userList.get(i).getName(), money);
                        databaseConnector.writeToFile(context, userList);
                        databaseConnector.saveBankStatement(context, accountNumber, transaction2);
                        databaseConnector.saveBankStatement(context, receivingAcc, transaction1);

                        return 1;
                    } else if ((userList.get(currentUser).transferMoney(payAccount, money)) == 0){
                        return 2;
                    } else {
                        return 3;
                    }

                }
            }
        }
        return 4;
    }


    public ArrayList<String> getAccounts() {
        ArrayList<String> accountList = userList.get(currentUser).getAccounts();
        return accountList;
    }


    public String getCardNumber(int index){
        String card = userList.get(currentUser).getCardNumber(index);
        return card;
    }

    public double getMoneyAmount() {
        double money = userList.get(currentUser).getMoneyAmount();
        return money;
    }


    public double getAccountsMoneyAmount(int index) {
       double money = userList.get(currentUser).getAccountsMoneyAmount(index);
        return money;
    }


    public String getAccountsPayPossibility(int index) {

        if ((userList.get(currentUser).getAccountsPayPossibility(index)) == 1) {
            return "Regular";
        } else {
            return "Savings";
        }
    }

    public String isCardCreditCard(int index){
        if(userList.get(currentUser).isCardCreditCard(index)){
            return "Credit";
        } else {
            return "Debit";
        }
    }


    public boolean addCard(int index) {
        String cardNumber = numberHandler.setCardNumber();
        int CVC = numberHandler.setCVC();
        int PIN = numberHandler.setPIN();
        if (userList.get(currentUser).addCard(index, cardNumber, CVC, PIN)) {
            databaseConnector.writeToFile(context, userList);
            return true;
        } else {
            return false;
        }
    }


    public boolean addCreditCard(int index, double creditLimit) {
        String cardNumber = numberHandler.setCardNumber();
        int CVC = numberHandler.setCVC();
        int PIN = numberHandler.setPIN();
        if (userList.get(currentUser).addCreditCard(index, cardNumber, CVC, PIN, creditLimit)) {
            databaseConnector.writeToFile(context, userList);
            return true;
        } else {
            return false;
        }
    }

    public Card getCardObj(int index){
        return userList.get(currentUser).getCardObject(index);
    }

    public String getAccountNumber(int index) {
        return userList.get(currentUser).getAccountNumber(index);
    }


   public String getUserInfo(int flag) {
       String info = userList.get(currentUser).getUserInfo(flag);
       return info;
   }



   public void editUserInfo(String change, int flag) {
        userList.get(currentUser).editUserInfo(change, flag);
        databaseConnector.writeToFile(context, userList);
   }

   public void deleteAccount(int index) {
       userList.get(currentUser).deleteCard(index);
       userList.get(currentUser).deleteAccount(index);
       databaseConnector.writeToFile(context, userList);
   }


}

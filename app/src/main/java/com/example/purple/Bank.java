package com.example.purple;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

// heart of the program

public class Bank {
    private static Bank bank = new Bank();
    private User admin = new User("Kalle", "Jaakonpoika", "admin", "0453299483", "admin", "empty");
    private ArrayList<User> userList = new ArrayList<>();
    private int currentUser;
    private boolean isAdmin = false;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private numberHandler nh = new numberHandler();
    private databaseConnector data = new databaseConnector();


    private Bank() {
       String BIC = "BOFAAFIHH";
       String bankName = "Bank of Finland";
    }

    public static Bank getInstance() {
        return bank;
    }


    public int addUser(String fname, String lname, String email, String pnumber, String pword, String salt, Context context) {
            User user = new User(fname, lname, email, pnumber, pword, salt);
            for (int i = 0; i < userList.size(); i++) {
                if (email.equals(userList.get(i).getUserEmail())) {
                    return 0;
                }
            }
            userList = data.readFromFile(context);
            if (userList.isEmpty()) {
                userList.add(admin);
                userList.addAll(data.readFromFile(context));
            }

            userList.add(user);
            data.writeToFile(context, userList);
            return 1;
    }


    public int login(String email, String password, Context context) {
        userList.clear();
        userList = data.readFromFile(context);
        if (userList.isEmpty()) {
            userList.add(admin);
            userList.addAll(data.readFromFile(context));
        }

        if ((email.equals(admin.getUserEmail())) && (password.equals(admin.getUserPassword()))) {
            isAdmin = true;
            currentUser = 0;
            return 1;
        } else {
            for (int i = 0; i < userList.size(); i++) {
                // when logging in, input password has to be hashed with same salt to test if it is a match
                if (email.equals(userList.get(i).getUserEmail()) && (nh.hasher(password, userList.get(i).getSalt().getBytes()).equals(userList.get(i).getUserPassword()))) {
                    currentUser = i;
                    return 1;
                }
            }
        }
        return 0;
    }


    public boolean addAccount(int flag, Context context) {
        String accountNumber = nh.setAccountNumber();
        if (userList.get(currentUser).addAccount(flag, accountNumber)) {
            data.writeToFile(context, userList);
            return true;
        } else {
            return false;
        }
    }


    public void addMoney(int index, double money, Context context) {
        Date date = new Date();
        String accountNumber = userList.get(currentUser).getAccountNumber(index);
        String transaction = String.format("%s        %s           +%s",sdf.format(date), userList.get(currentUser).getName(), money);
        userList.get(currentUser).addMoney(index, money);
        data.writeToFile(context, userList);
        data.saveBankStatement(context, accountNumber, transaction);
    }


    public int selfTransfer(int pay, int receive, double money, Context context) {
        if (userList.get(currentUser).getAccountsPayPossibility(pay) == 0) {
            return 2;
        }
        Date date = new Date();
        String accountNumber1 = userList.get(currentUser).getAccountNumber(pay);
        String accountNumber2 = userList.get(currentUser).getAccountNumber(receive);
        String transaction1 = String.format("%s        %s           -%s",sdf.format(date), userList.get(currentUser).getName(), money);
        String transaction2 = String.format("%s        %s           +%s",sdf.format(date), userList.get(currentUser).getName(), money);

        if (userList.get(currentUser).selfTransfer(pay, receive, money) == 1) {
            data.writeToFile(context, userList);
            data.saveBankStatement(context, accountNumber1, transaction1);
            data.saveBankStatement(context, accountNumber2, transaction2);
            return 1;
        }
        return 0;
    }


    public int transferMoney(String receivingAcc, int payAccount, double money, Context context) {
        Date date = new Date();
        String accountNumber = userList.get(currentUser).getAccountNumber(payAccount);
        ArrayList<String> list;

        for (int i = 0; i < userList.size(); i++) {
            list = userList.get(i).getAccounts();
            for (int x = 0; x < list.size(); x++) {
                if (receivingAcc.equals(list.get(x))) {
                    if ((userList.get(currentUser).transferMoney(payAccount, money)) == 1) {
                        userList.get(i).addMoney(x + 1, money);
                        String transaction1 = String.format("%s        %s           +%s",sdf.format(date), userList.get(currentUser).getName(), money);
                        String transaction2 = String.format("%s        %s           -%s",sdf.format(date), userList.get(i).getName(), money);
                        data.writeToFile(context, userList);
                        data.saveBankStatement(context, accountNumber, transaction2);
                        data.saveBankStatement(context, receivingAcc, transaction1);

                        return 1;
                    } else if ((userList.get(currentUser).transferMoney(payAccount, money)) == 0){
                        return 2;
                    } else {
                        return 3;
                    }
                }
            }
        }
        //To external account(only in Finland)
        if ((receivingAcc.length() == 18) && (receivingAcc.contains("FI"))) {
            if ((userList.get(currentUser).transferMoney(payAccount, money)) == 1) {
                String transaction = String.format("%s        %s           -%s",sdf.format(date), "External user", money);
                data.writeToFile(context, userList);
                data.saveBankStatement(context, accountNumber, transaction);
                return 1;
            } else if ((userList.get(currentUser).transferMoney(payAccount, money)) == 0){
                return 2;
            } else {
                return 3;
            }
        } else {
            return 4;
        }
    }

    //Raise of money or payment
    public int cardTransaction(int account, double money, boolean isPayment, Context context) {
        Date date = new Date();
        String accountNumber = userList.get(currentUser).getAccountNumber(account);
        if (isPayment) {
            if (userList.get(currentUser).transferMoney(account, money) == 1) {
                String transaction = String.format("%s        %s           -%s", sdf.format(date), "External user", money);
                data.saveBankStatement(context, accountNumber, transaction);
                data.writeToFile(context, userList);
                return 1;
            } else {
                return 0;
            }

        } else {
            if (userList.get(currentUser).transferMoney(account, money) == 1) {
                String transaction = String.format("%s        %s           -%s",sdf.format(date), userList.get(currentUser).getName(), money);
                data.saveBankStatement(context, accountNumber, transaction);
                data.writeToFile(context, userList);
                return 1;
            } else {
                return 0;
            }
        }
    }


    //Save credit payments
    public void saveCredit(int account, Context context) {
        data.writeToFile(context, userList);
    }



    public Card getCardObj(int index){
        return userList.get(currentUser).getCardObject(index);
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


    public boolean addCard(int index, Context context) {
        String cardNumber = nh.setCardNumber();
        int CVC = nh.setCVC();
        int PIN = nh.setPIN();
        if (userList.get(currentUser).addCard(index, cardNumber, CVC, PIN)) {
            data.writeToFile(context, userList);
            return true;
        } else {
            return false;
        }
    }


    public boolean addCreditCard(int index, double creditLimit, Context context) {
        String cardNumber = nh.setCardNumber();
        int CVC = nh.setCVC();
        int PIN = nh.setPIN();
        if (userList.get(currentUser).addCreditCard(index, cardNumber, CVC, PIN, creditLimit)) {
            data.writeToFile(context, userList);
            return true;
        } else {
            return false;
        }
    }


    public String getAccountNumber(int index) {
        return userList.get(currentUser).getAccountNumber(index);
    }


   public String getUserInfo(int flag) {
       String info = userList.get(currentUser).getUserInfo(flag);
       return info;
   }


   public void editUserInfo(String change, int flag, Context context) {
       String hashedPw;
       String saltString;
       if(flag == 5) {
           byte[] salt = nh.getSalt();
           saltString = Arrays.toString(salt);
           hashedPw = nh.hasher(change, saltString.getBytes());
           userList.get(currentUser).editUserInfo(hashedPw, flag , saltString);
       }else{
           userList.get(currentUser).editUserInfo(change, flag, "");
       }

       data.writeToFile(context, userList);
   }

   public void deleteAccount(int index, Context context) {
       userList.get(currentUser).deleteCard(index);
       userList.get(currentUser).deleteAccount(index);
       data.writeToFile(context, userList);
   }


   public ArrayList<String> getAllUsers() {
        ArrayList<String> list = new ArrayList<>();
        String user;
        for (int i = 1; i < userList.size(); i++) {
            user = (i + "." + userList.get(i).getName());
            list.add(user);
        }
        return list;
   }


   public boolean isUserAdmin() {
        return isAdmin;
   }

   //Only admin method!
   public void setCurrentUser(int userToModify) {
        currentUser = userToModify;
   }


   public void resetCurrentUser() {
        currentUser = 0;
   }


   //Only admin can use
   public void deleteAll(Context context) {
        if (isAdmin) {
            userList.clear();
            userList.add(admin);
            data.writeToFile(context, userList);
        }
   }


   //Only admin can use
   public void deleteUser(int user, Context context) {
       if (isAdmin) {
            for (int i = 1; i < userList.size(); i++) {
                if (user == i) {
                    userList.remove(i);
                }
            }
           data.writeToFile(context, userList);
       }
   }


   public void editAccount(int index, int hasPayPossibility, Context context) {
        userList.get(currentUser).editAccount(index, hasPayPossibility);
        data.writeToFile(context, userList);
   }


   public void deleteCard(int index, Context context) {
        userList.get(currentUser).deleteCard(index);
        data.writeToFile(context, userList);
   }

   public int getCardRaiseLimit(int index) {
        int raiseLimit = userList.get(currentUser).getCardRaiseLimit(index);
        return raiseLimit;
   }


   public int getCardAmount() {
        int cardAmount = userList.get(currentUser).getCardAmount();
        return cardAmount;
   }


    public int getCardPin(int index) {
        int PIN = userList.get(currentUser).getCardPin(index);
        return PIN;
    }


    public double getCreditLimit(int index) {
        double creditLimit = userList.get(currentUser).getCardCreditLimit(index);
        return creditLimit;
    }


    public String isCardCreditCard(int index){
        if(userList.get(currentUser).isCardCreditCard(index)){
            return "Credit";
        } else {
            return "Debit";
        }
    }

    public void editCard(int change, int flag, int index, Context context) {
        userList.get(currentUser).editCard(change, flag, index);
        data.writeToFile(context, userList);
    }



}

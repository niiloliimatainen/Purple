package com.example.purple;

//Class for creating user. Abstract class shows the structure of user classes.
import java.util.ArrayList;

public class User {
    protected String firstName, lastName, email, phoneNumber, password;
    protected Account account1, account2, account3;
    protected int counter = 0;


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



    public boolean addAccount(int payPossibility) {
        if (counter == 0) {
            String accountNumber = numberHandler.setAccountNumber();
            account1 = new Account(accountNumber, payPossibility);
            counter += 1;
            System.out.println(counter);
        } else if (counter == 1) {
            String accountNumber = numberHandler.setAccountNumber();
            account2 = new Account(accountNumber, payPossibility);
            counter += 1;

        } else if (counter == 2) {
            String accountNumber = numberHandler.setAccountNumber();
            account3 = new Account(accountNumber, payPossibility);
            counter += 1;
        } else {
            return false;
        }
        return true;
    }



    public double getMoneyAmount() {
        double money = 0;

        if (counter == 1) {
            money += account1.getMoneyAmount();
        } else if (counter == 2) {
            money += account1.getMoneyAmount() + account2.getMoneyAmount();
        } else if (counter == 3) {
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



    public void editUser() {

    }



    public ArrayList<String> getAccounts() {
        ArrayList<String> list = new ArrayList<>();
        if (counter == 1) {
            list.add(account1.getAccountNumber());
        } else if (counter == 2) {
            list.add(account1.getAccountNumber());
            list.add(account2.getAccountNumber());
        } else if (counter == 3) {
            list.add(account1.getAccountNumber());
            list.add(account2.getAccountNumber());
            list.add(account3.getAccountNumber());
        }
        return list;
    }


    public void delAccount(int index) {
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
}



class Admin extends User {

    public Admin(String fname, String lname,String email, String pnumber, String pword) {
        super(fname, lname, email, pnumber, pword);
    }


    public void delAnyAccount() {

    }


    public void getAnyAccount() {

    }

    public void changeAnyUserInfo() {

    }

}

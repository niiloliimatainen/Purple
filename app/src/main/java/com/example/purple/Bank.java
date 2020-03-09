package com.example.purple;

import java.util.ArrayList;
import java.util.Scanner;

public class Bank {

    ArrayList<Account> accounts = new ArrayList<>();

    private Scanner sc = new Scanner(System.in);

    private String account;
    private int money, credit;

    public void addAccount(int flag) {

        if (flag == 1) {
            System.out.print("Give an account number: ");
            account = sc.next();

            System.out.print("Amount of money to deposit: ");
            money = sc.nextInt();

            Account ac = new Regular(account, money);
            accounts.add(ac);

        } else {
            System.out.print("Give an account number: ");
            account = sc.next();

            System.out.print("Amount of money to deposit: ");
            money = sc.nextInt();

            System.out.print("Give a credit limit: ");
            credit = sc.nextInt();

            Account cac = new Credit(account, money, credit);
            accounts.add(cac);
        }
        System.out.println("Account created.");
    }

    public void delAccount() {
        System.out.print("Give the account number of the account to delete: ");
        account = sc.next();

        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getAccountNumber().equals(account)) {
                accounts.remove(i);
                break;
            }
        }

        System.out.println("Account removed.");
    }


    public void getAccount(String account, int flag) {
        int index = 0;

        if (flag == 7) {
            for (int i = 0; i < accounts.size(); i++) {
                accounts.get(i).getInfo();
            }
        } else {
            for (int i = 0; i < accounts.size(); i++) {
                if (accounts.get(i).getAccountNumber().equals(account)) {
                    index = i;
                    break;
                }
            }

            if (flag == 6) {
                accounts.get(index).getInfo();

            } else if (flag == 4) {
                System.out.print("Amount of money to withdraw: ");
                money = sc.nextInt();

                accounts.get(index).getMoney(money);

            } else if (flag == 3) {
                System.out.print("Amount of money to deposit: ");
                money = sc.nextInt();

                accounts.get(index).addMoney(money);
            }
        }
    }
}

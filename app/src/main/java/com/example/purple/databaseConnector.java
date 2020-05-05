package com.example.purple;

import android.content.Context;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

//This is an utility class for file input and output.

public class databaseConnector {
    //Constructor is private to make sure this class cannot be instantiated.
    private databaseConnector() { }

    public static void writeToFile(Context context, ArrayList<User> userList) {
        try {
            FileOutputStream fos = context.openFileOutput("database.json", Context.MODE_PRIVATE);
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(fos, "UTF-8"));
            for (int i = 0; i < userList.size(); i++) {
                Gson gson = new Gson();
                String json_object = gson.toJson(userList.get(i));
                System.out.println(json_object);
                writer.print(json_object);
                writer.println();
                writer.flush();

            }

            writer.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static ArrayList<User> readFromFile(Context context) {
        ArrayList<User> list = new ArrayList<User>();
        Gson gson = new Gson();
        String line;
        try {
            FileInputStream fis = context.openFileInput("database.json");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(isr);

            while ((line = reader.readLine()) != null) {
                User user = gson.fromJson(line, User.class);
                list.add(user);
            }
            reader.close();
        }  catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }



    // Saving user's bank statement by email address
    public static void saveBankStatement(Context context, String accountNumber, String transaction) {
        try {
            FileOutputStream fos = context.openFileOutput(accountNumber + ".txt", Context.MODE_APPEND);
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(fos, "UTF-8"));

            writer.print(transaction);
            writer.println();
            writer.flush();

            writer.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Reading user's bank statement by email address
    public static ArrayList<String> readBankStatement(Context context, String accountNumber) {
        ArrayList<String> list = new ArrayList<>();
        String line;
        try {
            FileInputStream fis = context.openFileInput(accountNumber + ".txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(isr);

            while ((line = reader.readLine()) != null) {
                list.add(line);
            }

            reader.close();
        }  catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }





}

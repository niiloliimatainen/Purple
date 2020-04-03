package com.example.purple;

import android.content.Context;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

//This is an utility class for file input and output.

public class databaseConnector {

    //Constructor is private to make sure this class cannot be instantiated.
    private databaseConnector() { }

    public static void writeToFile(Context context, User user) {
        try {
            FileOutputStream fos = context.openFileOutput("database.json", Context.MODE_APPEND);
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(fos, "UTF-8"));
            Gson gson = new Gson();
            String json_object = gson.toJson(user);
            System.out.println("moi");
            System.out.println(json_object);
            writer.print(json_object);
            writer.println();
            writer.flush();
            writer.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static ArrayList<regularUser> readFromFile(Context context) {
        ArrayList<regularUser> list = new ArrayList<regularUser>();
        Gson gson = new Gson();
        String line;
        try {
            FileInputStream fis = context.openFileInput("database.json");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(isr);

            while ((line = reader.readLine()) != null) {
                regularUser user = gson.fromJson(line, regularUser.class);
                list.add(user);
                System.out.println(user.firstName);
            }
            reader.close();
        }  catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}

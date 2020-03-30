package com.example.purple;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

//This is an utility class for file input and output.

public class databaseConnector {

    //Constructor is private to make sure this class cannot be instantiated.
    private databaseConnector() { }

    public static void writeToFile(User user) {
        try {
            FileWriter file = new FileWriter("database.json", true);
            BufferedWriter writer = new BufferedWriter(file);
            Gson gson = new Gson();
            String json_object = gson.toJson(user);

            writer.write(json_object);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static ArrayList<User> readFromFile() {
        ArrayList<User> list = new ArrayList<User>();
        Gson gson = new Gson();
        try {
            FileReader file = new FileReader("database.json");
            BufferedReader reader = new BufferedReader(file);
            String line = reader.readLine();
            while (line != null) {
                User user = gson.fromJson(line, User.class);
                list.add(user);
            }
            reader.close();
        }  catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}

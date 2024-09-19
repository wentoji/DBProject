package com.example.dbproject;

public class Main {

    public static void main(String[] args) {
        Repository manager = Repository.getInstance(); // Use Singleton instance
        manager.executeQuery("SELECT * FROM ingredients");


    }
}
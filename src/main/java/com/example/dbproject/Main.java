package com.example.dbproject;

public class Main {

    public static void main(String[] args) {
        DatabaseManager manager = DatabaseManager.getInstance(); // Use Singleton instance
        manager.fetchData("SELECT * FROM actor WHERE first_name LIKE '%ER'");
        manager.clearQueryLog();
        manager.printQueryLog(); // Print the entire query log
    }
}
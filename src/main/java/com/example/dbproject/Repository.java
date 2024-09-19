package com.example.dbproject;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Repository {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/pizzarestaurant";
    private static final String USER = "root";
    private static final String PASS = "1810";
    private static final String LOG_FILE = "query_log.txt";

    private List<String> queryLog = new ArrayList<>();
    private static Repository instance;

    private Repository() {
        // Load existing logs from the file
        loadQueryLog();
    }

    public static synchronized Repository getInstance() {
        if (instance == null) {
            instance = new Repository();
        }
        return instance;
    }

    public void executeQuery(String query) {
        String[] queries = query.split(";"); // Split the query into individual statements
        for (String individualQuery : queries) {
            individualQuery = individualQuery.trim(); // Trim extra spaces and new lines
            if (!individualQuery.isEmpty()) { // Skip empty queries
                if (individualQuery.toUpperCase().startsWith("SELECT") || individualQuery.toUpperCase().startsWith("SHOW")) {
                    fetchData(individualQuery);
                } else {
                    executeUpdate(individualQuery);
                }
            }
        }
    }

    // For SELECT queries
    private void fetchData(String query) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        long startTime, endTime;

        try {
            // Measure time to connect to the database
            startTime = System.currentTimeMillis();
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            endTime = System.currentTimeMillis();
            System.out.println("Time to connect to the database: " + (endTime - startTime) + " ms");

            stmt = conn.createStatement();

            // Measure time to execute the query
            startTime = System.currentTimeMillis();
            rs = stmt.executeQuery(query);
            endTime = System.currentTimeMillis();
            System.out.println("Time to execute the query: " + (endTime - startTime) + " ms");

            // Log the query with timestamp
            logQuery(query);

            // Measure time to format the result set
            startTime = System.currentTimeMillis();
            String result = formatResultSet(rs);
            endTime = System.currentTimeMillis();
            System.out.println("Time to format the result set: " + (endTime - startTime) + " ms");

            System.out.println(result);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    // For INSERT, UPDATE, DELETE queries
    private void executeUpdate(String sql) {
        Connection conn = null;
        Statement stmt = null;

        long startTime, endTime;

        try {
            // Measure time to connect to the database
            startTime = System.currentTimeMillis();
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            endTime = System.currentTimeMillis();
            System.out.println("Time to connect to the database: " + (endTime - startTime) + " ms");

            stmt = conn.createStatement();

            // Measure time to execute the statement
            startTime = System.currentTimeMillis();
            int affectedRows = stmt.executeUpdate(sql);
            endTime = System.currentTimeMillis();
            System.out.println("Time to execute the statement: " + (endTime - startTime) + " ms");

            // Log the statement with timestamp
            logQuery(sql);
            System.out.println("Affected rows: " + affectedRows);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    private String formatResultSet(ResultSet rs) throws SQLException {
        StringBuilder output = new StringBuilder();

        // Get the number of columns in the ResultSet
        int columnCount = rs.getMetaData().getColumnCount();
        String[] columnNames = new String[columnCount];

        // Get column names and create header row
        for (int i = 1; i <= columnCount; i++) {
            columnNames[i - 1] = rs.getMetaData().getColumnName(i);
        }

        // Build the header
        for (String columnName : columnNames) {
            output.append(String.format("%-20s", columnName));
        }
        output.append("\n");
        output.append("-".repeat(Math.max(0, 20 * columnCount)));
        output.append("\n");

        // Build the rows
        while (rs.next()) {
            for (String columnName : columnNames) {
                output.append(String.format("%-20s", rs.getString(columnName)));
            }
            output.append("\n");
        }

        return output.toString();
    }

    private void logQuery(String query) {
        String timestamp = LocalDateTime.now().toString();
        String logEntry = "[" + timestamp + "] " + query;
        queryLog.add(logEntry);
        try (FileWriter fw = new FileWriter(LOG_FILE, true); // Append mode
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(logEntry);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadQueryLog() {
        File logFile = new File(LOG_FILE);
        if (logFile.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(logFile))) {
                String line;
                while ((line = br.readLine()) != null) {
                    queryLog.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void printQueryLog() {
        if (queryLog.isEmpty()) {
            System.out.println("Query log is empty.");
        } else {
            for (String logEntry : queryLog) {
                System.out.println(logEntry);
            }
        }
    }

    public void clearQueryLog() {
        queryLog.clear();
        try (PrintWriter pw = new PrintWriter(new FileWriter(LOG_FILE))) {
            // Clear the file content
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Query log cleared.");
    }
}

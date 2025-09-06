package com.enomy.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/enomy_finance";
    private static final String DB_USER = "root"; // Change as needed
    private static final String DB_PASSWORD = ""; // Change as needed

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load MySQL driver
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error getting DB connection", e);
        }
    }
}

package com.brokeroffice.springbootws.helpers;

import java.sql.Connection;
import java.sql.DriverManager;

public class Dao {

    public static Connection connection() throws Exception {
        // Correct connection URL
        String url = "jdbc:mysql://localhost:3306/cpm";
        String username = "kim"; // Replace with your actual username
        String password = "1234"; // Replace with your actual password

        // Establish the connection
        Connection con = DriverManager.getConnection(url, username, password);

        if (con != null) {
            System.out.println("Connection success");
        } else {
            System.out.println("Connection failed");
        }
        return con;
    }
}
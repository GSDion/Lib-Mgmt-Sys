package com.dh.auth;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;
import com.dh.app.DatabaseHelper;

public class SignupFrame {

     private static String hashPassword(String password) {
        int workload = 12; // security requirements? 
        String salt = BCrypt.gensalt(workload);
        return BCrypt.hashpw(password, salt);
    }

    private static boolean storeUserInDatabase(String username, String password, int userType) {
        boolean isSuccess = false;
        try {
            // Create a JDBC connection
            Connection connection =DatabaseHelper.connect();
    
            // Hash the password using bcrypt
            String hashedPassword = hashPassword(password);
    
            // Create a prepared statement to insert the user into the table
            String query = "INSERT INTO users (USERNAME, PASSWORD, USER_TYPE) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, hashedPassword);
            preparedStatement.setInt(3, userType);

    
            // Execute the query
            long rowsAffected = preparedStatement.executeUpdate();
    
            // If the query successfully inserted a row, the user was stored in the database
            if (rowsAffected > 0) {
                isSuccess = true;
            }
    
            // Close the resources
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return isSuccess;
    }

     private static boolean isUsernameAvailable(String username) {
        boolean isAvailable = true;
        try {
            // Create a JDBC connection
            Connection connection = DatabaseHelper.connect();

            // Create a prepared statement to check if the username exists in the table
            String query = "SELECT * FROM users WHERE USERNAME = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query); //Exception in thread "AWT-EventQueue-0" java.lang.NullPointerException: Cannot invoke "java.sql.Connection.prepareStatement(String)" because "connection" 
            //is null
            preparedStatement.setString(1, username);

            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();

            // If a row is returned, the username already exists
            if (resultSet.next()) {
                isAvailable = false;
            }

            // Close the resources
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return isAvailable;
    }

}

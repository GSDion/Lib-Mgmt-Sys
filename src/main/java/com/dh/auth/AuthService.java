package com.dh.auth;
// SQL
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
// Hashing/Salting
import org.mindrot.jbcrypt.BCrypt;
// Database Connection
import com.dh.app.DatabaseHelper;

public class AuthService {


    public static String hashPassword(String password) {
        int workload = 12; // security requirements? 
        String salt = BCrypt.gensalt(workload);
        return BCrypt.hashpw(password, salt);
    }


    public static boolean storeUserInDatabase(String username, String password, int userType) {
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


     public static boolean isUsernameAvailable(String username) {
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

    public static boolean verifyPassword(String username, String password) {
        try {
            // Create a JDBC connection
            Connection connection = DatabaseHelper.connect();
    
            // Retrieve the hashed password from the database
            String query = "SELECT PASSWORD FROM users WHERE USERNAME = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
    
            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();
    
            if (resultSet.next()) {
                String hashedPassword = resultSet.getString("PASSWORD");
                // Verify the hashed password using BCrypt's checkpw method
                return BCrypt.checkpw(password, hashedPassword);
            }
    
            // Close the resources
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return false;
    }
}

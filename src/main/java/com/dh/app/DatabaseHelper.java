package com.dh.app;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;


public class DatabaseHelper {

    private static String url;
    private static String user;
    private static String password;

    static {
        loadDatabaseConfig();
    }

    private static void loadDatabaseConfig() {
        Properties props = new Properties();
        try (var input = DatabaseHelper.class.getClassLoader()
                     .getResourceAsStream("dbconfig.properties")) {
    
            if (input == null) {
                System.err.println("Sorry, unable to find dbconfig.properties");
                return;
            }
            props.load(input);
            url = props.getProperty("db.url");
            user = props.getProperty("db.user");
            password = props.getProperty("db.password");
        } catch (IOException e) {
            System.err.println("Failed to load database configuration: " + e.getMessage());
        }
    }

    public static Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
        }
        return connection;
    }

     // General method to retrieve data from any table 
    public static Object[][] retrieveTableData(String tableName, String[] columns) {
        List<Object[]> data = new ArrayList<>();
        String query = "SELECT " + String.join(", ", columns) + " FROM " + tableName;

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            int columnCount = columns.length;
            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 0; i < columnCount; i++) {
                    row[i] = rs.getObject(columns[i]);
                }
                data.add(row);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving data: " + e.getMessage());
        }

        return data.toArray(new Object[0][0]);
    }

    /**
     * Inserts a record into the specified table with the provided column names and values.
     *
     * @param tableName the name of the table to insert into
     * @param columns   an array of column names to insert values into
     * @param values    an array of values to insert corresponding to the columns
     * @return true if insertion was successful, false otherwise
     */
    public static boolean insertRecord(String tableName, String[] columns, Object[] values) {
        if (columns.length != values.length) {
            throw new IllegalArgumentException("Columns and values length must match.");
        }

        // Build the SQL INSERT query dynamically
        String columnsPart = String.join(", ", columns);
        String placeholders = String.join(", ", Arrays.stream(values).map(v -> "?").toArray(String[]::new));
        String query = "INSERT INTO " + tableName + " (" + columnsPart + ") VALUES (" + placeholders + ")";

        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(query)) {
            // Set each value in the PreparedStatement
            for (int i = 0; i < values.length; i++) {
                stmt.setObject(i + 1, values[i]);
            }

            // Execute the insert operation
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Returns true if at least one row was affected
        } catch (SQLException e) {
            System.err.println("Error inserting data: " + e.getMessage());
            return false;
        }
    }

    // Method to retrieve data from any table based on UID
public static Object[][] retrieveTableDataByUID(String tableName, String[] columns, String UID) {
    List<Object[]> data = new ArrayList<>();
    String query = "SELECT " + String.join(", ", columns) + " FROM " + tableName + " WHERE UID = ?";

    try (Connection conn = connect();
         PreparedStatement stmt = conn.prepareStatement(query)) {

        // Set the UID parameter, converting the string to an integer for the query
        stmt.setInt(1, Integer.parseInt(UID));
        
        try (ResultSet rs = stmt.executeQuery()) {
            int columnCount = columns.length;
            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 0; i < columnCount; i++) {
                    row[i] = rs.getObject(columns[i]);
                }
                data.add(row);
            }
        }
    } catch (SQLException e) {
        System.err.println("Error retrieving data: " + e.getMessage());
    } catch (NumberFormatException e) {
        System.err.println("Invalid UID format: " + e.getMessage());
    }

    return data.toArray(new Object[0][0]);
}

    // MODIFY ENTRY IN DATABSE (PLACE EDIT BUTTON ON TABLE, NEXT TO RESPECTIVE ENTRY)

    //DELETE ENTRY IN DATABASE (PLACE DELETE BUTTON ON TABLE, NEXT TO RESPECTIVE ENTRY)
    
}

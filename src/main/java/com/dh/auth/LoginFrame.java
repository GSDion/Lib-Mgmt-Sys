package com.dh.auth;
import com.dh.app.DatabaseHelper;
// RoundedButton
import com.dh.components.RoundedButton;
// Change these to be more specific
import javax.swing.*;
import java.awt.*;
// Fine
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
// Frames
import com.dh.librarian.LibrarianFrame;
import com.dh.user.UserFrame;

public class LoginFrame extends JFrame {
    // private JFrame frame;  // Store the frame reference
    private JTextField loginUsernameFld;
    private JPasswordField loginPasswordFld;

    public LoginFrame() {
        initComponents();
    }

    // rgb(69, 150, 209)
    private void initComponents() {
        // Basics
        // frame = this; 
        setTitle("LMS - Login");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 2)); // Two equal panels

        // === LEFT PANEL ===
        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setBackground(new Color(69, 150, 209)); // Blue background

        JLabel titleLabel = new JLabel("Login");
        titleLabel.setFont(new Font("Sans-serif", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE); // White text

        loginUsernameFld = new JTextField(15);
        loginUsernameFld.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        loginUsernameFld.setText("Username");
        loginUsernameFld.setForeground(Color.GRAY);

        loginPasswordFld = new JPasswordField(15);
        loginPasswordFld.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        loginPasswordFld.setText("Password");
        loginPasswordFld.setForeground(Color.GRAY);

        JLabel forgotPassword = new JLabel("<HTML><U>Forgot your password?</U></HTML>");
        forgotPassword.setForeground(Color.WHITE);
        forgotPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // RoundedButton object used here
        RoundedButton loginButton = new RoundedButton("LOGIN");
        loginButton.setBackground(Color.WHITE); // White background
        loginButton.setForeground(new Color(69, 150, 209)); // Green text

        //Action for LOGIN button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });

        // Layout constraints for left panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;

        gbc.gridy = 0;
        leftPanel.add(titleLabel, gbc);

        gbc.gridy = 1;
        leftPanel.add(loginUsernameFld, gbc);

        gbc.gridy = 2;
        leftPanel.add(loginPasswordFld, gbc);

        gbc.gridy = 3;
        leftPanel.add(forgotPassword, gbc);

        gbc.gridy = 4;
        leftPanel.add(loginButton, gbc);

        // === RIGHT PANEL ===
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(Color.WHITE); // White background

        JLabel welcomeLabel = new JLabel("Hello, Friend!");
        welcomeLabel.setFont(new Font("Sans-serif", Font.BOLD, 24));
        welcomeLabel.setForeground(new Color(69, 150, 209)); // Green text

        JLabel messageLabel = new JLabel("Enter your personal details");
        messageLabel.setForeground(new Color(69, 150, 209)); // Green text

        JLabel messageLabel2 = new JLabel("and start your journey with us");
        messageLabel2.setForeground(new Color(69, 150, 209)); // Green text

        // RoundedButton Object used here
        RoundedButton signUpButton = new RoundedButton("SIGNUP");
        signUpButton.setBackground(new Color(69, 150, 209)); // Green background
        signUpButton.setForeground(Color.WHITE); // White text

        //Action for LOGIN button
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 // Close the current LoginFrame instance
                dispose();  // This will work (instead of LoginFrame.dispose) b/c it is an instance method

                // Create a new instance of SignupFrame and make it visible
                SignupFrame signupFrame = new SignupFrame();
                signupFrame.setVisible(true);
            }
        });


        // Layout constraints for right panel
        gbc.gridy = 0;
        rightPanel.add(welcomeLabel, gbc);

        gbc.gridy = 1;
        rightPanel.add(messageLabel, gbc);

        gbc.gridy = 2;
        rightPanel.add(messageLabel2, gbc);

        gbc.gridy = 3;
        rightPanel.add(signUpButton, gbc);

        // Add panels to frame
        add(leftPanel);
        add(rightPanel);

        // Center frame on screen
        setLocationRelativeTo(null);
    }

    //LOGIN Method
    // private void handleLogin() {
    //     String username = loginUsernameFld.getText();
    //     String password = loginPasswordFld.getText();
    
    //     if (username.isEmpty()) {
    //         JOptionPane.showMessageDialog(null, "Please enter username");
    //     } else if (password.isEmpty()) {
    //         JOptionPane.showMessageDialog(null, "Please enter password");
    //     } else {
    //         Connection connection = DatabaseHelper.connect();
    //         if (connection == null) {
    //             JOptionPane.showMessageDialog(null, "Database connection failed.");
    //             return;
    //         }
    
    //         try {
    //             // Use TYPE_SCROLL_INSENSITIVE to allow cursor movement in both directions.
    //             // ResultSet objects created by default are TYPE_FORWARD_ONLY, which means they can only move forward. 
    //             // CANNOT use rs.beforeFirst().
    //             Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    //             String st = "SELECT * FROM USERS WHERE USERNAME='" + username + "' AND PASSWORD='" + password + "'";
    //             ResultSet rs = stmt.executeQuery(st);
    
    //             if (!rs.next()) {
    //                 JOptionPane.showMessageDialog(null, "Invalid Username/Password!");
    //             } else {
    //                 dispose();
    //                 rs.beforeFirst();  // Now this will work.
    
    //                 while (rs.next()) {
    //                     String admin = rs.getString("USER_TYPE");
    //                     String UID = rs.getString("UID");
    
    //                     if (admin.equals("1")) {
    //                         librarian_frame();
    //                     } else {
    //                         user_frame(UID);
    //                     }
    //                 }
    //             }
    //         } catch (Exception ex) {
    //             ex.printStackTrace();
    //         }
    //     }
    // }

    private void handleLogin() {
        String username = loginUsernameFld.getText().trim();
        String password = new String(loginPasswordFld.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in both username and password.", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (AuthService.verifyPassword(username, password)) {
            try {
                // Create a JDBC connection
                Connection connection = DatabaseHelper.connect();
                String query = "SELECT USER_TYPE, UID FROM users WHERE USERNAME = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, username);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    String userType = resultSet.getString("USER_TYPE");
                    String UID = resultSet.getString("UID");

                    // Close the resources
                    resultSet.close();
                    preparedStatement.close();
                    connection.close();

                    // Direct the user to the appropriate frame based on userType (1 for admin, 2 for user)
                    dispose(); // Close the current login frame
                    if ("1".equals(userType)) {
                        // Open LibrarianFrame
                        new LibrarianFrame().setVisible(true);
                    } else {
                        // Open UserFrame, passing the UID to access user data
                        new UserFrame(UID, username, userType).setVisible(true);
                    }

                    JOptionPane.showMessageDialog(this, "Login successful!" + UID + username, "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "An error occurred while logging in.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Invalid username or password.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
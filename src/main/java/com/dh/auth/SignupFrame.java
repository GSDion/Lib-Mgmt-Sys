package com.dh.auth;
// Panels
import javax.swing.*;
import java.awt.*;
// RoundedButton
import com.dh.components.RoundedButton;
// Buttons
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
// Login

public class SignupFrame extends JFrame {
    private JTextField signupUsernameFld;
    private JPasswordField signupPasswordFld;
    private JPasswordField signupConpasswordFld;

    // Constructor
    public SignupFrame() {
        initComponents();
    }

     // rgb(69, 150, 209)
     private void initComponents() {
        // Basics
        // frame = this; 
        setTitle("LMS - Signup");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel
        JPanel signupPanel = new JPanel(new GridBagLayout());
        signupPanel.setBackground(new Color(69, 150, 209)); // Blue background

        JLabel titleLabel = new JLabel("Login");
        titleLabel.setFont(new Font("Sans-serif", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE); // White text

        signupUsernameFld = new JTextField(15);
        signupUsernameFld.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        signupUsernameFld.setText("Username");
        signupUsernameFld.setForeground(Color.GRAY);

        signupPasswordFld = new JPasswordField(15);
        signupPasswordFld.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        signupPasswordFld.setText("Password");
        signupPasswordFld.setForeground(Color.GRAY);

        signupConpasswordFld = new JPasswordField(15);
        signupConpasswordFld.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        signupConpasswordFld.setText(" Confirm Password");
        signupConpasswordFld.setForeground(Color.GRAY);


        // Signup button - log-in the user after signup
        RoundedButton signupButton = new RoundedButton("SIGNUP");
        signupButton.setBackground(Color.WHITE); // White background
        signupButton.setForeground(new Color(69, 150, 209)); // Blue text

        //Action for SIGNUP button
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSignup();
            }
        });

        // Back Button (go back to LoginFrame)
        RoundedButton backButton = new RoundedButton("BACK");
        backButton.setBackground(Color.WHITE); // White background
        backButton.setForeground(new Color(69, 150, 209)); // Blue text

         //Action for BACK button
         backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginFrame loginFrame = new LoginFrame();
                loginFrame.setVisible(true);
            }
        });

        // Layout constraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;

        gbc.gridy = 0;
        signupPanel.add(titleLabel, gbc);

        gbc.gridy = 1;
        signupPanel.add(signupUsernameFld, gbc);

        gbc.gridy = 2;
        signupPanel.add(signupPasswordFld, gbc);

        gbc.gridy = 3;
        signupPanel.add(signupConpasswordFld,gbc);

        gbc.gridy = 4;
        signupPanel.add(signupButton, gbc);

        gbc.gridy = 5;
        signupPanel.add(backButton, gbc);

        // CHANGE NAME
        add(signupPanel);  

        // Center frame on screen
        setLocationRelativeTo(null);

     }

    private void handleSignup() {
        String username = signupUsernameFld.getText().trim();
        String password = new String(signupPasswordFld.getPassword());
        String confirmPassword = new String(signupConpasswordFld.getPassword());

        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            // Perform signup logic here
            if (AuthService.isUsernameAvailable(username)) {
                // Store the user's information in the database
                if (AuthService.storeUserInDatabase(username, password, 2)) {
                    JOptionPane.showMessageDialog(this, "Signup successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Error while signing up. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Username already exists.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}

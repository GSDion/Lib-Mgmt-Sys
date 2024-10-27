package com.dh.auth;
import com.dh.components.RoundedButton;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    public LoginFrame() {
        initComponents();
    }

    // rgb(69, 150, 209)
    private void initComponents() {
        // Basics
        setTitle("LMS - Login");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 2)); // Two equal panels

        // === LEFT PANEL ===
        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setBackground(new Color(34, 153, 84)); // Green background

        JLabel titleLabel = new JLabel("Login");
        titleLabel.setFont(new Font("Sans-serif", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE); // White text

        JTextField emailField = new JTextField(15);
        emailField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        emailField.setText("Email");
        emailField.setForeground(Color.GRAY);

        JPasswordField passwordField = new JPasswordField(15);
        passwordField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        passwordField.setText("Password");
        passwordField.setForeground(Color.GRAY);

        JLabel forgotPassword = new JLabel("<HTML><U>Forgot your password?</U></HTML>");
        forgotPassword.setForeground(Color.WHITE);
        forgotPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // RoundedButton object used here
        RoundedButton loginButton = new RoundedButton("LOGIN");
        loginButton.setBackground(Color.WHITE); // White background
        loginButton.setForeground(new Color(34, 153, 84)); // Green text

        // Layout constraints for left panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;

        gbc.gridy = 0;
        leftPanel.add(titleLabel, gbc);

        gbc.gridy = 1;
        leftPanel.add(emailField, gbc);

        gbc.gridy = 2;
        leftPanel.add(passwordField, gbc);

        gbc.gridy = 3;
        leftPanel.add(forgotPassword, gbc);

        gbc.gridy = 4;
        leftPanel.add(loginButton, gbc);

        // === RIGHT PANEL ===
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(Color.WHITE); // White background

        JLabel welcomeLabel = new JLabel("Hello, Friend!");
        welcomeLabel.setFont(new Font("Sans-serif", Font.BOLD, 24));
        welcomeLabel.setForeground(new Color(34, 153, 84)); // Green text

        JLabel messageLabel = new JLabel("Enter your personal details");
        messageLabel.setForeground(new Color(34, 153, 84)); // Green text

        JLabel messageLabel2 = new JLabel("and start your journey with us");
        messageLabel2.setForeground(new Color(34, 153, 84)); // Green text

        // RoundedButton Object used here
        RoundedButton signUpButton = new RoundedButton("SIGNUP");
        signUpButton.setBackground(new Color(34, 153, 84)); // Green background
        signUpButton.setForeground(Color.WHITE); // White text

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
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        java.awt.EventQueue.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}
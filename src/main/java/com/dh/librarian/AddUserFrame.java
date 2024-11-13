
package com.dh.librarian;
import javax.swing.*;

import org.mindrot.jbcrypt.BCrypt;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.dh.app.DatabaseHelper;



public class AddUserFrame extends JFrame {
    
    private JPanel body;
    private com.dh.components.Header header;
    private com.dh.components.menu.Menu menu;

    // Fields for book attributes
    private JTextField usernameField, passwordField ,conPasswordField, userTypeField;

    public AddUserFrame() {
        menu = new com.dh.components.menu.Menu(this, 1); // initiate this here (BEFORE initComponents)
        initComponents();
        header.setWelcomeMessage("Welcome Admin"); // Set a personalized welcome message
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        header = new com.dh.components.Header(this);
        body = new JPanel(new BorderLayout());
    
        setTitle("LMS Admin - Add Users");
        setPreferredSize(new Dimension(1200, 700));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    
        JScrollPane menuScrollPane = new JScrollPane(menu);
        menuScrollPane.setBorder(null);
        header.setPreferredSize(new Dimension(getWidth(), 60));
    
        // Set up top panel with title and breadcrumb
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
    
        JLabel titleLabel = new JLabel("Admin Dashboard - Add User", SwingConstants.LEFT);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        titleLabel.setForeground(Color.DARK_GRAY);
    
        JLabel breadcrumbLabel = new JLabel("Home > Add User", SwingConstants.RIGHT);
        breadcrumbLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        breadcrumbLabel.setForeground(Color.DARK_GRAY);
    
        topPanel.add(titleLabel, BorderLayout.WEST);
        topPanel.add(breadcrumbLabel, BorderLayout.EAST);
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    
        // Add topPanel to body
        body.add(topPanel, BorderLayout.NORTH);
    
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setPreferredSize(new Dimension(800, 500));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        body.add(formPanel, BorderLayout.CENTER);
    
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
    
        // Initialize form fields for 
        usernameField = new JTextField(20);
        passwordField = new JTextField(20);
        conPasswordField = new JTextField(20);
        userTypeField = new JTextField(20);
      
    
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        JLabel conPasswordLabel = new JLabel("Confirm Password:");
        JLabel  userTypeLabel = new JLabel("User Type (1 or 2):");
      
    
        JButton addUserButton = new JButton("Add User");
        addUserButton.setPreferredSize(new Dimension(120, 40));
        addUserButton.setBackground(new Color(69, 150, 209));
        addUserButton.setForeground(Color.WHITE);
        addUserButton.setFocusPainted(false);
        addUserButton.setBorder(BorderFactory.createEmptyBorder());
        addUserButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    
        // Add button action
        addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addUserToDatabase();
            }
        });
    
        // Place labels and fields in two-column layout
        int row = 0;
    
        // Column 1: Labels, Column 2: Fields
        gbc.gridx = 0; gbc.gridy = row; formPanel.add(usernameLabel, gbc);
        gbc.gridx = 1; gbc.gridy = row++; formPanel.add(usernameField, gbc);
    
        gbc.gridx = 0; gbc.gridy = row; formPanel.add(passwordLabel, gbc);
        gbc.gridx = 1; gbc.gridy = row++; formPanel.add(passwordField, gbc);
    
        gbc.gridx = 0; gbc.gridy = row; formPanel.add(conPasswordLabel, gbc);
        gbc.gridx = 1; gbc.gridy = row++; formPanel.add(conPasswordField, gbc);
    
        gbc.gridx = 0; gbc.gridy = row; formPanel.add(userTypeLabel, gbc);
        gbc.gridx = 1; gbc.gridy = row++; formPanel.add(userTypeField, gbc);
    
      
        // Place Button
        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 10, 10, 10);
        formPanel.add(addUserButton, gbc);
    
        GroupLayout layout = new GroupLayout(mainPanel);
        mainPanel.setLayout(layout);
    
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(header, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(menuScrollPane, 225, 225, 225)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(body, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
        );
    
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(header, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(menuScrollPane)
                        .addComponent(body, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                )
        );
    
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
    }


    private void addUserToDatabase() {
    // Get values from fields
    String username = usernameField.getText();
    String password = passwordField.getText();
    String confirmPassword = conPasswordField.getText();
    int userType = Integer.parseInt(userTypeField.getText());

    // Check if the passwords match
    if (!password.equals(confirmPassword)) {
        JOptionPane.showMessageDialog(this, "Passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Hash the password before inserting into the database
    String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

    // Column names and values for the insertRecord method
    String[] columns = { "username", "password", "user_type" };
    Object[] values = { username, hashedPassword, userType };

    // Insert into database
    boolean success = DatabaseHelper.insertRecord("users", columns, values);

    // Show confirmation message
    if (success) {
        JOptionPane.showMessageDialog(this, "User added successfully!");
        // Clear form fields
        usernameField.setText("");
        passwordField.setText("");
        conPasswordField.setText("");
        userTypeField.setText("");
    } else {
        JOptionPane.showMessageDialog(this, "Failed to add user. Please check the information.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

}
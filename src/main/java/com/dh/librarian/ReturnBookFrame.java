// Return Book to Database
package com.dh.librarian;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.dh.app.DatabaseHelper;



public class ReturnBookFrame extends JFrame {
    
    private JPanel body;
    private com.dh.components.Header header;
    private com.dh.components.menu.Menu menu;
    private JTextField bidField, uidField ,returnDateField, fineField;

    public ReturnBookFrame() {
        menu = new com.dh.components.menu.Menu(this, "1",null,null);  // initiate this here (BEFORE initComponents)
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
    
        JLabel titleLabel = new JLabel("Admin Dashboard - Return Book", SwingConstants.LEFT);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        titleLabel.setForeground(Color.DARK_GRAY);
    
        JLabel breadcrumbLabel = new JLabel("Home > Return Book", SwingConstants.RIGHT);
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
    
        // Initialize form fields
        bidField = new JTextField(20);
        uidField = new JTextField(20);
        returnDateField = new JTextField(20);
        fineField = new JTextField(20);
      
    
        JLabel bidLabel = new JLabel("BID:");
        JLabel uidLabel = new JLabel("UID:");
        JLabel returnDateLabel = new JLabel("Return Date (YYYY-MM-DD):");
        JLabel  fineLabel = new JLabel("Fine (Monetary Value):");
      
    
        JButton returnBookButton = new JButton("Return Book");
        returnBookButton.setPreferredSize(new Dimension(120, 40));
        returnBookButton.setBackground(new Color(69, 150, 209));
        returnBookButton.setForeground(Color.WHITE);
        returnBookButton.setFocusPainted(false);
        returnBookButton.setBorder(BorderFactory.createEmptyBorder());
        returnBookButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    
        // Add button action
        returnBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReturnBookToDatabase();
            }
        });
    
        // Place labels and fields in two-column layout
        int row = 0;
    
        // Column 1: Labels, Column 2: Fields
        gbc.gridx = 0; gbc.gridy = row; formPanel.add(bidLabel, gbc);
        gbc.gridx = 1; gbc.gridy = row++; formPanel.add(bidField, gbc);
    
        gbc.gridx = 0; gbc.gridy = row; formPanel.add(uidLabel, gbc);
        gbc.gridx = 1; gbc.gridy = row++; formPanel.add(uidField, gbc);
    
        gbc.gridx = 0; gbc.gridy = row; formPanel.add(returnDateLabel, gbc);
        gbc.gridx = 1; gbc.gridy = row++; formPanel.add(returnDateField, gbc);
    
        gbc.gridx = 0; gbc.gridy = row; formPanel.add(fineLabel, gbc);
        gbc.gridx = 1; gbc.gridy = row++; formPanel.add(fineField, gbc);
    
      
    
        // Place "Add" button below the fields
        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 10, 10, 10);
        formPanel.add(returnBookButton, gbc);
    
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


    // EDIT THIS: Book needs to be issued (as a prereq) (connect to UID?), then deleted from issued books too (based on BID)
    // RETURN BOOK TO LIBRARY 
    private void ReturnBookToDatabase() {
        // Get values from fields
        int bid = Integer.parseInt(bidField.getText());
        int uid = Integer.parseInt(uidField.getText());
        String date = returnDateField.getText();
        int fine = Integer.parseInt(fineField.getText());


        // Column names and values for the insertRecord method
        String[] columns = { "bid", "uid", "return_date","fine" };
        Object[] values = { bid, uid, date, fine };

        // Insert into database
        boolean success = DatabaseHelper.insertRecord("returned_books", columns, values);

        // Show confirmation message
        if (success) {
            JOptionPane.showMessageDialog(this, "Book Returned successfully!");
            // Clear form fields
            bidField.setText("");
            uidField.setText("");
            returnDateField.setText("");
            fineField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Failed to return book. Please check the information.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
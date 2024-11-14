// Add Issued Book to Database
package com.dh.librarian;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.dh.app.DatabaseHelper;



public class IssueBookFrame extends JFrame {
    
    private JPanel body;
    private com.dh.components.Header header;
    private com.dh.components.menu.Menu menu;
    private JTextField uidField, bidField ,issueDateField, periodField;

    public IssueBookFrame() {
        menu = new com.dh.components.menu.Menu(this, "1",null,null); // initiate this here (BEFORE initComponents)
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
    
        JLabel titleLabel = new JLabel("Admin Dashboard - Issue Book", SwingConstants.LEFT);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        titleLabel.setForeground(Color.DARK_GRAY);
    
        JLabel breadcrumbLabel = new JLabel("Home > Issue Book", SwingConstants.RIGHT);
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
        uidField = new JTextField(20);
        bidField = new JTextField(20);
        issueDateField = new JTextField(20);
        periodField = new JTextField(20);
      
    
        JLabel uidLabel = new JLabel("BID:");
        JLabel bidLabel = new JLabel("UID:");
        JLabel issueDateLabel = new JLabel("Issue Date (YYYY-MM-DD):");
        JLabel  periodLabel = new JLabel("Period:");
      
    
        JButton issueBookButton = new JButton("Return Book");
        issueBookButton.setPreferredSize(new Dimension(120, 40));
        issueBookButton.setBackground(new Color(69, 150, 209));
        issueBookButton.setForeground(Color.WHITE);
        issueBookButton.setFocusPainted(false);
        issueBookButton.setBorder(BorderFactory.createEmptyBorder());
        issueBookButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    
        // Add button action
        issueBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                issueBookToDatabase();
            }
        });
    
        // Place labels and fields in two-column layout
        int row = 0;
    
        // Column 1: Labels, Column 2: Fields
        gbc.gridx = 0; gbc.gridy = row; formPanel.add(uidLabel, gbc);
        gbc.gridx = 1; gbc.gridy = row++; formPanel.add(uidField, gbc);
    
        gbc.gridx = 0; gbc.gridy = row; formPanel.add(bidLabel, gbc);
        gbc.gridx = 1; gbc.gridy = row++; formPanel.add(bidField, gbc);
    
        gbc.gridx = 0; gbc.gridy = row; formPanel.add(issueDateLabel, gbc);
        gbc.gridx = 1; gbc.gridy = row++; formPanel.add(issueDateField, gbc);
    
        gbc.gridx = 0; gbc.gridy = row; formPanel.add(periodLabel, gbc);
        gbc.gridx = 1; gbc.gridy = row++; formPanel.add(periodField, gbc);
    
      
    
        // Place "Add" Button
        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 10, 10, 10);
        formPanel.add(issueBookButton, gbc);
    
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

    private void issueBookToDatabase() {
        // Get values from fields
        int uid = Integer.parseInt(bidField.getText());
        int bid = Integer.parseInt(uidField.getText());
        String issueDate = issueDateField.getText();
        int fine = Integer.parseInt(periodField.getText());


        // Column names and values for the insertRecord method
        String[] columns = { "uid", "bid","issue_date", "period" };
        Object[] values = { bid, uid, issueDate, fine };

        // Insert into database
        boolean success = DatabaseHelper.insertRecord("issued_books", columns, values);

        // Show confirmation message
        if (success) {
            JOptionPane.showMessageDialog(this, "Book Issued successfully!");
            // Clear form fields
            uidField.setText("");
            bidField.setText("");
            issueDateField.setText("");
            periodField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Failed to issue book. Please check the information.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
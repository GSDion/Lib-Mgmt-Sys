package com.dh.user;
import javax.swing.*;

import com.dh.app.DatabaseHelper;

import java.awt.*;
public class ViewUserIssuedBooksFrame extends JFrame {

    private JPanel body;
    private com.dh.components.Header header;
    private com.dh.components.menu.Menu menu;
    private JTable booksTable;
    private JScrollPane tableScrollPane;

    public ViewUserIssuedBooksFrame(String UID, String username, String userType) {
        menu = new com.dh.components.menu.Menu(this, "2", username, UID); // initiate this here (BEFORE initComponents), to pass the current JFrame component
        initComponents(UID, username);
    }

    public void initComponents(String UID, String username) {

         // Initialize main components
         JPanel mainPanel = new JPanel(new BorderLayout());
         header = new com.dh.components.Header(this);
         body = new JPanel(new BorderLayout());
     
         // Set up the frame properties
         setTitle("LMS User - View Issued Books");
         setSize(1200, 700);
         setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
     
         // Scroll pane for menu (similar to scrollPaneWin111)
         JScrollPane menuScrollPane = new JScrollPane(menu);
         menuScrollPane.setBorder(null);
     
         // Header
         header.setPreferredSize(new Dimension(getWidth(), 60)); 
         header.setWelcomeMessage("Welcome " + username); // Set welcome message with username HERE
     
         // Set up top panel with title and breadcrumb
         JPanel topPanel = new JPanel(new BorderLayout());
         topPanel.setOpaque(false);
     
         JLabel titleLabel = new JLabel("User Dashboard - View Issued Books", SwingConstants.LEFT);
         titleLabel.setFont(new Font("Arial", Font.PLAIN, 24));
         titleLabel.setForeground(Color.DARK_GRAY);
     
         JLabel breadcrumbLabel = new JLabel("Home > View Issued Books", SwingConstants.RIGHT);
         breadcrumbLabel.setFont(new Font("Arial", Font.PLAIN, 14));
         breadcrumbLabel.setForeground(Color.DARK_GRAY);
     
         topPanel.add(titleLabel, BorderLayout.WEST);
         topPanel.add(breadcrumbLabel, BorderLayout.EAST);
         topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
     
         // Add topPanel to body
         body.add(topPanel, BorderLayout.NORTH);

           // Table columns
        String[] columnNames = {"iid", "uid", "bid", "issued_date", "period"};

        // Fetch data from the database
        Object[][] data = DatabaseHelper.retrieveTableDataByUID("issued_books", columnNames, UID);

        // Create table with retrieved data
        booksTable = new JTable(data, columnNames);
        tableScrollPane = new JScrollPane(booksTable);
    
        // Add the table to the center of body
        body.add(tableScrollPane, BorderLayout.CENTER);
    
        // Set up the layout for mainPanel
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
                    // .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(menuScrollPane)
                        .addComponent(body, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    // .addContainerGap()
                    )
        );
    
        // Configure frame layout
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        setLocationRelativeTo(null); // Center the window on screen

    }

}
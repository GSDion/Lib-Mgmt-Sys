package com.dh.user;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserFrame extends JFrame {
    
   
   

    public UserFrame(String UID, String username) {
        
        header1 = new com.dh.components.Header(this);
        
        header1.setWelcomeMessage("Welcome " + username); // Set welcome message with username
   
        initComponents(UID);
    }

    private void initComponents(String UID) {
        
        setTitle("LMS Dashboard - User");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

    
        jPanel1 = new javax.swing.JPanel();
        scrollPaneWin111 = new com.dh.components.scroll.win11.ScrollPaneWin11();
        menu1 = new com.dh.components.menu.Menu(2);
        header1 = new com.dh.components.Header(this);
        body = new javax.swing.JPanel();

        setTitle("LMS Dashboard - Librarian");
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        // Configure jPanel1 background and border
        jPanel1.setBackground(new java.awt.Color(245, 245, 245));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(163, 163, 163)));

        // Set up scrollPaneWin111 with menu1
        scrollPaneWin111.setBorder(null);
        scrollPaneWin111.setViewportView(menu1);

        // Configure body panel to use BorderLayout
        body.setBackground(new java.awt.Color(245, 245, 245));
        body.setLayout(new java.awt.BorderLayout());

        UserDashboardPanel dashboardPanel = new UserDashboardPanel(UID);
        body.add(dashboardPanel, java.awt.BorderLayout.CENTER);

        // Set up GroupLayout for jPanel1
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(scrollPaneWin111, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
                .addComponent(header1, javax.swing.GroupLayout.DEFAULT_SIZE, 1096, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addComponent(header1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(scrollPaneWin111, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addContainerGap())))
        );

        // Set up main layout for the frame
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }

    // Change names to be more descriptive of UserFrame.java
    private javax.swing.JPanel body;
    private com.dh.components.Header header1;
    private javax.swing.JPanel jPanel1;
    private com.dh.components.menu.Menu menu1;
    private com.dh.components.scroll.win11.ScrollPaneWin11 scrollPaneWin111;
        
    }


package com.dh.user;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserFrame extends JFrame {
    private com.dh.components.Header header1;
   
   

    public UserFrame(String UID, String username) {
        header1 = new com.dh.components.Header(this);
        header1.setWelcomeMessage("Welcome " + username); // Set welcome message with username
   
        initComponents();
    }

    private void initComponents() {
        setTitle("LMS Dashboard - User");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        
    }

}
package com.dh.user;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserFrame extends JFrame {

    public UserFrame(String UID) {
        initComponents();
    }

    private void initComponents() {
        setTitle("LMS Dashboard - User");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    }

}
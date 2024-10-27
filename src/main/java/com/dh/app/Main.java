package com.dh.app;
import com.dh.auth.LoginFrame;
import java.sql.Connection;
import java.sql.*;

class Main {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new LoginFrame().setVisible(true));
    }
    
}
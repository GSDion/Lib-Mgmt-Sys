package com.dh.app;
import com.dh.auth.LoginFrame;

class Main {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new LoginFrame().setVisible(true));
    }

}
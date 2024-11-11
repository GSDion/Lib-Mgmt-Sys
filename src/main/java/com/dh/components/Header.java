package com.dh.components;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.dh.auth.LoginFrame;
import com.dh.components.menu.MenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 *
 * @author RAVEN
 */
public class Header extends javax.swing.JPanel {

    private JFrame parentFrame; // Reference to the parent frame (LoginFrame)
    private JLabel welcomeLabel;

    public Header(JFrame parentFrame) {
        this.parentFrame = parentFrame; // Store the reference
        initComponents();
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        System.out.println("Painting Header Component");

    if (welcomeLabel != null) {
        System.out.println("Welcome message: " + welcomeLabel.getText());
    } else {
        System.out.println("WelcomeLabel is null");
    }
        Graphics2D g2 = (Graphics2D) grphcs.create();
        //darker, then lighter rgb(69, 150, 209)
        g2.setPaint(new GradientPaint(0, 0, new Color(44, 107, 153), 0, getHeight(), new Color(69, 150, 209)));
        g2.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
        g2.dispose();
        super.paintComponent(grphcs);
    }

   
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel1.setForeground(Color.WHITE);

        //jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/dh/pictures/local_library_icon_72px.png"))); // NOI18N
        //42 x 30 
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/com/dh/pictures/local_library_icon_72px.png"));
        Image originalImage = originalIcon.getImage(); 
        // Resize the image
        int width = 30; 
        int height = 30; 
        Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        jLabel1.setIcon(new ImageIcon(resizedImage));
        jLabel1.setText("Library Management System");

        //Welcome Message (Display admin for all userType: 1, else username for userType: 2)
        welcomeLabel = new JLabel("Welcome"); // Default welcome message
        welcomeLabel.setFont(new java.awt.Font("sansserif", 1, 18));
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setVisible(true);

        //Log out button
        MenuItem logoutButton = new MenuItem("Logout", 0, false);
        logoutButton.setForeground(Color.WHITE);

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Confirm logout 
                int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?", "Confirm Logout",
                        JOptionPane.YES_NO_OPTION);
                
                if (response == JOptionPane.YES_OPTION) {
                    // Close the current frame
                    if (parentFrame != null) {
                        parentFrame.dispose();
                    }
                    
                    // Redirect to the login frame
                    LoginFrame loginFrame = new LoginFrame();
                    loginFrame.setVisible(true);
                }
            }
        });


        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(100, 100, 100) 
                .addComponent(welcomeLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(50, 50, 50) 
                .addComponent(logoutButton)
                .addContainerGap())
        );
       
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(welcomeLabel)
                        .addComponent(logoutButton))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    public void setWelcomeMessage(String message) {
        System.out.println("Welcome Message: " + message);  // Debugging line
        welcomeLabel.setText(message);
        this.revalidate();
        this.repaint();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}


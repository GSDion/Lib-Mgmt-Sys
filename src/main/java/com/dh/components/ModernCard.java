package com.dh.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;
 // Create the gradient background
        // GradientPaint gradientPaint = new GradientPaint(0, 0, gradientStartColor, 0, getHeight(), gradientEndColor);
        // g2d.setPaint(gradientPaint);
    // public ModernCard(String title, int count, Color gradientStartColor, Color gradientEndColor) {
    //     this.title = title;
    //     this.count = count;
    //     this.gradientStartColor = gradientStartColor;
    //     this.gradientEndColor = gradientEndColor;
    //     setPreferredSize(new Dimension(150, 100));
    //     setOpaque(false);
    // }

    public class ModernCard extends JPanel {
        private String title;
        private int count;
        private Color backgroundColor;
        private Color shadowColor = new Color(0, 0, 0, 30); // Shadow with low opacity
    
        public ModernCard(String title, int count, Color backgroundColor) {
            this.title = title;
            this.count = count;
            this.backgroundColor = backgroundColor;
            setPreferredSize(new Dimension(150, 100));
            setOpaque(false);
        }
    
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    
            // Add drop shadow
            g2d.setColor(shadowColor);
            g2d.fillRoundRect(5, 5, getWidth() - 10, getHeight() - 10, 20, 20);
    
            // Set solid background color
            g2d.setColor(backgroundColor);
            g2d.fillRoundRect(0, 0, getWidth() - 10, getHeight() - 10, 20, 20);
    
            // Draw the card's title and count
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Roboto", Font.BOLD, 14));
            g2d.drawString(title, 15, 30);
    
            g2d.setFont(new Font("Roboto", Font.BOLD, 24));
            g2d.drawString(String.valueOf(count), 15, 60);
    
            super.paintComponent(g);
        }
    

       // Method to update the count and refresh the display
       public void setCount(int newCount) {
            this.count = newCount;
            repaint(); // Redraws the component with the updated count
        }
}


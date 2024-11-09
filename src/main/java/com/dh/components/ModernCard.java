package com.dh.components;

import java.awt.*;
import javax.swing.*;

public class ModernCard extends JPanel {
    private String title;
    private int count;
    private Color gradientStartColor;
    private Color gradientEndColor;

    public ModernCard(String title, int count, Color gradientStartColor, Color gradientEndColor) {
        this.title = title;
        this.count = count;
        this.gradientStartColor = gradientStartColor;
        this.gradientEndColor = gradientEndColor;
        setPreferredSize(new Dimension(150, 100));
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Create the gradient background
        GradientPaint gradientPaint = new GradientPaint(0, 0, gradientStartColor, 0, getHeight(), gradientEndColor);
        g2d.setPaint(gradientPaint);

        // Draw rounded rectangle
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

        // Draw the card's title and count
        g2d.setColor(Color.GRAY);
        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        g2d.drawString(title, 20, 30);
        g2d.drawString(String.valueOf(count), 20, 60);

        super.paintComponent(g);
    }

       // Method to update the count and refresh the display
       public void setCount(int newCount) {
            this.count = newCount;
            repaint(); // Redraws the component with the updated count
        }
}


package com.dh.components;

import javax.swing.*;

import com.dh.app.DatabaseHelper;

import java.awt.*;
import java.sql.*;

public class DashboardPanel extends JPanel {
    //Top Card Panel: total genres, authors, publishers, total price of books, total staffs (admins)
    private ModernCard totalGenresCard;
    private ModernCard totalAuthorsCard;
    private ModernCard totalPublishersCard;
    private ModernCard totalPriceCard;
    //Bottom Card Panel: total books, total users, total issued, total reserved
    private ModernCard totalBooksCard;
    private ModernCard totalUsersCard;
    private ModernCard totalIssuedCard;
    private ModernCard totalReservedCard;

    public DashboardPanel() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(new Color(0, 0, 0, 0)); // Transparent

        // ----TOP DASHBOARD PANEL----
        // Top panel with dashboard title and breadcrumb
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);

        JLabel titleLabel = new JLabel("LMS Dashboard", SwingConstants.LEFT);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        titleLabel.setForeground(Color.DARK_GRAY);

        JLabel breadcrumbLabel = new JLabel("Home > Dashboard", SwingConstants.RIGHT);
        breadcrumbLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        breadcrumbLabel.setForeground(Color.DARK_GRAY);

        topPanel.add(titleLabel, BorderLayout.WEST);
        topPanel.add(breadcrumbLabel, BorderLayout.EAST);
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(topPanel, BorderLayout.NORTH);


        // ----TOP CARD PANEL----
        // Top section with four summary cards
        JPanel topCardPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        topCardPanel.setOpaque(false); // Transparent background
        topCardPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        //total genres, authors, publishers, total price of books
        totalGenresCard = new ModernCard("Total Genres", 0, new Color(255, 255, 128), new Color(255, 204, 128));
        totalAuthorsCard = new ModernCard("Total Authors", 0, new Color(128, 255, 255), new Color(102, 178, 255));
        totalPublishersCard = new ModernCard("Total Publishers", 0, new Color(255, 204, 128), new Color(255, 153, 51));
        totalPriceCard = new ModernCard("Total Price", 0, new Color(153, 255, 153), new Color(102, 204, 102));

        topCardPanel.add(totalGenresCard);
        topCardPanel.add(totalAuthorsCard);
        topCardPanel.add(totalPublishersCard);
        topCardPanel.add(totalPriceCard);

        JPanel topWrapperPanel = new JPanel(new BorderLayout());
        topWrapperPanel.setOpaque(false);
        topWrapperPanel.add(topCardPanel, BorderLayout.CENTER);

        add(topWrapperPanel, BorderLayout.CENTER);


        // ----CENTER PANEL----
        // Center panel with image on the left and additional cards on the right
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setOpaque(false); // Transparent background
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel imageLabel = new JLabel("Image Placeholder", SwingConstants.CENTER);
        imageLabel.setOpaque(true);
        imageLabel.setBackground(new Color(230, 230, 250));
        imageLabel.setPreferredSize(new Dimension(400, 300));
        centerPanel.add(imageLabel, BorderLayout.WEST);


        // ----BOTTOM RIGHT CARD PANEL----
        // Bottom-right panel with four additional cards in a 2x2 grid
        JPanel bottomRightCardPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        bottomRightCardPanel.setOpaque(false); // Transparent background
        bottomRightCardPanel.setPreferredSize(new Dimension(400, 200));

        //Bottom Card Panel: total books, total users, total issued, total reserved
        totalBooksCard = new ModernCard("Total Books", 0, new Color(255, 128, 255), new Color(255, 153, 255));
        totalUsersCard = new ModernCard("Total Users", 0, new Color(192, 192, 192), new Color(160, 160, 160));
        totalIssuedCard= new ModernCard("Total Issued Books", 0, new Color(255, 182, 193), new Color(255, 160, 180));
        totalReservedCard = new ModernCard("Total Reserved Books", 0, new Color(173, 216, 230), new Color(135, 206, 235));

        bottomRightCardPanel.add(totalBooksCard);
        bottomRightCardPanel.add(totalUsersCard);
        bottomRightCardPanel.add(totalIssuedCard);
        bottomRightCardPanel.add(totalReservedCard);

        centerPanel.add(bottomRightCardPanel, BorderLayout.EAST);
        add(centerPanel, BorderLayout.SOUTH);

        // Load data from the database
        updateStatsFromDatabase();
    }

    public void updateStatsFromDatabase() {
        try (Connection connection = DatabaseHelper.connect()) {
            //Top Card Panel: total genres, authors, publishers, total price of books
            totalGenresCard.setCount(fetchCount(connection, "SELECT COUNT(book_genre) FROM books"));
            totalAuthorsCard.setCount(fetchCount(connection, "SELECT COUNT(book_author) FROM books"));
            totalPublishersCard.setCount(fetchCount(connection, "SELECT COUNT(book_publisher) FROM books"));
            totalPriceCard.setCount(fetchCount(connection, "SELECT book_price FROM books"));
             //Bottom Card Panel: total books, total users, total issued, total reserved
            totalBooksCard.setCount(fetchCount(connection, "SELECT COUNT(*) FROM books"));
            totalUsersCard.setCount(fetchCount(connection, "SELECT COUNT(*) FROM users"));
            totalIssuedCard.setCount(fetchCount(connection, "SELECT COUNT(*) FROM issued_books"));
            totalReservedCard.setCount(fetchCount(connection, "SELECT COUNT(*) FROM returned_books"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private int fetchCount(Connection conn, String query) throws SQLException {
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }


    
}

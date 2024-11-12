package com.dh.user;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dh.app.DatabaseHelper;
import com.dh.components.ModernCard;

public class UserDashboardPanel extends JPanel{
    // four cards on the left, picture of books on right
    //"Total Books", "Total Genres", "Total Issued Books", "Total Returned Books"
    private ModernCard totalBooksCard;
    private ModernCard totalGenresCard;
    private ModernCard totalIssuedCard;
    private ModernCard totalReturnedCard;
    private String UID;
   

    public UserDashboardPanel(String UID) {
        this.UID = UID;
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

        // -- FOUR CARDS ON LEFT--
        JPanel leftCardPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        leftCardPanel.setPreferredSize(new Dimension(450, 500));
        leftCardPanel.setOpaque(false);
        leftCardPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        totalBooksCard = new ModernCard("Total Books", 0, new Color(76, 176, 81)); //rgb(76, 176, 81)
        totalGenresCard = new ModernCard("Total Genres", 0, new Color(33, 150, 245));//rgb(33, 150, 245)
        totalIssuedCard = new ModernCard("Total Issued Books", 0, new Color(0, 150, 136)); //rgb(0, 150, 136)
        totalReturnedCard = new ModernCard("Total Returned Books", 0, new Color(61, 89, 149));//rgb(61, 89, 149)

        leftCardPanel.add(totalBooksCard);
        leftCardPanel.add(totalGenresCard);
        leftCardPanel.add(totalIssuedCard);
        leftCardPanel.add(totalReturnedCard);

        add(leftCardPanel,BorderLayout.WEST);

        // -- PICTURE ON RIGHT --
         JPanel picturePanel = new JPanel(new BorderLayout());
        picturePanel.setOpaque(false); // Transparent background
        // picturePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Load the image from a file or resource
        ImageIcon icon = new ImageIcon(getClass().getResource("/com/dh/pictures/ColorfulStackofBooks.png"));
        // Scale image
        Image scaledImage = icon.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        // Create the JLabel with the scaled icon
        JLabel imageLabel = new JLabel(scaledIcon, SwingConstants.CENTER);
        imageLabel.setPreferredSize(new Dimension(400, 300));
        picturePanel.add(imageLabel, BorderLayout.EAST);
        add(picturePanel,BorderLayout.EAST);

        updateStatsFromDatabase();

  }

  public void updateStatsFromDatabase() {
        try (Connection connection = DatabaseHelper.connect()) {
            // TOTAL OVERALL
            totalBooksCard.setCount(fetchCount(connection, "SELECT COUNT(*) FROM books"));
            totalGenresCard.setCount(fetchCount(connection, "SELECT COUNT(book_genre) FROM books"));
             //FOR THIS PARTICULAR USER (UID)
             totalIssuedCard.setCount(fetchCount(connection, "SELECT COUNT(*) FROM issued_books WHERE UID = ?", UID));
             totalReturnedCard.setCount(fetchCount(connection, "SELECT COUNT(*) FROM returned_books WHERE UID = ?", UID));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private int fetchCount(Connection conn, String query, String... params) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            // Set any provided parameters
            for (int i = 0; i < params.length; i++) {
                stmt.setString(i + 1, params[i]);
            }
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

}



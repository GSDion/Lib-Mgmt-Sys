// Import necessary packages
package com.dh.librarian;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.dh.app.DatabaseHelper;
import javax.swing.border.Border;
public class BookFrame extends JFrame {
    
    private JPanel body;
    private com.dh.components.Header header;
    private com.dh.components.menu.Menu menu;

    // Fields for book attributes
    private JTextField isbnField, nameField, publisherField, editionField, genreField, pagesField, priceField, authorField;

    public BookFrame() {
        menu = new com.dh.components.menu.Menu(this, 1); // initiate this here (BEFORE initComponents)
        initComponents();
        header.setWelcomeMessage("Welcome Admin"); // Set a personalized welcome message
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        header = new com.dh.components.Header(this);
        body = new JPanel(new BorderLayout());
    
        setTitle("LMS Admin - Add Books");
        setPreferredSize(new Dimension(1200, 700));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    
        JScrollPane menuScrollPane = new JScrollPane(menu);
        menuScrollPane.setBorder(null);
        header.setPreferredSize(new Dimension(getWidth(), 60));
    
        // Set up top panel with title and breadcrumb
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
    
        JLabel titleLabel = new JLabel("Admin Dashboard - Add Books", SwingConstants.LEFT);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        titleLabel.setForeground(Color.DARK_GRAY);
    
        JLabel breadcrumbLabel = new JLabel("Home > Add Books", SwingConstants.RIGHT);
        breadcrumbLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        breadcrumbLabel.setForeground(Color.DARK_GRAY);
    
        topPanel.add(titleLabel, BorderLayout.WEST);
        topPanel.add(breadcrumbLabel, BorderLayout.EAST);
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    
        // Add topPanel to body
        body.add(topPanel, BorderLayout.NORTH);
    
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setPreferredSize(new Dimension(800, 500));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        body.add(formPanel, BorderLayout.CENTER);
    
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
    
        // Initialize form fields for book attributes using class-level variables
        isbnField = new JTextField(20);
        nameField = new JTextField(20);
        publisherField = new JTextField(20);
        editionField = new JTextField(20);
        genreField = new JTextField(20);
        pagesField = new JTextField(20);
        priceField = new JTextField(20);
        authorField = new JTextField(20);
    
        JLabel isbnLabel = new JLabel("Book ISBN:");
        JLabel nameLabel = new JLabel("Book Name:");
        JLabel publisherLabel = new JLabel("Publisher:");
        JLabel editionLabel = new JLabel("Edition:");
        JLabel genreLabel = new JLabel("Genre:");
        JLabel pagesLabel = new JLabel("Pages:");
        JLabel priceLabel = new JLabel("Price:");
        JLabel authorLabel = new JLabel("Author:");
    
        JButton addBookButton = new JButton("Add Book");
        addBookButton.setPreferredSize(new Dimension(120, 40));
        addBookButton.setBackground(new Color(69, 150, 209));
        addBookButton.setForeground(Color.WHITE);
        addBookButton.setFocusPainted(false);
        addBookButton.setBorder(BorderFactory.createEmptyBorder());
        addBookButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    
        // Add button action
        addBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBookToDatabase();
            }
        });
    
        // Place labels and fields in two-column layout
        int row = 0;
    
        // Column 1: Labels, Column 2: Fields
        gbc.gridx = 0; gbc.gridy = row; formPanel.add(isbnLabel, gbc);
        gbc.gridx = 1; gbc.gridy = row++; formPanel.add(isbnField, gbc);
    
        gbc.gridx = 0; gbc.gridy = row; formPanel.add(nameLabel, gbc);
        gbc.gridx = 1; gbc.gridy = row++; formPanel.add(nameField, gbc);
    
        gbc.gridx = 0; gbc.gridy = row; formPanel.add(publisherLabel, gbc);
        gbc.gridx = 1; gbc.gridy = row++; formPanel.add(publisherField, gbc);
    
        gbc.gridx = 0; gbc.gridy = row; formPanel.add(editionLabel, gbc);
        gbc.gridx = 1; gbc.gridy = row++; formPanel.add(editionField, gbc);
    
        gbc.gridx = 0; gbc.gridy = row; formPanel.add(genreLabel, gbc);
        gbc.gridx = 1; gbc.gridy = row++; formPanel.add(genreField, gbc);
    
        gbc.gridx = 0; gbc.gridy = row; formPanel.add(pagesLabel, gbc);
        gbc.gridx = 1; gbc.gridy = row++; formPanel.add(pagesField, gbc);
    
        gbc.gridx = 0; gbc.gridy = row; formPanel.add(priceLabel, gbc);
        gbc.gridx = 1; gbc.gridy = row++; formPanel.add(priceField, gbc);
    
        gbc.gridx = 0; gbc.gridy = row; formPanel.add(authorLabel, gbc);
        gbc.gridx = 1; gbc.gridy = row++; formPanel.add(authorField, gbc);
    
        // Place "Add Book" button 
        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 10, 10, 10);
        formPanel.add(addBookButton, gbc);
    
        GroupLayout layout = new GroupLayout(mainPanel);
        mainPanel.setLayout(layout);
    
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(header, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(menuScrollPane, 225, 225, 225)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(body, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
        );
    
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(header, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(menuScrollPane)
                        .addComponent(body, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                )
        );
    
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
    }


    private void addBookToDatabase() {
        // Get values from fields
        String isbn = isbnField.getText();
        String name = nameField.getText();
        String publisher = publisherField.getText();
        String edition = editionField.getText();
        String genre = genreField.getText();
        int pages = Integer.parseInt(pagesField.getText());
        int price = Integer.parseInt(priceField.getText());
        String author = authorField.getText();

        // Column names and values for the insertRecord method
        String[] columns = { "book_isbn", "book_name", "book_publisher", "book_edition", "book_genre", "book_pages", "book_price", "book_author" };
        Object[] values = { isbn, name, publisher, edition, genre, pages, price, author };

        // Insert into database
        boolean success = DatabaseHelper.insertRecord("books", columns, values);

        // Show confirmation message
        if (success) {
            JOptionPane.showMessageDialog(this, "Book added successfully!");
            // Clear form fields
            isbnField.setText("");
            nameField.setText("");
            publisherField.setText("");
            editionField.setText("");
            genreField.setText("");
            pagesField.setText("");
            priceField.setText("");
            authorField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Failed to add book. Please check the information.");
        }
    }
}


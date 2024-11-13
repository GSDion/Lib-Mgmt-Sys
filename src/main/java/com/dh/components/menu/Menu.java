package com.dh.components.menu;

import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
    // @Override
    // protected void paintComponent(Graphics grphcs) {
    //     Graphics2D g2 = (Graphics2D) grphcs.create();
    //     // g2.setColor(new Color(241, 196, 15)); //lighter
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.dh.librarian.AddUserFrame;
import com.dh.librarian.BookFrame;
import com.dh.librarian.IssueBookFrame;
import com.dh.librarian.IssuedBooksFrame;
import com.dh.librarian.LibrarianFrame;
import com.dh.librarian.ReturnBookFrame;
import com.dh.librarian.ReturnedBooksFrame;
import com.dh.librarian.ViewBooksFrame;
import com.dh.librarian.ViewUsersFrame;

import net.miginfocom.swing.MigLayout;

    //     g2.setPaint(new GradientPaint(0, 0, new Color(44, 107, 153), 0, getHeight(), new Color(69, 150, 209)));
    //     //rgb(241, 196, 15), original: 69, 150, 209
    //     g2.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
    //     super.paintComponent(grphcs);
    // }
public class Menu extends JComponent {
    private JFrame currentFrame;
    private MenuEvent event;
    private MigLayout layout;
    
    // Menu Items for Admin and User
    private String[][] adminMenuItems = new String[][]{
        {"Dashboard"},
        {"Manage Users", "Add User", "View Users"},
        {"Manage Books", "Add Book", "View Books", "Issue Book", "View Issued Books", "Return Book", "View Returned Books"},
        {"Settings"},
        {"Help"}
    };

    private String[][] userMenuItems = new String[][]{
        {"Dashboard"},
        {"Manage Books", "View Books", "Issued Books", "Returned Books"},
        {"Settings"},
        {"Help"}
    };

    private int userType; // 1 for Admin, 2 for User

    public Menu(JFrame currentFrame, int userType) {
        this.currentFrame = currentFrame; // Set the current frame reference
        this.userType = userType; // Set the userType
        init();
    }

    private void init() {
        layout = new MigLayout("wrap 1, fillx, gapy 0, inset 2", "fill");
        setLayout(layout);
        setOpaque(true);
        
        // Use the appropriate menu items based on the user type
        //
        String[][] menuItems = (userType == 1) ? adminMenuItems : userMenuItems;
        
        //  Init MenuItem based on the selected menu
        for (int i = 0; i < menuItems.length; i++) {
            addMenu(menuItems[i][0], i, menuItems);  // Pass the menuItems to addMenu
        }
    }

    private Icon getIcon(int index) {
        URL url = getClass().getResource("/com/dh/components/menu/" + index + ".png");
        if (url != null) {
            ImageIcon originalIcon = new ImageIcon(url);
            Image scaledImage = originalIcon.getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH); 
            return new ImageIcon(scaledImage);
        } else {
            return null;
        }
    }
    
    private void addMenu(String menuName, int index, String[][] menuItems) {
        int length = menuItems[index].length;
        MenuItem item = new MenuItem(menuName, index, length > 1);
        Icon icon = getIcon(index);
        if (icon != null) {
            item.setIcon(icon);
        }
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (length > 1) {
                    if (!item.isSelected()) {
                        item.setSelected(true);
                        addSubMenu(item, index, length, getComponentZOrder(item), menuItems);
                    } else {
                        hideMenu(item, index);
                        item.setSelected(false);
                    }
                } else {
                    // FIX
                    if (userType==1) {
                        handleLibrarianMenuAction(index, 0);
                    } else {
                        handleUserMenuAction(index, 0);
                    }
                    //handleLibrarianMenuAction(index, 0);
                }
            }
        });
        add(item);
        revalidate();
        repaint();
    }

    private void addSubMenu(MenuItem item, int index, int length, int indexZorder, String[][] menuItems) {
        JPanel panel = new JPanel(new MigLayout("wrap 1, fillx, inset 0, gapy 0", "fill"));
        panel.setName(index + "");
        // Dropdown Area
        panel.setBackground(new Color(44, 107, 153)); // Darker background than Menu item
        for (int i = 1; i < length; i++) {
            final int subIndex = i;
            MenuItem subItem = new MenuItem(menuItems[index][i], i, false);
            subItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    // FIX
                    // handleLibrarianMenuAction(index, subIndex);
                    if (userType==1) {
                        handleLibrarianMenuAction(index, subIndex);
                    } else {
                        handleUserMenuAction(index, subIndex);
                    }
                }
            });
            subItem.initSubMenu(i, length);
            panel.add(subItem);
        }
        add(panel, "h 0!", indexZorder + 1);
        revalidate();
        repaint();
        MenuAnimation.showMenu(panel, item, layout, true);
    }

    private void hideMenu(MenuItem item, int index) {
        for (Component com : getComponents()) {
            if (com instanceof JPanel && com.getName() != null && com.getName().equals(index + "")) {
                com.setName(null);
                MenuAnimation.showMenu(com, item, layout, false);
                break;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        GradientPaint gradient = new GradientPaint(0, 0, new Color(56, 128, 181), getWidth(), getHeight(), new Color(69, 150, 209));
        g2.setPaint(gradient);
        g2.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
        g2.dispose();
        super.paintComponent(grphcs);
    }

    //admin menu actions
    private void handleLibrarianMenuAction(int menuIndex, int subMenuIndex) {
        // Handle specific actions based on the menu and submenu indexes
        switch (menuIndex) {
            case 0: // Dashboard
                transitionToFrame(new LibrarianFrame());
                System.out.println("Dashboard clicked");
                break;
            case 1: // Manage Users
                switch (subMenuIndex) {
                    case 1: // Add User
                        // Specific action for Add User
                        transitionToFrame(new AddUserFrame());
                        System.out.println("Add User clicked");
                        break;
                    case 2: // View Users
                        // Specific action for View Users
                        transitionToFrame(new ViewUsersFrame());
                        System.out.println("View Users clicked");
                        break;
                }
                break;
            case 2: // Manage Books
                switch (subMenuIndex) {
                    case 1: // Add Book
                        // Specific action for Add Book
                        transitionToFrame(new BookFrame());
                        System.out.println("Add Book clicked");
                        break;
                    case 2: // View Books
                        transitionToFrame(new ViewBooksFrame());
                        System.out.println("View Books clicked");
                        break;
                    case 3: // Issue Book
                        // Specific action for Issue Book
                        transitionToFrame(new IssueBookFrame());
                        System.out.println("Issue Book clicked");
                        break;
                    case 4: // View Issued Books
                        transitionToFrame(new IssuedBooksFrame());
                        System.out.println("View Issued Books clicked");
                        break;
                    case 5: // Return Book
                        // Specific action for Return Book
                        transitionToFrame(new ReturnBookFrame());
                        System.out.println("Return Book clicked");
                        break;
                    case 6: // View Returned Books
                        transitionToFrame(new ReturnedBooksFrame());
                        System.out.println("View Returned Books clicked");
                        break;
                }
                break;
            case 3: // Settings
                // Specific action for Settings
                System.out.println("Settings clicked");
                break;
            case 4: // Help
                // Specific action for Help
                System.out.println("Help clicked");
                break;
            default:
                break;
        }
    }

    // User menu actions
    private void handleUserMenuAction(int menuIndex, int subMenuIndex) {
        // Handle specific actions based on the menu and submenu indexes
        switch (menuIndex) {
            case 0: // Dashboard
                // Specific action for Dashboard
                System.out.println("Dashboard clicked");
                break;
            case 1: // Manage Books
                switch (subMenuIndex) {
                    case 1: // View Books
                        // Specific action for View Books
                        System.out.println("View Books clicked");
                        break;
                    case 2: // View Issued Books
                        // Specific action for View Issued Books
                        System.out.println("View Issued Books clicked");
                        break;
                    case 3: // View Returned Books
                        // Specific action for View Returned Books
                        System.out.println("View Returned Books clicked");
                        break;
                }
                break;
            case 2: // Settings
                // Specific action for Settings
                System.out.println("Settings clicked");
                break;
            case 3: // Help
                // Specific action for Help
                System.out.println("Help clicked");
                break;
            default:
                break;
        }
    }

    private void transitionToFrame(JFrame newFrame) {
        if (currentFrame != null) {
            currentFrame.dispose(); // Dispose of the current frame
        }
    
        newFrame.setVisible(true); // Make the new frame visible
        newFrame.setLocationRelativeTo(null); // Center the new frame on the screen
    }
    
}
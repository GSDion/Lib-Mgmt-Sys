package com.dh.components.menu;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

public class Menu extends JComponent {

    public MenuEvent getEvent() {
        return event;
    }

    public void setEvent(MenuEvent event) {
        this.event = event;
    }

    private MenuEvent event;
    private MigLayout layout;
    private String[][] menuItems = new String[][]{
        {"Dashboard"},
        // Icon: Person
        {"Manage Users", "Add User", "View Users"}, // Main menu index 1, "Add User" (subItem index 1), "View Users" (subItem index 2)
         // Icon: Book
        {"Manage Books", "Add Book", "View Books", "Issue Book", "View Issued Books", "Return Book", "View Returned Books"}, // Main menu index 2
         // Icon: Question Mark
        {"Settings"}, // Main menu index 2
         // Icon: Cog
        {"Help"}, // Main menu index 4

        /*
         * User Options
         *  {"Dashboard"},
            {"Manage Books", "View Books", "Issued Books", "Returned Books"}
            {"Settings"},
            {"Help"},
         */
        
    };

 

    public Menu() {
        init();
    }

    private void init() {
        layout = new MigLayout("wrap 1, fillx, gapy 0, inset 2", "fill");
        setLayout(layout);
        setOpaque(true);
        //  Init MenuItem
        for (int i = 0; i < menuItems.length; i++) {
            addMenu(menuItems[i][0], i);
        }

    }

    private Icon getIcon(int index) {
        URL url = getClass().getResource("/com/dh/components/menu/" + index + ".png");
        if (url != null) {
            return new ImageIcon(url);
        } else {
            return null;
        }
    }

    private void addMenu(String menuName, int index) {
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
                        addSubMenu(item, index, length, getComponentZOrder(item));
                    } else {
                        //  Hide menu
                        hideMenu(item, index);
                        item.setSelected(false);
                    }
                } else {
                    // if (event != null) {
                    //     event.selected(index, 0);
                    handleMenuAction(index, 0);
                    }
                }
            
        });
        add(item);
        revalidate();
        repaint();
    }

    private void addSubMenu(MenuItem item, int index, int length, int indexZorder) {
        JPanel panel = new JPanel(new MigLayout("wrap 1, fillx, inset 0, gapy 0", "fill"));
        panel.setName(index + "");
        panel.setBackground(new Color(44, 107, 153)); //darker
        for (int i = 1; i < length; i++) {
            final int subIndex = i; // Create a final variable to hold the current value of i
            MenuItem subItem = new MenuItem(menuItems[index][i], i, false);
            subItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                     // Handle specific actions for submenu items
                     handleMenuAction(index, subIndex); // Use the submenu index
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
        g2.setColor(new Color(69, 150, 209)); //lighter
        g2.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
        super.paintComponent(grphcs);
    }

    private void handleMenuAction(int menuIndex, int subMenuIndex) {
        // Handle specific actions based on the menu and submenu indexes
        switch (menuIndex) {
            case 0: // Dashboard
                // Specific action for Dashboard
                System.out.println("Dashboard clicked");
                break;
            case 1: // Manage Users
                switch (subMenuIndex) {
                    case 1: // Add User
                        // Specific action for Add User
                        System.out.println("Add User clicked");
                        break;
                    case 2: // View Users
                        // Specific action for View Users
                        System.out.println("View Users clicked");
                        break;
                }
                break;
            case 2: // Manage Books
                switch (subMenuIndex) {
                    case 1: // Add Book
                        // Specific action for Add Book
                        System.out.println("Add Book clicked");
                        break;
                    case 2: // View Books
                        // Specific action for View Books
                        System.out.println("View Books clicked");
                        break;
                    case 3: // Issue Book
                        // Specific action for Issue Book
                        System.out.println("Issue Book clicked");
                        break;
                    case 4: // View Issued Books
                        // Specific action for View Issued Books
                        System.out.println("View Issued Books clicked");
                        break;
                    case 5: // Return Book
                        // Specific action for Return Book
                        System.out.println("Return Book clicked");
                        break;
                    case 6: // View Returned Books
                        // Specific action for View Returned Books
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
    

}

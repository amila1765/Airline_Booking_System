
package gui;

import models.User;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Amila_Vishwajith
 */



public class DashboardAdmin extends JFrame
{
    private User adminUser;
    
    public DashboardAdmin(User user) 
    {
        this.adminUser = user;
        setTitle("Admin Dashboard - " + user.getUsername());
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout (new GridLayout(6, 1, 10, 10));

        JLabel titleLabel = new JLabel("Welcome, " + user.getUsername(), SwingConstants.CENTER);
        add(titleLabel);

        JButton viewUsersBtn = new JButton("👤 View All Users");
        JButton createUserBtn = new JButton("➕ Create New User");
        JButton viewBookingsBtn = new JButton("📄 View All Bookings");
        JButton reportsBtn = new JButton("📊 Generate Reports (optional)");
        JButton logoutBtn = new JButton("🚪 Logout");

        // Add to UI
        add(viewUsersBtn);
        add(createUserBtn);
        add(viewBookingsBtn);
        add(reportsBtn);
        add(logoutBtn);

        // Actions
        viewUsersBtn.addActionListener(e -> new ViewUsersForm());
        createUserBtn.addActionListener(e -> new SignUpForm());
        viewBookingsBtn.addActionListener(e -> new AllBookingsForm());
        reportsBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Report generation coming soon."));
        logoutBtn.addActionListener(e -> 
{
            dispose();
            new LoginForm();
        });

        setVisible(true);
    }
}

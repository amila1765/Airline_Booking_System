
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

        
        JButton manageUsersButton = new JButton("👥 Manage Users");
        JButton viewBookingsBtn = new JButton("📄 View All Bookings");
        JButton reportsBtn = new JButton("📊 Generate Reports (optional)");
        JButton logoutBtn = new JButton("🚪 Logout");

        // Add to UI
        
        add(manageUsersButton);
        add(viewBookingsBtn);
        add(reportsBtn);
        add(logoutBtn);

        // Actions
        manageUsersButton.addActionListener(e -> new ViewUsersForm());
        viewBookingsBtn.addActionListener(e -> new AllBookingsForm());
        reportsBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Report generation coming soon."));
        logoutBtn.addActionListener(e -> 
        {
        dispose(); 
            
        });

        setVisible(true);
    }
}

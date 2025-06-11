
package gui;

import models.User;
import javax.swing.*;

/**
 *
 * @author Amila_Vishwajith
 */
public class DashboardAdmin extends JFrame
{
    public DashboardAdmin(User user) {
        setTitle("Admin Dashboard");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        add(new JLabel("👋 Welcome, " + user.getUsername()));
        add(new JButton("Manage Users"));
        add(new JButton("Manage Flights"));
        add(new JButton("Manage Airports"));
        add(new JButton("Generate Reports"));
        
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> 
        {
            new LoginForm();
            dispose();
        });
        
        add(logoutButton);

        setVisible(true);
    }
}

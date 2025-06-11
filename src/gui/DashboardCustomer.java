
package gui;

import models.User;
import javax.swing.*;

/**
 *
 * @author Amila_Vishwajith
 */
public class DashboardCustomer extends JFrame 
{
    public DashboardCustomer(User user) 
    {
        setTitle("Customer Dashboard");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        add(new JLabel("👋 Welcome, " + user.getUsername()));
        add(new JButton("View Flights"));
        add(new JButton("Book Flight"));
        add(new JButton("My Bookings"));
        
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
   
    
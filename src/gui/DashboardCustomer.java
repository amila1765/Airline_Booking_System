
package gui;

import models.User;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Amila_Vishwajith
 */
public class DashboardCustomer extends JFrame 
{
    public DashboardCustomer(User user) 
    {
        setTitle("Customer Dashboard");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JLabel welcomeLabel = new JLabel("👋 Welcome, " + user.getUsername());
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalStrut(20));
        add(welcomeLabel);
        add(Box.createVerticalStrut(20));

        // Search Flights Button
        JButton searchFlightsButton = new JButton("🔍 Search Flights");
        searchFlightsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchFlightsButton.addActionListener(e -> new FlightSearchForm());
        add(searchFlightsButton);
        add(Box.createVerticalStrut(10));

        // Placeholder Book Flight Button
        JButton bookFlightButton = new JButton("🎟 Book Flight (Coming soon)");
        bookFlightButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(bookFlightButton);
        add(Box.createVerticalStrut(10));

        // Placeholder My Bookings Button
        JButton myBookingsButton = new JButton("📋 My Bookings (Coming soon)");
        myBookingsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(myBookingsButton);
        add(Box.createVerticalStrut(10));

        // Logout Button
        JButton logoutButton = new JButton("🔓 Logout");
        logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoutButton.addActionListener(e ->
        {
            new LoginForm(); // return to login screen
            dispose();       // close dashboard
        });
        
        add(logoutButton);

        setVisible(true);
    }
}
   
    

package gui;

import java.awt.*;
import models.User;
import javax.swing.*;

/**
 *
 * @author Amila_Vishwajith
 */
public class DashboardOperator extends JFrame 
{
    public DashboardOperator(User user) 
    {
        setTitle("Operator Dashboard");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JLabel welcomeLabel = new JLabel("👋 Welcome, " + user.getUsername());
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalStrut(20));
        add(welcomeLabel);
        add(Box.createVerticalStrut(20));

        // Search Flights
        JButton searchFlightsButton = new JButton("🔍 Search Flights");
        searchFlightsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchFlightsButton.addActionListener(e -> new FlightSearchForm(user));
        add(searchFlightsButton);
        add(Box.createVerticalStrut(10));

        // Book for Customer (coming soon)
        JButton bookFlightButton = new JButton("📦 Book for Customer");
        bookFlightButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        bookFlightButton.addActionListener(e -> new FlightSearchForm(user));
        add(bookFlightButton);
        add(Box.createVerticalStrut(10));

        // Generate Reports (coming soon)
        JButton reportsButton = new JButton("📊 Generate Reports");
        reportsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(reportsButton);
        add(Box.createVerticalStrut(10));

        // Logout
        JButton logoutButton = new JButton("🔓 Logout");
        logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoutButton.addActionListener(e -> 
        {
            dispose();
        });
        
        add(logoutButton);

        setVisible(true);
    }
}

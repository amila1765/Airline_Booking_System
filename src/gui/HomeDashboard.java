
// File: gui/HomeDashboard.java
package gui;

import javax.swing.*;
import java.awt.*;
import java.util.Properties;
import org.jdatepicker.impl.*;


public class HomeDashboard extends JFrame 
{

    public HomeDashboard() 
    {
        //setTitle("Airline Booking System - Home");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //Header Panel
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        headerPanel.setBackground(new Color(25, 118, 210));

        JLabel logoLabel = new JLabel("✈ SkyWings");
        logoLabel.setForeground(Color.WHITE);
        logoLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        headerPanel.add(logoLabel);

        headerPanel.add(Box.createHorizontalStrut(200)); // spacing

        JButton homeBtn = new JButton("Home");
        JButton bookBtn = new JButton("Book Flight");
        JButton statusBtn = new JButton("Flight Status");
        JButton loginBtn = new JButton("Login");
        JButton signupBtn = new JButton("Sign Up");

        JButton[] navButtons = {homeBtn, bookBtn, statusBtn, loginBtn, signupBtn};
        for (JButton btn : navButtons) {
            btn.setFocusPainted(false);
            headerPanel.add(btn);
        }

        add(headerPanel, BorderLayout.NORTH);

        //Center Welcome Panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(240, 248, 255));

        JLabel title = new JLabel("Find Your Perfect Flight");
        title.setFont(new Font("SansSerif", Font.BOLD, 32));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel("Book your next adventure with comfort, reliability, and service.");
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 16));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerPanel.add(Box.createVerticalStrut(50));
        centerPanel.add(title);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(subtitle);
        centerPanel.add(Box.createVerticalStrut(30));

        //Search Panel
        JPanel searchPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        searchPanel.setMaximumSize(new Dimension(700, 100));
        searchPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchPanel.setBackground(new Color(240, 248, 255));

        JComboBox<String> fromCombo = new JComboBox<>(new String[]{"Colombo", "London", "Dubai"});
        JComboBox<String> toCombo = new JComboBox<>(new String[]{"Colombo", "London", "Dubai"});
        JComboBox<String> classCombo = new JComboBox<>(new String[]{"Economy", "Business", "First"});
        JComboBox<Integer> passengerCombo = new JComboBox<>();
        for (int i = 1; i <= 10; i++) 
        {
            passengerCombo.addItem(i);
        }
        JTextField dateField = new JTextField("YYYY-MM-DD");
        JButton searchBtn = new JButton("Search Flights");

        searchPanel.add(new JLabel("From:")); searchPanel.add(new JLabel("To:")); searchPanel.add(new JLabel("Depart Date:"));
        searchPanel.add(fromCombo); searchPanel.add(toCombo); searchPanel.add(dateField);

        centerPanel.add(searchPanel);

        JPanel bottomSearch = new JPanel(new FlowLayout());
        bottomSearch.setBackground(new Color(240, 248, 255));
        bottomSearch.add(new JLabel("Passengers:"));
        bottomSearch.add(passengerCombo);
        bottomSearch.add(new JLabel("Class:"));
        bottomSearch.add(classCombo);
        bottomSearch.add(searchBtn);

        centerPanel.add(bottomSearch);
        add(centerPanel, BorderLayout.CENTER);

        // Footer (optional)
        JLabel footer = new JLabel("© 2025 SkyWings Airlines - All rights reserved.", SwingConstants.CENTER);
        footer.setForeground(Color.GRAY);
        add(footer, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(HomeDashboard::new);
    }
}

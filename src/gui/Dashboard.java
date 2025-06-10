
package gui;

/**
 *
 * @author Amila_Vishwajith
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Dashboard extends JFrame
{
   public Dashboard() 
   {
        setTitle("Airline Ticket Booking Dashboard");
        setSize(400,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

                JLabel welcomeLabel = new JLabel("Welcome to the Airline Booking System");
        add(welcomeLabel);

        JButton loginButton = new JButton("Login");
        JButton exitButton = new JButton("Exit");

        add(loginButton);
        add(exitButton);

        loginButton.addActionListener((ActionEvent e) -> 
        {
            new LoginForm();
        });

        exitButton.addActionListener((ActionEvent e) -> 
        {
            System.exit(0);
        });

        setVisible(true);
    }

    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(Dashboard::new);
    }
}
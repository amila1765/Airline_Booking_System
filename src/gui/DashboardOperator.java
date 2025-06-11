
package gui;

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
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        add(new JLabel("👋 Welcome, " + user.getUsername()));
        add(new JButton("Book for Customer"));
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

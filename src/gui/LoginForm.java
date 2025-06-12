
package gui;

import models.User;
import services.UserService;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class LoginForm extends JFrame 
{
    public LoginForm() 
    {
        setTitle("Login");
        setSize(350, 250);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(30, 30, 80, 25);
        add(userLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(120, 30, 160, 25);
        add(usernameField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(30, 70, 80, 25);
        add(passLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(120, 70, 160, 25);
        add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(120, 110, 80, 30);
        add(loginButton);
        
        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setBounds(210, 110, 80, 30);
        add(signUpButton);
        
        JLabel messageLabel = new JLabel();
        messageLabel.setBounds(30, 150, 300, 25);
        add(messageLabel);

        loginButton.addActionListener(e -> 
        {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            UserService userService = new UserService();
            try {
                User loggedInUser = userService.login(username, password);

                if (loggedInUser != null) 
                {
                    messageLabel.setText("✅ Welcome, " + loggedInUser.getUsername());
                    // Proceed to dashboard or user panel
                    
                    dispose(); // Close login form

                switch (loggedInUser.getRole()) 
                {
                case "Customer":
                new DashboardCustomer(loggedInUser);
                break;
                case "Operator":
                new DashboardOperator(loggedInUser);
                break;
                case "Admin":
                new DashboardAdmin(loggedInUser);
                break;
                default:
                JOptionPane.showMessageDialog(this, "Unknown user role.");
                }
                }
                else 
                {
                    messageLabel.setText("❌ Invalid credentials.");
                }
            } 
            catch (SQLException ex)
            {
                ex.printStackTrace();
                messageLabel.setText("❌ Database error." + ex.getMessage());
            }
        });

        signUpButton.addActionListener(e -> new SignUpForm());

        setVisible(true);
    }
}
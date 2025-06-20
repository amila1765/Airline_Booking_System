
package gui;

import models.User;
import services.UserService;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.function.Consumer;

public class LoginForm extends JFrame 
{
    private Consumer<User> loginCallback; // Optional callback
    private final Consumer<User> onLoginSuccess;
    
    // Default constructor
    public LoginForm() 
    {
        this(null); // Call the overloaded constructor with null callback
    }
    
     // Constructor with callback
    public LoginForm(Consumer<User> onLoginSuccess) 
    {
        this.onLoginSuccess = onLoginSuccess ;

        setTitle("Login");
        setSize(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 40, 80, 25);
        add(userLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(150, 40, 180, 25);
        add(usernameField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(50, 80, 80, 25);
        add(passLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(150, 80, 180, 25);
        add(passwordField);

        // Login button
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(150, 120, 100, 30);
        add(loginButton);
        
        // Message label
        JLabel messageLabel = new JLabel();
        messageLabel.setBounds(50, 160, 300, 25);
        add(messageLabel);
        
        // Don't have an account? Sign up
        JLabel noAccountLabel = new JLabel("Don't have an account?");
        noAccountLabel.setBounds(50, 200, 160, 25);
        add(noAccountLabel);
        
        JButton goToSignUpBtn = new JButton("Sign Up");
        goToSignUpBtn.setBounds(210, 200, 100, 25);
        goToSignUpBtn.setBorderPainted(false);
        goToSignUpBtn.setContentAreaFilled(false);
        goToSignUpBtn.setForeground(Color.BLUE);
        goToSignUpBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(goToSignUpBtn);
        
        // Login button action
        loginButton.addActionListener(e -> 
        {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            UserService userService = new UserService();
            try {
                User loggedInUser = userService.login(username, password);

                if (loggedInUser != null) 
                {
                    // Callback to update HomeDashboard
                    if (onLoginSuccess != null) 
                    {
                        onLoginSuccess.accept(loggedInUser);
                    }
                    
                // Navigate to dashboard
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
                }
                dispose(); // Close login form
                }
                else 
                {
                    messageLabel.setText("❌ Invalid credentials.");
                }
            } 
            catch (SQLException ex)
            {
               messageLabel.setText("❌ Database error." + ex.getMessage());
               ex.printStackTrace();
            }
        });
        
        // Sign up action
        goToSignUpBtn.addActionListener(e ->
        {
                new SignUpForm();
                dispose();
        });
        
        setVisible(true);
    }
}
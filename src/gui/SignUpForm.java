
package gui;
import javax.swing.*;
import models.User;
import services.UserService;

public class SignUpForm extends JFrame 
{
    public SignUpForm() 
    {
        setTitle("Sign Up");
        setSize(350, 300);
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

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(30, 110, 80, 25);
        add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(120, 110, 160, 25);
        add(emailField);

        JLabel roleLabel = new JLabel("Role:");
        roleLabel.setBounds(30, 150, 80, 25);
        add(roleLabel);

        String[] roles = {"Customer", "Operator", "Admin"};
        JComboBox<String> roleCombo = new JComboBox<>(roles);
        roleCombo.setBounds(120, 150, 160, 25);
        add(roleCombo);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(120, 190, 100, 30);
        add(registerButton);

        JLabel messageLabel = new JLabel();
        messageLabel.setBounds(30, 230, 300, 25);
        add(messageLabel);

        registerButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String email = emailField.getText();
            String role = roleCombo.getSelectedItem().toString();
            String status = "Active";

            User newUser = new User(username, password, email, role, status);
            boolean success = UserService.register(newUser);

            if (success) {
                messageLabel.setText("✅ Registration successful!");
                usernameField.setText("");
                passwordField.setText("");
                emailField.setText("");
                roleCombo.setSelectedIndex(0);
            } else {
                messageLabel.setText("❌ Registration failed!");
            }
        });

        setVisible(true);
    }
}

    
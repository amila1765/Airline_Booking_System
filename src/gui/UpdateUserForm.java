
package gui;

/**
 *
 * @author Amila_Vishwajith
 */

import models.User;
import services.UserService;
import javax.swing.*;

public class UpdateUserForm extends JFrame 
{
    private final User userToEdit;
    private final Runnable onUserUpdatedCallback;

    public UpdateUserForm(User user, Runnable onUserUpdatedCallback) {
        this.userToEdit = user;
        this.onUserUpdatedCallback = onUserUpdatedCallback;
        setupForm();
    }

    private void setupForm() 
    {
        setTitle("Update User - ID " + userToEdit.getUserId());
        setSize(350, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(30, 30, 80, 25);
        add(userLabel);

        JTextField usernameField = new JTextField(userToEdit.getUsername());
        usernameField.setBounds(120, 30, 160, 25);
        add(usernameField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(30, 70, 80, 25);
        add(emailLabel);

        JTextField emailField = new JTextField(userToEdit.getEmail());
        emailField.setBounds(120, 70, 160, 25);
        add(emailField);

        JLabel roleLabel = new JLabel("Role:");
        roleLabel.setBounds(30, 110, 80, 25);
        add(roleLabel);

        String[] roles = {"Customer", "Operator", "Admin"};
        JComboBox<String> roleCombo = new JComboBox<>(roles);
        roleCombo.setBounds(120, 110, 160, 25);
        roleCombo.setSelectedItem(userToEdit.getRole());
        add(roleCombo);

        JLabel statusLabel = new JLabel("Status:");
        statusLabel.setBounds(30, 150, 80, 25);
        add(statusLabel);

        String[] statuses = {"Active", "Inactive"};
        JComboBox<String> statusCombo = new JComboBox<>(statuses);
        statusCombo.setBounds(120, 150, 160, 25);
        statusCombo.setSelectedItem(userToEdit.getStatus());
        add(statusCombo);

        JButton updateButton = new JButton("Update");
        updateButton.setBounds(120, 190, 100, 30);
        add(updateButton);

        JLabel messageLabel = new JLabel();
        messageLabel.setBounds(30, 230, 300, 25);
        add(messageLabel);

        updateButton.addActionListener(e -> {
            String newUsername = usernameField.getText().trim();
            String newEmail = emailField.getText().trim();
            String newRole = roleCombo.getSelectedItem().toString();
            String newStatus = statusCombo.getSelectedItem().toString();

            userToEdit.setUsername(newUsername);
            userToEdit.setEmail(newEmail);
            userToEdit.setRole(newRole);
            userToEdit.setStatus(newStatus);

            if (UserService.updateUser(userToEdit)) {
                messageLabel.setText("✅ User updated.");
                if (onUserUpdatedCallback != null) {
                    onUserUpdatedCallback.run();
                }
                dispose(); // Close form
            } else {
                messageLabel.setText("❌ Update failed.");
            }
        });

        setVisible(true);
    }

}

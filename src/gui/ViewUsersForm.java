
package gui;

/**
 *
 * @author Amila_Vishwajith
 */

import models.User;
import services.UserService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ViewUsersForm extends JFrame
{

    private JTable userTable;
    private DefaultTableModel tableModel;

    public ViewUsersForm()
    {
        setTitle("View All Users");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Columns
        String[] columns = {"User ID", "Username", "Email", "Role", "Status"};
        tableModel = new DefaultTableModel(columns, 0);
        userTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(userTable);
        add(scrollPane, BorderLayout.CENTER);

        // Load data
        loadUsers();

        // Button panel
        JPanel buttonPanel = new JPanel();
        JButton deactivateBtn = new JButton("Deactivate Selected User");
        buttonPanel.add(deactivateBtn);
        add(buttonPanel, BorderLayout.SOUTH);

        // Button action
        deactivateBtn.addActionListener(e -> deactivateUser());

        setVisible(true);
    }

    private void loadUsers() 
    {
        tableModel.setRowCount(0); // Clear table
        List<User> users = UserService.getAllUsers();

        for (User user : users) 
        {
            tableModel.addRow(new Object[]
            {
                    user.getUserId(),
                    user.getUsername(),
                    user.getEmail(),
                    user.getRole(),
                    user.getStatus()
            });
        }
    }

    private void deactivateUser() 
    {
        int selectedRow = userTable.getSelectedRow();

        if (selectedRow == -1) 
        {
            JOptionPane.showMessageDialog(this, "Select a user first.");
            return;
        }

        int userId = (int) tableModel.getValueAt(selectedRow, 0);
        String status = (String) tableModel.getValueAt(selectedRow, 4);

        if (status.equals("Inactive")) 
        {
            JOptionPane.showMessageDialog(this, "User is already inactive.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to deactivate this user?",
                "Confirm Deactivation", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) 
        {
            boolean success = UserService.deactivateUser(userId);

            if (success) {
                JOptionPane.showMessageDialog(this, "User deactivated.");
                loadUsers(); // Refresh
            } else {
                JOptionPane.showMessageDialog(this, "Failed to deactivate user.");
            }
        }
    }
}




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
        setTitle("👥 View All Users");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Columns
        String[] columns = {"User ID", "Username", "Email", "Role", "Status"};
        tableModel = new DefaultTableModel(columns, 0);
        userTable = new JTable(tableModel);
        add(new JScrollPane(userTable), BorderLayout.CENTER);
        
        // Action Panel
        JPanel actionPanel = new JPanel(new FlowLayout());

        JButton addUserBtn = new JButton("➕ Add User");
        JButton updateUserBtn = new JButton("📝 Update User");
        JButton deleteUserBtn = new JButton("🗑 Delete User");
        JButton toggleStatusBtn = new JButton("🔄 Activate/Deactivate");

        actionPanel.add(addUserBtn);
        actionPanel.add(updateUserBtn);
        actionPanel.add(deleteUserBtn);
        actionPanel.add(toggleStatusBtn);

        add(actionPanel, BorderLayout.SOUTH);

        // Load data
        loadUsers();
        
         //Add User
        addUserBtn.addActionListener(e -> new SignUpForm(this::loadUsers));   
        
        //Update User
        updateUserBtn.addActionListener(e -> 
        {
                int row = userTable.getSelectedRow();
        if (row >= 0) 
        {
        int userId = (int) tableModel.getValueAt(row, 0);
        String username = (String) tableModel.getValueAt(row, 1);
        String email = (String) tableModel.getValueAt(row, 2);
        String role = (String) tableModel.getValueAt(row, 3);
        String status = (String) tableModel.getValueAt(row, 4);

        // Reconstruct a User object
        User selectedUser = new User(userId, username, "", email, role, status);
        new UpdateUserForm(selectedUser, this::loadUsers);
        } 
        else 
        {
        JOptionPane.showMessageDialog(this, "❗ Please select a user to update.");
    }
    });
        
        //Delete User
        deleteUserBtn.addActionListener(e -> 
        {
            int row = userTable.getSelectedRow();
            if (row >= 0) 
            {
                int userId = (int) tableModel.getValueAt(row, 0);
                int confirm = JOptionPane.showConfirmDialog(this, "Are you sure?", "Delete User", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION)
                {
                    if (UserService.deleteUser(userId)) 
                    {
                        JOptionPane.showMessageDialog(this, "🗑 User deleted.");
                        loadUsers();
                    } 
                    else 
                    {
                        JOptionPane.showMessageDialog(this, "❌ Failed to delete.");
                    }
                }
            } 
            else 
            {
                JOptionPane.showMessageDialog(this, "❗ Select a user first.");
            }
        });
        
        //Toggle Status
        toggleStatusBtn.addActionListener(e -> 
        {
            int selectedRow = userTable.getSelectedRow();
            if (selectedRow >= 0) {
                int userId = (int) tableModel.getValueAt(selectedRow, 0);
                String currentStatus = (String) tableModel.getValueAt(selectedRow, 4);
                String newStatus = currentStatus.equals("Active") ? "Inactive" : "Active";
                boolean success = UserService.updateUserStatus(userId, newStatus);
                 
                if (success)
                {
                    JOptionPane.showMessageDialog(this, "🔄 Status updated to " + newStatus);
                    loadUsers();
                } else 
                {
                    JOptionPane.showMessageDialog(this, "❌ Failed to update status.");
                }
            } 
            else
            {
                JOptionPane.showMessageDialog(this, "❗ Select a user first.");
            }
        });
        
        setVisible(true);
    }

    private void loadUsers() 
    {
        tableModel.setRowCount(0);// clear existing rows
        List<User> users = UserService.getAllUsers();
        for (User u : users) 
        {
            tableModel.addRow(new Object[]
            {
                    u.getUserId(),
                    u.getUsername(),
                    u.getEmail(),
                    u.getRole(),
                    u.getStatus()
            });
        }
    }
}

        
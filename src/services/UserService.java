
package services;

/**
 *
 * @author Amila_Vishwajith
 */
import database.DBConnection;
import models.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService
{ 
    //register user
       
    public static boolean register(User user) 
        {
        String sql = "INSERT INTO users (username, password, email, role, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) 
        {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getRole());
            stmt.setString(5, user.getStatus());
            stmt.executeUpdate();
            return true;
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            return false;
        }
    }

// Login user
    public static User login(String username, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ? AND status = 'Active'";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("role"),
                        rs.getString("status")
                );
            }
        }
        return null;
    }

    // Get all customers (for operator booking)
    public static List<User> getAllCustomers()
    {
        List<User> customers = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE role = 'Customer' AND status = 'Active'";

        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) 
            {
                User user = new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("role"),
                        rs.getString("status")
                );
                customers.add(user);
            }

        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }

        return customers;
    }
    
    // Get all users (for admin use)
    public static List<User> getAllUsers() 
    {
    List<User> userList = new ArrayList<>();
    String sql = "SELECT * FROM users";

    try (Connection conn = DBConnection.getInstance().getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) 
    {

        while (rs.next()) 
        {
            User user = new User(
                    rs.getInt("user_id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("email"),
                    rs.getString("role"),
                    rs.getString("status")
            );
            userList.add(user);
        }

    } 
    catch (SQLException e) 
    {
        e.printStackTrace();
    }

    return userList;
}
    
    //update user(admin use)
    public static boolean updateUser(User user) 
    {
    String sql = "UPDATE users SET username = ?, email = ?, role = ?, status = ? WHERE user_id = ?";
    try (Connection conn = DBConnection.getInstance().getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) 
    {

        stmt.setString(1, user.getUsername());
        stmt.setString(2, user.getEmail());
        stmt.setString(3, user.getRole());
        stmt.setString(4, user.getStatus());
        stmt.setInt(5, user.getUserId());

        return stmt.executeUpdate() > 0;

    } catch (Exception e)
    {
        e.printStackTrace();
        return false;
    }
}

    
    //delete user(admin use)
    public static boolean deleteUser(int userId) 
    {
    String sql = "DELETE FROM users WHERE user_id = ?";
    try (Connection conn = DBConnection.getInstance().getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql))
    {
        stmt.setInt(1, userId);
        return stmt.executeUpdate() > 0;
    } catch (SQLException e) 
    {
        e.printStackTrace();
        return false;
    }
}

    //Deactivate user
    public static boolean deactivateUser(int userId) 
    {
    String sql = "UPDATE users SET status = 'Inactive' WHERE user_id = ?";
    try (Connection conn = DBConnection.getInstance().getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) 
    {

        stmt.setInt(1, userId);
        return stmt.executeUpdate() > 0;

    } 
    catch (SQLException e) 
    {
        e.printStackTrace();
        return false;
    }
}

    public static boolean updateUserStatus(int userId, String newStatus) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

   
  


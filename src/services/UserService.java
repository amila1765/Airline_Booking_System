
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

}

   
  


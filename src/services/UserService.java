
package services;

/**
 *
 * @author Amila_Vishwajith
 */
import database.DBConnection;
import models.User;
import java.sql.*;

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
            stmt.setString(3, user.getRole());
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
    public static User login(String username, String password) throws SQLException
    {
        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) 
                {
                    int userId = rs.getInt("user_id"); // or whatever your column is named
                    String email = rs.getString("email");
                    String role = rs.getString("role");
                    String status = rs.getString("status");
                    
                    // Return the valid user        
                    return new User(userId, username, password, email, role, status); 
                }
            
            return null;  // login failed
    } 
}
    
  



package models;

/**
 *
 * @author Amila_Vishwajith
 */
public class User 
{
    private int userId;
    private String username;
    private String password;
    private String email;
    private String role;   // Customer, Operator, Admin
    private String status; // check Active, Inactive

    // Constructor
    public User(int userId, String username, String password, String email , String role, String status)
    {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.status = status;
    }

    // Overloaded constructor without userId (for inserts)
    public User(String username , String password , String email , String role, String status) 
    {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.status = status;
    }

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
     public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // toString method
    @Override
        public String toString() 
        {
        return username;
        }

}

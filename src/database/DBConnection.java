
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Amila_Vishwajith
 */
public class DBConnection 
{
    private static DBConnection instance;
    private Connection connection;
    
    private static final String URL = "jdbc:mysql://localhost:3306/airline_booking_system";
    private static final String USER = "root"; 
    private static final String PASSWORD = ""; 

    private DBConnection() throws SQLException
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } 
        catch (ClassNotFoundException e) 
        {
            throw new SQLException("MySQL JDBC driver not found.", e);
        }
    }
        
    
public static DBConnection getInstance() throws SQLException
    {
         if (instance == null || instance.getConnection().isClosed()) 
        {
            instance = new DBConnection();
        }
        return instance;
    }
    
public Connection getConnection()
    {
        return connection;
    }
}


package services;

/**
 *
 * @author Amila_Vishwajith
 */
import database.DBConnection;
import java.sql.*;

public class ReportService 
{
 private Connection conn;

    public ReportService() throws SQLException 
    {
        conn = DBConnection.getInstance().getConnection();
    }

    public void generateManifest(int flightId) 
    {
        // List passengers on a flight
    }

    public void generateFlightReport(String airportName, Timestamp from, Timestamp to) {
        // List arriving and departing flights
    }
}
 


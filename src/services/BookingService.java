
package services;

/**
 *
 * @author Amila_Vishwajith
 */

import database.DBConnection;
import models.Booking;
import java.sql.*;
import java.util.ArrayList;

public class BookingService 
{
   private Connection conn;

    public BookingService() throws SQLException 
    {
        conn = DBConnection.getInstance().getConnection();
    }

    public boolean bookFlight(Booking booking) 
    {
        // Add booking after checking seat availability
        return false;
    }

    public ArrayList<Booking> getBookingsByUser(int userId) 
    {
        // Retrieve bookings for specific user
        return new ArrayList<>();
    }

    public boolean isSeatAvailable(int flightId, String seatClass) 
    {
        // Count booked seats and compare with capacity
        return false;
    }
}
 


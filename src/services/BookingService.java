
package services;

/**
 *
 * @author Amila_Vishwajith
 */

import database.DBConnection;
import models.Booking;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class BookingService 
{ 
    private int bookingId;
    
   // Register a new booking
    public static boolean register(Booking booking) 
    {
        String sql = "INSERT INTO bookings (user_id, flight_id, seat_class, booking_date) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) 
        {

            stmt.setInt(1, booking.getUserId());
            stmt.setInt(2, booking.getFlightId());
            stmt.setString(3, booking.getSeatClass());
            stmt.setTimestamp(4, booking.getBookingDate());

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) 
        {
            System.err.println("Booking insertion failed: " + e.getMessage());
            return false;
        }
    }
    
    public static List<Booking> getAllBookings() 
    {
    List<Booking> bookings = new ArrayList<>();
    String sql = "SELECT * FROM bookings";

    try (Connection conn = DBConnection.getInstance().getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) 
    {

        while (rs.next()) 
        {
            Booking booking = new Booking(
                    rs.getInt("booking_id"),
                    rs.getInt("user_id"),
                    rs.getInt("flight_id"),
                    rs.getString("seat_class"),
                    rs.getTimestamp("booking_date")
            );
            bookings.add(booking);
        }

    } catch (SQLException e)
    {
        e.printStackTrace();
    }

    return bookings;
}
    
    public static List<Booking> getBookingsByUser(int userId) 
    {
    List<Booking> list = new ArrayList<>();
    String sql = "SELECT * FROM bookings WHERE user_id = ?";

    try (Connection conn = DBConnection.getInstance().getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql))
    {

        stmt.setInt(1, userId);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) 
        {
            Booking booking = new Booking(
                    rs.getInt("booking_id"),
                    rs.getInt("user_id"),
                    rs.getInt("flight_id"),
                    rs.getString("seat_class"),
                    rs.getTimestamp("booking_date")
            );
            list.add(booking);
        }

    } 
    catch (SQLException e) 
    {
        e.printStackTrace();
    }

    return list;
}



    // You can add more functions later like:

    // - deleteBooking(int bookingId)
    // - getPassengerManifest(int flightId)
}
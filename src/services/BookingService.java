
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

public class BookingService 
{
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

        } catch (SQLException e) {
            System.err.println("Booking insertion failed: " + e.getMessage());
            return false;
        }
    }

    // You can add more functions later like:
    // - getBookingsByUser(int userId)
    // - getAllBookings()
    // - deleteBooking(int bookingId)
    // - getPassengerManifest(int flightId)
}
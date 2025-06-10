
package models;

import java.time.LocalDateTime;

/**
 *
 * @author Amila_Vishwajith
 */
public class Booking 
{
     private int bookingId;
    private int userId;
    private int flightId;
    private String seatClass;
    private LocalDateTime bookingDate;

    public Booking(int bookingId, int userId, int flightId, String seatClass, LocalDateTime bookingDate) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.flightId = flightId;
        this.seatClass = seatClass;
        this.bookingDate = bookingDate;
    }

    public int getBookingId() { return bookingId; }
    public int getUserId() { return userId; }
    public int getFlightId() { return flightId; }
    public String getSeatClass() { return seatClass; }
    public LocalDateTime getBookingDate() { return bookingDate; }

    @Override
    public String toString()
    {
        return "Booking " + bookingId + " | User: " + userId + " | Flight: " + flightId;
    }
}

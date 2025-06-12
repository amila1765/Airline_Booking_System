
package models;

import java.sql.Timestamp;

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
    private Timestamp bookingDate;

    public Booking(int userId, int flightId, String seatClass, Timestamp bookingDate) 
    {
        this.userId = userId;
        this.flightId = flightId;
        this.seatClass = seatClass;
        this.bookingDate = bookingDate;
    }
    
    public Booking(int bookingId, int userId, int flightId, String seatClass, Timestamp bookingDate) 
    {
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
    public Timestamp getBookingDate() { return bookingDate; 
    }
}

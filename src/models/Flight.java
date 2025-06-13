
package models;

import java.time.LocalDateTime;

/**
 *
 * @author Amila_Vishwajith
 */
public class Flight 
{
    private int flightId;
    private int airplaneId;
    private int departureAirportId;
    private int arrivalAirportId;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    public Flight(int flightId, int airplaneId, int departureAirportId, int arrivalAirportId, LocalDateTime departureTime, LocalDateTime arrivalTime) 
    {
        this.flightId = flightId;
        this.airplaneId = airplaneId;
        this.departureAirportId = departureAirportId;
        this.arrivalAirportId = arrivalAirportId;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public int getFlightId() { return flightId; }
    public int getAirplaneId() { return airplaneId; }
    public int getDepartureAirportId() { return departureAirportId; }
    public int getArrivalAirportId() { return arrivalAirportId; }
    public LocalDateTime getDepartureTime() { return departureTime; }
    public LocalDateTime getArrivalTime() { return arrivalTime; }

    @Override
    public String toString() 
    {
        return "Flight " + flightId + " | From: " + departureAirportId + " To: " + arrivalAirportId;
    }
}

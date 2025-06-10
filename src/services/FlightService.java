
package services;

/**
 *
 * @author Amila_Vishwajith
 */
import database.DBConnection;
import models.Flight;

import java.sql.*;
import java.util.ArrayList;

public class FlightService 
{
    private Connection conn;

    public FlightService() throws SQLException {
        conn = DBConnection.getInstance().getConnection();
    }

    public ArrayList<Flight> getAllFlights() {
        // Code to return a list of all flights
        return new ArrayList<>();
    }

    public boolean scheduleFlight(Flight flight) {
        // Insert new flight into DB after validations
        return false;
    }

    public boolean isFlightConflict(Flight flight) {
        // Check if airplane is already booked in given time
        return false;
    }
}

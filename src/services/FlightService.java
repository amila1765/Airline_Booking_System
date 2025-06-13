package services;

/**
 *
 * @author Amila_Vishwajith
 */
import database.DBConnection;
import models.Flight;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlightService 
{
        public static List<Flight> getFlightsByRoute(int originId, int destinationId)
        {
        List<Flight> flights = new ArrayList<>();

        String sql = "SELECT * FROM flights WHERE departure_airport_id = ? AND arrival_airport_id = ?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) 
        {

            stmt.setInt(1, originId);
            stmt.setInt(2, destinationId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) 
            {
                System.out.println("✅ Found flight: " + rs.getInt("flight_id"));
                Flight flight = new Flight(
                        rs.getInt("flight_id"),
                        rs.getInt("airplane_id"),
                        rs.getInt("departure_airport_id"),
                        rs.getInt("arrival_airport_id"),
                        rs.getTimestamp("departure_time").toLocalDateTime(),
                        rs.getTimestamp("arrival_time").toLocalDateTime()
                );
                flights.add(flight);
            }

        } catch (SQLException e) 
        {
            System.err.println("❌ Error in getFlightsByRoute: " + e.getMessage());
        }

        return flights;
    }
}

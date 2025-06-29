package services;

/**
 *
 * @author Amila_Vishwajith
 */
import database.DBConnection;
import models.Flight;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FlightService 
{
    //Direct flights by route
    public static List<Flight> getFlightsByRoute(int originId, int destinationId)
    {
        List<Flight> flights = new ArrayList<>();

        String sql = "SELECT * FROM flights WHERE departure_airport_id = ? AND arrival_airport_id = ?";
        
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) 
        {
            stmt.setInt(1, originId);
            stmt.setInt(2, destinationId);
            try (ResultSet rs = stmt.executeQuery()) 
            {
                while (rs.next()) 
            {
                System.out.println("✅ Found direct flight: " + rs.getInt("flight_id"));
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
            }

        } 
        catch (SQLException e) 
        {
            System.err.println("❌ Error in getFlightsByRoute: " + e.getMessage());
        }

        return flights;
    }
        
    //Transit flights
    public static List<List<Flight>> getTransitFlights(int originId, int destinationId)
    {
        List<List<Flight>> transitOptions = new ArrayList<>();

        String sqlroute1 = "SELECT * FROM flights WHERE departure_airport_id = ?";
        String sqlroute2 = "SELECT * FROM flights WHERE arrival_airport_id = ?";

        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt1 = conn.prepareStatement(sqlroute1))
        {
            stmt1.setInt(1, originId);
            try(ResultSet rs1 = stmt1.executeQuery())
            {
                while (rs1.next()) 
            {
               //First part on the route
                Flight route1 = new Flight
                    (
                    rs1.getInt("flight_id"),
                    rs1.getInt("airplane_id"),
                    rs1.getInt("departure_airport_id"),
                    rs1.getInt("arrival_airport_id"),
                    rs1.getTimestamp("departure_time").toLocalDateTime(),
                    rs1.getTimestamp("arrival_time").toLocalDateTime()
                    );
            
        //For each route1, try to find a matching route2 flight
        try(PreparedStatement stmt2 = conn.prepareStatement(sqlroute2))
            {       
                stmt2.setInt(1, destinationId);
                try(ResultSet rs2 = stmt2.executeQuery())
                {
                    while (rs2.next()) 
                {
                int transitStart = rs2.getInt("origin_airport_id");
                LocalDateTime route2Dep = rs2.getTimestamp("departure_time").toLocalDateTime();

                 // Check if transit is valid (time buffer + location match + different aircraft)
                if (    
                        transitStart == route1.getArrivalAirportId()
                        && route1.getArrivalTime().plusHours(1).isBefore(route2Dep)
                        && route1.getAirplaneId() != rs2.getInt("airplane_id")
                    ){

                    Flight route2 = new Flight
                    (
                            rs2.getInt("flight_id"),
                            rs2.getInt("airplane_id"),
                            rs2.getInt("origin_airport_id"),
                            rs2.getInt("destination_airport_id"),
                            route2Dep,
                            rs2.getTimestamp("arrival_time").toLocalDateTime()
                    );

                    List<Flight> option = new ArrayList<>();
                    option.add(route1);
                    option.add(route2);
                    transitOptions.add(option);
                    
                    System.out.println("🛫 Found transit route: " + route1.getFlightId() + " ➡ " + route2.getFlightId());
                     }
                }
                }
            }
            }
            }

        } 
            catch (SQLException e) 
        {
            System.err.println("❌ Error in getTransitFlights: " + e.getMessage());
        }

        return transitOptions;
    }
       
}
    
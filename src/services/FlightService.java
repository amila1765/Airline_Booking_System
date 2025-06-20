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
        

        public static List<List<Flight>> getTransitFlights(int originId, int destinationId)
        {
        List<List<Flight>> transitOptions = new ArrayList<>();

        String sqlroute1 = "SELECT * FROM flights WHERE origin_airport_id = ?";
        String sqlroute2 = "SELECT * FROM flights WHERE destination_airport_id = ?";

        try (Connection conn = DBConnection.getInstance().getConnection())
        {
            //Get all flights from origin
            PreparedStatement stmt1 = conn.prepareStatement(sqlroute1);
            stmt1.setInt(1, originId);
            ResultSet rs1 = stmt1.executeQuery();

            while (rs1.next()) 
            {
                Flight route1 = new Flight
                    (
                    rs1.getInt("flight_id"),
                    rs1.getInt("airplane_id"),
                    rs1.getInt("origin_airport_id"),
                    rs1.getInt("destination_airport_id"),
                    rs1.getTimestamp("departure_time").toLocalDateTime(),
                    rs1.getTimestamp("arrival_time").toLocalDateTime()
                    );

            //For each route1, try to find a matching route2 flight
            PreparedStatement stmt2 = conn.prepareStatement(sqlroute2);
            stmt2.setInt(1, destinationId);
            ResultSet rs2 = stmt2.executeQuery();

            while (rs2.next()) 
            {
                int transitStart = rs2.getInt("origin_airport_id");
                LocalDateTime route2Dep = rs2.getTimestamp("departure_time").toLocalDateTime();

                if (transitStart == route1.getArrivalAirportId()
                        && route1.getArrivalTime().plusHours(1).isBefore(route2Dep)) 
                {

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
                }
            }
            rs2.close();
            stmt2.close();
            }

            rs1.close();
            stmt1.close();

        }   
        catch (SQLException e)
        {
        e.printStackTrace();
        }

        return transitOptions;
    }

}
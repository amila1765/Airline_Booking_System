package services;

/**
 *
 * @author Amila_Vishwajith
 */

import database.DBConnection;
import models.Airport;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AirportService 
{
     public static List<Airport> getAllAirports() 
     {
        List<Airport> airports = new ArrayList<>();
        String sql = "SELECT * FROM airports";

        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) 
        {
            while (rs.next())
            {
                Airport airport = new Airport(
                        rs.getInt("airport_id"),
                        rs.getString("name"),
                        rs.getString("city"),
                        rs.getString("country")
                );
                airports.add(airport);
            }
        } 
        catch (SQLException e) 
        {
            System.err.println("❌ Error fetching airports: " + e.getMessage());
        }

        return airports;
    }

    public static Airport getAirportById(int id) 
    {
        String sql = "SELECT * FROM airports WHERE airport_id = ?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) 
        {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Airport(
                        rs.getInt("airport_id"),
                        rs.getString("name"),
                        rs.getString("city"),
                        rs.getString("country")
                );
            }
        } 
        catch (SQLException e) 
        {
            System.err.println("❌ Error fetching airport by ID: " + e.getMessage());
        }
        return null;
    }
}

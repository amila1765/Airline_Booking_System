
package models;

/**
 *
 * @author Amila_Vishwajith
 */
public class Airport 
{
    private int airportId;
    private String name;
    private String city;
    private String country;

    public Airport(int airportId, String name, String city, String country) 
    {
        this.airportId = airportId;
        this.name = name;
        this.city = city;
        this.country = country;
    }

    public int getAirportId() { return airportId; }
    public String getName() { return name; }
    public String getCity() { return city; }
    public String getCountry() { return country; }

    @Override
    public String toString() 
    {
        return name + " (" + city + ", " + country + ")";
    }
}

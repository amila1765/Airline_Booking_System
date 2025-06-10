
package models;

/**
 *
 * @author Amila_Vishwajith
 */
public class Airplane 
{
    private int airplaneId;
    private String model;
    private String capacityClass;

    public Airplane(int airplaneId, String model, String capacityClass) {
        this.airplaneId = airplaneId;
        this.model = model;
        this.capacityClass = capacityClass;
    }

    public int getAirplaneId() { return airplaneId; }
    public String getModel() { return model; }
    public String getCapacityClass() { return capacityClass; }

    @Override
    public String toString() 
    {
        return model + " [" + capacityClass + "]";
    }
}

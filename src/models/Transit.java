
package models;

/**
 *
 * @author Amila_Vishwajith
 */
public class Transit 
{
    private int transitId;
    private int originFlightId;
    private int connectingFlightId;

    public Transit(int transitId, int originFlightId, int connectingFlightId) 
    {
        this.transitId = transitId;
        this.originFlightId = originFlightId;
        this.connectingFlightId = connectingFlightId;
    }

    public int getTransitId() { return transitId; }
    public int getOriginFlightId() { return originFlightId; }
    public int getConnectingFlightId() { return connectingFlightId; }

    @Override
    public String toString() 
    {
        return "Transit " + transitId + " | From flight " + originFlightId + " to " + connectingFlightId;
    }
}

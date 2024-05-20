public class ServiceLocation {
    private int locationId;
    private double x;
    private double y;
    private String square;
    private int population;
    private int totalDeliveries;
    private int totalPickups;
    private int closestFacilityId;
    private int closestNodeId;


    // Full constructor
    public ServiceLocation(int locationId, double x, double y, String square, int population, int totalDeliveries, int totalPickups, int closestFacilityId, int closestNodeId) {
        this.locationId = locationId;
        this.x = x;
        this.y = y;
        this.square = square;
        this.population = population;
        this.totalDeliveries = totalDeliveries;
        this.totalPickups = totalPickups;
        this.closestFacilityId = closestFacilityId;
        this.closestNodeId = closestNodeId;
    }

    // Getters and setters
    public int getLocationId() { return locationId; }
    public double getX() { return x; }
    public double getY() { return y; }
    public String getSquare() { return square; }
    public int getPopulation() { return population; }
    public int getTotalDeliveries() { return totalDeliveries; }
    public int getTotalPickups() { return totalPickups; }
    public int getClosestFacilityId() { return closestFacilityId; }
    public void setClosestFacilityId(int facilityId) { this.closestFacilityId = facilityId; }
    public int getClosestNodeId() { return closestNodeId; }
}

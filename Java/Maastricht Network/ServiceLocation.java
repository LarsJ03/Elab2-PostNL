public class ServiceLocation {
    private String locationId;
    private double x;
    private double y;
    private String square;
    private int population;
    private int totalDeliveries;
    private int totalPickups;

    public ServiceLocation(String locationId, double x, double y, String square, int population, int totalDeliveries, int totalPickups) {
        this.locationId = locationId;
        this.x = x;
        this.y = y;
        this.square = square;
        this.population = population;
        this.totalDeliveries = totalDeliveries;
        this.totalPickups = totalPickups;
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public String getSquare() { return square; }
    public int getPopulation() { return population; }
    public int getTotalDeliveries() { return totalDeliveries; }
    public int getTotalPickups() { return totalPickups; }
}
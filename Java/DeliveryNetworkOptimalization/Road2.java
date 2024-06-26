public class Road2 {
    private int V1;
    private int V2;
    private double dist;
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private String type;  // Type of the road
    private int maxSpeed; // Maximum speed allowed on the road
    private double timeToDrive;  // Time to drive the road at max speed
    private int population;
    private double orderOdds;

    public Road2(int V1, int V2, double dist, double x1, double y1, double x2, double y2, 
                String type, int maxSpeed, int population, double orderOdds) {
        this.V1 = V1;
        this.V2 = V2;
        this.dist = dist;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.type = type;
        this.maxSpeed = maxSpeed;
        this.population = population;
        this.orderOdds = orderOdds;
        this.timeToDrive = calculateTimeToDrive();
    }

    private double calculateTimeToDrive() {
        if (maxSpeed == 0) return Double.POSITIVE_INFINITY; // Avoid division by zero
        return dist / (maxSpeed / 3.6); // dist divided by speed in m/s
    }

    // Getters
    public int getV1() { return V1; }
    public int getV2() { return V2; }
    public double getDist() { return dist; }
    public double getX1() { return x1; }
    public double getY1() { return y1; }
    public double getX2() { return x2; }
    public double getY2() { return y2; }
    public String getType() { return type; }
    public int getMaxSpeed() { return maxSpeed; }
    public double getTimeToDrive() { return timeToDrive; }
    public int getPopulation() { return population; }
    public double getOrderOdds() { return orderOdds; }
}

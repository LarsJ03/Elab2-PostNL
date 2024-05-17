public class Road {
    private double dist; 
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private String type;  // Type of the road
    private int maxSpeed; // Maximum speed allowed on the road
    private double timeToDrive;  // Time to drive the road at max speed

    // Updated constructor to include type and maxSpeed
    public Road(double dist, double x1, double y1, double x2, double y2, String type, int maxSpeed) {
        this.dist = dist;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.type = type;
        this.maxSpeed = maxSpeed;
        this.timeToDrive = timeToDrive();
        
    }

    // Getters
    public double getX1() { return x1; }
    public double getY1() { return y1; }
    public double getX2() { return x2; }
    public double getY2() { return y2; }
    public String getType() { return type; }
    public int getMaxSpeed() { return maxSpeed; }

    public double timeToDrive() {
        return dist / (maxSpeed/3.6);
    }
}

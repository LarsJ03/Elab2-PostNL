public class Intersection {
    private String nodeId;
    private double x;
    private double y;
    private String square;

    public Intersection(String nodeId, double x, double y, String square) {
        this.nodeId = nodeId;
        this.x = x;
        this.y = y;
        this.square = square;
    }

    // Getters and setters for all fields

    @Override
    public String toString() {
        return String.format("Intersection[nodeId=%s, x=%.2f, y=%.2f, square=%s]", nodeId, x, y, square);
    }
}


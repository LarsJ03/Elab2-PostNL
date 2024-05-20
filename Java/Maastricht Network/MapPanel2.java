import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MapPanel2 extends JPanel {
    private List<Road> roads;
    private List<ServiceLocation> serviceLocations;
    private List<Intersection> intersections;
    private Map<Integer, Color> facilityColors;

    public MapPanel2(List<Road> roads, List<ServiceLocation> serviceLocations, List<Intersection> intersections) {
        this.roads = roads;
        this.serviceLocations = serviceLocations;
        this.intersections = intersections;
        setPreferredSize(new Dimension(800, 600)); // Match the original panel size
        setBackground(Color.WHITE);
        this.facilityColors = generateFacilityColors();
    }

    @Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.setStroke(new BasicStroke(1.5f));
    double scaleFactor = calculateScaleFactor(intersections); // Consider using a unified scale factor calculation
    int panelHeight = getHeight();

    // Draw intersections based on their facility assignments
    for (Intersection intersection : intersections) {
        int facilityId = intersection.getAssignedFacility();
        Color color = facilityColors.getOrDefault(facilityId, Color.BLACK);
        g2d.setColor(color);
        int x = (int) (intersection.getX() * scaleFactor);
        int y = panelHeight - (int) (intersection.getY() * scaleFactor);
        g2d.fillOval(x - 2, y - 2, 5, 5);
    }

    // Draw roads in grey
    g2d.setColor(Color.GRAY);
    for (Road road : roads) {
        int x1 = (int) (road.getX1() * scaleFactor);
        int y1 = panelHeight - (int) (road.getY1() * scaleFactor);
        int x2 = (int) (road.getX2() * scaleFactor);
        int y2 = panelHeight - (int) (road.getY2() * scaleFactor);
        g2d.drawLine(x1, y1, x2, y2);
    }

    // Draw service locations as black dots, smaller size
    g2d.setColor(Color.BLACK);
    for (ServiceLocation location : serviceLocations) {
        int x = (int) (location.getX() * scaleFactor);
        int y = panelHeight - (int) (location.getY() * scaleFactor);
        g2d.fillOval(x - 5, y - 5, 10, 10); 
    }
}


    private Map<Integer, Color> generateFacilityColors() {
        Map<Integer, Color> colors = new HashMap<>();
        Random rand = new Random();
        // Assign a random color for each facility ID
        intersections.forEach(intersection -> colors.putIfAbsent(intersection.getAssignedFacility(),
                new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat())));
        return colors;
    }

    private double calculateScaleFactor(List<Intersection> intersections) {
        double maxX = Double.MIN_VALUE;
        double maxY = Double.MIN_VALUE;
        double minX = Double.MAX_VALUE;
        double minY = Double.MAX_VALUE;
    
        // Include both roads and intersections in the scale calculation
        for (Road road : roads) {
            maxX = Math.max(maxX, Math.max(road.getX1(), road.getX2()));
            minX = Math.min(minX, Math.min(road.getX1(), road.getX2()));
            maxY = Math.max(maxY, Math.max(road.getY1(), road.getY2()));
            minY = Math.min(minY, Math.min(road.getY1(), road.getY2()));
        }
        for (Intersection intersection : intersections) {
            maxX = Math.max(maxX, intersection.getX());
            minX = Math.min(minX, intersection.getX());
            maxY = Math.max(maxY, intersection.getY());
            minY = Math.min(minY, intersection.getY());
        }
    
        double rangeX = maxX - minX;
        double rangeY = maxY - minY;
        double scaleX = 780 / rangeX;
        double scaleY = 580 / rangeY;
    
        return Math.min(scaleX, scaleY);
    }

}
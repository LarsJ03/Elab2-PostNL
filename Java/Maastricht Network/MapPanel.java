import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MapPanel extends JPanel {
    private List<Road> roads;

    public MapPanel(List<Road> roads) {
        this.roads = roads;
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(2));

        // Debug: Draw a test line to ensure drawing works
        g2d.drawLine(10, 10, 100, 100);

        if (roads.isEmpty()) {
            System.out.println("No roads to display.");
            return;
        }

        // Optional: Calculate scale factor based on expected min/max values
        double scaleFactor = calculateScaleFactor(roads);

        for (Road road : roads) {
            int x1 = (int) (road.getX1() * scaleFactor);
            int y1 = (int) (road.getY1() * scaleFactor);
            int x2 = (int) (road.getX2() * scaleFactor);
            int y2 = (int) (road.getY2() * scaleFactor);
            g2d.drawLine(x1, y1, x2, y2);
        }
    }

    private double calculateScaleFactor(List<Road> roads) {
        double maxX = Double.MIN_VALUE;
        double maxY = Double.MIN_VALUE;
        double minX = Double.MAX_VALUE;
        double minY = Double.MAX_VALUE;

        for (Road road : roads) {
            if (road.getX1() > maxX) maxX = road.getX1();
            if (road.getX2() > maxX) maxX = road.getX2();
            if (road.getY1() > maxY) maxY = road.getY1();
            if (road.getY2() > maxY) maxY = road.getY2();
            if (road.getX1() < minX) minX = road.getX1();
            if (road.getX2() < minX) minX = road.getX2();
            if (road.getY1() < minY) minY = road.getY1();
            if (road.getY2() < minY) minY = road.getY2();
        }

        double rangeX = maxX - minX;
        double rangeY = maxY - minY;
        double scaleX = 780 / rangeX; // Adjusted to fit within panel width, leaving some margin
        double scaleY = 580 / rangeY; // Adjusted to fit within panel height, leaving some margin

        return Math.min(scaleX, scaleY); // Use the smaller scale factor to ensure everything fits
    }
}

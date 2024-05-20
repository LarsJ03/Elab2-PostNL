    import javax.swing.*;
    import java.awt.*;
    import java.util.List;
    import java.util.HashMap;
    import java.util.Map;

    public class MapPanel extends JPanel {
        private List<Road> roads;
        private List<ServiceLocation> serviceLocations;
        private Map<String, Color> typeColorMap;

        public MapPanel(List<Road> roads, List<ServiceLocation> serviceLocations) {
            this.roads = roads;
            this.serviceLocations = serviceLocations;
            setPreferredSize(new Dimension(800, 600));
            setBackground(Color.WHITE);
            initializeColorMap();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setStroke(new BasicStroke(1.5f));
            double scaleFactor = calculateScaleFactor(roads);
            int panelHeight = getHeight();

            // Draw roads
            for (Road road : roads) {
                Color color = typeColorMap.getOrDefault(road.getType(), Color.BLACK);
                g2d.setColor(color);
                int x1 = (int) (road.getX1() * scaleFactor);
                int y1 = panelHeight - (int) (road.getY1() * scaleFactor);
                int x2 = (int) (road.getX2() * scaleFactor);
                int y2 = panelHeight - (int) (road.getY2() * scaleFactor);
                g2d.drawLine(x1, y1, x2, y2);
            }

            // Draw service locations
            g2d.setColor(Color.CYAN); // Use a distinct color for service points
            for (ServiceLocation location : serviceLocations) {
                int x = (int) (location.getX() * scaleFactor);
                int y = panelHeight - (int) (location.getY() * scaleFactor);
                g2d.fillOval(x - 5, y - 5, 10, 10); // Draw a small circle for each location
            }
        }

        private void initializeColorMap() {
            typeColorMap = new HashMap<>();
            typeColorMap.put("motorway", Color.RED);
            typeColorMap.put("motorway_link", Color.RED);
            typeColorMap.put("primary", Color.PINK);
            typeColorMap.put("primary_link", Color.PINK);
            typeColorMap.put("living_street", Color.ORANGE);

            typeColorMap.put("secondary", Color.BLUE);
            typeColorMap.put("service", Color.GREEN);
            typeColorMap.put("residential", Color.ORANGE);
            typeColorMap.put("tertiary", Color.YELLOW);
            typeColorMap.put("trunk", Color.MAGENTA);
            typeColorMap.put("trunk_link", Color.MAGENTA);
            typeColorMap.put("unclassified", Color.BLACK);
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

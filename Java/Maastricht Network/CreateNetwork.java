import java.util.*;
import javax.swing.*;

public class CreateNetwork {
    public static void main(String[] args) {
        // Reading data from files
        List<Intersection> intersections = ReadData.readIntersectionsFromFile("Data/Raw/nodes.csv");
        List<Road> roads = ReadData.readRoadsFromFile("Data/Raw/edges.csv");
        List<ServiceLocation> serviceLocations = ReadData.readServiceLocationsFromFile("Data/Raw/Service Point Locations.csv");

        // Creating the road network for shortest path calculations
        RoadNetwork network = new RoadNetwork(intersections, roads);

        // Example of calculating and printing the shortest path between two nodes
        // (Adjust node IDs as needed based on your data)
        if (!intersections.isEmpty()) {
            int startNodeId = intersections.get(0).getNodeId(); // Assuming first intersection in list
            int endNodeId = intersections.get(intersections.size() - 1).getNodeId(); // Assuming last intersection in list
            String shortestPathResult = network.getShortestPath(startNodeId, endNodeId);
            System.out.println(shortestPathResult);
        }

        // Visualizing the road network
        JFrame frame = new JFrame("Road Map Visualization");
        MapPanel panel = new MapPanel(roads, serviceLocations); // Update constructor to handle service locations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

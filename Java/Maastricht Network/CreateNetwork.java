import java.util.*;
import javax.swing.*;

public class CreateNetwork {
    public static void main(String[] args) {
        List<Intersection> intersections = ReadData.readIntersectionsFromFile("Data/Raw/nodes.csv");
        List<Road> roads = ReadData.readRoadsFromFile("Data/Raw/edges.csv");
        List<ServiceLocation> serviceLocations = ReadData.readServiceLocationsFromFile("Data/Raw/Service Point Locations.csv");

        JFrame frame = new JFrame("Road Map Visualization");
        MapPanel panel = new MapPanel(roads, serviceLocations); // Update constructor to handle service locations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

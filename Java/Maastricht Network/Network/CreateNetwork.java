package Network;
import java.util.*;
import javax.swing.*;
import java.io.File;

public class CreateNetwork {
    public static void main(String[] args) {
        // Reading data from files
        List<Intersection> intersections = ReadData.readIntersectionsFromFile("Data/Raw/nodes.csv");
        List<Road> roads = ReadData.readRoadsFromFile("Data/Raw/edges.csv");
        List<ServiceLocation> serviceLocations = ReadData.readServiceLocationsFromFile("Data/OwnDataset/ServicePointLocationsUpdated.csv");

        // Check if the distances file exists
        if (!new File("Data/OwnDataset/Distances.csv").exists()) {
            RoadNetwork network = new RoadNetwork(intersections, roads);
            // Optionally write distances to a file inside RoadNetwork if not already done
        }

        // Visualizing the road network
        JFrame frame = new JFrame("Road Map Visualization");
        MapPanel panel = new MapPanel(roads, serviceLocations); // Ensure MapPanel can handle updated intersections
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Initialize facility allocation and update intersections
        FacilityAllocation facilityAllocation = new FacilityAllocation(intersections, roads);
        
        JFrame frame2 = new JFrame("Road Map Visualization");
        // Correct the order of parameters according to the constructor's definition
        MapPanel2 panel2 = new MapPanel2(roads, serviceLocations, intersections);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.add(panel2);
        frame2.pack();
        frame2.setLocationRelativeTo(null);
        frame2.setVisible(true);
        
    }
}
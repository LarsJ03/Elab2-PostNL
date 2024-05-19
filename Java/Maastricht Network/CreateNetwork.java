import java.util.*;
import javax.swing.*;
import java.io.File;

public class CreateNetwork {
    public static void main(String[] args) {
        // Reading data from files
        List<Intersection> intersections = ReadData.readIntersectionsFromFile("Data/Raw/nodes.csv");
        List<Road> roads = ReadData.readRoadsFromFile("Data/Raw/edges.csv");
        List<ServiceLocation> serviceLocations = ReadData.readServiceLocationsFromFile("Data/OwnDataset/ServicePointLocationsUpdated.csv");

        if (!new File("Data/Owndataset/Distances.csv").exists()) {
            RoadNetwork network = new RoadNetwork(intersections, roads);
        
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

import java.util.*;
import javax.swing.*;


public class CreateNetwork {
    public static void main(String[] args) {
        List<Intersection> intersections = ReadData.readIntersectionsFromFile("Data/Raw/nodes.csv");
        List<Road> roads = ReadData.readRoadsFromFile("Data/Raw/edges.csv");

        JFrame frame = new JFrame("Road Map Visualization");
        MapPanel panel = new MapPanel(roads);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
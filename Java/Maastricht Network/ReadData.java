import java.io.*;
import java.util.*;

public class ReadData {
    public static List<Intersection> readIntersectionsFromFile(String filename) {
        List<Intersection> intersections = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean firstLine = true;

            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                String[] data = line.split(";");
                if (data.length == 4) {
                    Intersection intersection = new Intersection(data[0], Double.parseDouble(data[1]), Double.parseDouble(data[2]), data[3]);
                    intersections.add(intersection);  // Adding to the list as well
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Loaded Intersections: " + intersections.size());  // Optional: Print the number of loaded intersections
        return intersections;  // Continuing to return the map for use with roads
    }


    public static List<Road> readRoadsFromFile(String filename) {
        List<Road> roads = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                if (data.length == 15) {
                    double x1 = Double.parseDouble(data[8]);
                    double y1 = Double.parseDouble(data[9]);
                    double x2 = Double.parseDouble(data[10]);
                    double y2 = Double.parseDouble(data[11]);
                    Road road = new Road(x1, y1, x2, y2);
                    roads.add(road);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return roads;
    }
}

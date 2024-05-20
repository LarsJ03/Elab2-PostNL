import java.io.*;
import java.util.*;

public class ReadData {
    public static List<Road> readRoadsFromFile(String filename) {
        ArrayList<Road> roads = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean firstLine = true; // to skip header

            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // Skip the header row
                    continue;
                }
                String[] data = line.split(";");
                if (data.length == 15) {
                    int V1 = Integer.parseInt(data[1]);
                    int V2 = Integer.parseInt(data[2]);
                    double dist = Double.parseDouble(data[3].replace(",", "."));
                    double x1 = Double.parseDouble(data[8].replace(",", "."));
                    double y1 = Double.parseDouble(data[9].replace(",", "."));
                    double x2 = Double.parseDouble(data[10].replace(",", "."));
                    double y2 = Double.parseDouble(data[11].replace(",", "."));
                    String type = data[5];
                    int maxSpeed = Integer.parseInt(data[7]);
                    int population = Integer.parseInt(data[12]);
                    double orderOdds = Double.parseDouble(data[13].replace(",", "."));  


                    Road2 road = new Road2(V1, V2, dist, x1, y1, x2, y2, type, maxSpeed, population, orderOdds);
                    
                    roads.add(road);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return roads;
    }

    public static List<Intersection> readIntersectionsFromFile(String filename) {
        List<Intersection> intersections = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = reader.readLine()) != null) {
                line = removeUtf8Bom(line); // Handle potential BOM on every line
                String[] data = line.split(";");
                if (data.length == 4) {
                    Intersection intersection = new Intersection(
                        Integer.parseInt(data[0]),
                        Double.parseDouble(data[1]),
                        Double.parseDouble(data[2]),
                        data[3]
                    );
                    intersections.add(intersection);  // Adding to the list as well
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Loaded Intersections: " + intersections.size());  // Optional: Print the number of loaded intersections
        return intersections;  // Returning the list for use
    }

    private static String removeUtf8Bom(String s) {
        if (s.startsWith("\uFEFF")) {
            return s.substring(1);
        }
        return s;
    }

    public static List<ServiceLocation> readServiceLocationsFromFile(String filename) {
        List<ServiceLocation> locations = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
    
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                if (data.length == 7) {
                    ServiceLocation location = new ServiceLocation(
                        Integer.parseInt(data[0]), // Location ID, converted from String to int
                        Double.parseDouble(data[1]), // X
                        Double.parseDouble(data[2]), // Y
                        data[3], // Square
                        Integer.parseInt(data[4]), // Population
                        Integer.parseInt(data[5]), // Total Deliveries
                        Integer.parseInt(data[6]), // Total Pickups
                        -1, // Closest Facility ID, hardcoded as -1 if not available
                        Integer.parseInt(data[7])); // Closest Node ID, hardcoded as -1 if not available (adjust if data[7] is intended to be used)
                    locations.add(location);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return locations;
    }
    
}

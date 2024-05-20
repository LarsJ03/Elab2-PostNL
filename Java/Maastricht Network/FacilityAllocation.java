import java.io.*;
import java.util.*;

public class FacilityAllocation {

    private double[][] distances;
    private Map<Integer, Integer> nodeToFacility = new HashMap<>();
    private Set<Integer> facilityNodes = new HashSet<>();
    private List<Intersection> intersections = new ArrayList<>();

    public FacilityAllocation(List<Intersection> intersections) {
        loadDistances("Data/OwnDataset/Distances.csv");
        loadFacilityLocations("Data/OwnDataset/ServicePointLocationsUpdated.csv");
        this.intersections = intersections;
        allocateNodes();
        assignFacilitiesToIntersections();
        printAllocations();
    }

    private void loadDistances(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int i = 0;
            distances = new double[8486][8486]; // Assuming this size is correct
            while ((line = reader.readLine()) != null && i < distances.length) {
                String[] values = line.split(";");
                for (int j = 0; j < distances[i].length && j < values.length; j++) {
                    String value = values[j].replace(",", "."); // Replace commas with dots
                    distances[i][j] = value.equals("INF") ? Double.MAX_VALUE : Double.parseDouble(value);
                }
                i++;

                System.out.println("Loaded " + i + " rows of distances");
            }
        } catch (IOException e) {
            System.err.println("Error reading the distance file: " + e.getMessage());
        }
    }

    private void loadFacilityLocations(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                int facilityNodeId = Integer.parseInt(data[7]); // Assuming the facility node ID is in the 8th column
                facilityNodes.add(facilityNodeId);
            }
        } catch (IOException e) {
            System.err.println("Error reading the facility locations file: " + e.getMessage());
        }
    }

    private void loadIntersections(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                int nodeId = Integer.parseInt(data[0]);
                double x = Double.parseDouble(data[1].replace(",", "."));
                double y = Double.parseDouble(data[2].replace(",", "."));
                String square = data[3];
                intersections.add(new Intersection(nodeId, x, y, square));
            }
        } catch (IOException e) {
            System.err.println("Error reading the nodes file: " + e.getMessage());
        }
    }

    private void allocateNodes() {
        for (Intersection intersection : intersections) {
            int nodeId = intersection.getNodeId();
            double minDistance = Double.MAX_VALUE;
            int closestFacility = -1;
            for (int facilityNodeId : facilityNodes) {
                double distance = distances[nodeId][facilityNodeId]; // Direct access assumes correct indexing
                if (distance < minDistance) {
                    minDistance = distance;
                    closestFacility = facilityNodeId;
                }
            }
            nodeToFacility.put(nodeId, closestFacility);
        }
    }

    private void assignFacilitiesToIntersections() {
        for (Intersection intersection : intersections) {
            int nodeId = intersection.getNodeId();
            if (nodeToFacility.containsKey(nodeId)) {
                intersection.setAssignedFacility(nodeToFacility.get(nodeId));
            }
        }
    }

    public void printAllocations() {
        intersections.forEach(intersection -> System.out.println(intersection));
    }
}


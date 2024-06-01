package Network;
import java.io.*;
import java.util.*;

public class FacilityAllocation {

    private double[][] distances;
    private Map<Integer, Integer> nodeToFacility = new HashMap<>();
    private Set<Integer> facilityNodes = new HashSet<>();
    private List<Intersection> intersections = new ArrayList<>();
    private List<Road> roads = new ArrayList<>();

    public FacilityAllocation(List<Intersection> intersections, List<Road> roads) {
        loadDistances("Data/Raw/Distances.csv");
        loadFacilityLocations("Data/Raw/Service Point Locations.csv");
        this.intersections = intersections;
        this.roads = roads;
        allocateNodes();
        assignFacilitiesToIntersections();
    }

    private void loadDistances(String filePath) {
        List<double[]> tempDistances = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] values = line.split(";");
                double[] distanceRow = new double[values.length];
                
                for (int j = 0; j < values.length; j++) {
                    String value = values[j].replace(",", ".");
                    distanceRow[j] = value.equals("INF") ? Double.MAX_VALUE : Double.parseDouble(value);
                }
                
                tempDistances.add(distanceRow);
            }

            // Convert List to 2D array
            int numRows = tempDistances.size();
            int numCols = tempDistances.isEmpty() ? 0 : tempDistances.get(0).length;
            distances = new double[numRows][numCols];
            
            for (int i = 0; i < numRows; i++) {
                distances[i] = tempDistances.get(i);
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
                String facilityNodeIdStr = data[0].trim(); // Trim the input string
                if (facilityNodeIdStr.startsWith("\uFEFF")) {
                    facilityNodeIdStr = facilityNodeIdStr.substring(1); // Remove BOM if present
                }
                int facilityNodeId = Integer.parseInt(facilityNodeIdStr);
                facilityNodes.add(facilityNodeId);
            }
        } catch (IOException e) {
            System.err.println("Error reading the facility locations file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing facility node ID: " + e.getMessage());
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

    public void assignFacilitiesToIntersections() {
        for (Intersection intersection : intersections) {
            int nodeId = intersection.getNodeId();
            if (nodeToFacility.containsKey(nodeId)) {
                intersection.setAssignedFacility(nodeToFacility.get(nodeId));
            }
        }
        
        for(Road road : roads) {
            int facility1 = 0;
            int facility2 = 0;
            int counter = 0;
        	for (Intersection intersection : intersections) {
        		if(road.getV1() == intersection.getAssignedFacility()) {
        			facility1 = intersection.getAssignedFacility();
        			counter++;
        			if(counter == 2) {
        				break;
        			}
        		}
        		else if(road.getV2() == intersection.getAssignedFacility()) {
        			facility2 = intersection.getAssignedFacility();
        			counter++;
        			if(counter == 2) {
        				break;
        			}
        		}
        	}
        	
        	if(facility1 == facility2) {
        		road.setAssignedFacility(facility1);
        	}
        	else {
        		double rand = Math.random();
        		if(rand > 0.5) {
        			road.setAssignedFacility(facility1);
        		}
        		else {
        			road.setAssignedFacility(facility2);
        		}
        	}
        }
    }

    
}


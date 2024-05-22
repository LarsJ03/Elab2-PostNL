import java.io.*;
import java.util.*;

public class ReadData {
    public static List<Road> readRoadsFromFile(String filename) {
        List<Road> roads = new ArrayList<>();
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
                    String Square1 = data[12];
                    String Square2 = data[13];
                    String SquareMid = data[14];
                    
                    String type = data[5];
                    int maxSpeed = Integer.parseInt(data[7]);

                    Road road = new Road(V1, V2, dist, x1, y1, x2, y2, type, maxSpeed, Square1, Square2, SquareMid);
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
    
    public static List<Square> readSquaresFromFile(String filename) {
        List<Square> squares = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean firstLine = true; // to skip header

            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // Skip the header row
                    continue;
                }
                String[] data = line.split(";");
                if (data.length == 27) {
                	String coordinate = data[0];
                    int population = Integer.parseInt(data[1]);
                    int male = Integer.parseInt(data[2]);
                    int female = Integer.parseInt(data[3]);
                    int children = Integer.parseInt(data[4]);
                    int youngAdults = Integer.parseInt(data[5]);
                    int adults = Integer.parseInt(data[6]);
                    int old = Integer.parseInt(data[7]);
                    int veryOld = Integer.parseInt(data[8]);
                    int households = Integer.parseInt(data[9]);
                    int singleHouseholds = Integer.parseInt(data[10]);
                    int multiHouseholds = Integer.parseInt(data[11]);
                    int singleParentHouseholds = Integer.parseInt(data[12]);
                    int twoParentHouseholds = Integer.parseInt(data[13]);
                    int houses = Integer.parseInt(data[14]);
                    int homeOwnershipPercentage = Integer.parseInt(data[15]);
                    int rentalPercentage = Integer.parseInt(data[16]);
                    int socialHousingPercentage = Integer.parseInt(data[17]);
                    int vacantHouses = Integer.parseInt(data[18]);
                    int avgHomeValue = Integer.parseInt(data[19]);
                    int urbanizationIndex = Integer.parseInt(data[20]);
                    int medianHouseholdIncomeLowBound = Integer.parseInt(data[21]);
                    int medianHouseholdIncomeUpperBound = Integer.parseInt(data[22]);
                    
                    String[] parts = data[23].split("-");
                    int lowIncomePercentage = Integer.parseInt(parts[0].trim());
                    String[] secondParts = parts[1].split(" ");
                    int highIncomePercentage = Integer.parseInt(secondParts[0].trim());
                    
                    int distToSupermarket = Integer.parseInt(data[24]);
                    int x = Integer.parseInt(data[25]);
                    int y = Integer.parseInt(data[26]);

                    Square square = new Square(
                        coordinate, population, male, female, children, youngAdults, adults, old, veryOld, households, 
                        singleHouseholds, multiHouseholds, singleParentHouseholds, twoParentHouseholds, houses, homeOwnershipPercentage, 
                        rentalPercentage, socialHousingPercentage, vacantHouses, avgHomeValue, urbanizationIndex, 
                        medianHouseholdIncomeLowBound, medianHouseholdIncomeUpperBound, lowIncomePercentage, highIncomePercentage, 
                        distToSupermarket, x, y
                    );

                    squares.add(square);
                
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return squares;
    }
}

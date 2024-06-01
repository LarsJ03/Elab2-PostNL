package Network;
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
                // Remove BOM if present
                if (line.startsWith("\uFEFF")) {
                    line = line.substring(1);
                }
                
                String[] data = line.split(";");
                
                if (data.length == 7) {
                    // Trim each data element
                    for (int i = 0; i < data.length; i++) {
                        data[i] = data[i].trim();
                    }

                    ServiceLocation location = new ServiceLocation(
                        Integer.parseInt(data[0]), // Location ID
                        Double.parseDouble(data[1]), // X
                        Double.parseDouble(data[2]), // Y
                        data[3], // Square
                        Integer.parseInt(data[4]), // Population
                        Integer.parseInt(data[5]), // Total Deliveries
                        Integer.parseInt(data[6]), // Total Pickups
                        -1, // Closest Facility ID, hardcoded as -1 if not available
                        -1  // Closest Node ID, hardcoded as -1 if not available
                    );
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
                String[] data = line.split(",");
                data = processData(data);
                if (parseInteger(data[1]) != -1) {
                	String coordinate = data[0];
                    int population = parseInteger(data[1]);
                    int male = parseInteger(data[2]);
                    int female = parseInteger(data[3]);
                    int children = parseInteger(data[4]);
                    int youngAdults = parseInteger(data[5]);
                    int adults = parseInteger(data[6]);
                    int old = parseInteger(data[7]);
                    int veryOld = parseInteger(data[8]);
                    int households = parseInteger(data[9]);
                    int singleHouseholds = parseInteger(data[10]);
                    int multiHouseholds = parseInteger(data[11]);
                    int singleParentHouseholds = parseInteger(data[12]);
                    int twoParentHouseholds = parseInteger(data[13]);
                    int houses = parseInteger(data[14]);
                    int homeOwnershipPercentage = parseInteger(data[15]);
                    int rentalPercentage = parseInteger(data[16]);
                    int socialHousingPercentage = parseInteger(data[17]);
                    int vacantHouses = parseInteger(data[18]);
                    int avgHomeValue = parseInteger(data[19]);
                    int urbanizationIndex = parseInteger(data[20]);
                    int medianHouseholdIncomeLowBound = -1;
                    int medianHouseholdIncomeUpperBound = -1;
                    
                    if (data[21] != null) {
                    	String[] parts = data[21].split("-");
                    	medianHouseholdIncomeLowBound = parseInteger(parts[0].trim());
                    	String[] secondParts = parts[1].split(" ");
                    	medianHouseholdIncomeUpperBound = parseInteger(secondParts[0].trim());
                    }
                    
                    int lowIncomePercentage = parseInteger(data[22]);
                	int highIncomePercentage = parseInteger(data[23]);
                	


                    int distToSupermarket = -1;
                    if(data[21] != null) {
                    	Double.parseDouble(data[24]);
                    }
                    double x = Double.parseDouble(data[25]);
                    double y = Double.parseDouble(data[26]);

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
    
    public static String[] processData(String[] data) {
        String[] result = new String[data.length];
        for (int i = 0; i < data.length; i++) {
            if (data[i].isEmpty()) {
                result[i] = null;
            } else {
                result[i] = data[i];
            }
        }
        return result;
    }
    
    private static Integer parseInteger(String value) {
        if (value == null || value.isEmpty()) {
            return -1;
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            // Handle the case where the value is not a valid integer
            return null;
        }
    }
    
}

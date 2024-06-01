package Network;
import java.io.*;
import java.util.*;

public class RoadNetwork {
    private static final double INF = Double.MAX_VALUE;
    private double[][] dist;
    private Map<Integer, Integer> nodeIdToIndex; // This becomes unnecessary if we use node IDs directly as indices
    private Map<Integer, Intersection> indexToNode; // Also may be unnecessary

    public RoadNetwork(List<Intersection> intersections, List<Road> roads) {
        // Determine the maximum node ID for matrix dimension
        int maxNodeId = 0;
        for (Intersection intersection : intersections) {
            if (intersection.getNodeId() > maxNodeId) {
                maxNodeId = intersection.getNodeId();
            }
        }

        // Initialize the distance matrix
        dist = new double[maxNodeId + 1][maxNodeId + 1]; // Adjusting index to fit node IDs

        for (int i = 0; i <= maxNodeId; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0; // Distance to self is zero
        }

        // Assign distances based on roads using direct node IDs as indices
        for (Road road : roads) {
            dist[road.getV1()][road.getV2()] = road.getDist();
            dist[road.getV2()][road.getV1()] = road.getDist(); // For undirected roads
        }

        floydWarshall(maxNodeId);

        writeDistancesToFile("Data/Raw/Distances.csv");
    }

    private void floydWarshall(int maxNodeId) {
        for (int k = 0; k <= maxNodeId; k++) {
            if (k % 100 == 0) {
                System.out.println("Processing node " + (k + 1) + " of " + (maxNodeId + 1));
            }
            
            for (int i = 0; i <= maxNodeId; i++) {
                for (int j = 0; j <= maxNodeId; j++) {
                    if (dist[i][k] < INF && dist[k][j] < INF && dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
    }

    public void writeDistancesToFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (int i = 0; i < dist.length; i++) {
                for (int j = 0; j < dist[i].length; j++) {
                    writer.write(dist[i][j] < INF ? String.format("%.2f", dist[i][j]) : "INF");
                    if (j < dist[i].length - 1) {
                        writer.write(";");
                    }
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public String getShortestPath(int startNodeId, int endNodeId) {
        if (startNodeId >= dist.length || endNodeId >= dist.length || dist[startNodeId][endNodeId] == INF) {
            return "No path exists";
        }
        return "Shortest path from " + startNodeId + " to " + endNodeId + " is " + dist[startNodeId][endNodeId] + " units";
    }
}

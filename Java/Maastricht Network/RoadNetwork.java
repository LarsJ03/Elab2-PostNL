import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class RoadNetwork {
    private static final double INF = Double.MAX_VALUE;
    private double[][] dist;
    private Map<Integer, Integer> nodeIdToIndex;
    private Map<Integer, Intersection> indexToNode;

    public RoadNetwork(List<Intersection> intersections, List<Road> roads) {
        int n = intersections.size();
        dist = new double[n][n];
        nodeIdToIndex = new HashMap<>();
        indexToNode = new HashMap<>();

        for (int i = 0; i < n; i++) {
            Intersection intersection = intersections.get(i);
            nodeIdToIndex.put(intersection.getNodeId(), i);
            indexToNode.put(i, intersection);
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0; 
        }

        for (Road road : roads) {
            int fromIndex = nodeIdToIndex.get(road.getV1());
            int toIndex = nodeIdToIndex.get(road.getV2());
            dist[fromIndex][toIndex] = road.getDist();
            dist[toIndex][fromIndex] = road.getDist(); // Assuming undirected road for bidirectional travel
        }

        floydWarshall();
        writeDistancesToFile("Data/OwnDataset/Distances.csv");
    }

    private void floydWarshall() {
        int n = dist.length;
        for (int k = 0; k < n; k++) {
            System.out.println("Iteration " + (k + 1) + " of " + n + ": Processing node " + k);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] < INF && dist[k][j] < INF && dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
    }

    private void writeDistancesToFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (double[] row : dist) {
                for (int i = 0; i < row.length; i++) {
                    double value = row[i];
                    writer.write(value < INF ? String.format("%.2f", value) : "INF");
                    // Write a semicolon after each value except the last one
                    if (i < row.length - 1) {
                        writer.write(";");
                    }
                }
                writer.newLine();  // Move to the next line after each row is written
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public String getShortestPath(int startNodeId, int endNodeId) {
        int startIndex = nodeIdToIndex.get(startNodeId);
        int endIndex = nodeIdToIndex.get(endNodeId);
        if (dist[startIndex][endIndex] == INF) {
            return "No path exists";
        }
        return "Shortest path from " + startNodeId + " to " + endNodeId + " is " + dist[startIndex][endIndex] + " units";
    }
}


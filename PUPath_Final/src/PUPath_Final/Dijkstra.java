package PUPath_Final;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Dijkstra {

    public List<Integer> generateShortestPath(int[][] graph, int source, int destination) {
        HashMap<Integer, Integer> pathTable = new HashMap<>();

        int count = graph.length;
        boolean[] visitedVertex = new boolean[count];
        int[] distance = new int[count];

        for (int i = 0; i < count; i++) {
            visitedVertex[i] = false;
            distance[i] = Integer.MAX_VALUE;
        }

        // Distance of self loop is zero
        distance[source] = 0;
        for (int i = 0; i < count; i++) {

            // Update the distance between neighboring vertex and source vertex
            int u = findMinDistance(distance, visitedVertex);
            visitedVertex[u] = true;

            // Update all the neighboring vertex distances
            for (int v = 0; v < count; v++) {
                if (!visitedVertex[v] && graph[u][v] != 0 && (distance[u] + graph[u][v] < distance[v])) {
                    distance[v] = distance[u] + graph[u][v];
                    pathTable.put(v, u);
                }
            }
        }

        // backtracking
        List<Integer> path = new ArrayList<>();
        path.add(destination);

        int index = destination;
        while (index != source) {
            index = pathTable.get(index);
            path.add(index);
        }

        return path;
    }

    // Finding the minimum distance
    private int findMinDistance(int[] distance, boolean[] visitedVertex) {
        int minDistance = Integer.MAX_VALUE;
        int minDistanceVertex = -1;
        for (int i = 0; i < distance.length; i++) {
            if (!visitedVertex[i] && distance[i] < minDistance) {
                minDistance = distance[i];
                minDistanceVertex = i;
            }
        }
        return minDistanceVertex;
    }
}

package PUPath_Final;

import java.util.ArrayList;
import java.util.List;

public class GraphGenerator {
    public static final List<Node> paths = new ArrayList<>();
    public static final List<String> destinations = new ArrayList<>();

    static {
        destinations.add("entrance");
        destinations.add("shrine");
        destinations.add("west wing");
        destinations.add("right wing");
        destinations.add("chapel");
        destinations.add("south wing");

        paths.add(new Node("entrance", "shrine", 12, Direction.STRAIGHT));
        paths.add(new Node("entrance", "west wing", 6, Direction.RIGHT));

        paths.add(new Node("west wing", "shrine", 8, Direction.RIGHT));
        paths.add(new Node("west wing", "chapel", 5, Direction.RIGHT));
        paths.add(new Node("west wing", "right wing", 3, Direction.LEFT));
        paths.add(new Node("west wing", "entrance", 6, Direction.STRAIGHT));
        paths.add(new Node("west wing", "south wing", 11, Direction.STRAIGHT));

        paths.add(new Node("shrine", "entrance", 12, Direction.LEFT));
        paths.add(new Node("shrine", "west wing", 8, Direction.STRAIGHT));
        paths.add(new Node("shrine", "chapel", 17, Direction.STRAIGHT));

        paths.add(new Node("chapel", "shrine", 17, Direction.RIGHT));
        paths.add(new Node("chapel", "west wing", 5, Direction.LEFT));
        paths.add(new Node("chapel", "right wing", 4, Direction.LEFT));

        paths.add(new Node("right wing", "chapel", 4, Direction.STRAIGHT));
        paths.add(new Node("right wing", "west wing", 3, Direction.LEFT));
        paths.add(new Node("right wing", "south wing", 7, Direction.LEFT));

        paths.add(new Node("south wing", "west wing", 11, Direction.STRAIGHT));
        paths.add(new Node("south wing", "right wing", 7, Direction.STRAIGHT));
    }

    public int[][] generateMatrixGraph() {
        int count = destinations.size();

        int[][] matrix = new int[count][count];

        for (int x = 0; x < count; x++) {
            String current = destinations.get(x);

            for (int j = 0; j < count; j++) {
                String child = destinations.get(j);
                matrix[x][j] = findNodeWeight(current, child);
            }
        }

        return matrix;
    }

    private int findNodeWeight(String start, String end) {
        int weight = 0;
        for (Node node : paths) {
            if (node.getStart().equals(start) && node.getEnd().equals(end))
                weight = node.getWeight();
        }

        return weight;
    }

    public Integer analyzeChat(String chat) {
        for (int x = 0; x < destinations.size(); x++) {
            if (chat.contains(destinations.get(x))) return x;
        }
        return null;
    }

    public Node findNodeByDestination(int start, int end) {
        for (Node node : paths) {
            if (node.getStart().equals(destinations.get(start)) && node.getEnd().equals(destinations.get(end)))
                return node;
        }

        return null;
    }

    public List<String> generateInstructions(List<Integer> paths) {
        List<String> steps = new ArrayList<>();

        for (int x = 0; x < paths.size() - 1; x++)
            steps.add("    " + (x + 1) + ". " + findNodeByDestination(paths.get(x), paths.get(x + 1)).getInstruction());

        return steps;
    }
}

package PUPath_Final;

public class Node {
    private final String start;
    private final String end;
    private final int weight;
    private final Direction direction;

    public Node(String start, String end, int weight, Direction direction) {
        this.start = start;
        this.end = end;
        this.weight = weight;
        this.direction = direction;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public int getWeight() {
        return weight;
    }

    public String getInstruction() {
        if (direction.equals(Direction.STRAIGHT)) return "From " + start + " go " + direction + " to " + end + ".";
        return "From " + start + " turn " + direction + " to " + end + ".";
    }
}

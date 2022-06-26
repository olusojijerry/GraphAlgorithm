import java.util.ArrayList;
import java.util.List;

public class CycleDetection {
}

class VertexCycle{
    public String name;
    public boolean visited;
    public boolean beingVisited;
    public List<VertexCycle> adjacencyList;

    public VertexCycle(String name) {
        this.name = name;
        this.adjacencyList = new ArrayList<>();
    }


}

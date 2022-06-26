import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graphs {
    //Graph representation by array
    private static int[][] graphRepByArray = {
        {0,2,4,0}, {0,0,0,3},{0,0,0,0}, {0,0,0,0}
    };

    public static void main (String[] args){
        for (int i = 0; i < graphRepByArray.length; i++){
            for (int j = 0; j < graphRepByArray.length; j++){
                if (graphRepByArray[i][j] != 0){
                    System.out.println("Edge with weight: " + graphRepByArray[i][j]);
                }
            }
        }

        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");
        Vertex c = new Vertex("C");
        Vertex d = new Vertex("D");

        a.addNeighbor(b);
        a.addNeighbor(c);
        b.addNeighbor(d);

        a.showNeighbours();
        b.showNeighbours();
        c.showNeighbours();
        d.showNeighbours();
    }

}

//this is graph representation by composition
class Vertex{
    private String name;
    private List<Vertex> adjacencyList;

    public Vertex(String name) {
        this.name = name;
        this.adjacencyList = new ArrayList<Vertex>();
    }

    public void addNeighbor(Vertex vertex){
        adjacencyList.add(vertex);
    }

    public void showNeighbours(){
        for(Vertex vertex: adjacencyList){
            System.out.println(vertex);
        }
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "name='" + name + '\'' +
                '}';
    }
}

//Time complexity of BFS O(V+E)
//disadvantage: It has to store alot of references so the memory complexity is not favourable
class BreathFirstSearch{
    public void traverse(VertexBFS root){
        Queue<VertexBFS> queue = new LinkedList<VertexBFS>();

        root.setVisited(true);
        queue.add(root);

        while(!queue.isEmpty()){
            VertexBFS actualVertex = queue.remove();

            for(VertexBFS v : actualVertex.getAdjacencyList()){
                v.setVisited(true);
                queue.add(v);
            }
        }
    }
}

class VertexBFS{
    private String name;
    private List<VertexBFS> adjacencyList;
    private boolean visited;

    public VertexBFS(String name) {
        this.name = name;
        this.adjacencyList = new ArrayList<VertexBFS>();
    }

    public List<VertexBFS> getAdjacencyList() {
        return adjacencyList;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public void addNeighbor(VertexBFS vertex){
        adjacencyList.add(vertex);
    }

    public void showNeighbours(){
        for(VertexBFS vertex: adjacencyList){
            System.out.println(vertex);
        }
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "name='" + name + '\'' +
                '}';
    }
}


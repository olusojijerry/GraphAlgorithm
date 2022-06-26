import java.util.*;

public class TopologicalOrdering {
    private Stack<VertexTop> stack;

    public TopologicalOrdering() {
        this.stack = new Stack<>();
    }

    public void dfs(VertexTop vertexTop){
        vertexTop.setVisited(true);

        for (VertexTop v : vertexTop.getNeighborList()){
            if(!v.isVisited())
                dfs(v);
        }

        stack.push(vertexTop);
    }

    public Stack<VertexTop> getStack(){
        return stack;
    }
}

class VertexTop{
    private String name;
    private boolean visited;
    private List<VertexTop> neighborList;

    public VertexTop(String name) {
        this.name = name;
        this.neighborList = new ArrayList<>();
    }

    public void addNeighbor(VertexTop vertexTop){
        neighborList.add(vertexTop);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public List<VertexTop> getNeighborList() {
        return neighborList;
    }
}

class MainTopologicalOrdering{
    public static void main(String[] args){
        TopologicalOrdering topologicalOrdering = new TopologicalOrdering();

        List<VertexTop> graph = new ArrayList<>();
        VertexTop v0 = new VertexTop("0");
        VertexTop v1 = new VertexTop("1");
        VertexTop v2 = new VertexTop("2");
        VertexTop v3 = new VertexTop("3");
        VertexTop v4 = new VertexTop("4");
        VertexTop v5 = new VertexTop("5");

        v5.addNeighbor(v2);
        v5.addNeighbor(v0);
        v4.addNeighbor(v0);
        v4.addNeighbor(v1);
        v3.addNeighbor(v1);
        v2.addNeighbor(v3);

        graph.add(v0);
        graph.add(v1);
        graph.add(v2);
        graph.add(v3);
        graph.add(v4);
        graph.add(v5);

        for (int i = 0; i<graph.size(); i++){
            if (!graph.get(i).isVisited())
                topologicalOrdering.dfs(graph.get(i));
        }

        Stack<VertexTop> stack = topologicalOrdering.getStack();

        for (int i = 0; i < graph.size(); i++){
            System.out.print(stack.pop().getName() + " => ");
        }

    }

}

class Edge{
    private VertexShortestDist target;
    private int weight;

    public Edge(VertexShortestDist target, int weight) {
        this.target = target;
        this.weight = weight;
    }

    public VertexShortestDist getTarget() {
        return target;
    }

    public int getWeight() {
        return weight;
    }
}

class VertexShortestDist{
    private String name;
    private boolean visited;
    //this is used to track the shortest distance
    private int minDist;
    private VertexShortestDist predecessor;
    private List<Edge> adjacency;

    public VertexShortestDist(String name){
        this.name = name;
        this.minDist = Integer.MAX_VALUE;
        this.adjacency = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public int getMinDist() {
        return minDist;
    }

    public void setMinDist(int minDist) {
        this.minDist = minDist;
    }

    public VertexShortestDist getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(VertexShortestDist predecessor) {
        this.predecessor = predecessor;
    }

    public List<Edge> getAdjacency() {
        return adjacency;
    }

    public void setAdjacency(Edge edge) {
        adjacency.add(edge);
    }

    @Override
    public String toString() {
        return "" + "name='" + name + ", predecessor= " + predecessor;
    }
}

class TopologicalShortestPath{
    private Stack<VertexShortestDist> stack;

    public TopologicalShortestPath(List<VertexShortestDist> vertex){
        stack = new Stack<>();

        for(VertexShortestDist v : vertex){
            if (!v.isVisited()){
                dfs(v);
            }
        }
    }

    public void dfs(VertexShortestDist vertex){
        vertex.setVisited(true);

        for(Edge v : vertex.getAdjacency()){
            if(!v.getTarget().isVisited()){
                dfs(v.getTarget());
            }
        }
        stack.add(vertex);
    }

    public Stack<VertexShortestDist> getStack(){
        return stack;
    }
}

class ShortestPath{
    private TopologicalShortestPath topo;

    Queue<Integer> queue = new LinkedList<>();

    public ShortestPath(List<VertexShortestDist> vertex) {
        this.topo = new TopologicalShortestPath(vertex);
        vertex.get(0).setMinDist(0);
    }

    public void compute(){
        Stack<VertexShortestDist> topologicalOrdering = topo.getStack();

        while(!topologicalOrdering.isEmpty()){
            VertexShortestDist v = topologicalOrdering.pop();

            for( Edge e: v.getAdjacency()){
                VertexShortestDist u = e.getTarget();
                if(u.getMinDist() < v.getMinDist() + e.getWeight()) {
                    u.setMinDist(u.getMinDist() + e.getWeight());
                    u.setPredecessor(v);
                }
            }
        }
    }
}


class Main4 {

    // Add any helper functions you may need here
    class Position {
        int index;
        int val;

        Position() {

        }

        Position(int index, int val) {
            this.index = index;
            this.val = val;
        }
    }


    int[] findPositions(int[] arr, int x) {
        // Write your code here
        int[] output = new int[x];
        Queue<Position> pos = new LinkedList<>();

        for (int i = 0; i < arr.length; i++) {
            pos.add(new Position(i, arr[i]));
        }

        List<Position> pops = null;

        int outIndex = 0;

        for (int i = 0; i < x; i++) {
            pops = new ArrayList<>();

            Integer highest = 0;
            int maxIndex = Integer.MAX_VALUE;

            for (int j = 0; j < x && !pos.isEmpty(); j++) {
                Position p = pos.poll();
                pops.add(p);
                if (highest == p.val) {
                    maxIndex = Math.min(maxIndex, p.index);
                } else if (p.val > highest) {
                    maxIndex = p.index;
                    highest = p.val;
                }
            }

            output[outIndex++] = maxIndex;

            for (Position p : pops) {
                if (p.index != maxIndex) {
                    Position nxt = new Position(p.index, p.val == 0 ? p.val : p.val - 1);
                    pos.add(nxt);
                }
            }

        }
        return output;

    }
}

class Main9 {

    static class Node {
        int data;
        Node next;

        Node(int x) {
            data = x;
            next = null;
        }
    }

    // Add any helper functions you may need here
    public static void main(String[] a){
        Node root = new Node(1);
        root.next = new Node(2);
        root.next.next = new Node(8);
        root.next.next.next = new Node(9);
        root.next.next.next.next= new Node(12);
        root.next.next.next.next.next = new Node(16);

        reverse(root);
    }

    static Node reverse(Node head) {
        // Write your code here

        Node newHead = new Node(0);
        Node newCurr = newHead;
        Node curr = head;

        while (curr != null) {
            // a new subpart has been reached
            if (curr.data % 2 == 0) {
                Stack<Integer> stack = new Stack<Integer>();
                stack.push(curr.data);

                // push each subpart element to the stack
                while (curr.next != null && curr.next.data % 2 == 0) {
                    stack.push(curr.next.data);
                    curr = curr.next;
                }

                // pop each subpart element from stack and add to resulting list
                while (!stack.isEmpty()) {
                    newCurr.next = new Node(stack.pop());
                    newCurr = newCurr.next;
                }

                // set curr to the next non-subpart element
                curr = curr.next;

            } else {
                newCurr.next = new Node(curr.data);
                newCurr = newCurr.next;
                curr = curr.next;
            }
        }

        return newHead.next;
    }

    private static Node deleteNodes(Node head){
        Node another = head;
        while (another.next.data != 2 && another.next.data != 12) {
            deleteNodes(another.next);
        }

        int deleteNode = another.next.data;

        if(another.next != null){
            another.next = another.next.next;
        }

        Node newNode = new Node(deleteNode);

        another = another.next;
        newNode.next = another.next;
        another.next = newNode;

        return another;
    }


}


class SetIntersection{
    public int intersection(int[][] arr){
        for(int i = 0; i < arr.length; i++){

        }

        return 1;
    }

    public Set<Integer> integerSet(int[] arr){
        Set<Integer> sets = new HashSet<>();
        for(int i = arr[0]; i <= arr[1]; i++){
            sets.add(i);
        }

        return sets;
    }
}
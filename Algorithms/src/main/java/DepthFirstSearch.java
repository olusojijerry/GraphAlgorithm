import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DepthFirstSearch {
    private Stack<VertexDFS> stack;

    public DepthFirstSearch() {
        this.stack = new Stack<>();
    }

    public void dfsRecursion(List<VertexDFS> vertexDFSList){
        for (VertexDFS v : vertexDFSList){
            if(!v.isVisited()){
                v.setVisited(true);
                dfsHelperRecursion(v);
            }
        }
    }

    public void dfsHelperRecursion(VertexDFS vertex){
        System.out.println(vertex);

        for(VertexDFS v : vertex.getAdejacencyLis()){
            if (!v.isVisited()){
                v.setVisited(true);
                dfsHelperRecursion(v);
            }
        }
    }

    public void dfs(List<VertexDFS> vertexDFSList){
        for(VertexDFS vertex : vertexDFSList){
            if (!vertex.isVisited()){
                vertex.setVisited(true);
                dfsHelper(vertex);
            }
        }
    }

    private void dfsHelper(VertexDFS v){
        stack.add(v);
        v.setVisited(true);

        while(!stack.isEmpty()){
            VertexDFS vertex = stack.pop();
            System.out.println(vertex);

            for (VertexDFS a : vertex.getAdejacencyLis()){
                if (!a.isVisited()){
                    stack.add(a);
                    a.setVisited(true);
                }
            }
        }
    }
}

class VertexDFS{
    private String name;
    private boolean visited;
    private List<VertexDFS> adejacencyLis;

    public VertexDFS(String name) {
        this.name = name;
        this.adejacencyLis = new ArrayList<>();
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public List<VertexDFS> getAdejacencyLis() {
        return adejacencyLis;
    }

    public void addNeighbor(VertexDFS vertexDFS){
        adejacencyLis.add(vertexDFS);
    }

    @Override
    public String toString() {
        return "VertexDFS{" +
                "name='" + name + '\'' +
                '}';
    }
}

class Testing{
    public static void main(String[] args){
        VertexDFS v1 = new VertexDFS("A");
        VertexDFS v2 = new VertexDFS("B");
        VertexDFS v3 = new VertexDFS("C");
        VertexDFS v4 = new VertexDFS("D");
        VertexDFS v5 = new VertexDFS("E");

        List<VertexDFS> list = new ArrayList<>();

        v1.addNeighbor(v3);
        v1.addNeighbor(v2);
        v2.addNeighbor(v4);
        v2.addNeighbor(v5);

        list.add(v1);
        list.add(v2);
        list.add(v3);
        list.add(v4);
        list.add(v5);

        DepthFirstSearch dfs = new DepthFirstSearch();

        dfs.dfsRecursion(list);
        System.out.println("Non Recursive");
        //dfs = new DepthFirstSearch();
//        dfs.dfs(list);

    }
}

class assignment{
    public static void main(String[] args){
        maze(new int[][]{{1,1,1,1,1}, {1,2,0,1,1}, {1,1,0,1,1}, {1,1,0,0,0}, {1,1,1,1,3}});
    }

    public static void maze(int[][] arr){
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr.length; j++){
                if(arr[i][j]== 2){
                    System.out.println("Beginning move at " + i + "," + j);
                    DFS dfs = new DFS(i, j, arr);
                    dfs.beginMove();
                    break;
                }
            }
        }
    }
}

class DFS{
    private int startRow;
    private int startCol;
    private int[][] maze;
    private boolean[][] visited;

    public DFS(int startRow, int startCol, int[][] maze) {
        this.startRow = startRow;
        this.startCol = startCol;
        this.maze = maze;
        this.visited = new boolean[maze.length][maze.length];
    }

    public void beginMove(){
        if(dfs(startRow, startCol)){
            System.out.println("There is a way in this maze");
        }else
            System.out.println("No way in this maze");
    }

    private boolean isNotFeasible(int x, int y){
        return x < 0 || y < 0 || x >= maze.length || y >= maze.length || maze[x][y] == 1 || visited[x][y];
    }

    private boolean dfs(int startRow, int startCol) {
        if(startRow == maze.length - 1 && startCol == maze.length - 1){
            return true;
        }

        if(!isNotFeasible(startRow, startCol)){
            visited[startRow][startCol]= true;
            System.out.println("Moving through " + startRow + "," +startCol);

            if(dfs(startRow+1, startCol))
                return true;

            if (dfs(startRow - 1, startCol))
                return true;

            if(dfs(startRow, startCol + 1))
                return true;

            if(dfs(startRow, startCol - 1))
                return true;

            visited[startRow][startCol] = false;
            return false;
        }

        return false;
    }
}

class opologicalOrdering{
    private Stack<VertexDFS> stack;

    public opologicalOrdering() {
        this.stack = new Stack<>();
    }

    private void dfs(VertexDFS vertex){
        vertex.setVisited(true);

        for(VertexDFS v : vertex.getAdejacencyLis()){
            if(!v.isVisited())
                dfs(v);
        }

        stack.push(vertex);
    }

    public Stack<VertexDFS> getStack(){
        return stack;
    }
}
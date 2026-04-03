import java.util.*;

class GraphList {
    private int vertices;
    private LinkedList<Integer>[] adjList;

    public GraphList(int v) {
        vertices = v;
        adjList = new LinkedList[v];

        for (int i = 0; i < v; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    public void addEdge(int src, int dest) {
        adjList[src].add(dest);
        adjList[dest].add(src); // Undirected Graph
    }

    // Breadth First Traversal
    public void BFT(int start) {
        boolean[] visited = new boolean[vertices];
        Queue<Integer> queue = new LinkedList<>();

        visited[start] = true;
        queue.add(start);

        System.out.print("BFT: ");
        while (!queue.isEmpty()) {
            int v = queue.poll();
            System.out.print(v + " ");

            for (int neighbor : adjList[v]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }

    // Depth First Traversal
    public void DFT(int start) {
        boolean[] visited = new boolean[vertices];
        System.out.print("DFT: ");
        DFTUtil(start, visited);
        System.out.println();
    }

    private void DFTUtil(int v, boolean[] visited) {
        visited[v] = true;
        System.out.print(v + " ");

        for (int neighbor : adjList[v]) {
            if (!visited[neighbor]) {
                DFTUtil(neighbor, visited);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int v = sc.nextInt();

        GraphList g = new GraphList(v);

        System.out.print("Enter number of edges: ");
        int e = sc.nextInt();

        System.out.println("Enter edges (source destination):");
        for (int i = 0; i < e; i++) {
            int src = sc.nextInt();
            int dest = sc.nextInt();
            g.addEdge(src, dest);
        }

        System.out.print("Enter starting vertex: ");
        int start = sc.nextInt();

        g.BFT(start);
        g.DFT(start);

        sc.close();
    }
}
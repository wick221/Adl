import java.util.*;

class GraphMatrix {
    private int vertices;
    private int[][] matrix;

    public GraphMatrix(int v) {
        vertices = v;
        matrix = new int[v][v];
    }

    public void addEdge(int src, int dest) {
        matrix[src][dest] = 1;
        matrix[dest][src] = 1; // Undirected Graph
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

            for (int i = 0; i < vertices; i++) {
                if (matrix[v][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    queue.add(i);
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

        for (int i = 0; i < vertices; i++) {
            if (matrix[v][i] == 1 && !visited[i]) {
                DFTUtil(i, visited);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int v = sc.nextInt();

        GraphMatrix g = new GraphMatrix(v);

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
import java.util.*;

class Node implements Comparable<Node> {
    int vertex, distance;

    Node(int v, int d) {
        vertex = v;
        distance = d;
    }

    public int compareTo(Node other) {
        return this.distance - other.distance;
    }
}

public class DijkstraList {

    static void dijkstra(List<List<Node>> adj, int V, int src) {

        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[V];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        pq.add(new Node(src, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int u = current.vertex;

            for (Node neighbor : adj.get(u)) {
                int v = neighbor.vertex;
                int weight = neighbor.distance;

                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.add(new Node(v, dist[v]));
                }
            }
        }

        System.out.println("Vertex \t Distance from Source");
        for (int i = 0; i < V; i++)
            System.out.println(i + " \t\t " + dist[i]);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();

        List<List<Node>> adj = new ArrayList<>();

        for (int i = 0; i < V; i++)
            adj.add(new ArrayList<>());

        System.out.print("Enter number of edges: ");
        int E = sc.nextInt();

        System.out.println("Enter edges (u v weight):");
        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();

            adj.get(u).add(new Node(v, w));
            adj.get(v).add(new Node(u, w));  // remove if directed graph
        }

        System.out.print("Enter source vertex: ");
        int src = sc.nextInt();

        dijkstra(adj, V, src);
    }
}
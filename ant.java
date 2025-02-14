import java.util.*;

public class ant {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // Number of campuses
        
        for (int i = 0; i < n; i++) {
            int numHills = scanner.nextInt();
            int numTunnels = scanner.nextInt();
            Graph graph = new Graph(numHills);

            for (int j = 0; j < numTunnels; j++) {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                int cost = scanner.nextInt();
                graph.addEdge(x, y, cost);
            }

            int result = graph.kruskalMST();
            if (result == -1) {
                System.out.println("Campus #" + (i + 1) + ": I'm a programmer, not a miracle worker!");
            } else {
                System.out.println("Campus #" + (i + 1) + ": " + result);
            }
        }

        scanner.close();
    }
}

// Edge class to store tunnels
class Edge implements Comparable<Edge> {
    int x, y, cost;

    public Edge(int x, int y, int cost) {
        this.x = x;
        this.y = y;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.cost, other.cost); // Sort edges by cost (ascending)
    }
}

// Disjoint Set (Union-Find) for Kruskal's Algorithm
class DisjointSet {
    int[] parent, rank;

    public DisjointSet(int size) {
        parent = new int[size + 1];
        rank = new int[size + 1];
        for (int i = 1; i <= size; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // Path compression
        }
        return parent[x];
    }

    public boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        
        if (rootX == rootY) return false; // Already connected
        
        // Union by rank
        if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
        return true;
    }
}

// Graph class
class Graph {
    private int numHills;
    private List<Edge> edges;

    public Graph(int numHills) {
        this.numHills = numHills;
        this.edges = new ArrayList<>();
    }

    public void addEdge(int x, int y, int cost) {
        edges.add(new Edge(x, y, cost));
    }

    public int kruskalMST() {
        if (numHills == 1) return 0; // If only one hill, no cost needed

        Collections.sort(edges); // Sort edges by weight
        DisjointSet ds = new DisjointSet(numHills);

        int totalCost = 0, edgesUsed = 0;
        for (Edge edge : edges) {
            if (ds.union(edge.x, edge.y)) {
                totalCost += edge.cost;
                edgesUsed++;
                if (edgesUsed == numHills - 1) break; // MST is complete
            }
        }

        // If we haven't used (h - 1) edges, the graph isn't fully connected
        return (edgesUsed == numHills - 1) ? totalCost : -1;
    }
}

import java.util.*;

public class ant {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numCampuses = scanner.nextInt(); 

        for (int campus = 1; campus <= numCampuses; campus++) {
            int numHills = scanner.nextInt();
            int numTunnels = scanner.nextInt(); 
            List<Edge> edges = new ArrayList<>();

            for (int i = 0; i < numTunnels; i++) {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                int cost = scanner.nextInt();
                edges.add(new Edge(x, y, cost));
            }
            Collections.sort(edges, Comparator.comparingInt(e -> e.cost));
            UnionFind unionFind = new UnionFind(numHills + 1); // +1 because ant hills are numbered from 1 to numHills

            // Kruskal's
            int totalCost = 0;
            int edgesUsed = 0;

            for (Edge edge : edges) {
                if (unionFind.union(edge.x, edge.y)) { // if union is successful no cycle
                    totalCost += edge.cost;
                    edgesUsed++;
                    if (edgesUsed == numHills - 1) break; 
                }
            }

            // check if ALL ant hills connected
            if (edgesUsed == numHills - 1) {
                System.out.println("Campus #" + campus + ": " + totalCost);
            } else {
                System.out.println("Campus #" + campus + ": I'm a programmer, not a miracle worker!");
            }
        }
        scanner.close();
    }

    static class Edge {
        int x, y, cost;

        Edge(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }

    static class UnionFind {
        int[] parent;
        int[] rank;

        UnionFind(int size) {
            parent = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
                rank[i] = 1;   
            }
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // path compression
            }
            return parent[x];
        }

        boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX == rootY) return false; // already in the same set

            // union by rank
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
}
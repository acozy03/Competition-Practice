import java.util.*;

public class duel {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int numEssays = scanner.nextInt();
            int numRelationships = scanner.nextInt();
            if (numEssays == 0 && numRelationships == 0) break;

            Graph graph = new Graph(numEssays);
            for (int i = 0; i < numRelationships; i++) {
                int definedHere = scanner.nextInt();
                int usedHere = scanner.nextInt();
                graph.addEdge(definedHere, usedHere);
            }

            System.out.println(graph.topologicalSort());
        }

        scanner.close();
    }
}

class Graph {
    private int numEssays;
    private List<Integer>[] adj;
    private int[] indegree;

    public Graph(int numEssays) {
        this.numEssays = numEssays;
        adj = new ArrayList[numEssays + 1];
        indegree = new int[numEssays + 1];

        for (int i = 1; i <= numEssays; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    // 2. Using the graph edges, for each vertex calculate its in-degree and store these in an array indegree. 
    public void addEdge(int definedHere, int usedHere) {
        adj[definedHere].add(usedHere);
        indegree[usedHere]++;
    }

// i literally just took the method from the notes 
    public int topologicalSort() {
        // 1. Initialize TOP to be an empty list
        List<Integer> TOP = new ArrayList<>();

        // 3. Create a list of next items. Add to next each vertex with in degree 0.
        Queue<Integer> next = new LinkedList<>();
 
        for (int i = 1; i <= numEssays; i++) {
            if (indegree[i] == 0) next.add(i);
        }

        int ways = 1;  

        // 4. While TOP has fewer than n items:
        while (TOP.size() < numEssays) {
            // a. Take the next item, v, in the list next. If next is empty, there is no top sort.
            if (next.isEmpty()) return 0; 

            if (next.size() > 1) ways = 2; 
            // taking v 
            int v = next.poll();
            // b. Add v to TOP.
            TOP.add(v);

            for (int w : adj[v]) {
                // i. subtract 1 from the indegree of w.
                indegree[w]--; 
                // ii. if the indegree[w] == 0, then add w to the list next.
                if (indegree[w] == 0) next.add(w); 
            }
        }

        return ways;
    }
}

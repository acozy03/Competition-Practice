import java.util.*;

public class stars {
    static List<Integer>[] graph;
    static boolean[] visited;
    static boolean hasCycle;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numCases = scanner.nextInt();  
        for (int i = 0; i < numCases; i++) {
            int numStars = scanner.nextInt();   
            visited = new boolean[numStars + 1];
            int constellationCount = 0;
            int needstobefixed = 0;     
            int numConnections = scanner.nextInt();   
            graph = new ArrayList[numStars + 1];
            for (int j = 1; j <= numStars; j++) {
                graph[j] = new ArrayList<>();
            }

            for (int j = 0; j < numConnections; j++) {
                int u = scanner.nextInt();
                int v = scanner.nextInt();
                graph[u].add(v);
                graph[v].add(u);
            }

            for (int j = 1; j <= numStars; j++) {
                if (!visited[j] && !graph[j].isEmpty()) {
                    hasCycle = false;
                    constellationCount++;
                    dfs(j, -1);
                    if (hasCycle) {
                        needstobefixed++;
                    }
                }
            }

            System.out.printf("Night sky #%d: %d constellations, of which %d need to be fixed.%n", i+1, constellationCount, needstobefixed);
            System.out.println();
        }
        scanner.close();
    }

    private static void dfs(int node, int parent) {
        visited[node] = true;

        for (int neighbor : graph[node]) {
            if (!visited[neighbor]) {
                dfs(neighbor, node);
            } else if (neighbor != parent) {  
                hasCycle = true;
            }
        }
    }
}

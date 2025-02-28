package AllContests.Comp6;
import java.util.*;

public class alarge {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numCases = scanner.nextInt(); 

        while (numCases > 0) {
            int numLocations = scanner.nextInt(); 
            int numRoads = scanner.nextInt(); 
            int maxCrosswalks = scanner.nextInt(); 

            List<List<Edge>> graph = new ArrayList<>();
            for (int i = 0; i <= numLocations; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < numRoads; i++) {
                int startRoad = scanner.nextInt();
                int endRoad = scanner.nextInt();
                int isCrosswalk = scanner.nextInt();
                int lengthOfRoad = scanner.nextInt(); // length
                graph.get(startRoad).add(new Edge(endRoad, isCrosswalk, lengthOfRoad));
                graph.get(endRoad).add(new Edge(startRoad, isCrosswalk, lengthOfRoad)); // has to go both directions
            }

            int res = dijkstra(graph, numLocations, maxCrosswalks);

            System.out.println(res);
            numCases--; 
        }
        scanner.close();
    }

    private static int dijkstra(List<List<Edge>> graph, int numLocations, int maxCrosswalks) {
        // dist[v][k] = shortest length to vertex v using k crosswalks
        int[][] dist = new int[numLocations + 1][maxCrosswalks + 1];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        //enqueue ordered pairs of vertex number and number of crosswalks
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, 1, 0}); // vertex 1 with 0 crosswalks and 0 length
        dist[1][0] = 0;

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int currDist = current[0];
            int currVertex = current[1];
            int currCrosswalks = current[2];

            //Stop looking for paths once ANY path to the class is found
            if (currVertex == numLocations) {
                return currDist;
            }

        
            if (currDist > dist[currVertex][currCrosswalks]) {
                continue;
            }

            for (Edge edge : graph.get(currVertex)) {
                int nextVertex, nextCrosswalks, nextDist;
                 nextVertex = edge.destinationVertex;
                nextCrosswalks = currCrosswalks + edge.isCrosswalk;
                nextDist = currDist + edge.length;

                // consider paths with only <= maxCrosswalks crosswalks
                if (nextCrosswalks <= maxCrosswalks && nextDist < dist[nextVertex][nextCrosswalks]) {
                    dist[nextVertex][nextCrosswalks] = nextDist;
                    pq.offer(new int[]{nextDist, nextVertex, nextCrosswalks});
                }
            }
        }
        // could not find path
        return -1;
    }

    static class Edge {
        int destinationVertex; 
        int isCrosswalk; 
        int length; 

        Edge(int destinationVertex, int isCrosswalk, int length) {
            this.destinationVertex = destinationVertex;
            this.isCrosswalk = isCrosswalk;
            this.length = length;
        }
    }
}
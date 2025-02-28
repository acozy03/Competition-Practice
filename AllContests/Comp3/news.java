package AllContests.Comp3;
import java.util.*;

public class news {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int numCases = scanner.nextInt();
        
        while (numCases > 0) {
            int n = scanner.nextInt();
            int[] supervisor = new int[n];
        List<List<Integer>> tree = new ArrayList<>();
    
         for (int i = 0; i < n; i++) {
                tree.add(new ArrayList<>());
            }
        
            for (int i =1; i < n; i++) {
                 supervisor[i] = scanner.nextInt();
                tree.get(supervisor[i]).add(i);
            }
            int result = calculateTime(tree, 0);
            System.out.println(result);
            numCases--; 
        }
        
        scanner.close();
    }
    private static int calculateTime(List<List<Integer>> tree, int node) {
        List<Integer> times = new ArrayList<>();
    
        for (int child: tree.get(node)) {
            times.add(calculateTime(tree, child));
        }
        Collections.sort(times, Collections.reverseOrder());
        
        int maxTime = 0;
  
        for (int i = 0; i < times.size(); i++) {
            int addtoItime = times.get(i) + i + 1;  
            maxTime = Math.max(maxTime, addtoItime);
        }
        return maxTime;
    }
}

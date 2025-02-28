import java.util.*;

public class profits {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            int n = scanner.nextInt();
            if (n == 0) break; 
            int maxRightNow = -100; // so we can handle negative profit if needed
            int maxRightHere = 0;
            
            for (int i = 0; i < n; i++) {
                int profit = scanner.nextInt();
                maxRightHere = Math.max(profit, maxRightHere + profit);
                maxRightNow = Math.max(maxRightNow, maxRightHere);
            }
            
            System.out.println(maxRightNow);
        }
        
        scanner.close();
    }
}

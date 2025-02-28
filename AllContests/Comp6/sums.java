package AllContests.Comp6;
import java.util.Scanner;

public class sums {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int numCases = scanner.nextInt();
        for (int i = 1; i <= numCases; i++) {
            int n = scanner.nextInt();
            int sum = 0;
            
            for (int k = 1; k <= n; k++) {
                sum += (k * (k + 1) * (k + 2)) / 2;
            }
            
            System.out.println(i + " " + n + " " + sum);
        }
        
        scanner.close();
    }
}

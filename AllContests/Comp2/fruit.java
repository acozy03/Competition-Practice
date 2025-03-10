package AllContests.Comp2;
import java.util.*;

public class fruit {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numTestCases = scanner.nextInt();
        if (numTestCases < 1 || numTestCases > 200) {
            scanner.close();
            return;
        }

        int printNum = numTestCases;
        int res[] = new int[numTestCases * 2];
        int cnt = 0;

        // Process each test case
        while (numTestCases > 0) {
            int days = scanner.nextInt();
            if (days < 1 || days > 1000) {
                scanner.close();
                return;
            }

            int[] dailySales = new int[days];

            // Input daily sales data with constraints between 0 and 1000
            for (int i = 0; i < days; i++) {
                dailySales[i] = scanner.nextInt();
                if (dailySales[i] < 0 || dailySales[i] > 1000) {
                    scanner.close();
                    return;
                }
            }

            // Calculate 
            int minOrder = calculateCeilingMinOrder(dailySales);
            int maxExtra = calculateMaxExtraFruits(dailySales, minOrder);
            res[cnt * 2] = minOrder;
            res[cnt * 2 + 1] = maxExtra;
            cnt++;

            numTestCases--;
        }

        // Print results
        for (int i = 0; i < printNum * 2; i += 2) {
            System.out.print(res[i] + " " + res[i + 1]);
            System.out.println();
        }
        scanner.close();
    }

    // Function to calculate minimum order using ceiling average approach
    private static int calculateCeilingMinOrder(int[] sales) {
        int sum = 0;
        int maxOrder = 0;

        for (int i = 0; i < sales.length; i++) {
            sum += sales[i];
            int ceilingOrder = (int) Math.ceil((double) sum / (i + 1));
            maxOrder = Math.max(maxOrder, ceilingOrder);
        }
        return maxOrder;
    }

    // Function to calculate the maximum extra fruits stored at the end of any day
    private static int calculateMaxExtraFruits(int[] sales, int dailyOrder) {
        int leftover = 0, maxExtra = 0;
        for (int sale : sales) {
            leftover += dailyOrder;
            leftover -= sale;
            maxExtra = Math.max(maxExtra, leftover);
        }
        return maxExtra;
    }
}

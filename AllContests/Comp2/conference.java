import java.util.*;

public class conference {
    // 2^(29 - day)
    private static long calculatePrice(int day) {
        return (long) Math.pow(2, 29 - day);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numTestCases = scanner.nextInt();

        while (numTestCases > 0) {
            int n = scanner.nextInt();
            ArrayList<int[]> confOffers = new ArrayList<>();

            // Read the offers
            for (int i = 0; i < n; i++) {
                int start = scanner.nextInt();
                int length = scanner.nextInt();
                confOffers.add(new int[] { start, length });
            }

            // Sort offers by start day
            confOffers.sort(Comparator.comparingInt(a -> a[0]));

            // Track when the venue is available
            int venueFreeDay = 0;
            long totalProfit = 0;

            // Process each offer
            for (int[] offer : confOffers) {
                int start = offer[0];
                int length = offer[1];
                int end = start + length - 1;

                if (end > 29)
                    continue;

                // If the venue is free before the start day, schedule this conference
                if (start >= venueFreeDay) {
                    long profit = 0;
                    for (int day = start; day <= end; day++) {
                        profit += calculatePrice(day);
                    }
                    totalProfit += profit;
                    venueFreeDay = end + 1; // Update venue availability
                }
            }

            // Print the result for the current test case
            System.out.println(totalProfit);
            numTestCases--;
        }
        scanner.close();
    }
}

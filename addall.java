import java.util.*;

public class addAll {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numCases = scanner.nextInt(); // Number of test cases
        int res[] = new int[20000];
        for (int t = 0; t < numCases; t++) {
            int n = scanner.nextInt(); // Number of integers
            ArrayList<Integer> numbers = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                numbers.add(scanner.nextInt());
            }

            int totalCost = 0;

            // Keep merging until one element remains
            while (numbers.size() > 1) {
                // Sort the list to find the two smallest elements
                Collections.sort(numbers);

                // Take the two smallest elements
                int num1 = numbers.remove(0);
                int num2 = numbers.remove(0);

                int sum = num1 + num2;
                totalCost += sum;

                // Add the sum back to the list
                numbers.add(sum);
            }

            res[t] = totalCost;

        }

        for (int i = 0; i < numCases; i++) {
            System.out.println(res[i]);
        }

        scanner.close();
    }
}

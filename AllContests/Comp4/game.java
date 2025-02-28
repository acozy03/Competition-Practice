import java.util.Scanner;

public class game {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read number of test cases
        int testCases = Integer.parseInt(scanner.nextLine());

        // Process each test case
        for (int i = 0; i < testCases; i++) {
            String moves = scanner.nextLine().trim();
            int currentLevel = 0;  // The player starts at ground level (0)
            int maxDepth = 0;  // Track the maximum depth below ground

            // Process each movement in the string
            for (char move : moves.toCharArray()) {
                if (move == '>') {
                    continue;  // No change in level
                } else if (move == '<') {
                    continue;  // No change in level
                } else if (move == '^') {
                    currentLevel = Math.max(currentLevel - 1, 0);  // Can't go above ground
                } else if (move == 'v') {
                    currentLevel++;  // Move one level down
                }

                // Update maximum depth if the player is deeper
                maxDepth = Math.max(maxDepth, currentLevel);
            }

            // Output the maximum depth for this test case
            System.out.println(maxDepth);
        }

        // Close the scanner
        scanner.close();
    }
}

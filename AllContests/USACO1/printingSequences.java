package AllContests.USACO1;
import java.util.*;

public class printingSequences {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numCases = scanner.nextInt();

        for (int t = 0; t < numCases; t++) {
            int sizeOfSequence = scanner.nextInt(); 
            int maxUniqueNums = scanner.nextInt(); 

            // array to store the sequence of integers.
            int[] sequence = new int[sizeOfSequence];
            for (int i = 0; i < sizeOfSequence; i++) {
                sequence[i] = scanner.nextInt();
            }

            boolean possible = canGenerateSequence(sequence, maxUniqueNums);
            if (possible) System.out.println("YES");
            else System.out.println("NO");
        }
        scanner.close();
    }

    private static boolean canGenerateSequence(int[] sequence, int maxUniqueNums) {
        // Get the length of the sequence.
        int sizeOfSequence = sequence.length;
        return true; 
    }
}

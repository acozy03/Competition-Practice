package AllContests.Comp1;
import java.util.Scanner;

public class passwords {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        // read in test cases
        int testcases = Integer.parseInt(scanner.nextLine());
        // output
        String outputRes[] = new String[testcases];
        if (testcases <= 100) {
            for (int i = 0; i < testcases; i++) {
                // length of password
                int passwordLength = Integer.parseInt(scanner.nextLine());
                // array of arrays to hold each input separately in characters
                char inputArray[][] = new char[passwordLength][];
                if (passwordLength <= 20) {
                    // read in input for each possible letter for respective password slot
                    for (int j = 0; j < passwordLength; j++) {
                        inputArray[j] = scanner.nextLine().toCharArray();
                        // loaded in first input "abc" as char array to do combinations, which letter
                        // goes into slot 0? (based off first test case on paper)
                    }
                    int rank = Integer.parseInt(scanner.nextLine()) - 1;
                    if (rank <= 1048576) {
                        outputRes[i] = combinations(inputArray, rank, 0);
                    }
                }

            }
        }

        for (int i = 0; i < testcases; i++) {
            System.out.println(outputRes[i]);
        }
        scanner.close();

    }

    public static String combinations(char input[][], int rank, int n) {
        // base case return character selected when last slot is hit
        if (n == input.length) {
            return "";
        }

        // how many combinations are possible for remaining slots
        int remaining = 1;
        for (int i = n + 1; i < input.length; i++)
            remaining *= input[i].length;

        // which character to choose from remaining slot
        int charIndex = rank / remaining;
        char chosenChar = input[n][charIndex];

        // reduce rank and recurse
        int newRank = rank % remaining;
        return chosenChar + combinations(input, newRank, n + 1);
    }
}

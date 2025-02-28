package AllContests.Comp1;
import java.util.Scanner;

public class upwards {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        // num words to eval
        int numwords = Integer.parseInt(scanner.nextLine());
        // res array to store yes or no
        String res[] = new String[numwords];

        if (numwords <= 1000) {

            // loop through each word and check logic
            for (int i = 0; i < numwords; i++) {
                // get word
                String word = scanner.nextLine().toLowerCase();
                boolean isAlphabetical = true;
                int length = word.length();
                // check if alphabetical with no repeats
                for (int j = 0; j < length - 1; j++) {
                    if (word.charAt(j) >= word.charAt(j + 1)) {
                        isAlphabetical = false;
                        break;
                    }
                }
                if (isAlphabetical) {
                    res[i] = "YES";
                } else {
                    res[i] = "NO";
                }
            }

        }

        for (int i = 0; i < numwords; i++) {
            System.out.println(res[i]);
        }
        scanner.close();

    }
}
import java.util.*;

public class change {
    public static void main(String args[]) {

        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int res[] = new int[n];

        for (int i = 0; i < n; i++) {

            int numPackages = Integer.parseInt(scanner.next());
            int packages[] = new int[numPackages];
            for (int j = 0; j < numPackages; j++) {
                if (scanner.hasNextInt()) {
                    packages[j] = scanner.nextInt();
                }
            }

            Arrays.sort(packages);
            res[i] = changeLogic(packages);
        }

        for (int i = 0; i < n; i++) {
            System.out.println(res[i]);
        }
        scanner.close();
    }

    public static int changeLogic(int[] denom) {

        // Needed for our greedy algorithm.
        // Basically, the current sum+1 is the maximum value the next number
        // must be. If it's bigger, we know we can't hit this current target exactly.
        int sum = 0, curTarget = 1, index = 0;
        while (index < denom.length && denom[index] <= curTarget) {
            sum += denom[index];
            curTarget = sum + 1;
            index++;
        }

        return curTarget;
    }

}

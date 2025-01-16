import java.util.*;

public class change {
    public static void Main(String args[]) {

        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int res[] = new int[n];

        for (int i = 0; i < n; i++) {

            int numPackages = Integer.parseInt(scanner.nextLine());

            for (int j = 0; j < numPackages; j++) {

                int packages[] = new int[numPackages];
                if (scanner.hasNextInt()) {
                    packages[j] = scanner.nextInt();
                }

                Arrays.sort(packages);
                res[i] = changeLogic(packages, numPackages);

            }
        }

    }

    public static int changeLogic(int packages[], int numPackages) {

        int res = 0;
        int max = 0;

        for (int i = 0; i < numPackages; i++)
            max += packages[i];

        System.out.println(max);

        return res;

    }

}

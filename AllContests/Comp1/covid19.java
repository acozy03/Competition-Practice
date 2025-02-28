package AllContests.Comp1;
import java.util.Scanner;

public class covid19 {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        // number of input cases
        int n = Integer.parseInt(scanner.nextLine());
        if (n > 10) {
            scanner.close();
            return;
        }
        int res[] = new int[10];
        // for each test case
        for (int i = 0; i < n; i++) {
            int cnt = 0;
            // take in number of family members
            int familynum = Integer.parseInt(scanner.next());
            if (familynum <= 20) {
                for (int j = 0; j < familynum; j++) {
                    // take in weight of person before
                    int before = Integer.parseInt(scanner.next());
                    // take in weight of person after
                    int after = Integer.parseInt(scanner.next());
                    if (before <= 400 && after <= 400) {
                        int difference = after - before;
                        if (difference == 19)
                            cnt++;
                    }

                }
                res[i] = cnt;
            }

        }

        for (int i = 0; i < n; i++) {
            System.out.println(res[i]);
        }
        scanner.close();
    }
}

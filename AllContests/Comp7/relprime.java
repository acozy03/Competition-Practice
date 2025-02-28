package AllContests.Comp7;
import java.util.*;

public class relprime {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            long num = scanner.nextLong();
            List<Long> factors = getFactors(num);
            relPrime(num, factors);
        }
        scanner.close();
    }

    public static List<Long> getFactors(long num) {
        List<Long> factors = new ArrayList<>();
        if (num < 2) return factors; // Edge case

        long sqrt = (long) Math.sqrt(num);
        for (long i = 1; i <= sqrt; i++) {
            if (num % i == 0) {
                factors.add(i);
                if (i != num / i) { // Don't add sqrt twice
                    factors.add(num / i);
                }
            }
        }
        return factors;
    }

    public static void relPrime(long num, List<Long> factors) {
        System.out.println("Numbers relatively prime to " + num + ":");
        for (long i = 1; i < num; i++) {
            if (gcd(num, i) == 1) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }

    public static long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}

package AllContests.Comp8;
import java.util.*;

public class series {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        sieve(); 
        int numCases = scanner.nextInt();
        for(int i = 0; i < numCases; i++) {
            int numNumbers = scanner.nextInt();
            long firstTerm = scanner.nextLong();
            long res = 0;
            for (int j = 1; j <= numNumbers; j++) {
                long term = firstTerm * j; 
                res += countDivisors(term);
            }
            System.out.println(res);
        }
        scanner.close();
    }

    static ArrayList<Integer> primes = new ArrayList<>();
    // sieve please save me once again
    public static void sieve() {
        boolean[] isPrime = new boolean[1000000+1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        
        for (int i = 2; i <= 1000000; i++) {
            if (isPrime[i]) {
                primes.add(i);
                for (long j = (long) i * i; j <= 1000000; j += i) {
                    isPrime[(int) j] = false;
                }
            }
        }
    }
    public static long countDivisors(long num) {
        long count = 1;  // num of divisors
    
        for (int prime : primes) {  
            if ((long) prime * prime > num) break;  // stop if prime > sqrt num
            long power = 0;  // count exponent of this prime 
            while (num % prime == 0) { 
                num /= prime;
                power++;
            }
            count *= (power + 1);  // multiply power 
            if (num == 1) break;  
        }
        // if num is still greater than 1, it must be a prime itself
        if (num > 1) count *= 2;
    
        return count;
    }
}

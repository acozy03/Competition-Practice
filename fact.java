import java.util.*;
// most of this code was taken straight from math notes from this week 
public class fact {
   public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
       int n = Integer.parseInt(scanner.nextLine()); 
       
       for (int i = 0; i < n; i++) {
           int input = Integer.parseInt(scanner.nextLine()); 
           
           ArrayList<Pair> list = primeFactorizeFactorial(input);
           
           for (int j = 0; j < list.size() - 1; j++)
               System.out.print(list.get(j).exp + " ");
           System.out.println(list.get(list.size() - 1).exp);
       }
       scanner.close();
   } 

   // literally straight from notes
   public static int numTimesDivide(int n, int p) {
       int res = 0;
       while (n > 0) {
           res += n / p;
           n /= p;
       }
       return res;
   }

  // sieve logic also literally from notes
   public static List<Integer> sieve(int n) {
       boolean[] isPrime = new boolean[n + 1];
       Arrays.fill(isPrime, true);
       isPrime[0] = isPrime[1] = false; // 0 and 1 are not primes
       
       List<Integer> primes = new ArrayList<>();
       
       for (int i = 2; i <= n; i++) {
           if (isPrime[i]) {
               primes.add(i);
               for (int j = i * 2; j <= n; j += i) {
                   isPrime[j] = false;
               }
           }
       }
       return primes;
   }

   public static ArrayList<Pair> primeFactorizeFactorial(int n) {
       ArrayList<Pair> res = new ArrayList<>();
        // gets all primes
       List<Integer> primes = sieve(n);
       
       // gets the exponent of each prime to print 
       for (int prime : primes) {
           int exp = numTimesDivide(n, prime);
           res.add(new Pair(prime, exp));
       }
       
       return res;
   }
}

/**
 * Class to store prime factors and their exponents.
 */
class Pair {
   public int prime;
   public int exp;

   public Pair(int p, int e) {
       prime = p;
       exp = e;
   }
}

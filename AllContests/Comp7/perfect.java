import java.util.Scanner;
// i only iterate up to sqrt n instead of n/2 bc of huge input size and that's how it shouldve been in the first place tbh 
public class perfect {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            long num = scanner.nextLong();
            sumDivisors(num);
        }
        scanner.close();
    }

    public static void sumDivisors(long num) {
        if (num < 2) return; // check edge case
        long sum = 1;
        long sqrt = (long)Math.sqrt(num);
        for (long i = 2; i <= sqrt; i++) {
            if (num % i == 0) {
                sum += i;
                if (i != num / i) { // dont add sqrt twice
                    sum += num / i;
                }
            }
        }
        if(sum == num) System.out.println("perfect");
        else if(sum < num) System.out.println("defective");
        else System.out.println("abundant");
    }
}

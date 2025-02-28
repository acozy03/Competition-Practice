import java.util.*;

public class dot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int cases = scanner.nextInt();

        while (cases-- > 0) {
            int size = scanner.nextInt();
            int n = scanner.nextInt();
            List<Integer> dots = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                dots.add(scanner.nextInt());
            }

            System.out.println(findMinEats(size, dots));
        }

        scanner.close();
    }

    private static int findMinEats(int size, List<Integer> dots) {
        Collections.sort(dots); // Sort dots in ascending order

        PriorityQueue<Integer> availableDots = new PriorityQueue<>(Collections.reverseOrder());
        int eats = 0, i = 0;
        int maxDot = dots.isEmpty() ? size : dots.get(dots.size() - 1);

        if (size > maxDot) {
            return 0; // Already the largest
        }

        while (true) {
            // Consume all available dots that are currently eatable
            while (i < dots.size() && dots.get(i) < size) {
                availableDots.add(dots.get(i));
                i++;
            }

            // If we can't eat any dots, return -1
            if (availableDots.isEmpty()) {
                return -1;
            }

            // Eat the largest available dot to maximize growth
            size += availableDots.poll();
            eats++;

            // If we become the largest, return the count
            if (size > maxDot) {
                return eats;
            }
        }
    }
}

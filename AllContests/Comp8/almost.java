package AllContests.Comp8;
import java.util.*;

public class almost {
    // backtracking and perm problem 
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numCases = scanner.nextInt(); 

        for (int i = 0; i < numCases; i++) {
            int d = scanner.nextInt(); 
            int[] magicSquare = new int[9]; 
            for (int j = 0; j < 9; j++) {
                magicSquare[j] = scanner.nextInt();
            }
            // arraylist of arraylist bc im psycho and love 2d arrays, hear me out 
            // each permutation represents a possible 3x3 square
            // instead of modifying the original array each time, we store each permutation as a separate list
            ArrayList<ArrayList<Integer>> permutations = new ArrayList<>();
            permute(magicSquare, 0, permutations);
            int res = 0;
            for (ArrayList<Integer> perm : permutations) {
                if (isAlmostMagic(perm, d)) {
                    res++;
                }
        }
            System.out.println(res); 
        }
        scanner.close();
    }

    private static void permute(int[] magicSquare, int start, ArrayList<ArrayList<Integer>> res) {
        if (start == magicSquare.length) {
            // if we placed all numbers, store the perm
            ArrayList<Integer> perm = new ArrayList<>();
            for (int num : magicSquare) {
                perm.add(num);
            }
            res.add(perm);
            return;
        }
        for (int i = start; i < magicSquare.length; i++) {
            // swap each number with every other number (including itself)
            swap(magicSquare, start, i);
            permute(magicSquare, start + 1, res); // recurse
            swap(magicSquare, start, i); // backtrack which gets back original order 
        }
    }

    private static boolean isAlmostMagic(ArrayList<Integer> magicSquare, int d) {
        int[] rowSums = new int[3];
        int[] colSums = new int[3];

        // these operations took way too long to code out in my head but im very proud of myself
        for (int row = 0; row < 3; row++) {
            rowSums[row] = magicSquare.get(row * 3) + magicSquare.get(row * 3 + 1) + magicSquare.get(row * 3 + 2);
        }
        for (int col = 0; col < 3; col++) {
            colSums[col] = magicSquare.get(col) + magicSquare.get(col + 3) + magicSquare.get(col + 6);
         }

        int diag1 = magicSquare.get(0) + magicSquare.get(4) + magicSquare.get(8);
        int diag2 = magicSquare.get(2) + magicSquare.get(4) + magicSquare.get(6);

        // store all sums to make it easier
        ArrayList<Integer> allSums = new ArrayList<>();
        for (int sum : rowSums) 
        {
            allSums.add(sum);
        }
        for (int sum : colSums) 
        {
            allSums.add(sum);
        }

        allSums.add(diag1);
        allSums.add(diag2);

        int maxSum = Collections.max(allSums);
        int minSum = Collections.min(allSums);

        if(maxSum-minSum <= d) return true; // WE GOT THE FREAKING SQUARE
        else return false;
    }

    private static void swap(int[] magicSquare, int i, int j) {
        int temp = magicSquare[i];
        magicSquare[i] = magicSquare[j];
        magicSquare[j] = temp;
    }
}

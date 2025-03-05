package AllContests.Comp8;

import java.util.*;

public class almost {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numCases = scanner.nextInt();
        for (int i = 0; i < numCases; i++) {
            // d being the value to work towards
            // if max row, col, or diag sum - min row, col, diag is <= d, we have a match 
            int d = scanner.nextInt();
            int[] square = new int[9]; 
            // store the numbers for square 
            for (int j = 0; j < 9; j++) {
                square[j] = scanner.nextInt();
            }
            // stuff for print perms 
            int[] perm = new int[9]; 
            boolean[] used = new boolean[9]; 
             // we take our square and fill in every perm, check if almostmagic for every perm and if it is, add it to res
            int res = printperms(square, perm, used, 0, d);
            System.out.println(res);
        }
        scanner.close();
    }

    public static int printperms(int[] square, int[] perm, boolean[] used, int k, int d) {
        if (k == 9) {
            // filled the square, now check if its valid 
            return isAlmostMagic(perm, d); 
        }

        int res = 0;
        for (int i = 0; i < 9; i++) {
            if (!used[i]) {
                used[i] = true;
                perm[k] = square[i]; 
                res += printperms(square, perm, used, k + 1, d); 
                used[i] = false; 
            }
        }
        return res;
    }

    private static int isAlmostMagic(int[] perm, int d) {
        // this is how im vizualizing the square, it makes it easier to calculate the sums 
        int[][] square = {
            {perm[0], perm[1], perm[2]},
            {perm[3], perm[4], perm[5]},
            {perm[6], perm[7], perm[8]}
        };
        // there are 3 row sums, 3 column sums and 2 diagonals
        // i need an array of 8 that holds all the sums, 0-2 will hold row, 3-5 hold column, 5-7 hold diagonal 
        int[] sums = new int[8];
        // rows
        for (int i = 0; i < 3; i++) {
            sums[i] = square[i][0] + square[i][1] + square[i][2]; 
        }
        // columns
        int columnIdx = 3; 
        for (int i = 0; i < 3; i++) {
            sums[columnIdx] = square[0][i] + square[1][i] + square[2][i]; 
            columnIdx++; 
        }
        // diagonals 
        sums[6] = square[0][0] + square[1][1] + square[2][2]; 
        sums[7] = square[0][2] + square[1][1] + square[2][0]; 
        
        int minSum = sums[0], maxSum = sums[0];
        for (int i = 1; i < 8; i++) {
            if (sums[i] < minSum) minSum = sums[i];
            if (sums[i] > maxSum) maxSum = sums[i];
        }

        // if max row, col, or diag sum - min row, col, diag is <= d, we have a match
        if(maxSum - minSum <= d) return 1; 
        else return 0; 
    }
}

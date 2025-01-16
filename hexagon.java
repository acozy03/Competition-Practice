import java.util.Scanner;

public class hexagon {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numTestCases = Integer.parseInt(scanner.nextLine());
        int[][] res = new int[numTestCases][7];
        boolean[] temp = new boolean[numTestCases];

        // full next line is going to be 42 integers, storing double array for input, 7
        // arrays for 6 space, easy to print result since i have the indexes and their
        // groups inherently

        // loop for each test case
        for (int i = 0; i < numTestCases; i++) {
            temp[i] = false;
            int[][] inputArr = new int[7][6];
            int totalInputs = 0;

            // get input for each row
            for (int j = 0; j < 7; j++) {
                for (int k = 0; k < 6; k++) {
                    if (scanner.hasNextInt()) {
                        inputArr[j][k] = scanner.nextInt();
                        totalInputs++;
                    } else {
                        return;
                    }
                }
            }

            if (totalInputs != 42) {
                return;
            }

            // empty stuff to use for solving, straightfoward using hints to set up
            int[] perm = new int[7];
            boolean[] used = new boolean[7];
            int[] tempRes = checkPerms(inputArr, perm, used, 0);

            if (tempRes == null) {
                temp[i] = true;
            } else {
                for (int j = 0; j < 7; j++) {
                    res[i][j] = tempRes[j];
                }
            }
        }

        // solution
        for (int i = 0; i < numTestCases; i++) {
            System.out.print("Case " + (i + 1) + ":");
            if (temp[i]) {
                System.out.println(" No solution");
            } else {
                for (int j = 0; j < 7; j++) {
                    System.out.print(" " + res[i][j]);
                }
                System.out.println();
            }
        }
        scanner.close();
    }

    // performs a single rotation and returns a new rotated array
    public static int[] rotateTile(int[] tile, int position, int number) {
        int[] rotatedTile = new int[6];
        for (int i = 0; i < 6; i++) {
            rotatedTile[i] = tile[i];
        }

        // find where the number is located
        while (rotatedTile[position] != number) {
            int first = rotatedTile[0];
            // create rotated array based on the shift
            for (int j = 0; j < 5; j++) {
                rotatedTile[j] = rotatedTile[j + 1];
            }
            rotatedTile[5] = first;
        }
        return rotatedTile;
    }

    // try all 7! perms
    public static int[] checkPerms(int[][] inputArr, int[] perm, boolean[] used, int k) {
        // base case
        if (k == 7) {
            if (evalSinglePerm(inputArr, perm)) {
                int[] result = new int[7];
                for (int i = 0; i < 7; i++) {
                    result[i] = perm[i];
                }
                return result;
            }
            return null;
        }

        // basic permutation code storing potential result in array if found
        for (int i = 0; i < 7; i++) {
            if (!used[i]) {
                used[i] = true;
                perm[k] = i;
                int[] possibleSol = checkPerms(inputArr, perm, used, k + 1);
                if (possibleSol != null) {
                    return possibleSol;
                }
                used[i] = false;
            }
        }
        return null;
    }

    public static boolean evalSinglePerm(int[][] inputArr, int[] perm) {

        // create a new 2d array and fill it with inputArr rows, but the content of the
        // rows is from the order that is specified in perm for this iteration (dont
        // change original input)

        int[][] currentIteration = new int[7][6];

        // reorder the tiles according to the current permutation
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                currentIteration[i][j] = inputArr[perm[i]][j];
            }
        }

        currentIteration[0] = rotateTile(currentIteration[0], 0, 1);

        // rotate the other tiles to align their sides with the previous tile's matching
        // sides
        for (int i = 1; i < 7; i++) {
            int sideToRotate = (i + 2) % 6;
            int sideValue = currentIteration[0][(i + 5) % 6];
            currentIteration[i] = rotateTile(currentIteration[i], sideToRotate, sideValue);
        }

        // check if all adjacent tiles have matching sides
        for (int i = 1; i < 7; i++) {
            int nextIndex;
            if (i == 6) {
                nextIndex = 1;
            } else {
                nextIndex = i + 1;
            }

            int currentSide = (i + 1) % 6;
            int nextSide = (i + 4) % 6;

            if (currentIteration[i][currentSide] != currentIteration[nextIndex][nextSide]) {
                return false;
            }
        }

        // if we made it this far, it's a valid sol!
        return true;
    }
}

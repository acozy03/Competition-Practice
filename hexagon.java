import java.util.Scanner;

public class hexagon {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in); 
        try {
            int numTestCases = Integer.parseInt(scanner.nextLine()); 
            int res[][] = new int[numTestCases][6]; 
        
        // full next line is going to be 42 integers, storing double array for input, 7 arrays for 6 space, easy to print result since i have the indexes and their groups inherently
        int inputArr[][] = new int[7][6]; 
        int cnt = 1; 
        boolean temp[] = new boolean[numTestCases]; 
        
        // loop for each test case 
        for (int i = 0; i < numTestCases; i++) {
            temp[i] = false; 
            int totalInputs = 0;
            
            // get input for each row
            for (int j = 0; j < 7; j++) {
				 // get the 6 integers for respective spot 
                for (int k = 0; k < 6; k++) {
                    if (scanner.hasNextInt()) {
                        inputArr[j][k] = Integer.parseInt(scanner.next()); 
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
            
            if (tempRes == null) temp[i] = true; 
            else res[i] = tempRes; 
        }

		// solution
        for (int i = 0; i < numTestCases; i++) {
			// no solution 
            if (temp[i]) {
                System.out.print("Case " + cnt + ": No solution");
            } else {
                System.out.print("Case " + cnt + ":");
                for (int j = 0; j < 7; j++) {
                    System.out.print(" " + res[i][j]); 
                }
            }
            if (i < numTestCases - 1) System.out.println(); 
			// this cnt is purely just to print the case num we on 
            cnt++; 
        }
        } finally {
            scanner.close(); 
        }
    }

    // performs a single rotation and returns a new rotated array
    public static int[] rotateTile(int[] tile, int position, int number) {
        int[] rotatedTile = new int[6];
        int shift = 0;

        // find where the number is located
        while (tile[position] != number) {
            shift++;
            position = (position + 1) % 6; 
        }

        // create rotated array based on the shift
        for (int i = 0; i < 6; i++) {
            int newIndex = (i + shift) % 6;
            rotatedTile[i] = tile[newIndex];
        }

        return rotatedTile;
    }

    // try all 7! perms
    public static int[] checkPerms(int inputArr[][], int perm[], boolean[] used, int k) {
        // base case 
        boolean baseCase = false;
        if (k == 7) {
            baseCase = evalSinglePerm(inputArr, perm);
            if (baseCase) return perm;
            else return null;
        }

        // basic permutation code storing potential result in array if found 
        for (int i = 0; i < 7; i++) {
            if (!used[i]) {
                used[i] = true;
                perm[k] = i;
                int[] possibleSol = checkPerms(inputArr, perm, used, k + 1);
                if (possibleSol != null) return possibleSol;
                used[i] = false;
            }
        }

        return null;
    }

    public static boolean evalSinglePerm(int[][] inputArr, int perm[]) {

        // create a new 2d array and fill it with inputArr rows, but the content of the rows is from the order that is specified in perm for this iteration (dont change original input)
        int[][] currentIteration = new int[7][];

        for (int i = 0; i < 7; i++) {
            int rowIndex = perm[i];
            currentIteration[i] = inputArr[rowIndex];
        }

        // for middle tile 
        currentIteration[0] = rotateTile(currentIteration[0], 0, 1);

        // fix the other tiles based on middle, calculate the correct side to rotate based on the index then get the side value from the middle tile to use for comparison, then rotate
        for (int i = 1; i < 7; i++) {
            int sideToRotate = (i + 2) % 6;
            int sideValue = currentIteration[0][i - 1];
            currentIteration[i] = rotateTile(currentIteration[i], sideToRotate, sideValue);
        }

        for (int i = 1; i < 7; i++) {

            // wrap around after 6 
            int index = i + 1;
            if (index >= 7) index = 1;

            // comparing sides now
            int currentSide = (i + 1) % 6;
            int nextSide = (i + 4) % 6;
            int currentValue = currentIteration[i][currentSide];
            int nextValue = currentIteration[index][nextSide];

            if (currentValue != nextValue) {
                return false;
            }
        }

        return true;
    }
}

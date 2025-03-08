import java.util.*;

public class reflection {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // these are what i'll be outputting 
        int beforeUpdateNumChanges = 0; 
        int afterUpdateNumChanges = 0; 

        // take input 
        int gridSize = scanner.nextInt();
        char[][] grid = new char[gridSize][gridSize];
        int numUpdates = scanner.nextInt();
        scanner.nextLine(); 

        for (int i = 0; i < gridSize; i++) {
            grid[i] = scanner.nextLine().toCharArray();
        }

        beforeUpdateNumChanges = computeMinOperations(gridSize, grid);
        System.out.println(beforeUpdateNumChanges); 

        // reading in every update and flipping the char in the coordinate for it 
        for (int i = 0; i < numUpdates; i++) {
            int r = scanner.nextInt() - 1;
            int c = scanner.nextInt() - 1;
            
            // flip the cell
            if (grid[r][c] == '#') {
                grid[r][c] = '.';
            } else {
                grid[r][c] = '#';
            }            

            // now output afterUpdateNumChanges for each change 
            afterUpdateNumChanges = (computeMinOperations(gridSize, grid));
            System.out.println(afterUpdateNumChanges);
        }

        scanner.close();
    }

    static int computeMinOperations(int gridSize, char[][] grid) {
        // this function will go through every point (UPDATE : QUADRANT***) in the grid, computing the min number of changes and returning it to print 
        // UPDATE : it wasn't fast enough, I did not realize this until i had to divide every answer by 4, but just doing one quadrant is enough to satisfy reflection condition since it translates to everything else
        // just gotta adjust this loop 
        int minChanges = 0;
        for (int r = 0; r < gridSize; r++) {
            for (int c = 0; c < gridSize; c++) {
                minChanges += numMinChanges(r, c, gridSize, grid);
            }
        }
        // every answer returns 4x what it should be, this is because I loop through everything except just a single quadrant, which I am not sure how to do (hopefully this is fast enough)
        return minChanges/4;
    }

    static int numMinChanges(int r, int c, int gridSize, char[][] grid) {
        // i am going to get all 4 reflection points from the starting (r,c), and then keep 2 counters, one to check how many changes it would take to make them all #, and the other for .
        // take the min of each and whichever is smaller, thats the best route we got for this specific point and its reflections 

        // these calcs from my notes will make it easier to get the characters at those mirror coordinates 
        int topRightRow = r;
        int topRightCol = c;                     
        int topLeftRow = r;
        int topLeftCol = gridSize - c - 1;               
        int bottomLeftRow = gridSize - r - 1;
        int bottomLeftCol = gridSize - c - 1;  
        int bottomRightRow = gridSize - r - 1;
        int bottomRightCol = c;        
    
        // getting characters using pre-calculated coordinate formula for all 4 reflection points (step 1)
        char topRight = grid[topRightRow][topRightCol];
        char topLeft = grid[topLeftRow][topLeftCol];
        char bottomLeft = grid[bottomLeftRow][bottomLeftCol];
        char bottomRight = grid[bottomRightRow][bottomRightCol];
    
        // two counters to keep track of which is the min (step 2)    
        int changesToHash = 0;
        int changesToDot = 0;

        // count changes needed if we make all #
        if (topRight != '#') changesToHash++;
        if (topLeft != '#') changesToHash++;
        if (bottomLeft != '#') changesToHash++;
        if (bottomRight != '#') changesToHash++;
    
        // count changes needed if we make all .
        if (topRight != '.') changesToDot++;
        if (topLeft != '.') changesToDot++;
        if (bottomLeft != '.') changesToDot++;
        if (bottomRight != '.') changesToDot++;
    
        // whichever is less is best case scenario so that's our min for this point (step 3) 
        return Math.min(changesToHash, changesToDot);
    }
    
    
}

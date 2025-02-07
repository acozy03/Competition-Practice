import java.util.*;

class PuzzleState implements Comparable<PuzzleState> {
    int[] board;
    int numMoves; 
    int distanceEstimate; 
    int zeroIndex;
    PuzzleState parent;

    public PuzzleState(int[] board, int numMoves, PuzzleState parent) {
        this.board = board.clone();
        this.numMoves = numMoves;
        this.parent = parent;
        this.zeroIndex = findZero(board);
        this.distanceEstimate = distanceNumIsFromWinningPos(board);
    }

    private int findZero(int[] board) {
        for (int i = 0; i < board.length; i++) {
            if (board[i] == 0) return i;
        }
        return -1;
    }

    private int distanceNumIsFromWinningPos(int[] board) {
        int distance = 0;
        for (int i = 0; i < 9; i++) {
            if (board[i] == 0) continue;
            int targetRow = (board[i] - 1) / 3;
            int targetCol = (board[i] - 1) % 3;
            int currentRow = i / 3;
            int currentCol = i % 3;
            distance += Math.abs(targetRow - currentRow) + Math.abs(targetCol - currentCol);
        }
        return distance;
    }

    public int getTotalCost() {
        return numMoves + distanceEstimate;
    }

    @Override
    public int compareTo(PuzzleState other) {
        return Integer.compare(this.getTotalCost(), other.getTotalCost());
    }

    public boolean isGoal() {
        return Arrays.equals(board, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 0});
    }

    public List<PuzzleState> getNeighbors() {
        List<PuzzleState> neighbors = new ArrayList<>();
        int[] directions = {-3, 3, -1, 1}; // Up, Down, Left, Right
        for (int dir : directions) {
            int newZeroIndex = zeroIndex + dir;
            if (isValidMove(zeroIndex, newZeroIndex)) {
                int[] newBoard = board.clone();
                newBoard[zeroIndex] = newBoard[newZeroIndex];
                newBoard[newZeroIndex] = 0;
                neighbors.add(new PuzzleState(newBoard, numMoves + 1, this));
            }
        }
        return neighbors;
    }

    private boolean isValidMove(int zeroIndex, int newZeroIndex) {
        if (newZeroIndex < 0 || newZeroIndex >= 9) return false;
        if (zeroIndex % 3 == 0 && newZeroIndex % 3 == 2) return false;
        if (zeroIndex % 3 == 2 && newZeroIndex % 3 == 0) return false;
        return true;
    }

    public static int hash(int[] board) {
        return Arrays.hashCode(board);
    }
}

public class puzzle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numCases = scanner.nextInt();
        for (int i = 0; i < numCases; i++) {
            int[] board = new int[9];
            for (int j = 0; j < 9; j++) {
                board[j] = scanner.nextInt();
            }
            System.out.println(solve(board));
        }
    }

    public static int solve(int[] startBoard) {
        PriorityQueue<PuzzleState> openSet = new PriorityQueue<>();
        HashMap<Integer, Integer> costMap = new HashMap<>();
        
        PuzzleState startState = new PuzzleState(startBoard, 0, null);
        openSet.add(startState);
        costMap.put(PuzzleState.hash(startBoard), 0);
        
        while (!openSet.isEmpty()) {
            PuzzleState current = openSet.poll();
            
            if (current.isGoal()) {
                return current.numMoves;
            }
            
            for (PuzzleState neighbor : current.getNeighbors()) {
                int boardHash = PuzzleState.hash(neighbor.board);
                if (!costMap.containsKey(boardHash) || neighbor.numMoves < costMap.get(boardHash)) {
                    costMap.put(boardHash, neighbor.numMoves);
                    openSet.add(neighbor);
                }
            }
        }
        return -1; 
    }
}

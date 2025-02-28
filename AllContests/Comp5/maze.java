package AllContests.Comp5;
import java.util.*;

public class maze {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 
        int n = Integer.parseInt(scanner.nextLine()); 
        
        for (int i = 0; i < n; i++) {
            int r = scanner.nextInt(); 
            int c = scanner.nextInt(); 
            int startX = -1, startY = -1; 
            scanner.nextLine();
            
            char[][] maze = new char[r][c]; 
            for (int j = 0; j < r; j++) {
                char[] temp = scanner.nextLine().toCharArray();
                for (int k = 0; k < c; k++) {
                    maze[j][k] = temp[k];
                    if (maze[j][k] == 'S') {
                        startX = j;
                        startY = k;
                    }
                }
            }

            System.out.println(mazeLogic(maze, startX, startY));
        }
        scanner.close();
    }

    public static int mazeLogic(char[][] maze, int startX, int startY) {
        int rows = maze.length;
        int cols = maze[0].length;

        if (startX == 0 || startY == 0 || startX == rows - 1 || startY == cols - 1) {
            return 0;
        }
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startX, startY, 0});
        
        boolean[][] visited = new boolean[rows][cols];
        visited[startX][startY] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0], y = current[1], steps = current[2];

            if (x == 0 || y == 0 || x == rows - 1 || y == cols - 1) {
                return steps;
            }

            for (int[] dir : directions) {
                int newX = x + dir[0];
                int newY = y + dir[1];

                if (newX >= 0 && newX < rows && newY >= 0 && newY < cols 
                    && maze[newX][newY] != 'X' && !visited[newX][newY]) {
                    visited[newX][newY] = true;
                    queue.offer(new int[]{newX, newY, steps + 1});
                }
            }
        }

        return -1;
    }
}

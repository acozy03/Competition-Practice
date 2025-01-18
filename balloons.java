import java.util.*;

public class balloons {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int firstLine[] = new int[3];
            for (int cnt = 0; cnt < 3; cnt++) {
                firstLine[cnt] = scanner.nextInt();
            }

            if (firstLine[0] == 0 && firstLine[1] == 0 && firstLine[2] == 0) {
                break;
            }

            int numTeams = firstLine[0];
            int balloonsinA = firstLine[1];
            int balloonsinB = firstLine[2];

            int inputArr[][] = new int[numTeams][4];

            for (int i = 0; i < numTeams; i++) {
                for (int j = 0; j < 3; j++) {
                    inputArr[i][j] = scanner.nextInt();
                }

                inputArr[i][3] = Math.abs(inputArr[i][1] - inputArr[i][2]);
            }

            Arrays.sort(inputArr, (a, b) -> Integer.compare(b[3], a[3]));

            if (numTeams <= 1000 && numTeams >= 1 && balloonsinA <= 10000 && balloonsinA >= 0 && balloonsinB <= 10000
                    && balloonsinB >= 0) {
                int res = distanceLogic(inputArr, balloonsinA, balloonsinB);
                System.out.println(res);
            }
        }
        scanner.close();
    }

    public static int distanceLogic(int[][] teams, int balloonsA, int balloonsB) {
        int totalCost = 0;

        for (int[] team : teams) {
            int balloonsNeeded = team[0];
            int costA = team[1];
            int costB = team[2];

            if (costA <= costB) {
                int usedA = Math.min(balloonsNeeded, balloonsA);
                int usedB = balloonsNeeded - usedA;

                balloonsA -= usedA;
                balloonsB -= usedB;

                totalCost += usedA * costA + usedB * costB;
            } else {
                int usedB = Math.min(balloonsNeeded, balloonsB);
                int usedA = balloonsNeeded - usedB;

                balloonsB -= usedB;
                balloonsA -= usedA;

                totalCost += usedA * costA + usedB * costB;
            }
        }
        return totalCost;
    }
}

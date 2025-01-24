import java.util.Scanner;

public class base {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 1; i <= n; i++) {
            String fullInputLine = scanner.nextLine();
            String[] splitLine = fullInputLine.split(" ");
            int dataSetNum = Integer.parseInt(splitLine[0]);
            if (dataSetNum != i) {
                break;
            }
            String inputAsString = splitLine[1];
            int octalVal = 0; 
            for (char c : inputAsString.toCharArray()) {
                if (c >= '0' && c <= '7') {
                    octalVal = (octalVal << 3) | (c - '0');
                } 
                else {
                    octalVal = 0;
                    break;
                }
            }
            int decimalVal = Integer.parseInt(inputAsString, 10);
            int hexiVal = Integer.parseInt(inputAsString, 16);
            System.out.println(dataSetNum + " " + octalVal + " " + decimalVal + " " + hexiVal);
        }

        scanner.close();
    }
}
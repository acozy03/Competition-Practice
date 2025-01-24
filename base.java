import java.util.*;
public class base {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        for(int i = 0; i < n; i++) {
            int inputArr[] = new int[7];
            int dataSetNum = scanner.nextInt();
            System.out.println(dataSetNum);
            for (int j = 0; j < 7; j++) {
                inputArr[j] = scanner.nextInt();
            } 
            System.out.println(dataSetNum + " " + octal(inputArr) + " " + decimal(inputArr) + " " + hexideciaml(inputArr));
            scanner.close();
        }
    }

    public static int octal(int inputArr[]) {
        return 0;
    }

    
    public static int decimal(int inputArr[]) {
        return 0;
    }

    
    public static int hexideciaml(int inputArr[]) {
        return 0;
    }
}

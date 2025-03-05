package AllContests.Comp8;


import java.util.*;
public class carry {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numCases = scanner.nextInt();
        for(int i = 0; i < numCases; i++) {
            int num1 = scanner.nextInt();
            int num2 = scanner.nextInt();
            int num3 = scanner.nextInt(); 
            if(((num1*2) + num2) < ((num1*2)+num3)) System.out.println(1);
            else if(((num1*2) + num2) > ((num1*2)+num3)) System.out.println(2);
            else System.out.println(3);
        } 
        scanner.close();
    }
}

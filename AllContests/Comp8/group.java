package AllContests.Comp8;
import java.util.*;

public class group {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numCases = scanner.nextInt();
        
        for (int i = 0; i < numCases; i++) {
            int numStudents = scanner.nextInt();
            int numQueries = scanner.nextInt();
            Map<String, Integer> studentGroupMap = new HashMap<>();
            for (int j = 0; j < numStudents; j++) {
                String studentName = scanner.next();
                int group = scanner.nextInt();
                studentGroupMap.put(studentName, group); 
            }
            for (int j = 0; j < numQueries; j++) {
                String studentToSearch = scanner.next();
                System.out.println(studentGroupMap.getOrDefault(studentToSearch, -1));
            }
        }
        
        scanner.close();
    }
}

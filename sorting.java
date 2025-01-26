import java.util.*;
public class sorting {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numCases = Integer.parseInt(scanner.nextLine()); 

        for (int i = 0; i < numCases; i++) {
            // take in # on next line 
            int numPapers = Integer.parseInt(scanner.nextLine());
            PriorityQueue<Integer> papers = new PriorityQueue<>(); 
            for (int j = 0; j < numPapers; j++) {
                papers.add(scanner.nextInt());
            }
            scanner.nextLine(); 
            System.out.println(stackingPapers(papers, numPapers));
        }
        scanner.close();
    }

    public static int stackingPapers(PriorityQueue<Integer> papers, int numPapers) {
        if (numPapers == 1)
            return 0; 
        int a = papers.poll(); 
        int b = papers.poll();
        int res = a+b;
        papers.add(res); 
        numPapers--; 
        return res + stackingPapers(papers, numPapers);
    }
}

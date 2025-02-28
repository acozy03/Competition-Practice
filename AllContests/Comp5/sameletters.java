import java.util.*;
public class sameletters {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int cnt = 0; 
        int caseNum = 0; 
        while (cnt != 2) {
        caseNum++; 
        String[] input = new String[2]; 
            for (int i = 0; i < 2; i++){
                String temp = scanner.nextLine();  
                if(temp.equals("END")) {
                    cnt++;
                    continue; 
                }
                else {
                    input[i] = temp;
                }
        }
        if (cnt == 2) {
            break; 
        }
        System.out.println(sameLettersLogic(input, caseNum)); 
    }
    scanner.close(); 

}

public static String sameLettersLogic(String[] input, int caseNum) {

    if(input[0].length() != input[1].length()) return "Case " + caseNum +": different"; 
    
    char []tempArray1 = input[0].toCharArray();
    Arrays.sort(tempArray1);

    char []tempArray2 = input[1].toCharArray();
    Arrays.sort(tempArray2);
    

    for(int i = 0; i < input[0].length(); i++) {
        if(tempArray1[i] != tempArray2[i]) {
            return "Case " + caseNum +": different"; 
        } 
    }
    
    return "Case " + caseNum +": same"; 
}
}

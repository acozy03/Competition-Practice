package AllContests.USACO1;
import java.util.*;

public class mex {
    // so in order to get the numChanges, we need to remove all copies of i in the list and also add all the values below it that dont exist 
    public static void main(String[] args) {
        // stuff i need 
        Scanner scanner = new Scanner(System.in);
        int numInts = scanner.nextInt();
        int[] inputArr = new int[numInts];

        // reading line 
        for (int i = 0; i < numInts; i++) {
            inputArr[i] = scanner.nextInt();
        }
        
        // basic setup of freq array 
        int[] freq = new int[numInts + 1];
        for (int num : inputArr) {
            if (num <= numInts) {
                freq[num]++;
            }
        }
        // we have the frequency of each number, now we have to go through each number checking how many numbers beneath it aren't present 
        // for example of 2 2 2 0 input, to make 2 the mex, we check 1's frequency, since it's 0, we have to take from somebody that's bigger which would be 2 here
        for (int i = 0; i <= numInts; i++) {
            // copy frequency array to avoid modifying the original
            int[] tempFreq = Arrays.copyOf(freq, freq.length);
            
            int operations = 0;
            
             // for every number below the one we are starting at N that has 0 frequency, we have to find somebody to "take" from and decrease their frequency
            for (int j = 0; j < i; j++) {
                if (tempFreq[j] == 0) {
                    // find a number >= i with frequency > 0 to "take" from
                    boolean found = false;
                    for (int k = i; k <= numInts; k++) {
                        if (tempFreq[k] > 0) {
                            tempFreq[k]--;
                            tempFreq[j]++;
                            operations++;
                            found = true;
                            break;
                        }
                    }
                    
                    // if no number >= i is found, change any arbitrary number to j, in the case of 3 and 4 in the test input 
                    if (!found) {
                        operations++; 
                    }
                }
            }
            
            // ensure that the mex is still not present, this works for cases like repeated 2 where you cant borrow more than once 
            operations += tempFreq[i];
            System.out.println(operations);
        }
        
        scanner.close();
    }
}
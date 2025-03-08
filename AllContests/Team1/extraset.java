package AllContests.Team1;
import java.util.*;
// i didnt think hashset would be required but i hope the fast lookup makes a difference compared to double array
public class extraset {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numGames = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numGames; i++) {
            int numAttributes = scanner.nextInt();
            int numCards = scanner.nextInt();
            scanner.nextLine();
            int[][] cards = new int[numCards][numAttributes]; 

            // for each card, store the attributes 
            for (int card = 0; card < numCards; card++) {
                for (int attribute = 0; attribute < numAttributes; attribute++) {
                    cards[card][attribute] = scanner.nextInt(); 
                }   
            }
            // each triple was discovered 3 times 
            System.out.println(countValidSets(cards, numCards, numAttributes)/3);
        }
        scanner.close();
    }

    public static int countValidSets(int[][] cards, int numCards, int numAttributes) {
        int validCombos = 0;
        
        // convert cards to hashset, essentially doing the same thing as earlier read
        // store each card as an arraylist, just add each attribute by .add(cards[card][attribute])
        // add card with .add(cardList)
        HashSet<ArrayList<Integer>> cardSet = new HashSet<>();
        for (int card = 0; card < numCards; card++) {
            ArrayList<Integer> cardList = new ArrayList<>();
            for (int attribute = 0; attribute < numAttributes; attribute++) {
                cardList.add(cards[card][attribute]);
            }
            cardSet.add(cardList); 
        }
        // check every combo of both cards, where j = i+1 or else it checks dupes 
        for (int i = 0; i < numCards; i++) {
            for (int j = i + 1; j < numCards; j++) {
                ArrayList<Integer> thirdCard =  new ArrayList<>();
                
                // third card can be found using i and j 
                for (int attr = 0; attr < numAttributes; attr++) {
                    int iAttribute = cards[i][attr];
                    int jAttribute = cards[j][attr];

                    // either they are all the same 
                    if (iAttribute == jAttribute) {
                        thirdCard.add(iAttribute);
                    } 
                    // or they are all different
                    else if (iAttribute != jAttribute){
                        thirdCard.add(Math.abs((iAttribute + jAttribute)- 3)); // iAtt + jAtt + kAtt = 3, solve for kAtt
                    }
                }

                // Check if thirdCardList exists in cardSet
                if (cardSet.contains(thirdCard)) {
                    validCombos++;
                }
            }
        }
        return validCombos;
    }
}

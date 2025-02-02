import java.util.*;
import java.io.*;

class TrieNode {
    Map<Character, TrieNode> children;
    int count;
    Set<Integer> indexes;  // Stores the indexes at which words were inserted

    TrieNode() {
        children = new HashMap<>();
        count = 0;
        indexes = new HashSet<>();
    }
}

class Trie {
    TrieNode root;

    Trie() {
        root = new TrieNode();
    }

    void insert(String word, int index) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            node.children.putIfAbsent(c, new TrieNode());
            node = node.children.get(c);
            node.count++;
            node.indexes.add(index);
            
          
        }
      

    }
    int countPrefix(String prefix, int maxIndex) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            if (!node.children.containsKey(c)) {
                return 0;
            }
            node = node.children.get(c);
        }
    
        // For reversed strings, we only want to count unique strings up to maxIndex
        Set<String> uniqueStrings = new HashSet<>();
        int lastValidIndex = -1;
        
        // Sort indexes to process them in order
        List<Integer> sortedIndexes = new ArrayList<>(node.indexes);
        Collections.sort(sortedIndexes);
        
        for (int idx : sortedIndexes) {
            if (idx <= maxIndex) {
                // If we haven't seen a valid index yet, or this is a new index
                if (lastValidIndex == -1 || idx > lastValidIndex) {
                    lastValidIndex = idx;
                    uniqueStrings.add(prefix); // Using prefix as a marker for unique occurrence
                }
            }
        }
        
        return uniqueStrings.size();
    }
}

public class lottery {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int t = Integer.parseInt(br.readLine());
        for (int i = 1; i <= t; i++) {
            out.println("Lottery #" + i + ":");
            processLottery(br, out);
            out.println();
        }

        out.flush();
        out.close();
        br.close();
    }

    private static void processLottery(BufferedReader br, PrintWriter out) throws IOException {
        Trie normalTrie = new Trie();
        Trie reverseTrie = new Trie();
        int currentIndex = 0;  // Index counter
        int maxSearchIndex = 0;  // Highest index that should be considered for searches
        boolean isReversed = false;
        int totalStringLength = 0;  // Track total string length

        int q = Integer.parseInt(br.readLine());

        // Constraint 1: Ensure 1 ≤ q ≤ 100000
        if (q < 1 || q > 100000) {
            return;
        }

        for (int i = 0; i < q; i++) {
            String line = br.readLine();
            String[] query = line.split(" ", 2);

            // Constraint 2: Ensure the query starts with 1, 2, or 3
            if (query.length == 0 || (!query[0].equals("1") && !query[0].equals("2") && !query[0].equals("3"))) {
                return; 
            }

            int type = Integer.parseInt(query[0]);

            switch (type) {
                case 1: // Insert operation
                    if (query.length < 2) {
                        return;
                    }

                    String name = query[1];

                    // Constraint 3: Ensure each string is at most 100000 characters long
                    if (name.length() > 100000) {
                        return; 
                    }

                    // Constraint 4: Ensure the sum of all strings is at most 600000
                    totalStringLength += name.length();
                    if (totalStringLength > 600000) {
                        return; 
                    }

                    normalTrie.insert(name, currentIndex);
                    reverseTrie.insert(new StringBuilder(name).reverse().toString(), currentIndex);
                    currentIndex++;  // Increment index after insertion
                    break;

                case 2: // Count prefix operation
                    if (query.length < 2) {
                        throw new IllegalArgumentException("Error: Count query (2) must be followed by a prefix.");
                    }

                    String prefix = query[1];

                    // Constraint 3 (applies to prefix as well): Ensure each prefix is at most 100000 characters long
                    if (prefix.length() > 100000) {
                        throw new IllegalArgumentException("Error: A prefix cannot exceed 100000 characters.");
                    }

                    if (isReversed) {
                        out.println(reverseTrie.countPrefix(new StringBuilder(prefix).toString(), maxSearchIndex));
                    } else {
                        out.println(normalTrie.countPrefix(prefix, currentIndex - 1));
                    }
                    break;

                case 3: // Reverse operation
                    isReversed = !isReversed;
                    maxSearchIndex = currentIndex - 1;  // Update max index for searches
                    break;
            }
        }
    }
}
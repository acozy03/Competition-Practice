import java.util.*;

public class polling {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        HashMap<String, Integer> voteMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String name = scanner.nextLine().trim();

            if (voteMap.containsKey(name)) {
                voteMap.put(name, voteMap.get(name) + 1);
            } else {
                voteMap.put(name, 1);
            }
        }

        int maxVotes = -1;
        List<String> winners = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : voteMap.entrySet()) {
            int votes = entry.getValue();

            if (votes > maxVotes) {
                maxVotes = votes;
                winners.clear();
                winners.add(entry.getKey());
            } else if (votes == maxVotes) {
                winners.add(entry.getKey());
            }
        }

        Collections.sort(winners);

        for (String winner : winners) {
            System.out.println(winner);
        }

        scanner.close();
    }
}

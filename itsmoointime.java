import java.util.*;

public class itsmoointime {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : arr) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        Set<String> distinctMoos = new HashSet<>();

        Map<Integer, Integer> firstValues = new HashMap<>();

        for (int j = 0; j < n; j++) {
            int middleValue = arr[j];
            
            countMap.put(middleValue, countMap.get(middleValue) - 1);
            if (countMap.get(middleValue) == 0) {
                countMap.remove(middleValue);
            }
            for (Map.Entry<Integer, Integer> entry : firstValues.entrySet()) {
                int firstValue = entry.getKey();

                if (firstValue != middleValue && countMap.containsKey(middleValue)) {
                    String moo = firstValue + " " + middleValue + " " + middleValue;
                    distinctMoos.add(moo);
                }
            }
            firstValues.put(middleValue, firstValues.getOrDefault(middleValue, 0) + 1);
        }

        System.out.println(distinctMoos.size());
    }
}

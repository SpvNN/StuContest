package medium;

import java.util.*;

public class HIndex {
    public static int solution(int[] citations) {
        if (citations == null || citations.length == 0) {
            return 0;
        }

        Arrays.sort(citations);
        int n = citations.length;
        int hIndex = 0;

        for (int i = 0; i < n; i++) {
            int smallerCount = Math.min(citations[i], n - i);
            hIndex = Math.max(hIndex, smallerCount);
        }

        return hIndex;
    }

    public static int[] generateRandomArray() {
        int length = (int) (Math.random() * 10) + 1; // Случайная длина массива от 1 до 10
        int[] citations = new int[length];

        for (int i = 0; i < length; i++) {
            citations[i] = (int) (Math.random() * 10); // Случайное число от 0 до 9
        }

        return citations;
    }

    public static Map<int[], Integer> testGeneration(int n) {
        Map<int[], Integer> tests = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int[] citations = generateRandomArray();
            int result = solution(citations);
            tests.put(citations, result);
            System.out.println("Test " + (i + 1) + ":");
            System.out.println("Input: " + Arrays.toString(citations));
            System.out.println("Expected Output: " + result);
            System.out.println();
        }
        return tests;
    }
}

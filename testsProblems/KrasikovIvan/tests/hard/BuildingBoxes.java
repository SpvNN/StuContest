package hard;

import java.util.*;

public class BuildingBoxes {
    public static int solution(int n) {
        int rowCapacity = 1;
        int maxRowCapacity = 1;
        int rowNumber = 0;
        while (n>0) {
            rowNumber++;
            n -= rowCapacity;
            if (rowCapacity == maxRowCapacity) {
                rowCapacity = 1;
                maxRowCapacity++;
            } else
                rowCapacity++;
        }
        return rowNumber;
    }

    public static Map<Integer, Integer> testGeneration(int n) {
        Map<Integer, Integer> tests = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int m = (int) (Math.random() * 100) + 1; // Случайное число от 1 до 100
            int result = solution(m);
            tests.put(m, result);

            System.out.println("Test " + (i + 1) + ":");
            System.out.println("Input: " + m);
            System.out.println("Expected Output: " + result);
            System.out.println();
        }
        return tests;
    }

}

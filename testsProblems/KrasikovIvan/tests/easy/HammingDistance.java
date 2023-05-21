package easy;

import java.util.*;

public class HammingDistance {
    public static int solution(int x, int y) {
        int distance = 0;
        int xor = x ^ y;

        while (xor != 0) {
            if ((xor & 1) == 1) {
                distance++;
            }
            xor >>= 1;
        }

        return distance;
    }

    public static int generateRandomNumber() {
        // Генерация случайного числа от 0 до 1000
        return (int) (Math.random() * 1000);
    }

    public static Map<int[], Integer> testGeneration(int n) {
        Map<int[], Integer> tests = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int x = generateRandomNumber();
            int y = generateRandomNumber();
            int distance = solution(x, y);
            int[] xy = new int[]{x, y};
            tests.put(xy, distance);
            System.out.println("Test " + (i + 1) + ":");
            System.out.println("Input: x = " + x + ", y = " + y);
            System.out.println("Expected Output: " + distance);
            System.out.println();
        }
        return tests;
    }

}

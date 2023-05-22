package medium;

import java.util.*;

public class IntegerReplacement {
    static public int solution(int n) {
        int c = 0;
        while (n != 1) {
            if ((n & 1) == 0) {
                n >>>= 1;
            } else if (n == 3 || Integer.bitCount(n + 1) > Integer.bitCount(n - 1)) {
                --n;
            } else {
                ++n;
            }
            ++c;
        }
        return c;
    }

    public static int generateRandomNumber() {
        return (int) (Math.random() * 1000) + 1; // Случайное число от 1 до 1000
    }

    public static Map<Integer, Integer> testGeneration(int n) {
        Map<Integer, Integer> tests = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int num = generateRandomNumber();
            int result = solution(num);
            tests.put(num, result);
            System.out.println("Test " + (i + 1) + ":");
            System.out.println("Input: " + num);
            System.out.println("Expected Output: " + result);
            System.out.println();
        }
        return tests;
    }
}

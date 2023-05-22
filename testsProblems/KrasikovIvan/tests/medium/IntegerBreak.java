package medium;

import java.util.*;

public class IntegerBreak {
    public static int solution(int n) {
        if (n <= 3) {
            return n - 1;
        }

        int quotient = n / 3;
        int remainder = n % 3;

        if (remainder == 0) {
            return (int) Math.pow(3, quotient);
        } else if (remainder == 1) {
            return (int) Math.pow(3, quotient - 1) * 4;
        } else {
            return (int) Math.pow(3, quotient) * 2;
        }
    }

    public static int generateRandomNumber() {
        return (int) (Math.random() * 100) + 2; // Случайное число от 2 до 101
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

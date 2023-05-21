package medium;

import java.util.*;

public class EliminationGame {
    public static int solution(int n) {
        boolean leftToRight = true;
        int remaining = n;
        int step = 1;
        int head = 1;

        while (remaining > 1) {
            if (leftToRight || remaining % 2 == 1) {
                head += step;
            }

            remaining /= 2;
            step *= 2;
            leftToRight = !leftToRight;
        }

        return head;
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
        return  tests;
    }
}

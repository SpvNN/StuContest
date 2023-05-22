package easy;

import java.util.*;

public class SelfDividingNumbers {
    public static List<Integer> solution(int left, int right) {
        List<Integer> result = new ArrayList<>();

        for (int i = left; i <= right; i++) {
            if (isSelfDividing(i)) {
                result.add(i);
            }
        }

        return result;
    }

    public static boolean isSelfDividing(int num) {
        int n = num;
        while (n > 0) {
            int digit = n % 10;
            if (digit == 0 || num % digit != 0) {
                return false;
            }
            n /= 10;
        }
        return true;
    }

    public static Map<int[], List<Integer>> testGeneration(int n) {
        Map<int[], List<Integer>> tests = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int left = (int) (Math.random() * 1000); // Случайное число от 0 до 999
            int right = left + (int) (Math.random() * (1000 - left + 1)); // Случайное число от left до 1000
            List<Integer> result = solution(left, right);
            int[] lr = new int[]{left, right};
            tests.put(lr, result);

            System.out.println("Test " + (i + 1) + ":");
            System.out.println("Input: left = " + left + ", right = " + right);
            System.out.println("Expected Output: " + result);
            System.out.println();
        }
        return tests;
    }

}

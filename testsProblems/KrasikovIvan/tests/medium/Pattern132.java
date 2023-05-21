package medium;

import java.util.*;

public class Pattern132 {
    public static boolean solution(int[] nums) {
        int n = nums.length;
        Stack<Integer> stack = new Stack<>();
        int third = Integer.MIN_VALUE;

        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] < third) {
                return true;
            }

            while (!stack.isEmpty() && nums[i] > stack.peek()) {
                third = stack.pop();
            }

            stack.push(nums[i]);
        }

        return false;
    }

    public static int[] generateRandomArray() {
        int size = (int) (Math.random() * 10) + 5; // Размер массива от 5 до 14
        int[] nums = new int[size];

        for (int i = 0; i < size; i++) {
            nums[i] = (int) (Math.random() * 100) - 50; // Случайное число от -50 до 49
        }

        return nums;
    }

    public static Map<int[], Boolean> testGeneration(int n) {
        Map<int[], Boolean> tests = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int[] nums = generateRandomArray();
            boolean result = solution(nums);
            tests.put(nums, result);
            System.out.println("Test " + (i + 1) + ":");
            System.out.println("Input: " + Arrays.toString(nums));
            System.out.println("Expected Output: " + result);
            System.out.println();
        }
        return tests;
    }

}

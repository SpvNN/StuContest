package easy;

import java.util.*;

public class FindPivotIndex {
    public static int solution(int[] nums) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        int leftSum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (leftSum == totalSum - leftSum - nums[i]) {
                return i;
            }
            leftSum += nums[i];
        }

        return -1; // Если не найден индекс
    }

    public static int[] generateRandomArray() {
        int length = (int) (Math.random() * 10) + 1; // Длина случайного массива от 1 до 10
        int[] nums = new int[length];

        for (int i = 0; i < length; i++) {
            nums[i] = (int) (Math.random() * 10) - 5; // Случайное число от -5 до 5
        }

        return nums;
    }

    public static Map<int[], Integer> testGeneration(int n) {
        Map<int[], Integer> tests = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int[] nums = generateRandomArray();
            int index = solution(nums);
            tests.put(nums, index);

            System.out.println("Test " + (i + 1) + ":");
            System.out.println("Input: " + Arrays.toString(nums));
            System.out.println("Expected Output: " + index);
            System.out.println();
        }
        return tests;
    }
}

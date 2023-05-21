package hard;

import java.util.*;

public class CountSubWithMedianK {
    public static int solution(int[] nums, int k) {
        Map<Integer, Integer> prefixSumOfBalance = new HashMap<>();
        prefixSumOfBalance.put(0, 1); // Dummy value of 0's frequency is 1.
        int ans = 0, runningBalance = 0;
        boolean found = false;
        for (int num : nums) {
            if (num < k) {
                --runningBalance;
            }else if (num > k) {
                ++runningBalance;
            }else {
                found = true;
            }
            if (found) {
                ans += prefixSumOfBalance.getOrDefault(runningBalance, 0) + prefixSumOfBalance.getOrDefault(runningBalance - 1, 0);
            }else {
                prefixSumOfBalance.put(runningBalance, prefixSumOfBalance.getOrDefault(runningBalance, 0) + 1);
            }
        }
        return ans;
    }

    public static int[] generateRandomArray() {
        int size = (int) (Math.random() * 10) + 5; // Размер массива от 5 до 14
        int[] nums = new int[size];

        for (int i = 0; i < size; i++) {
            nums[i] = (int) (Math.random() * 10) + 1; // Случайное число от 1 до 10
        }

        return nums;
    }

    public static Map<Object[], Integer> testGeneration(int n) {
        Map<Object[], Integer> tests = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int[] nums = generateRandomArray();
            int k = (int) (Math.random() * 10) + 1; // Случайное число от 1 до 10
            int result = solution(nums, k);
            Object[] nk = new Object[]{nums, k};
            tests.put(nk, result);

            System.out.println("Test " + (i + 1) + ":");
            System.out.println("Input: nums = " + Arrays.toString(nums) + " k = " + k);
            System.out.println("Expected Output: " + result);
            System.out.println();
        }
        return tests;
    }
}

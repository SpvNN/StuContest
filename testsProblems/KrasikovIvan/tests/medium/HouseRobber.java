package medium;
import java.util.*;

public class HouseRobber {
    public static int solution(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] dp = new int[nums.length + 1];
        dp[1] = nums[0];

        for (int i = 2; i <= nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
        }

        return dp[nums.length];
    }

    public static int[] generateRandomArray() {
        int length = (int) (Math.random() * 10) + 1; // Случайная длина массива от 1 до 10
        int[] nums = new int[length];

        for (int i = 0; i < length; i++) {
            nums[i] = (int) (Math.random() * 100); // Случайное число от 0 до 99
        }

        return nums;
    }

    public static Map<int[], Integer> testGeneration(int n) {
        Map<int[], Integer> tests = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int[] nums = generateRandomArray();
            int result = solution(nums);
            tests.put(nums, result);
            System.out.println("Test " + (i + 1) + ":");
            System.out.println("Input: " + Arrays.toString(nums));
            System.out.println("Expected Output: " + result);
            System.out.println();
        }
        return tests;
    }
}

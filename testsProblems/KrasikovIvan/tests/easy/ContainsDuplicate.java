package easy;
import java.util.*;

public class ContainsDuplicate {
    static public boolean solution(int[] nums){
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return true;
            }
            set.add(num);
        }
        return false;
    }

    public static Map<int[], Boolean> testGeneration(int n) {
        Map<int[], Boolean> tests = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int[] nums = generateRandomArray();
            boolean sol = solution(nums);
            tests.put(nums, sol);
        }
        return tests;
    }

    public static int[] generateRandomArray() {
        // Генерация случайного массива длиной от 1 до 10
        int length = (int) (Math.random() * Math.pow(10, 5)) + 1;
        int[] nums = new int[length];
        for (int i = 0; i < length; i++) {
            // Генерация случайного числа от 1 до 100
            nums[i] = (int) (Math.random() * Math.pow(10, 9)) - (int)Math.pow(10, 9);
        }
        return nums;
    }
}

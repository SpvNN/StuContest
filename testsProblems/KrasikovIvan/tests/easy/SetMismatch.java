package easy;
import java.util.*;

public class SetMismatch {
    public static int[] solution(int[] nums) {
        int[] result = new int[2];
        int[] count = new int[nums.length + 1];

        for (int num : nums) {
            count[num]++;
        }

        for (int i = 1; i < count.length; i++) {
            if (count[i] == 2) {
                result[0] = i;
            } else if (count[i] == 0) {
                result[1] = i;
            }
        }

        return result;
    }

    public static int[] generateRandomArray() {
        // Генерация случайного массива от 1 до n
        int length = (int) (Math.random() * 10) + 2; // Длина случайного массива от 2 до 11
        int[] nums = new int[length];

        for (int i = 0; i < length; i++) {
            nums[i] = i + 1;
        }

        // Перемешиваем элементы массива
        for (int i = 0; i < length; i++) {
            int j = (int) (Math.random() * length);
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }

        // Интродуцируем ошибку - дубликат и пропущенное число
        int duplicate = (int) (Math.random() * length) + 1;
        int missing = (int) (Math.random() * length) + 1;

        while (missing == duplicate) {
            missing = (int) (Math.random() * length) + 1;
        }

        nums[(int) (Math.random() * length)] = duplicate;
        nums[(int) (Math.random() * length)] = missing;

        return nums;
    }

    public static Map<int[], int[]> testGeneration(int n) {
        Map<int[], int[]> tests = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int[] nums = generateRandomArray();
            int[] result = solution(nums);
            tests.put(nums, result);
            System.out.println("Test " + (i + 1) + ":");
            System.out.println("Input: " + Arrays.toString(nums));
            System.out.println("Expected Output: " + Arrays.toString(result));
            System.out.println();
        }
        return tests;
    }

}

package hard;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MinNumRemMakeMountArray {
    public static int solution(int[] nums) {
        int n = nums.length;
        int[] increasing = new int[n];
        int[] decreasing = new int[n];

        // Вычисляем длины возрастающих последовательностей, начиная с каждого элемента в массиве
        for (int i = 0; i < n; i++) {
            increasing[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    increasing[i] = Math.max(increasing[i], increasing[j] + 1);
                }
            }
        }

        // Вычисляем длины убывающих последовательностей, начиная с каждого элемента в массиве
        for (int i = n - 1; i >= 0; i--) {
            decreasing[i] = 1;
            for (int j = n - 1; j > i; j--) {
                if (nums[i] > nums[j]) {
                    decreasing[i] = Math.max(decreasing[i], decreasing[j] + 1);
                }
            }
        }

        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            if (increasing[i] > 1 && decreasing[i] > 1) {
                maxLen = Math.max(maxLen, increasing[i] + decreasing[i] - 1);
            }
        }

        return n - maxLen;
    }

    public static int[] generateRandomArray() {
        int size = (int) (Math.random() * 1000) + 3;
        int[] nums = new int[size];

        for (int i = 0; i < size; i++) {
            nums[i] = (int) (Math.random() * Math.pow(10, 9)) + 1;
        }

        return nums;
    }

    public static Map<int[], Integer> testGeneration(int n) {
        Map<int[], Integer> tests = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int[] array = generateRandomArray(); // Создаем копию исходного массива
            int result = solution(array); // Находим минимальное количество удалений для преобразования в горный массив
            tests.put(array, result);
            System.out.println("Test " + (i + 1) + ":");
            System.out.println("Input: " + Arrays.toString(array));
            System.out.println("Expected Output: " + result);
            System.out.println();
        }
        return tests;
    }
}

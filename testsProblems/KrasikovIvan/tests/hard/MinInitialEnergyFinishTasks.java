package hard;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MinInitialEnergyFinishTasks {
    public static int solution(int[][] A) {
        int res = 0;
        Arrays.sort(A, (a1, a2) -> (a1[1] - a1[0]) - (a2[1] - a2[0]));
        for (int[] a : A) {
            res = Math.max(res + a[0], a[1]);
        }
        return res;
    }

    public static int[][] generateRandomArray() {
        int size = (int) (Math.random() * Math.pow(10, 5)) + 1;
        int[][] nums = new int[size][2];

        for (int i = 0; i < size; i++) {
            nums[i][0] = (int) (Math.random() * Math.pow(10, 4)) + 1;
            nums[i][1] = (int) (Math.random() * Math.pow(10, 4)) + 1;
        }

        return nums;
    }

    public static Map<int[][], Integer> testGeneration(int n) {
        Map<int[][], Integer> tests = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int[][] tasksArray = generateRandomArray(); // Создаем копию исходного массива задач
            int result = solution(tasksArray); // Находим минимальное начальное значение энергии для завершения задач
            tests.put(tasksArray, result);
            System.out.println("Test " + (i + 1) + ":");
            System.out.println("Input: " + Arrays.deepToString(tasksArray));
            System.out.println("Expected Output: " + result);
            System.out.println();
        }
        return tests;
    }
}

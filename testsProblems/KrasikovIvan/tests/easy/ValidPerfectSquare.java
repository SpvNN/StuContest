package easy;
import java.util.*;

public class ValidPerfectSquare {
    static public boolean solution(int num){
        if (num < 0) {
            return false;
        }
        if (num == 0 || num == 1) {
            return true;
        }
        long start = 1;
        long end = num;
        while (start <= end) {
            long mid = start + (end - start) / 2;
            long square = mid * mid;

            if (square == num) {
                return true;
            } else if (square < num) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return false;
    }

    public static int generateRandomNumber() {
        // Генерация случайного числа от 1 до 1000
        return (int) (Math.random() * 1000) + 1;
    }

    public static Map<Integer, Boolean> testGeneration(int n) {
        Map<Integer, Boolean> tests = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int nums = generateRandomNumber();
            boolean sol = solution(nums);
            tests.put(nums, sol);
        }
        return tests;
    }
}

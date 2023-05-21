package easy;
import java.util.*;



public class HappyNum {
    static public boolean solution(int num){
        HashSet<Integer> set = new HashSet<>();

        while (num != 1 && !set.contains(num)) {
            set.add(num);
            num = getNextNumber(num);
        }

        return num == 1;
    }
    public static int getNextNumber(int num) {
        int sum = 0;

        while (num > 0) {
            int digit = num % 10;
            sum += digit * digit;
            num /= 10;
        }

        return sum;
    }

    public static int generateRandomNumber() {
        // Генерация случайного числа от 1 до 1000
        return (int) (Math.random() * 1000) + 1;
    }

    public static Map<Integer, Boolean> testGeneration(int n) {
        Map<Integer, Boolean> tests = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int num = generateRandomNumber();
            boolean sol = solution(num);
            tests.put(num, sol);
        }
        return tests;
    }
}

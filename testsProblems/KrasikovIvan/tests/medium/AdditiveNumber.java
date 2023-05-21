package medium;

import java.util.*;

public class AdditiveNumber {
    public static boolean solution(String num) {
        int n = num.length();

        for (int i = 1; i <= n / 2; i++) {
            if (num.charAt(0) == '0' && i > 1) {
                break;
            }

            long num1 = Long.parseLong(num.substring(0, i));

            for (int j = 1; Math.max(i, j) <= n - i - j; j++) {
                if (num.charAt(i) == '0' && j > 1) {
                    break;
                }

                long num2 = Long.parseLong(num.substring(i, i + j));
                if (isAdditive(num.substring(i + j), num1, num2)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean isAdditive(String num, long num1, long num2) {
        if (num.equals("")) {
            return true;
        }

        long sum = num1 + num2;
        String sumStr = String.valueOf(sum);

        if (!num.startsWith(sumStr)) {
            return false;
        }

        return isAdditive(num.substring(sumStr.length()), num2, sum);
    }

    public static String generateRandomNumber() {
        int length = (int) (Math.random() * 10) + 3; // Случайная длина числа от 3 до 12
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int digit = (int) (Math.random() * 10); // Случайная цифра от 0 до 9
            sb.append(digit);
        }

        return sb.toString();
    }

    public static Map<String, Boolean> testGeneration(int n) {
        Map<String, Boolean> tests = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String num = generateRandomNumber();
            boolean result = solution(num);
            tests.put(num, result);

            System.out.println("Test " + (i + 1) + ":");
            System.out.println("Input: " + num);
            System.out.println("Expected Output: " + result);
            System.out.println();
        }
        return tests;
    }
}

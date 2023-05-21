package easy;

import java.util.*;

public class ToLowerCase {
    public static String solution(String str) {
        char[] chars = str.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= 'A' && chars[i] <= 'Z') {
                chars[i] = (char) (chars[i] - 'A' + 'a');
            }
        }

        return new String(chars);
    }

    public static String generateRandomString() {
        int length = (int) (Math.random() * 10) + 1; // Длина случайной строки от 1 до 10
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int rand = (int) (Math.random() * 26); // Случайное число от 0 до 25
            char c = (char) ('A' + rand); // Преобразуем число в символ A-Z
            sb.append(c);
        }

        return sb.toString();
    }

    public static Map<String, String> testGeneration(int n) {
        Map<String, String> tests = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String str = generateRandomString();
            String result = solution(str);
            tests.put(str, result);
            System.out.println("Test " + (i + 1) + ":");
            System.out.println("Input: " + str);
            System.out.println("Expected Output: " + result);
            System.out.println();
        }
        return tests;
    }
}

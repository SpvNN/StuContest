package easy;
import java.util.*;


public class LongestUncommonSubsequence {
    public static int solution(String a, String b) {
        if (a.equals(b)) {
            return -1; // Если строки идентичны, нет неподпоследовательностей
        } else {
            return Math.max(a.length(), b.length()); // Возвращаем длину более длинной строки
        }
    }

    public static String generateRandomString() {
        int length = (int) (Math.random() * 10) + 1; // Длина случайной строки от 1 до 10
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            char c = (char) ('a' + Math.random() * 26); // Случайная буква от 'a' до 'z'
            sb.append(c);
        }

        return sb.toString();
    }

    public static Map<String[], Integer> testGeneration(int n) {
        Map<String[], Integer> tests = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String a = generateRandomString();
            String b = generateRandomString();
            int length = solution(a, b);
            String[] ab = new String[]{a, b};
            tests.put(ab, length);

            System.out.println("Test " + (i + 1) + ":");
            System.out.println("Input: a = " + a + ", b = " + b);
            System.out.println("Expected Output: " + length);
            System.out.println();
        }
        return tests;
    }
}

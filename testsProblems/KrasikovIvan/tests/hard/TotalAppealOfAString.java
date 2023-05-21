package hard;

import java.util.*;

public class TotalAppealOfAString {
    public static long solution(String s) {
        int[] a = new int[26];
        long c=0;
        long ans=0;
        for(int i=0;i<s.length();i++)
        {
            c-=a[s.charAt(i)-'a'];
            ans+=c+=a[s.charAt(i)-'a']=i+1;
        }
        return ans;
    }

    public static String generateRandomString() {
        int size = (int) (Math.random() * 10) + 5; // Размер строки от 5 до 14
        StringBuilder sb = new StringBuilder(size);

        for (int i = 0; i < size; i++) {
            char c = (char) ('a' + (int) (Math.random() * 26)); // Случайная буква от 'a' до 'z'
            sb.append(c);
        }

        return sb.toString();
    }

    public static Map<String, Long> testGeneration(int n) {
        Map<String, Long> tests = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String s = generateRandomString();
            long result = solution(s);
            tests.put(s, result);
            System.out.println("Test " + (i + 1) + ":");
            System.out.println("Input: " + s);
            System.out.println("Expected Output: " + result);
            System.out.println();
        }
        return tests;
    }

}

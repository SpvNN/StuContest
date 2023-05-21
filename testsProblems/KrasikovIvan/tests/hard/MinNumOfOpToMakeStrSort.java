package hard;

import java.math.BigInteger;
import java.util.*;

public class MinNumOfOpToMakeStrSort {
    public static int solution(String s) {
        BigInteger res = new BigInteger("0");
        BigInteger mod = new BigInteger("1000000007");

        int[] count = new int[26];
        BigInteger[] fact = factory(s.length(), mod);

        int n = s.length();
        BigInteger div = new BigInteger("1");

        for (int i = n - 1; i >= 0; --i) {
            char c = s.charAt(i);
            count[c-'a'] ++;
            BigInteger c1 = countInverse(count, c);
            BigInteger ans = c1.multiply(fact[n-i-1]);
            div = div.multiply(new BigInteger(String.valueOf(count[c-'a'])));
            ans = ans.multiply(div.modPow(mod.subtract(new BigInteger(String.valueOf(2))), mod)).mod(mod);
            res = res.add(ans).mod(mod);
        }

        return res.intValue();
    }

    static public BigInteger countInverse(int[] count, char c) {
        long cnt = 0;
        for (int i = 0; i < (c - 'a'); ++i) {
            cnt += count[i];
        }

        return new BigInteger(String.valueOf(cnt));
    }

    static public BigInteger[] factory(int n, BigInteger mod) {
        BigInteger[] fact = new BigInteger[n+1];
        fact[0] = new BigInteger("1");
        for (int i = 1; i <= n; ++i) {
            fact[i] = fact[i-1].multiply(new BigInteger(String.valueOf(i))).mod(mod);
        }

        return fact;
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

    public static Map<String, Integer> testGeneration(int n) {
        Map<String, Integer> tests = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String s = generateRandomString();
            int result = solution(s);
            tests.put(s, result);
            System.out.println("Test " + (i + 1) + ":");
            System.out.println("Input: " + s);
            System.out.println("Expected Output: " + result);
            System.out.println();
        }
        return tests;
    }
}

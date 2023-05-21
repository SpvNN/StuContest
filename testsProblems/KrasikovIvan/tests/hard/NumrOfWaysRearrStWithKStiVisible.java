package hard;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class NumrOfWaysRearrStWithKStiVisible {
    public static int solution(int n, int k) {
        long[][] dp = new long[n+1][k+1];
        dp[0][0] = 1L;
        int mod = (int)(1e9+7);
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= Math.min(i, k); j++) {
                dp[i][j] = dp[i-1][j] * (long)(i-1)+ dp[i-1][j-1];
                dp[i][j] %= mod;
            }
        }
        return (int)(dp[n][k] % mod);
    }

    public static Map<int[], Long> testGeneration(int n) {
        Map<int[], Long> tests = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int sticks = (int) (Math.random() * 1000) + 1; // Случайное число от 1 до 1000
            int k = (int) (Math.random() * sticks) + 1; // Случайное число от 1 до sticks
            long result = solution(sticks, k);
            int[] nk = new int[]{sticks, k};
            tests.put(nk, result);

            System.out.println("Test " + (i + 1) + ":");
            System.out.println("Input: n = " + sticks + " k = " + k);
            System.out.println("Expected Output: " + result);
            System.out.println();
        }
        return tests;
    }
}

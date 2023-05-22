package hard;

import java.util.*;

public class PaintGridWithThreeDiffColors {
    static int mod = (int) 1e9 + 7;
    static public int solution(int m, int n) {
        Map<Integer, Long> state = new HashMap();
        dfs(state, 0, m, -1, 0);
        Set<Integer> set = new HashSet(state.keySet());
        for (int i = 1; i < n; ++i) {
            Map<Integer, Long> dp = new HashMap();
            for (int a : set) {
                for (int b : set) {
                    if (0 == (a & b)) dp.put(a, (dp.getOrDefault(a, 0L) + state.get(b)) % mod);
                }
            }
            state = dp;
        }
        long res = 0L;
        for (long val : state.values()) res = (res + val) % mod;
        return (int) res;
    }
    static private void dfs(Map<Integer, Long> state, int j, int m, int prev, int cur) {
        if (j == m) {
            state.put(cur, state.getOrDefault(cur, 0L) + 1);
            return;
        }
        for (int i = 0; i < 3; ++i) {
            if (i == prev) continue;
            dfs(state, j + 1, m, i, (cur << 3) | (1 << i));
        }
    }

    public static Map<int[], Integer> testGeneration(int n) {
        Map<int[], Integer> tests = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int m = (int) (Math.random() * 5) + 1; // Случайное число от 1 до 5
            int n1 = (int) (Math.random() * 1000) + 1;
            int result = solution(m, n);
            int[] mn = new int[]{m, n};
            tests.put(mn, result);
            System.out.println("Test " + (i + 1) + ":");
            System.out.println("Input: m = " + m + " n = " + n1);
            System.out.println("Expected Output: " + result);
            System.out.println();
        }
        return tests;
    }

}

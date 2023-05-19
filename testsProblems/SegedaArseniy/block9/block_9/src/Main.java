import java.util.Arrays;

class Solution {
//    easy
    public int thirdMax(int[] nums) {
        long m1 = Long.MIN_VALUE;
        long m2 = Long.MIN_VALUE;
        long m3 = Long.MIN_VALUE;
        for (int num : nums) {
            if (num == m1 || num == m2 || num == m3) {
                continue;
            }
            if (num > m1) {
                m3 = m2;
                m2 = m1;
                m1 = num;
            } else if (num > m2) {
                m3 = m2;
                m2 = num;
            } else if (num > m3) {
                m3 = num;
            }
        }
        return (int) (m3 != Long.MIN_VALUE ? m3 : m1);
    }
//    medium
    public int maxSubArray(int[] nums) {
        int size = nums.length;
        int max_so_far = Integer.MIN_VALUE, max_ending_here = 0;

        for (int i = 0; i < size; i++) {
            max_ending_here = max_ending_here + nums[i];
            if (max_so_far < max_ending_here)
                max_so_far = max_ending_here;
            if (max_ending_here < 0)
                max_ending_here = 0;
        }
        return max_so_far;
    }
//    hard
    public int calculateMinimumHP(int[][] dungeon) {
        int n = dungeon.length;
        int m = dungeon[0].length;
        int[][] dp = new int[n][m];
        for(int i=0; i<n; i++){
            Arrays.fill(dp[i], -1);
        }
        dp[n-1][m-1] = Math.max(1, 1-dungeon[n-1][m-1]);
        minHealthHelper(dungeon, n, m, 0, 0, dp);
        return dp[0][0];
    }
    public int minHealthHelper(int[][] dungeon, int n, int m, int i, int j, int[][] dp){
        if(i == dungeon.length || j == dungeon[0].length){
            return Integer.MAX_VALUE;
        }
        if(dp[i][j] == -1){
            int a = minHealthHelper(dungeon, n, m, i+1, j, dp);
            int b = minHealthHelper(dungeon, n, m, i, j+1, dp);
            dp[i][j] = Math.max(1, Math.min(a,b)-dungeon[i][j]);
        }
        return dp[i][j];
    }
}

class Test{
    Solution np = new Solution();
    void test1_thirdMax(){
        int count = 20;
        while(count > 0){
            int n = 1 + (int) (Math.random() * 10000);
//            int n = 10;
            System.out.print("Входные данные: ");
            int[] massiv = new int[n];
            for(int i = 0; i < n; i++){
                massiv[i] = Integer.MIN_VALUE + (int) (Math.random() * Integer.MAX_VALUE);
                System.out.print(massiv[i] + " ");
            }
            int result = np.thirdMax(massiv);
            System.out.println(" ");
            System.out.print("Выходные данные: ");
            System.out.print(result);
            System.out.println(" ");
            count--;
        }
    }
    void test2_maxSubArray(){
        int count = 20;
        while(count > 0){
            int n = 1 + (int) (Math.random() * 100);
//            int n = 10;
            System.out.print("Входные данные: ");
            int[] massiv = new int[n];
            for(int i = 0; i < n; i++){
                massiv[i] = -10000 + (int) (Math.random() * 10000);
                System.out.print(massiv[i] + " ");
            }
            int result = np.maxSubArray(massiv);
            System.out.println(" ");
            System.out.print("Выходные данные: ");
            System.out.print(result);
            System.out.println(" ");
            count--;
        }
    }
    void test3_calculateMinimumHP(){
        int count = 20;
        while(count > 0){
            int n = 1 + (int) (Math.random() * 200);
            int m = 1 + (int) (Math.random() * 200);
//            int n = 10;
//            int m = 10;
            System.out.print("Входные данные: ");
            int[][] massiv = new int[n][m];
            System.out.print("[");
            for(int i = 0; i < n; i++){
                System.out.print("[");
                for(int j = 0; j < m; j++){
                    massiv[i][j] = -1000 + (int) (Math.random() * 1000);
                    System.out.print(massiv[i][j] + " ");
                }
                System.out.print("],");
            }
            System.out.print("]");
            int result = np.calculateMinimumHP(massiv);
            System.out.println(" ");
            System.out.print("Выходные данные: ");
            System.out.print(result);
            System.out.println(" ");
            count--;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Test test = new Test();
//        test.test1_thirdMax();
//        test.test2_maxSubArray();
        test.test3_calculateMinimumHP();
    }
}
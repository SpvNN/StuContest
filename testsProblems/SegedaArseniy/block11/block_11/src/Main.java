import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
//    easy
    public List<Integer> luckyNumbers (int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[] rows = new int[m];
        int[] cols = new int[n];
        Arrays.fill(rows, Integer.MAX_VALUE);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                rows[i] = Math.min(rows[i], matrix[i][j]);
                cols[j] = Math.max(cols[j], matrix[i][j]);
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (rows[i] == cols[j]) {
                    ans.add(matrix[i][j]);
                }
            }
        }
        return ans;
    }
//    medium
    public int[] nums;
    public void rotate(int[] nums, int k) {
        this.nums = nums;
        int n = nums.length;
        k %= n;
        reverse(0, n - 1);
        reverse(0, k - 1);
        reverse(k, n - 1);
    }
    private void reverse(int i, int j) {
        for (; i < j; ++i, --j) {
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
        }
    }
//    hard
    public int minFallingPathSum(int[][] grid) {
        for (int i = 1, m = grid.length; i < m; i++) {
            for (int j = 0, n = grid[0].length; j < n; j++) {
                int minValue = Integer.MAX_VALUE;
                for (int k = 0; k < n; k++) if (k != j) minValue = Math.min(minValue, grid[i - 1][k]);
                grid[i][j] += minValue;
            }
        }
        int result = Integer.MAX_VALUE;
        for (int k = 0, m = grid.length, n = grid[0].length; k < n; k++) result = Math.min(result, grid[m - 1][k]);
        return result;
    }
}

class Test{
    Solution np = new Solution();
    void test1_luckyNumbers(){
        int count = 20;
        while(count > 0){
            int n = 1 + (int) (Math.random() * 50);
            int m = 1 + (int) (Math.random() * 50);
//            int n = 3;
//            int m = 2;
            int[][] matrix = new int[n][m];
            System.out.print("Выходные данные: ");
            for(int i = 0; i < n; i++){
                System.out.print("[ ");
                for(int j = 0; j < m; j++){
                    matrix[i][j] = 1 + (int) (Math.random() * 100000);
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.print(" ],");
            }
            System.out.println(" ");
            System.out.print("Выходные данные: ");
            List result = np.luckyNumbers(matrix);
            if(result.size() == 0) System.out.print("[]");
            for(int i = 0; i < result.size(); i++){
                System.out.print(result.get(i));
            }
            System.out.println(" ");
            count--;
        }
    }
    void test2_rotate(){
        int count = 20;
        while(count > 0){
            int n = 1 + (int) (Math.random() * 100);
//            int n = 5;
            int[] nums = new int[n];
            System.out.print("Входные данные: ");
            for(int i = 0; i < n; i++){
                nums[i] = Integer.MIN_VALUE + (int) (Math.random() * Integer.MAX_VALUE);
                System.out.print(nums[i] + " ");
            }
            System.out.println(" ");
            int k = 0 + (int) (Math.random() * 100);
            System.out.print("k = " + k);
            System.out.println(" ");
            System.out.print("Выходные данные: ");
            np.rotate(nums, k);
            for(int i = 0; i < n; i++){
                System.out.print(np.nums[i] + " ");
            }
            System.out.println(" ");
            count--;
        }
    }
    void test3_minFallingPathSum(){
        int count = 20;
        while(count > 0){
            int n = 1 + (int) (Math.random() * 50);
//            int n = 5;
            int[][] matrix = new int[n][n];
            System.out.print("Входные данные: ");
            System.out.print("[");
            for(int i = 0; i < n; i++){
                System.out.print("[");
                for(int j = 0; j < n; j++){
                    matrix[i][j] = -99 + (int) (Math.random() * 99);
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.print("]");
            }
            System.out.print("]");
            System.out.println(" ");
            System.out.print("Выходные данные: ");
            int result = np.minFallingPathSum(matrix);
            System.out.print(result + " ");
            System.out.println(" ");
            count--;
        }
    }
}


public class Main {
    public static void main(String[] args) {
        Test test = new Test();
//        test.test1_luckyNumbers();
//        test.test2_rotate();
        test.test3_minFallingPathSum();
    }
}
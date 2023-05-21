import java.util.HashMap;
import java.util.Map;

class Solution {
//    easy
    public boolean isPowerOfThree(int n) {
        if (n == 0) return false;

        while (n % 3 == 0) n /= 3;

        if(n==1) return true;
        else return false;
    }
//    medium
    public int superPow(int a, int[] b) {
        long res = 1;
        a = a % 1337;
        for (int i = 0; i < b.length; ++i) {
            res = pow(res, 10) * pow(a, b[i]) % 1337;
        }
        return (int)res;
    }
    private long pow(long x, int n) {
        if (n == 0) return 1;
        long v = pow(x, n / 2);
        if (n % 2 == 0) {
            return v * v % 1337;
        }
        else {
            return v * v * x % 1337;
        }
    }
//    hard
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int xlen = matrix[0].length, ylen = matrix.length, ans = 0;
        Map<Integer, Integer> res = new HashMap<>();
        for (int[] r : matrix)
            for (int j = 1; j < xlen; j++)
                r[j] += r[j-1];
        for (int j = 0; j < xlen; j++)
            for (int k = j; k < xlen; k++) {
                res.clear();
                res.put(0,1);
                int csum = 0;
                for (int i = 0; i < ylen; i++) {
                    csum += matrix[i][k] - (j > 0 ? matrix[i][j-1] : 0);
                    ans += res.getOrDefault(csum - target, 0);
                    res.put(csum, res.getOrDefault(csum, 0) + 1);
                }
            }
        return ans;
    }
}

class Test{
    Solution np = new Solution();
    void test1_isPowerOfThree(){
        int count = 20;
        int temp = 0;
        while(count > 0){
            int n = Integer.MIN_VALUE + (int) (Math.random() * Integer.MAX_VALUE);
            if(temp > 10) n = (int) Math.pow(3, (1 + (int) (Math.random() * 20)));
            System.out.println("Входные данные: " + n);
            boolean result = np.isPowerOfThree(n);
            if(result == false) temp++;
            System.out.println("Выходные данные: " + result);
            count--;
        }
    }
    void test2_superPow(){
        int count = 20;
        while(count > 0){
            int a = 1 + (int) (Math.random() * Integer.MAX_VALUE);
            int n = 1 + (int) (Math.random() * 100);
            int[] nums = new int[n];
            nums[0] = 1 + (int) (Math.random() * 9);
            System.out.print("Входные данные: ");
            System.out.println("a = " + a);
            for(int i = 0; i < n; i++){
                nums[i] = 0 + (int) (Math.random() * 9);
                System.out.print(nums[i] + " ");
            }
            int result = np.superPow(a, nums);
            System.out.println(" ");
            System.out.print("Выходные данные: " + result);
            System.out.println(" ");
            count--;
        }
    }
    void test3_numSubmatrixSumTarget(){
        int count = 20;
        while(count > 0){
            int n = 1 + (int) (Math.random() * 100);
            int m = 1 + (int) (Math.random() * 100);
            int[][] matrix = new int[n][m];
            System.out.print("входные данные: ");
            for(int i = 0; i < n; i++){
                System.out.print("[");
                for(int j = 0; j < m; j++){
                    matrix[i][j] = -1000 + (int) (Math.random() * 1000);
                    System.out.print(matrix[i][j] + ", ");
                }
                System.out.print("],");
            }
            System.out.println(" ");
            int target = -10000 + (int) (Math.random() * 10000);
            System.out.println("target = " + target);
            int result = np.numSubmatrixSumTarget(matrix, target);
            System.out.println("Выходные данные: " + result);
            count--;
        }
    }
}



public class Main {
    public static void main(String[] args) {
        Test test = new Test();
//        test.test1_isPowerOfThree();
//        test.test2_superPow();
        test.test3_numSubmatrixSumTarget();
    }
}
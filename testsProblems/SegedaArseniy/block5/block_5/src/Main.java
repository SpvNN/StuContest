import java.lang.reflect.Array;
import java.util.Arrays;

class Solution {
    // easy
    public int singleNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            for (int j = 0; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    count++;
                }
            }
            if (count == 1) {
                return nums[i];
            }
        }
        return -1;
    }
    // medium
    public boolean searchMatrix(int[][] matrix, int target) {
        int i = 0;
        int j = matrix[0].length-1;
        while(i < matrix.length && j >= 0){
            if(target == matrix[i][j])
                return true;
            else if(target < matrix[i][j])
                j--;
            else
                i++;
        }
        return false;
    }
    // hard
    public int countDigitOne(int n) {
        int count = 0;
        for (int i = 1; i <= n; i *= 10){
            int divider = i * 10;
            count += (n / divider) * i + Math.min(Math.max(n % divider - i + 1, 0), i);
        }
        return count;
    }
}

class Test{
    Solution np = new Solution();
    int count;
    void test1_singleNumber(){
        count = 20;
        int n = 0;
        int result = 0;
        while(count > 0){
            n = 1 + (int) (Math.random() * 30000);
            int[] nums = new int[n];
            result = 0 + (int) (Math.random() * n-1);
            int temp = 0;
            for(int i = 0; i < n; i++){
                if(i % 4 == 0){
                    nums[i] = -30000 + (int) (Math.random() * 30000);
                    temp = nums[i];
                }
                else{
                    if(temp == 0) temp = -30000 + (int) (Math.random() * 30000);
                    nums[i] = temp;
                }
            }
            nums[result] = -30000 + (int) (Math.random() * 30000);
            for(int i = 0; i < n; i++) System.out.print(nums[i] + " ");
            System.out.println(" ");
            result = np.singleNumber(nums);
            System.out.print("Ответ: " + result);
            System.out.println(" ");
            count--;
        }
    }
    void test2_searchMatrix(){
        count = 20;
        int target = 0;
        boolean result = true;
        while(count > 0){
            int n,m;
            n = 1 + (int) (Math.random() * 100);
            m = 1 + (int) (Math.random() * 100);
            n = 2; m = 3;
            int[][] matrix = new int[n][m];
            for(int i = 0; i < n; i++){
                for(int j = 0; j < m; j++){
                    matrix[i][j] = -10000 + (int) (Math.random() * 10000);
                }
                Arrays.sort(matrix[i]);
            }
            for(int i = 0; i < n; i++){
                for(int j = 0; j < m; j++){
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.println(" ");
            }
            target = matrix[0 + (int) (Math.random() * n)][0 + (int) (Math.random() * m)];
            System.out.println("target = " + target);
            result = np.searchMatrix(matrix, target);
            System.out.println("Ответ: " + result);
            count--;
        }
    }
    void test3_countDigitOne(){
        count = 20;
        int n = 0;
        int result;
        while(count > 0){
            n = 0 + (int) (Math.random() * 1000000000);
            System.out.println("n = " + n);
            result = np.countDigitOne(n);
            System.out.println("Ответ: " + result);
            count--;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Test task = new Test();
        task.test1_singleNumber();
//        task.test2_searchMatrix();
//        task.test3_countDigitOne();
    }
}
import java.util.LinkedList;
import java.util.List;

class Solution {
    public List<Integer> addToArrayForm(int[] num, int k) {
        List<Integer> ans = new LinkedList<>();
        for (int i = num.length - 1; i >= 0; --i) {
            ans.add(0, (num[i] + k) % 10);
            k = (num[i] + k) / 10;
        }
        while (k > 0) {
            ans.add(0, k % 10);
            k /= 10;
        }
        return ans;
    }
    public void setZeroes(int[][] matrix) {
        int col0 = 1, rows = matrix.length, cols = matrix[0].length;
        for (int i = 0; i < rows; i++) {
            if (matrix[i][0] == 0) col0 = 0;
            for (int j = 1; j < cols; j++)
                if (matrix[i][j] == 0)
                    matrix[i][0] = matrix[0][j] = 0;
        }

        for (int i = rows - 1; i >= 0; i--) {
            for (int j = cols - 1; j >= 1; j--)
                if (matrix[i][0] == 0 || matrix[0][j] == 0)
                    matrix[i][j] = 0;
            if (col0 == 0) matrix[i][0] = 0;
        }
        System.out.print("[");
        for (int i = 0; i < rows; i++) {
            System.out.print("[ ");
            for (int j = 0; j < cols; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.print("]");
        }
    }
    public int countRangeSum(int[] nums, int lower, int upper) {
        int n = nums.length;
        long[] sums = new long[n + 1];
        for (int i = 0; i < n; ++i)
            sums[i + 1] = sums[i] + nums[i];
        return countWhileMergeSort(sums, 0, n + 1, lower, upper);
    }
    private int countWhileMergeSort(long[] sums, int start, int end, int lower, int upper) {
        if (end - start <= 1) return 0;
        int mid = (start + end) / 2;
        int count = countWhileMergeSort(sums, start, mid, lower, upper) + countWhileMergeSort(sums, mid, end, lower, upper);
        int j = mid, k = mid, t = mid;
        long[] cache = new long[end - start];
        for (int i = start, r = 0; i < mid; ++i, ++r) {
            while (k < end && sums[k] - sums[i] < lower) k++;
            while (j < end && sums[j] - sums[i] <= upper) j++;
            while (t < end && sums[t] < sums[i]) cache[r++] = sums[t++];
            cache[r] = sums[i];
            count += j - k;
        }
        System.arraycopy(cache, 0, sums, start, t - start);
        return count;
    }
}

class Test {
    Solution np = new Solution();

    void test1_addToArrayForm() {
        int count = 20;
        while (count > 0) {
            int n = 1 + (int) (Math.random() * 10000);
//            int n = 10;
            System.out.print("Входные данные: ");
            int[] massiv = new int[n];
            massiv[0] = 1 + (int) (Math.random() * 9);
            System.out.print(massiv[0] + " ");
            for (int i = 1; i < n; i++) {
                massiv[i] = 0 + (int) (Math.random() * 9);
                System.out.print(massiv[i] + " ");
            }
            int k = 1 + (int) (Math.random() * 10000);
            System.out.println();
            System.out.print("k = ");
            System.out.println(k);
            List result = np.addToArrayForm(massiv, k);
            System.out.print("Выходные данные: ");
            for (int i = 0; i < result.size(); i++) {
                System.out.print(result.get(i) + " ");
            }
            System.out.println(" ");
            count--;
        }
    }

    void test2_setZeroes() {
        int count = 20;
        while (count > 0) {
            int n = 1 + (int) (Math.random() * 200);
            int m = 1 + (int) (Math.random() * 200);
//            int n = 5;
//            int m = 5;
            System.out.print("Входные данные: ");
            int[][] massiv = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    massiv[i][j] = Integer.MIN_VALUE + (int) (Math.random() * Integer.MAX_VALUE);
                }
            }
            int random = 0 + (int) (Math.random() * n);
            for (int i = 0; i < random; i++) {
                int temp_i = 0 + (int) (Math.random() * n);
                int temp_j = 0 + (int) (Math.random() * m);
                massiv[temp_i][temp_j] = 0;
            }
            System.out.print("[");
            for (int i = 0; i < n; i++) {
                System.out.print("[ ");
                for (int j = 0; j < m; j++) {
                    System.out.print(massiv[i][j] + " ");
                }
                System.out.print("]");
            }
            System.out.print("]");
            System.out.println(" ");
            System.out.print("Выходные данные: ");
            np.setZeroes(massiv);
            System.out.println(" ");
            count--;
        }
    }

    void test3_countRangeSum() {
        int count = 20;
        while (count > 0) {
            int n = 1 + (int) (Math.random() * 100000);
//            int n = 10;
            System.out.print("Входные данные: ");
            int[] massiv = new int[n];
            for (int i = 0; i < n; i++) {
                massiv[i] = -10000 + (int) (Math.random() * 10000);
                System.out.print(massiv[i] + " ");
            }
            int temp = 0 + (int) (Math.random() * n-1);
            int lower = massiv[temp];
            temp = 0 + (int) (Math.random() * n-1);
            int upper = massiv[temp];
            if(upper < lower){
                temp = upper;
                upper = lower;
                lower = upper;
            }
            System.out.println(" ");
            System.out.print("lower = " + lower);
            System.out.print(" upper = " + upper);
            System.out.println(" ");
            int result = np.countRangeSum(massiv, lower, upper);
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
//        test.test1_addToArrayForm();
//        test.test2_setZeroes();
        test.test3_countRangeSum();
    }
}
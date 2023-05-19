import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
//    easy
    public List<String> fizzBuzz(int n) {
        List<String> result = new ArrayList<String>();
        for(int i=1;i<=n;i++){
            if(i%3==0 && i%5==0){
                result.add("FizzBuzz");
                continue;
            }

            if(i%3==0){
                result.add("Fizz");
                continue;
            }

            if(i%5==0){
                result.add("Buzz");
                continue;
            }

            result.add(i+"");
        }

        return result;
    }
//    medium
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int closest = nums[0] + nums[1] + nums[n - 1];
        for (int i = 0; i < n - 2; i++) {
            int j = i + 1;
            int k = n - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum <= target) {
                    j++;
                } else {
                    k--;
                }
                if (Math.abs(closest - target) > Math.abs(sum - target)) {
                    closest = sum;
                }
            }
        }
        return closest;
    }
//    hard
    public int candy(int[] ratings) {
        int sum = 0;
        if (ratings.length == 0)
            return sum;
        int[] lc = new int[ratings.length];
        int[] rc = new int[ratings.length];
        for (int i = 0 ; i < ratings.length; i++)
            lc[i] = rc[i] = 1;
        for (int i = 0 ; i < ratings.length-1; i++){
            if (ratings[i] < ratings[i+1])
                lc[i+1] = lc[i] + 1;
        }
        for (int i = ratings.length-1 ; i > 0 ; i--){
            if (ratings[i-1] > ratings[i])
                rc[i-1] = rc[i] + 1;
        }
        for (int i = 0 ; i < ratings.length ; i++){
            sum += max(lc[i] , rc[i]);
        }
        return sum;
    }
    private int max(int a , int b){
        return a > b ? a : b;
    }
}

class Test{
    Solution np = new Solution();
    void test1_fizzBuzz(){
        int count = 20;
        while(count > 0){
            int n = 1 + (int) (Math.random() * 10000);
            System.out.print("Входные данные: ");
            System.out.println(n);
            List result = np.fizzBuzz(n);
            System.out.print("Выходные данные: ");
            for(int i = 0; i < n; i++){
                System.out.print(result.get(i) + " ");
            }
            System.out.println(" ");
            count--;
        }
    }
    void test2_threeSumClosest(){
        int count = 20;
        while(count > 0){
            int n = 3 + (int) (Math.random() * 500);
            System.out.print("Входные данные: ");
            int[] massiv = new int[n];
            for(int i = 0; i < n; i++){
                massiv[i] = -1000 + (int) (Math.random() * 1000);
                System.out.print(massiv[i] + " ");
            }
            int target = -10000 + (int) (Math.random() * 10000);
            System.out.println(" ");
            System.out.print("target = ");
            System.out.print(target);
            int result = np.threeSumClosest(massiv, target);
            System.out.println(" ");
            System.out.print("Выходные данные: ");
            System.out.print(result);
            System.out.println(" ");
            count--;
        }
    }
    void test3_candy(){
        int count = 20;
        while(count > 0){
            int n = 1 + (int) (Math.random() * 20000);
//            int n = 10;
            System.out.print("Входные данные: ");
            int[] massiv = new int[n];
            for(int i = 0; i < n; i++){
                massiv[i] = 0 + (int) (Math.random() * 20000);
                System.out.print(massiv[i] + " ");
            }
            int result = np.candy(massiv);
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
//        test.test1_fizzBuzz();
//        test.test2_threeSumClosest();
        test.test3_candy();
    }
}
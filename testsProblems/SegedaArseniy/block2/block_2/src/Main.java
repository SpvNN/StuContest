import java.security.SecureRandom;
import java.security.Security;

class Solution {
    // easy
    public boolean isPalindrome(int x) {
        if(x < 0){
            return false;
        }
        int x_1 = x;
        int reciprocal = 0;
        while (x_1 > 0) {
            reciprocal = reciprocal * 10 + x_1 % 10;
            x_1 /= 10;
        }
        return x == reciprocal;
    }

    // medium
    public double myPow(double x, int n) {
        return Math.pow(x, n);
    }

    // hard
    public int trap(int[] height) {
        int count = 0;
        for (int i = 1; i < height.length; i++) {
            int left = height[i];
            for (int j = 0; j < i; j++) {
                left = Math.max(left, height[j]);
            }

            int right = height[i];
            for (int j = i + 1; j < height.length; j++) {
                right = Math.max(right, height[j]);
            }

            count += Math.min(left, right) - height[i];
        }
        return count;
    }
}

class Test{
    Solution np = new Solution();
    int count;
    void test1_isPalindrome(){
        count = 20;
        int x = 0;
        boolean result;
        int flag= 0;
        String str = "";
        while(count > 0){
            x = Integer.MIN_VALUE + (int) (Math.random() * Integer.MAX_VALUE - 1);
            if(flag > count/2){
                str = Integer.toString(x);
                x = 0;
                for(int i = 0; i < str.length()/2; i++){
                    if(str.charAt(i) == '-') continue;
                    x += Integer.parseInt(String.valueOf(str.charAt(i)));
                    x *= 10;
                }

                for(int i = (str.length()/2)-1; i >= 0; i--){
                    if(str.charAt(i) == '-') continue;
                    if(i != 0) x += Integer.parseInt(String.valueOf(str.charAt(i)));
                    if(i != 0 && i != 1) x *= 10;
                }
            }
            System.out.println(x);
            // output
            result = np.isPalindrome(x);
            if(result == false) flag++;
            System.out.println("Ответ:" + result);
            System.out.println(" ");
            count--;
        }
    }

    void test2_myPow(){
        count = 20;
        double x1 = 0;
        double result = 0;
        int flag = 0;
        int n = 0;
        // input
        while(count > 0){
            x1 = -100 + (double) (Math.random() * 100);
            n = -2000000000 + (int) (Math.random() * 2000000000-1);
            if(flag > count/2){
                n = -20 + (int) (Math.random() * 200);
            }
            System.out.println(x1);
            System.out.println(n);
            // output
            result = np.myPow(x1, n);
            if(result == 0.0) flag++;
            System.out.println("Ответ:" + result);
            System.out.println(" ");
            count--;
        }
    }

    void test3_trap(){
        count = 20;
        int result = 0;
        int n = 0;
        while(count > 0){
            // input
            n = 1 + (int) (Math.random() * 20000);
            int[] height = new int[n];
            for(int i = 0; i < n; i++){
                height[i] = 0 + (int) (Math.random() * 100000);
                System.out.print(height[i] + " ");
            }
            // output
            result = np.trap(height);
            System.out.println(" ");
            System.out.println("Ответ:" + result);
            count--;
        }
    }
}


public class Main {
    public static void main(String[] args) {
        Test task = new Test();
//        task.test1_isPalindrome();
//        task.test2_myPow();
//        task.test3_trap();
    }
}
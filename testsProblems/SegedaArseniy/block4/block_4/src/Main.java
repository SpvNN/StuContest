import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;

class Solution {
    // 1) easy
    public int removeElement(int[] nums, int val) { // easy
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[count++] = nums[i];
            }
        }
        return count;
    }

    // 2) medium
    public String largestNumber(int[] nums){
        String[] str = new String[nums.length];
        for(int i = 0;i < nums.length; i++){
            str[i] = String.valueOf(nums[i]);
        }
        Arrays.sort( str, (s1, s2) -> (s2+s1).compareTo(s1+s2) );
        if(str[0].charAt(0) == '0'){
            return "0";
        }
        StringBuilder ans = new StringBuilder();
        for(String s:str){
            ans.append(s);
        }
        return ans.toString();
    }

    // 3) hard
    public String getPermutation(int n, int k) {
        if(n < 1 || n > 9) return "";
        if(k < 1 || k > factorial(n)) return "";
        String result = "";
        ArrayList<Integer> index = new ArrayList<Integer>();
        for(int i = 0; i < n; i++) index.add(0);
        ArrayList<Integer> res = new ArrayList<Integer>();
        int kk = k - 1;
        for(int i = n - 1; i >= 0; i--){
            while(kk >= factorial(i)){
                index.set(i, index.get(i)+1);
                kk -= factorial(i);
            }
        }
        for(int i = 0; i < n; i++) res.add(i+1);
        for(int i= n - 1; i >= 0; i--){
            result += (char)(res.get(index.get(i)) + '0');
            res.remove(res.get(index.get(i)));
        }
        return result;
    }
    int factorial(int n){
        int f = 1;
        for(int i = 1; i <= n; i++) f *= i;
        return f;
    }
}

class Test{
    Solution np = new Solution();
    int count;
    int n = 0;
    void test1_removeElement(){
        count = 20;
        int val = 0;
        n = 0;
        int result = 0;
        while(count > 0){
            n = 0 + (int) (Math.random() * 100);
            int nums[] = new int[n];
            for(int i = 0; i < n; i++){
                nums[i] = 0 + (int) (Math.random() * 50);
                System.out.print(nums[i] + " ");
            }
            val = 0 + (int) (Math.random() * 100);
            System.out.println(val);
            result = np.removeElement(nums, val);
            System.out.println("Ответ: " + result);
            count--;
        }
    }
    void test2_largestNumber(){
        count = 20;
        String result;
        n = 0;
        while(count > 0){
            System.out.println(" ");
            n = 1 + (int) (Math.random() * 100);
            int nums[] = new int[n];
            for(int i = 0; i < n; i++){
                nums[i] = 0;
                nums[i] = 0 + (int) (Math.random() * 1000000000);
                System.out.print(nums[i] + " ");
            }
            System.out.println(" ");
            result = np.largestNumber(nums);
            System.out.println("Ответ: " + result);
            count--;
        }
    }
    void test3_getPermutation(){
        count = 20;
        String result;
        n = 0;
        int k = 0;
        while(count > 0){
            System.out.println(" ");
            n = 1 + (int) (Math.random() * 9);
            k = 1 + (int) (Math.random() * np.factorial(n));
            System.out.println(n);
            System.out.println(k);
            result = np.getPermutation(n, k);
            System.out.println("Ответ: " + result);
            count--;
            count--;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Test task = new Test();
//        task.test1_removeElement();
//        task.test2_largestNumber();
        task.test3_getPermutation();
    }
}
package hard;

import java.util.*;

public class PatchArr {
    public static int solution(int[] nums, int n) {
        Stack<Integer> stack = new Stack<>();
        int count = 0;
        for(int i = nums.length - 1; i >= 0; i--) stack.push(nums[i]);
        long runningSum = 0l;
        while(runningSum < n) {
            if(!stack.isEmpty() && stack.peek() <= runningSum+1) {
                runningSum += stack.pop();
            }
            else{
                count++;
                runningSum += (runningSum + 1);
            }
        }
        return count;
    }

    public static Object[][] testGeneration(int count){
        Object[][] result = new Object[count][3];
        for(int i = 0; i < count; i++){
            int len = 1 + (int)(Math.random()*1000);
            int[] nums = new int[len];
            for(int j = 0; j < len; j++){
                nums[j] = 1 + (int)(Math.random()*10000);
            }
            int n = 1 + (int)(Math.random()*(Math.pow(2, 21)-1));
            result[i][0] = nums;
            result[i][1] = n;
            result[i][2] = solution(nums, n);
        }
        return result;
    }
}

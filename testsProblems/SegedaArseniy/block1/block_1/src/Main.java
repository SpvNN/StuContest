class Solution {
    // 1) easy
    public int[] twoSum(int[] nums, int target) {
        int[] ans = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if ((nums[i]+nums[j]) == target) {
                    ans[0] = i;
                    ans[1] = j;
                    break;
                }
            }
        }
        return ans;
    }

    // 2) medium
    public int maxArea(int[] height) {
        int temp = 0, i = 0, j = height.length-1, result = 0;
        while (i < j) {
            if (height[i] <= height[j]) {
                result = height[i] * (j - i);
                i++;
            } else {
                result = height[j] * (j - i);
                j--;
            }
            if (result > temp) temp = result;
        }
        return temp;
    }

    // 3) hard
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        int N = 1000010;
        boolean[] present = new boolean[N];
        int maxele = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            if (nums[i] > 0 && nums[i] <= n)
                present[nums[i]] = true;
            maxele = Math.max(maxele, nums[i]);
        }

        for (int i = 1; i < N; i++)
            if (!present[i])
                return i;

        return maxele + 1;
    }
}

class Test{
    Solution np = new Solution();
    int count;
    int n;
    public void test1_twosum(){
        count = 20;
        n = 0;
        int target = 0;
        int[] result = new int[2];
        int sum = 0;
        int[] index = new int[2];
        while(count > 0){
            n = 2 + (int) (Math.random() * 10000);
            int[] height = new int[n];
            for(int i = 0; i < n; i++){
                height[i] = -1000000000 + (int) (Math.random() * 1000000000);
                System.out.print(height[i] + " ");
            }
            System.out.println(" ");
            target = -1000000000 + (int) (Math.random() * 1000000000);
            System.out.print(" " + target);
            if(sum > count/2){
                index[0] = 0 + (int) (Math.random() * height.length);
                index[1] = 0 + (int) (Math.random() * height.length);
                height[index[1]] = target;
                height[index[0]] = 0;
            }
            // output
            result = np.twoSum(height, target);
            System.out.println(" ");
            System.out.println("Ответ:" + result[0] + " " + result[1]);
            if(result[0] == 0 && result[1] == 0) sum++;
            count--;
        }
    }
    public void test2_maxArea(){
        count = 20;
        n = 0;
        int sum = 0;
        int result = 0;
        while(count > 0){
            n = 2 + (int) (Math.random() * 100000);
            int[] height = new int[n];
            for(int i = 0; i < n; i++){
                height[i] = 0 + (int) (Math.random() * 10000);
                System.out.print(height[i] + " ");
            }
            // output
            result = np.maxArea(height);
            System.out.println(" ");
            System.out.println("Ответ: " + result);
            if(result == 0) sum++;
            count--;
        }
    }
    public void test3_firstMissingPositive(){
        count = 20;
        int flag = 0;
        int result = 0;
        n = 0;
        while(count > 0){
            n = 1 + (int) (Math.random() * 100000);
            int[] nums = new int[n];
            if(flag > count/2){
                n = 1 + (int) (Math.random() * 20);
                nums = new int[n];
                nums[0] = 1;
                System.out.print(nums[0] + " ");
                for(int i = 1; i < n; i++){
                    nums[i] = 0;
                    nums[i] = 0 + (int) (Math.random() * 20);
                    System.out.print(nums[i] + " ");
                }
            }
            else {
                for (int i = 0; i < n; i++) {
                    nums[i] = -2000000000 + (int) (Math.random() * 2000000000 - 1);
                    System.out.print(nums[i] + " ");
                }
            }
            // output
            result = np.firstMissingPositive(nums);
            System.out.println(" ");
            System.out.println("Ответ: " + result);
            if(result == 1) flag++;
            count--;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Test task = new Test();
//        task.test1_twosum();
//        task.test2_maxArea();
//        task.test3_firstMissingPositive();
    }
}
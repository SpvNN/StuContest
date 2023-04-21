package hard;

import java.util.*;

public class BurstBalloons {
    static public int solution(int[] nums){
        int[] dummy_num = new int[nums.length + 2];
        dummy_num[0] = dummy_num[dummy_num.length - 1] = 1;
        for(int i = 1;i <= nums.length;i ++)
            dummy_num[i] = nums[i - 1];
        int[][] dp = new int[dummy_num.length][dummy_num.length];
        for (int i = dummy_num.length - 3; i >= 0; i --) {
            for (int j = i + 2; j < dummy_num.length;j ++) {
                for (int k = i + 1; k < j; k ++)
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k][j] + dummy_num[i] * dummy_num[k] * dummy_num[j]);
            }
        }
        return dp[0][dummy_num.length - 1];
    }

    public static Map<int[], Integer> testGeneration(int count){
        Map<int[], Integer> result = new HashMap<>();
        for(int i = 0; i < count; i++){
            int n = 1 + (int)(Math.random()*300);
            int[] nums = new int[n];
            for(int j = 0; j < n; j++){
                nums[j] = (int)(Math.random()*100);
            }
            result.put(nums, solution(nums));
        }
        return result;
    }
}

package hard;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MinimumIncompatibility {
    public static int solution(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[1<<n];
        int bucketSize = n/k;
        for(int i=1; i<(1<<n); i++){
            int count = Integer.bitCount(i);
            if(count==bucketSize){
                int max = 0;
                int min = Integer.MAX_VALUE;
                int[] freq = new int[n+1];
                for(int j=0; j<n; j++){
                    boolean isSet = (i & (1<<j))>0;
                    if(isSet){
                        if(++freq[nums[j]]>1){
                            dp[i] = -1;
                            break;
                        }
                        else{
                            min = Math.min(min, nums[j]);
                            max = Math.max(max, nums[j]);
                        }
                    }
                }
                if(dp[i]!=-1){
                    dp[i] = max-min;
                }
            }
            else if(count%bucketSize==0){
                dp[i] = Integer.MAX_VALUE;
                for(int cur = i; cur>0; cur=(cur-1)&i){
                    if(Integer.bitCount(cur)==bucketSize){
                        int comp = i ^ cur;
                        if(dp[cur]==-1 || dp[comp]==-1){
                            continue;
                        }
                        dp[i] = Math.min(dp[i], dp[cur]+dp[comp]);
                    }
                }
                if(dp[i] == Integer.MAX_VALUE){
                    dp[i] = -1;
                }
            }
        }
        return dp[(1<<n)-1];
    }

    public static int[] generateRandomArray(int n) {
        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = (int) (Math.random() * n) + 1;
        }

        return nums;
    }

    public static Map<int[], Integer> testGeneration(int n) {
        Map<int[], Integer> tests = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int size = (int) (Math.random() * 16) + 1;
            int k = (int) (Math.random() * 16) + 1;
            while(!(size % k == 0)){
                size = (int) (Math.random() * 16) + 1;
            }
            int[] nums = generateRandomArray(size);
            int result = solution(nums, k);
            tests.put(nums, result);

            System.out.println("Test " + (i + 1) + ":");
            System.out.println("Input: nums = " + Arrays.toString(nums) + " k = " + k);
            System.out.println("Expected Output: " + result);
            System.out.println();
        }
        return tests;
    }
}

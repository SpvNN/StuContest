import java.util.Arrays;
import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
class Solution {

    // hard
    //1187. Make Array Strictly Increasing https://leetcode.com/problems/make-array-strictly-increasing/
    public int makeArrayIncreasing(int[] arr1, int[] arr2) {

        int L1=arr1.length,L2=arr2.length;
        int[][]stack=new int[L1+1][2];
        stack[0][0]=-1000;
        stack[0][1]=0;
        int top=0;

        int[][]tmp=new int[L1+1][2];
        int t2=-1;

        Arrays.sort(arr2);

        for(int i=0;i<L1;i++){

            // first try replacing current number
            int i2=0,j=0;
            while(i2<L2&&j<=top){
                int cur=arr2[i2];

                if(cur<=stack[j][0]){++i2;continue;}

                while(j+1<=top&&cur>stack[j+1][0])++j;
                ++t2;
                tmp[t2][0]=cur;
                tmp[t2][1]=stack[j][1]+1;

                ++i2;
                ++j;
            }

            // if keep current number
            int num=arr1[i];
            while(top>=0&&stack[top][0]>=num)--top;
            if(top>=0){
                // new element: {num,stack[top][1]}
                int x=stack[top][1];

                int u=0;
                top=-1;

                while(u<=t2&&tmp[u][0]<num){
                    ++top;
                    stack[top][0]=tmp[u][0];
                    stack[top][1]=tmp[u][1];
                    ++u;
                }

                ++top;
                stack[top][0]=num;
                stack[top][1]=x;

                while(u<=t2&&tmp[u][1]>=x)++u;

                while(u<=t2){
                    ++top;
                    stack[top][0]=tmp[u][0];
                    stack[top][1]=tmp[u][1];
                    ++u;
                }
            }
            else{
                // top==-1
                for(int u=0;u<=t2;u++){
                    ++top;
                    stack[top][0]=tmp[u][0];
                    stack[top][1]=tmp[u][1];
                }

            }

            t2=-1;

            if(top<0)break;//stop early
        }

        return top>=0?stack[top][1]:-1;
    }
    //84. Largest Rectangle in Histogram https://leetcode.com/problems/largest-rectangle-in-histogram/description/
    public int largestRectangleArea(int[] heights) {
        if (heights== null || heights.length == 0)
        {
            return 0;
        }
        Stack<Integer> stack = new Stack<Integer>();
        int max = 0;
        int i = 0;
        while (i < heights.length)
        {
            //push index to stack when the current height is larger than the previous one
            if (stack.isEmpty() || heights[i] >= heights[stack.peek()])
            {
                stack.push(i);
                i++;
            }
            else
            {
                //calculate max value when the current height is less than the previous one
                int p = stack.pop();
                int h = heights[p];
                int w = stack.isEmpty() ? i : i - stack.peek() - 1;
                max = Math.max(h * w, max);
            }
        }
        while (!stack.isEmpty())
        {
            int p = stack.pop();
            int h = heights[p];
            int w = stack.isEmpty() ? i : i - stack.peek() - 1;
            max = Math.max(h * w, max);
        }
        return max;
    }
    // 164. Maximum Gap https://leetcode.com/problems/maximum-gap/
    public int maximumGap(int[] nums) {
        Arrays.sort(nums);
        if(nums.length<2){
            return 0;
        }
        int c=0;
        for(int i=1;i<nums.length;i++){

            if(nums[i]-nums[i-1]>c){
                c=nums[i]-nums[i-1];
            }
        }
        return c;

    }
    //239. Sliding Window Maximum https://leetcode.com/problems/sliding-window-maximum/
    public int[] maxSlidingWindow(int[] a, int k) {
        if (a == null || k <= 0) {
            return new int[0];
        }
        int n = a.length;
        int[] r = new int[n-k+1];
        int ri = 0;
        // store index
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < a.length; i++) {
            // remove numbers out of range k
            while (!q.isEmpty() && q.peek() < i - k + 1) {
                q.poll();
            }
            // remove smaller numbers in k range as they are useless
            while (!q.isEmpty() && a[q.peekLast()] < a[i]) {
                q.pollLast();
            }
            // q contains index... r contains content
            q.offer(i);
            if (i >= k - 1) {
                r[ri++] = a[q.peek()];
            }
        }
        return r;
    }
    // 315. Count of Smaller Numbers After Self https://leetcode.com/problems/count-of-smaller-numbers-after-self/
    // Wrapper class for each and every value of the input array,
    // to store the original index position of each value, before we merge sort the array
    private class ArrayValWithOrigIdx {
        int val;
        int originalIdx;

        public ArrayValWithOrigIdx(int val, int originalIdx) {
            this.val = val;
            this.originalIdx = originalIdx;
        }
    }

    public List<Integer> countSmaller(int[] nums) {
        if (nums == null || nums.length == 0) return new LinkedList<Integer>();
        int n = nums.length;
        int[] result = new int[n];

        ArrayValWithOrigIdx[] newNums = new ArrayValWithOrigIdx[n];
        for (int i = 0; i < n; ++i) newNums[i] = new ArrayValWithOrigIdx(nums[i], i);

        mergeSortAndCount(newNums, 0, n - 1, result);

        // notice we don't care about the sorted array after merge sort finishes.
        // we only wanted the result counts, generated by running merge sort
        List<Integer> resultList = new LinkedList<Integer>();
        for (int i : result) resultList.add(i);
        return resultList;
    }

    private void mergeSortAndCount(ArrayValWithOrigIdx[] nums, int start, int end, int[] result) {
        if (start >= end) return;

        int mid = (start + end) / 2;
        mergeSortAndCount(nums, start, mid, result);
        mergeSortAndCount(nums, mid + 1, end, result);

        // left subarray start...mid
        // right subarray mid+1...end
        int leftPos = start;
        int rightPos = mid + 1;
        LinkedList<ArrayValWithOrigIdx> merged = new LinkedList<ArrayValWithOrigIdx>();
        int numElemsRightArrayLessThanLeftArray = 0;
        while (leftPos < mid + 1 && rightPos <= end) {
            if (nums[leftPos].val > nums[rightPos].val) {
                // this code block is exactly what the problem is asking us for:
                // a number from the right side of the original input array, is smaller
                // than a number from the left side
                //
                // within this code block,
                // nums[rightPos] is smaller than the start of the left sub-array.
                // Since left sub-array is already sorted,
                // nums[rightPos] must also be smaller than the entire remaining left sub-array
                ++numElemsRightArrayLessThanLeftArray;

                // continue with normal merge sort, merge
                merged.add(nums[rightPos]);
                ++rightPos;
            } else {
                // a number from left side of array, is smaller than a number from
                // right side of array
                result[nums[leftPos].originalIdx] += numElemsRightArrayLessThanLeftArray;

                // Continue with normal merge sort
                merged.add(nums[leftPos]);
                ++leftPos;
            }
        }

        // part of normal merge sort, if either left or right sub-array is not empty,
        // move all remaining elements into merged result
        while (leftPos < mid + 1) {
            result[nums[leftPos].originalIdx] += numElemsRightArrayLessThanLeftArray;

            merged.add(nums[leftPos]);
            ++leftPos;
        }
        while (rightPos <= end) {
            merged.add(nums[rightPos]);
            ++rightPos;
        }

        // part of normal merge sort
        // copy back merged result into array
        int pos = start;
        for (ArrayValWithOrigIdx m : merged) {
            nums[pos] = m;
            ++pos;
        }
    }
    //363. Max Sum of Rectangle No Larger Than K https://leetcode.com/problems/max-sum-of-rectangle-no-larger-than-k/description/
    public int maxSumSubmatrix(int[][] matrix, int tar) {
        int n=matrix.length,m=matrix[0].length,i,j,k,l,dp[][] = new int[n][m],val,max=Integer.MIN_VALUE,target=tar;
        for(i=0;i<n;i++){
            for(j=0;j<m;j++){
                dp[i][j]=matrix[i][j];
                if(j>0) dp[i][j]+=dp[i][j-1];
            }
        }
        for(i=0;i<n;i++){
            for(j=0;j<m;j++){
                if(i>0) dp[i][j]+=dp[i-1][j];
            }
        }
        for(i=0;i<n;i++){
            for(j=0;j<m;j++){
                for(k=i;k<n;k++){
                    for(l=j;l<m;l++){
                        val=dp[k][l];
                        if((i-1)>=0 && (j-1)>=0) val += dp[i-1][j-1];
                        if((i-1)>=0) val=val-dp[i-1][l];
                        if((j-1)>=0) val=val-dp[k][j-1];
                        if(val>max && val<=target) max=val;
                    }
                }
            }
        }
        return max;
    }
    // 480. Sliding Window Median https://leetcode.com/problems/sliding-window-median/
    public double[] medianSlidingWindow(int[] nums, int k) {
        int len = nums.length, p = 0;
        double[] sol = new double[len - k + 1];
        boolean flag = (k % 2 == 0);
        List<Integer> list = new ArrayList<>();

        //Insert first k-1 elements into the Arraylist
        for (int j = 0; j < k - 1; j++) list.add(nums[j]);

        //sort the initial list with k-1 elements, later on we will just add and remove elements from this sorted list
        Collections.sort(list);

        for (int i = k - 1; i < len; i++) {
            //Binary search if the element is already present in the list
            //below function returns index if the element is present else it returns the  -(expected position +1) , yeah thats the negative sign
            int expectedindex = Collections.binarySearch(list, nums[i]);

            if (expectedindex > -1) {
                list.add(expectedindex + 1, nums[i]); // add just next to it
            }
            else {
                list.add(Math.abs(expectedindex + 1), nums[i]); // add it in its expected position
            }

            //Insert into the sol list the median according to the value of k
            if (flag) {
                sol[i - k + 1] = list.get((k / 2) - 1) / 2.0 + list.get((k / 2)) / 2.0;
            } else {
                sol[i - k + 1] = list.get((k / 2));
            }

            // when the window slides by one element, we just find its positon in the sorted list and delete it
            int index = Collections.binarySearch(list, nums[p]);
            list.remove(index);
            p++;
        }

        return sol;
    }
    //493. Reverse Pairs https://leetcode.com/problems/reverse-pairs/
    int merge(int[] nums,int low,int mid, int high){
        int count=0;
        int j = mid+1;
        for(int i=low;i<=mid;i++){
            while(j<=high && nums[i]>(2*(long)nums[j])){
                j++;
            }count+=j-(mid+1);
        }
        ArrayList<Integer>arr=new ArrayList<>();
        int left=low,right=mid+1;
        while(left<=mid && right<=high){
            if(nums[left]<=nums[right]){
                arr.add(nums[left++]);
            }else{
                arr.add(nums[right++]);
            }
        }
        while(left<=mid){
            arr.add(nums[left++]);
        }
        while(right<=high){
            arr.add(nums[right++]);
        }
        for(int i=low;i<=high;i++){
            nums[i]=arr.get(i-low);
        }
        return count;
    }
    public int mergeSort(int []nums,int low,int high){
        if(low>=high){
            return 0;
        }
        int mid=(low+high)/2;
        int inv=mergeSort(nums,low,mid);
        inv+=mergeSort(nums,mid+1,high);
        inv+=merge(nums,low,mid,high);
        return inv;
    }
    public int reversePairs(int[]nums){
        return mergeSort(nums,0,nums.length-1);
    }
    //829. Consecutive Numbers Sum https://leetcode.com/problems/consecutive-numbers-sum/
    public int consecutiveNumbersSum(int N) {

        int ans=0;

        for(int k=1;k*k<2*N;k++){

            if((N-k*(k-1)/2)%k==0) ans++;
        }
        return ans;
    }
    //1012. Numbers With Repeated Digits https://leetcode.com/problems/numbers-with-repeated-digits/
    public int numDupDigitsAtMostN(int N) {
        // Transform N + 1 to arrayList
        ArrayList<Integer> L = new ArrayList<Integer>();
        for (int x = N + 1; x > 0; x /= 10)
            L.add(0, x % 10);

        // Count the number with digits < N
        int res = 0, n = L.size();
        for (int i = 1; i < n; ++i)
            res += 9 * A(9, i - 1);

        // Count the number with same prefix
        HashSet<Integer> seen = new HashSet<>();
        for (int i = 0; i < n; ++i) {
            for (int j = i > 0 ? 0 : 1; j < L.get(i); ++j)
                if (!seen.contains(j))
                    res += A(9 - i, n - i - 1);
            if (seen.contains(L.get(i))) break;
            seen.add(L.get(i));
        }
        return N - res;
    }


    public int A(int m, int n) {
        return n == 0 ? 1 : A(m, n - 1) * (m - n + 1);
    }
    //1210. Minimum Moves to Reach Target with Rotations https://leetcode.com/problems/minimum-moves-to-reach-target-with-rotations/
    public int minimumMoves(int[][] grid) {
        int n = grid.length;
        int[][][] dp = new int[n][n][2];

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                for(int k=0;k<2;k++){
                    if(i==0&&j==0&&k==0)
                        dp[i][j][k]=0;
                    else if(grid[i][j]==1)
                        dp[i][j][k]=Integer.MAX_VALUE;
                    else if(k==0&&(j+1>=n||grid[i][j+1]==1))
                        dp[i][j][k]=Integer.MAX_VALUE;
                    else if(k==1&&(i+1>=n||grid[i+1][j]==1))
                        dp[i][j][k]=Integer.MAX_VALUE;
                    else{
                        int choice1 = i-1>=0? dp[i-1][j][k]:Integer.MAX_VALUE;//go down
                        int choice2 = j-1>=0? dp[i][j-1][k]:Integer.MAX_VALUE;//go right
                        int tempMinChoice = Math.min(choice1,choice2);
                        dp[i][j][k] = tempMinChoice==Integer.MAX_VALUE? tempMinChoice:(tempMinChoice+1);
                    }
                }
                //rotate
                if(i+1<n&&j+1<n&&grid[i][j+1]!=1&&grid[i+1][j+1]!=1&&dp[i][j][1]!=Integer.MAX_VALUE&&dp[i][j][0]>dp[i][j][1]+1)
                    dp[i][j][0]=dp[i][j][1]+1;
                if(i+1<n&&j+1<n&&grid[i+1][j]!=1&&grid[i+1][j+1]!=1&&dp[i][j][0]!=Integer.MAX_VALUE&&dp[i][j][1]>dp[i][j][0]+1)
                    dp[i][j][1]=dp[i][j][0]+1;
            }
        }
        return dp[n-1][n-2][0]==Integer.MAX_VALUE? -1:dp[n-1][n-2][0];
    }
    //1862. Sum of Floored Pairs https://leetcode.com/problems/sum-of-floored-pairs/
    public int sumOfFlooredPairs(int[] arr) {

        int i,j,k,max,n=arr.length;
        long sum=0,tot=0;
        Arrays.sort(arr);
        max=arr[n-1];
        int count[]=new int[max+1];
        for(i=0;i<n;i++)
            count[arr[i]]++;
        for(i=1;i<=max;i++)
            count[i]+=count[i-1];
        for(k=0;k<n;k++)
        {
            if(k>0&&arr[k]-arr[k-1]==0)
                continue;
            i=arr[k];
            for(j=2;j<=max/i+1;j++)
            {
                int lower=i*(j-1)-1;
                int upper=i*(j)-1;
                sum+=(count[Math.min(max, upper)]-count[lower])*(j-1);
            }
            tot+=sum*(count[i]-count[i-1]);
            sum=0;
        }
        return (int)(tot%1000000007);
    }
    //1220. Count Vowels Permutation https://leetcode.com/problems/count-vowels-permutation/
    public int countVowelPermutation(int n) {
        long[] vowels = new long[5];
        Arrays.fill(vowels, 1);
        int mod = (int) (Math.pow(10, 9) + 7);
        while (n > 1) {
            long[] temp = new long[5];
            temp[0] = (vowels[1] + vowels[2] + vowels[4]) % mod;
            temp[1] = vowels[0] + vowels[2] % mod;
            temp[2] = vowels[1] + vowels[3] % mod;
            temp[3] = vowels[2] % mod;
            temp[4] = vowels[2] + vowels[3] % mod;
            vowels = temp;
            n--;
        }
        return (int) ((vowels[0] + vowels[1] + vowels[2] + vowels[3] + vowels[4]) % mod);
    }
    //1223. Dice Roll Simulation https://leetcode.com/problems/dice-roll-simulation/
    public int dieSimulator(int n, int[] rollMax) {
        long m = 1000000007L;
        LinkedList<Long>[] c = new LinkedList[6];
        long[] ct = new long[6];
        for (int i = 0; i < 6; i++) {
            ct[i] = 1L;
            c[i] = new LinkedList<Long>();
            for (int j = 1; j < rollMax[i]; j++)
                c[i].addLast(0L);
            c[i].addLast(1L);
        }
        long resp = 6L;
        for (int i = 1; i < n; i++) {
            long tt = 0L;
            for (int j = 0; j < 6; j ++) {
                c[j].addLast((resp - ct[j] + m) % m);
                ct[j] = (ct[j] + c[j].peekLast() - c[j].pollFirst() + m) % m;
                tt += ct[j];
            }
            resp = tt % m;
        }

        return (int)resp;
    }
    //1240. Tiling a Rectangle with the Fewest Squares https://leetcode.com/problems/tiling-a-rectangle-with-the-fewest-squares/
    private int trUtil(int n, int m, int[][] cache)
    {
        if (n > m)
        {
            int temp = n;
            n = m;
            m = temp;
        }

        if (cache[n][m] != 0)
            return cache[n][m];

        if (n == 0)
        {
            cache[0][m] = 0;
            return 0;
        }

        if (n == 1)
        {
            cache[n][m] = m;
            return m;
        }

        if (n == m)
        {
            cache[n][m] = 1;
            return 1;
        }

        if (m % n == 0)
        {
            cache[n][m] = m/n;
            return m/n;
        }

        if (m > 2 * n)
        {
            int num = (m / n) - 1;
            int newM = m - num*n;
            cache[n][m] = num + trUtil(n, newM, cache);
            return cache[n][m];
        }

        cache[n][m] = 1 + trUtil(Math.min(n, m-n), Math.max(n, m-n), cache);


        for (int i = (m+1)/2; i < n; i++)
        {
            for (int j = 0; j <= (m - i); j++)
            {
                cache[n][m] = Math.min(cache[n][m], 2 +
                        trUtil(n-i, i+j, cache) +
                        trUtil(n - (m-i), (m-i) - j, cache) +
                        trUtil(j, i - (m-i), cache));
            }
        }

        return cache[n][m];
    }


    public int tilingRectangle(int n, int m) {
        int temp = Math.min(n, m);
        m = Math.max(n, m);
        n = temp;

        int[][] cache = new int[n+1][m+1];
        return trUtil(n, m, cache);
    }
    //1250. Check If It Is a Good Array https://leetcode.com/problems/check-if-it-is-a-good-array/
    public boolean isGoodArray(int[] nums) {
        //gcd of numbers
        if(nums.length == 0){
            return false;
        }
        int ans = nums[0];
        for(int i = 1;i < nums.length;i++){
            ans = gcd(ans, nums[i]);
        }
        if(ans == 1) return true;
        else return false;
    }
    public int gcd(int a, int b){
        if(a == 0){
            return b;
        }
        return gcd(b % a, a);
    }
    //1269. Number of Ways to Stay in the Same Place After Some Steps https://leetcode.com/problems/number-of-ways-to-stay-in-the-same-place-after-some-steps/
    public int numWays(int steps, int arrLen) {
        if(arrLen == 1){
            return 1;
        }
        long[] dpCurrent = new long[arrLen];
        dpCurrent[0] = 1;
        for(int i=0; i<steps; i++){
            long[] dpNext = new long[arrLen];
            for(int j=0; j<Math.min(i+1, arrLen); j++){
                if(j==0){
                    dpNext[j] = (dpNext[j] + dpCurrent[j])%1000000007;
                    dpNext[j+1] = (dpNext[j+1] + dpCurrent[j])%1000000007;
                }
                else if(j==arrLen-1){
                    dpNext[j] = (dpNext[j] + dpCurrent[j])%1000000007;
                    dpNext[j-1] = (dpNext[j-1] + dpCurrent[j])%1000000007;
                }
                else{
                    dpNext[j] = (dpNext[j] + dpCurrent[j])%1000000007;
                    dpNext[j-1] = (dpNext[j-1] + dpCurrent[j])%1000000007;
                    dpNext[j+1] = (dpNext[j+1] + dpCurrent[j])%1000000007;
                }
            }
            dpCurrent = dpNext;
        }

        return (int)dpCurrent[0];
    }

    //1345. Jump Game IV https://leetcode.com/problems/jump-game-iv/

    public int minJumps(int[] arr) {
        int n = arr.length;
        if(n == 1) return 0;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 0; i < n; i++) {
            List<Integer> list = map.get(arr[i]);
            if(list == null) map.put(arr[i], list = new ArrayList<>());
            list.add(i);
        }
        int[] visited = new int[n];
        Deque<Integer> forward = new LinkedList<>(), backward = new LinkedList<>();
        visited[0] = 1;
        forward.add(0);
        visited[n - 1] = 2;
        backward.add(n - 1);
        for(int res = 1, dir = 1; ; res++) {
            if(forward.size() > backward.size()) {
                Deque<Integer> temp = forward; forward = backward; backward = temp;
                dir = 3 - dir;
            }
            for(int size = forward.size(); size-- > 0; ) {
                int i = forward.poll();
                List<Integer> list = map.get(arr[i]);
                if(i - 1 >= 0) list.add(i - 1);
                if(i + 1 < n) list.add(i + 1);
                for(int j: list) {
                    if(visited[j] == 0) {
                        visited[j] = dir;
                        forward.add(j);
                    } else if(visited[j] != dir) return res;
                }
                list.clear();
            }
        }
    }

    //midl


    //400. Nth Digit https://leetcode.com/problems/nth-digit/
    public int findNthDigit(int n)
    {
        if(n<=9)
            return n;
        long x=9;
        int d=1,p=n,num=0;
        do
        {
            p-=x*d;
            num+=x;
            x=x*10;
            d++;
        }while(p>(x*d));
        num+=p/d;
        if(p%d==0)
            return num%10;
        else
        {
            num++;
            p=p%d;
            x=(int)Math.pow(10,(d-p));
            num/=x;
            return num%10;
        }
    }

    //416. Partition Equal Subset Sum https://leetcode.com/problems/partition-equal-subset-sum/
    public boolean canPartition(int[] nums) {
        int sum  = 0;

        for(int i=0;i<nums.length;i++){
            sum += nums[i];
        }

        if(sum%2 == 0){
            return subsetSum(nums,sum/2,nums.length);
        }

        return false;

    }

    public static boolean subsetSum(int[] arr, int sum,int n){


        boolean[][] dp = new boolean[n+1][sum+1];

        for(int i=0;i<dp.length; i++){
            for(int j=0;j<dp[0].length;j++){

                if(i==0 && j == 0){
                    dp[i][j] = true;
                }
                else if(i==0){
                    dp[i][j] = false;
                }
                else if(j==0){
                    dp[i][j] = false;
                }
                else{
                    if(arr[i-1]<=j){
                        dp[i][j] = dp[i-1][j-arr[i-1]] || dp[i-1][j];
                    }
                    else{
                        dp[i][j] = dp[i-1][j];
                    }
                }

            }
        }

        return dp[n][sum];
    }



    //473. Matchsticks to Square https://leetcode.com/problems/matchsticks-to-square/
    public boolean makesquare(int[] matchsticks) {

        int total = 0;

        for (int i : matchsticks) {
            total += i;
        }

        if (total % 4 != 0) return false; // if we cant make 4 equals sides then theres no way to make a square
        // sort the array and place the largest sides first. required optimization to not TLE
        Arrays.sort(matchsticks);
        return match(matchsticks, matchsticks.length - 1, 0, 0, 0, 0, total / 4);
    }

    public boolean match(int[] matchsticks, int index, int top, int bottom, int left, int right, int target) {

        if (top == target && bottom == target && left == target && right == target) return true;

        if (top > target || bottom > target || left > target || right > target) return false;

        int val = matchsticks[index];

        boolean t = match(matchsticks, index - 1, top + val, bottom, left, right, target);
        if (t) return true;
        boolean b = match(matchsticks, index - 1, top, bottom + val, left, right, target);
        if (b) return true;
        boolean l = match(matchsticks, index - 1, top, bottom, left + val, right, target);
        if (l) return true;
        boolean r = match(matchsticks, index - 1, top, bottom, left, right + val, target);
        if (r) return true;

        return false;
    }

    // 491. Non-decreasing Subsequences https://leetcode.com/problems/non-decreasing-subsequences/
    public List<List<Integer>> findSubsequences(int[] nums) {


        List<List<Integer>> ans = new ArrayList<List<Integer>>(); // ans list to return all distinct possible sub seq
        List <Integer> ls = new ArrayList <Integer>();

        allNonDecreasingSub(ans, ls, nums, 0); // this function will calculate all possible sub Subsequences.

        return ans;

    }


    public void allNonDecreasingSub(List<List<Integer>> ans, List <Integer> ls, int[] A, int index)
    {

        if(index == A.length) // if index becomes the array length than we can say that we genrated a subsequnce
        {
            return; // and returning from the recursive call
        }

        HashSet <Integer> set = new HashSet <Integer>(); // HashSet will help us us to find duplicates recursive calls

        for(int i = index; i < A.length; i++)
        {
            // if set contains the A[i] than we can say made the recursive call for this A[i] in past
            // so our recursive call will not execute for this index.

            if(set.contains(A[i]))
            {
                continue;
            }

            set.add(A[i]); // adding A[i] in set because to check duplicates in future

            if(ls.size() == 0) // if ls size is 0
            {
                ls.add(A[i]); // adding A[i] in ls
                allNonDecreasingSub(ans, ls, A, i + 1); // doing recusive call for the next index to add in ls list

                ls.remove(ls.size() - 1);
                // after recursion is completed during backtraking we will undo the all changes we made during recursion
            }

            else if(ls.size() > 0) // if ls size is greater than 0
            {
                if(A[i] >= ls.get(ls.size() - 1)) // than checking current A[i] >= ls list last element
                {
                    ls.add(A[i]); // adding A[i] in ls

                    List <Integer> tem = new ArrayList <Integer>(ls);
                    ans.add(tem); // updating our ans list because ls list size is now greater than 1

                    allNonDecreasingSub(ans, ls, A, i + 1);// doing recusive call for the next index to add in ls list

                    ls.remove(ls.size() - 1);

                    // after recursion is completed during backtraking we will undo the all changes we made during recursion
                }
            }

        }
    }

    //498. Diagonal Traverse https://leetcode.com/problems/diagonal-traverse/
    public int[] findDiagonalOrder(int[][] mat) {
        if (mat.length < 0)
            return new int[] {};
        int m = mat.length;
        int n = mat[0].length;
        int[] dag = new int[m * n];
        int i = 0;
        int j = 0;
        int max = m * n;
        int count = 0;
        boolean dir = true;
        while (count < dag.length) {
            if (dir) {
                while (i >= 0 && j < n) {
                    dag[count++] = mat[i][j];
                    i--;
                    j++;
                }
                i++;
                if (j >= n) {
                    j = n - 1;
                    i++;
                }
            } else {
                while (i < m && j >= 0) {
                    dag[count++] = mat[i][j];
                    i++;
                    j--;
                }
                j++;
                if (i >= m) {
                    i = m - 1;
                    j++;
                }
            }
            dir = !dir;
        }
        return dag;
    }

    //503. Next Greater Element II https://leetcode.com/problems/next-greater-element-ii/
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int ans[] = new int[n];
        Stack < Integer > st = new Stack < > ();
        for (int i = 2 * n - 1; i >= 0; i--) {
            while (st.isEmpty() == false && st.peek() <= nums[i%n]) {
                st.pop();
            }

            if (i<n) {
                if (st.isEmpty() == false) ans[i] = st.peek();
                else ans[i] = -1;
            }

            st.push(nums[i%n]);
        }
        return ans;
    }



    //526. Beautiful Arrangement https://leetcode.com/problems/beautiful-arrangement/
    public int countArrangement(int n) {
        int[] nums = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            nums[i] = i;
        }
        return recur(nums, 1);
    }

    private int recur(int[] nums, int index) {

        if (index == nums.length) {
            return 1;
        }
        int ans = 0;

        for (int i = index; i < nums.length; i++) {
            swap(nums, index, i);

            if (nums[index] % index == 0 || index % nums[index] == 0) {
                ans += recur(nums, index + 1);
            }
            swap(nums, index, i);
        }
        return ans;
    }

    private void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }

    //556. Next Greater Element III https://leetcode.com/problems/next-greater-element-iii/
    public int nextGreaterElement(int n) {
        String s = String.valueOf(n);
        int arr[] = new int[s.length()];
        int i=0;
        for(char c: s.toCharArray()) {
            arr[i++] = c - '0';
        }
        int index=-1;
        for(i=arr.length-1;i>0;i--) {
            if(arr[i-1]<arr[i]) {
                index = i-1;
                break;
            }
        }
        if(index == -1) return -1;
        for(i=arr.length-1;i>index;i--) {
            if (arr[i]>arr[index]) {
                int temp = arr[index];
                arr[index] = arr[i];
                arr[i] = temp;
                break;
            }
        }
        int l = index+1, r=arr.length-1;
        while(l<r) {
            int temp = arr[l];
            arr[l++] = arr[r];
            arr[r--] = temp;
        }
        int res = 0;
        for(i=0;i<arr.length;i++) {
            if ((Integer.MAX_VALUE-arr[i])/10<res) return -1;
            res = res*10 + arr[i];
        }
        return res;
    }
    //560. Subarray Sum Equals K https://leetcode.com/problems/subarray-sum-equals-k/
    public int subarraySum(int[] nums, int k) {

        int[] prefixSum = new int[nums.length];
        prefixSum[0] = nums[0];

        for(int i = 1; i < nums.length; i++)
            prefixSum[i] = prefixSum[i-1] + nums[i];

        int counter = 0;

        Map<Integer,Integer> map = new HashMap<>();

        map.put(0,1);

        for(int i=0;i<nums.length;i++)
        {
            if(map.containsKey(prefixSum[i] - k))
            {
                counter+= map.get(prefixSum[i]-k);
            }
            if(map.containsKey(prefixSum[i]))
            {
                map.put(prefixSum[i],map.get(prefixSum[i])+1);
            }
            else
                map.put(prefixSum[i], 1);
        }

        return counter;
    }



    //581. Shortest Unsorted Continuous Subarray https://leetcode.com/problems/shortest-unsorted-continuous-subarray/
    public int findUnsortedSubarray(int[] nums) {
        int minVal = Integer.MAX_VALUE, maxVal = Integer.MIN_VALUE;
        int n = nums.length;
        for(int i = 1; i < n; i++) {
            if(nums[i] < nums[i-1]) {
                minVal = Math.min(minVal, nums[i]);
            }
        }
        for(int i = n-2; i >= 0; i--) {
            if(nums[i] > nums[i+1]) {
                maxVal = Math.max(maxVal, nums[i]);
            }
        }
        if(minVal == Integer.MAX_VALUE && maxVal == Integer.MIN_VALUE) return 0;

        int start = 0, end = n-1;
        for(; start < n; start++) {
            if(nums[start] > minVal) break;
        }
        for(; end >= 0; end--) {
            if(nums[end] < maxVal) break;
        }
        return end - start + 1;
    }

    //611. Valid Triangle Number https://leetcode.com/problems/valid-triangle-number/
    public int triangleNumber(int[] nums) {
        if (nums == null || nums.length <= 2) {
            return 0;
        }
        Arrays.sort(nums);

        int[] indexMap = new int[2001];
        for (int i = 0; i < indexMap.length; ++i) {
            indexMap[i] = -1;
        }
        for (int i = 0; i < nums.length; ++i) {
            indexMap[nums[i]] = i;
        }
        for (int i = 1; i < indexMap.length; ++i) {
            if (indexMap[i] == -1) {
                indexMap[i] = indexMap[i - 1];
            }
        }
        int res = 0;
        for (int i = 0; i < nums.length; ++i) {
            for (int j = i + 1; j < nums.length; ++j) {
                if (nums[i] + nums[j] - 1 >= 0 && nums[i] + nums[j] - 1 <= 2000) {
                    res += Math.max(0, indexMap[nums[i] + nums[j] - 1] - j);
                }
            }
        }
        return res;
    }
    //633. Sum of Square Numbers https://leetcode.com/problems/sum-of-square-numbers/
    public boolean judgeSquareSum(int c) {
        for (int i = 2; i * i <= c; i++) {
            int count = 0;
            if (c % i == 0) {
                while (c % i == 0) {
                    count++;
                    c /= i;
                }
                if (i % 4 == 3 && count % 2 != 0)
                    return false;
            }
        }
        return c % 4 != 3;
    }


    //easily

    //476. Number Complement https://leetcode.com/problems/number-complement/
    public int findComplement(int num) {
        int s=0;
        int i=0;
        while(num!=0)
        {
            if((num&1)!=1)
            {
                s=s + (int)Math.pow(2,i);
            }
            i++;
            num=(num>>1);
        }
        return s;
    }
    //492. Construct the Rectangle https://leetcode.com/problems/construct-the-rectangle/
    public int[] constructRectangle(int area) {
        int width = (int) Math.sqrt(area);

        while (area % width > 0)
            --width;

        return new int[] {area / width, width};
    }

    //504. Base 7 https://leetcode.com/problems/base-7/
    public String convertToBase7(int num) {
        return Integer.toString(num, 7);
    }

    //507. Perfect Number https://leetcode.com/problems/perfect-number/
    public boolean checkPerfectNumber(int num) {
        int num1 = 0;
        if(num % 2 != 0){
            return false;
        }else{

            for(int i  = 1 ; i<=num/2 ; i++){
                if(num % i == 0){
                    num1 += i;
                }
            }
        }

        return num1==num;
    }

    //561. Array Partition https://leetcode.com/problems/array-partition/
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int sum=0;
        for(int i=0;i<nums.length;i+=2)
        {
            sum=sum+nums[i];
        }
        return sum;
    }

    //1013. Partition Array Into Three Parts With Equal Sum https://leetcode.com/problems/partition-array-into-three-parts-with-equal-sum/
    public boolean canThreePartsEqualSum(int[] arr) {
        int sum = 0;
        for (int n: arr) {
            sum += n;
        }

        if (sum % 3 != 0) {
            return false;
        }

        int target = sum / 3;
        int currentSum = 0;
        int count = 0;

        for (int i = 0; i < arr.length - 1; i++) {
            currentSum += arr[i];
            if (currentSum == target) {
                currentSum = 0;
                count++;
                if (count == 2) {
                    return true;
                }
            }
        }

        return false;
    }
    //594. Longest Harmonious Subsequence https://leetcode.com/problems/longest-harmonious-subsequence/
    public int findLHS(int[] nums) {
        Arrays.sort(nums);
        int prev = Integer.MIN_VALUE;
        int prevN = 0;
        int curr = nums[0];
        int currN = 0;
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != curr) {
                if (prev+1 == curr)
                    max = Math.max(prevN + currN, max);
                prev = curr;
                prevN = currN;
                curr = nums[i];
                currN = 1;
            } else {
                currN++;
            }
        }
        if (prev+1 == curr)
            max = Math.max(prevN + currN, max);
        return max;
    }
    //628. Maximum Product of Three Numbers https://leetcode.com/problems/maximum-product-of-three-numbers/
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int n=nums.length;
        return Math.max(nums[0]*nums[1]*nums[n-1],nums[n-1]*nums[n-2]*nums[n-3]);
    }
    //693. Binary Number with Alternating Bits https://leetcode.com/problems/binary-number-with-alternating-bits/
    public boolean hasAlternatingBits(int n) {
        String s = Integer.toBinaryString(n);
        char prev = s.charAt(0);
        for(int i=1; i<s.length(); i++) {
            int x = Character.compare(prev, s.charAt(i));
            //System.out.println(x);
            if(x == 0) {
                return false;
            }
            prev = s.charAt(i);
        }
        return true;
    }
    //746. Min Cost Climbing Stairs https://leetcode.com/problems/min-cost-climbing-stairs/
    public int minCostClimbingStairs(int[] cost) {
        int n=cost.length;
        for(int i=2;i<n;i++)
        {
            cost[i]+=Math.min(cost[i-1],cost[i-2]);
        }
        return Math.min(cost[n-1],cost[n-2]);
    }
    //747. Largest Number At Least Twice of Others https://leetcode.com/problems/largest-number-at-least-twice-of-others/
    public int dominantIndex(int[] nums) {
        int result = -1, tmp = -1, ans = -1;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] > result) {
                tmp = result;
                result = nums[i];
                ans = i;
            } else if (nums[i] > tmp)
                tmp = nums[i];
        }
        return result >= tmp * 2 ? ans : -1;
    }
    //762. Prime Number of Set Bits in Binary Representation https://leetcode.com/problems/prime-number-of-set-bits-in-binary-representation/
    public  int calculateSetBits(String s){
        int count=0;
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i)=='1') count++;
        }
        return count;
    }

    public  boolean isPrime(int n){
        if (n==0 || n==1) return false;
        for (int i = 2; i <= n/2; i++) {
            if(n%i ==0 ) return false;
        }
//        System.out.println(n+" - ");
        return true;
    }

    public  int countPrimeSetBits(int left, int right) {
        int count=0;
        for(int i=left;i<=right;i++){
            String b= Integer.toBinaryString(i);

            int n=calculateSetBits(b);

            if(isPrime(n)) count++;
        }
        return count;
    }
    //868. Binary Gap https://leetcode.com/problems/binary-gap/
    public int binaryGap(int n) {
        String bin = Integer.toBinaryString(n);
        int idx = 0, max = -1;
        for (int i = 0; i < bin.length(); i++) {
            char c = bin.charAt(i);
            if (c == '1') {
                int cur = i - idx;
                if (max < cur) {
                    max = cur;
                }
                idx = i;
            }
        }
        return max;
    }
    //976. Largest Perimeter Triangle https://leetcode.com/problems/largest-perimeter-triangle/
    public int largestPerimeter(int[] nums) {
        Arrays.sort(nums);
        int per=0;// case where triangle will not be formed will be handled by per=0;
        for(int i=nums.length-1;i>=2; i--)
        {
            if(nums[i-1]+nums[i-2]>nums[i])
            {
                per=nums[i]+nums[i-1]+nums[i-2];
                break;
            }

        }
        return per;
    }

    //1005. Maximize Sum Of Array After K Negations https://leetcode.com/problems/maximize-sum-of-array-after-k-negations/
    public int largestSumAfterKNegations(int[] nums, int k)
    {
        Arrays.sort(nums);
        int count=0;
        int prev=0;
        for(int i:nums)
        {
            if(i<0)
            {
                if(k>0)
                {
                    count+=Math.abs(i);
                    k--;
                    prev = i;
                }
                else
                {
                    count+=i;
                }
            }
            else if(i==0)
            {
                k=0;
            }
            else
            {
                if(k%2==0)
                {
                    count+=i;
                    k=0;
                }
                else
                {
                    if(prev==0)
                    {
                        count+=(-1*i);
                        k=0;
                    }
                    else
                    {
                        if(prev>i || Math.abs(prev)>i)
                        {
                            count+=(-1*i);
                        }
                        else
                        {
                            count+=prev;
                            count+=(prev + i);
                        }
                        k=0;
                    }
                }
            }
        }
        if(k%2!=0)
        {
            count+=(prev + prev);
        }
        return count;
    }
    //1009. Complement of Base 10 Integer https://leetcode.com/problems/complement-of-base-10-integer/
    public int bitwiseComplement(int n) {
        String bin = Integer.toBinaryString(n);
        String res = "";
        for(char c :bin.toCharArray())
        {
            if( c == '1')
                res += "0";
            else
                res += "1";
        }
        return Integer.parseInt(res, 2);
    }
    //1025. Divisor Game https://leetcode.com/problems/divisor-game/
    public boolean divisorGame(int n) {
        return n % 2 == 0;
    }



}


class Test{
    Solution np = new Solution();

    //1187. Make Array Strictly Increasing https://leetcode.com/problems/make-array-strictly-increasing/
    public void testH1_makeArrayIncreasing(){
        int count = 20;
        int n1 = 0;
        int n2 = 0;

        int result;

        while(count > 0){
            //System.out.println(count + " ");
            n1 = 1 + (int) (Math.random()*2000);
            n2= 1 + (int) (Math.random()*2000);
            int[] arr1 = new int[n1];
            int[] arr2 = new int[n2];
            //System.out.println("arr1");
            for(int i = 0; i < n1; i++){
                arr1[i] = 0 + (int) (Math.random() * 1000000000);
               // System.out.print(arr1[i] + " ");
            }
            System.out.println(" ");
            //System.out.println("arr2");
            for(int i = 0; i < n2; i++){
                arr2[i] = 0 + (int) (Math.random() * 1000000000);
                //System.out.print(arr2[i] + " ");
            }
            System.out.println(" ");



            // output
            result = np.makeArrayIncreasing(arr1, arr2);
            System.out.println(" ");
            System.out.println("Ответ:" + result);

            count--;
        }

    }

    //84. Largest Rectangle in Histogram https://leetcode.com/problems/largest-rectangle-in-histogram/description/
    public void testH2_makeArrayIncreasing(){
        int count = 20;
        int n1 = 0;


        int result;

        while(count > 0){
            //System.out.println(count + " ");
            n1 = 1 + (int) (Math.random()*100000);

            int[] arr1 = new int[n1];

            System.out.println("arr1");
            for(int i = 0; i < n1; i++){
                arr1[i] = 0 + (int) (Math.random() * 10000);
                 System.out.print(arr1[i] + " ");
            }
            System.out.println(" ");


            System.out.println(" ");



            // output
            result = np.largestRectangleArea(arr1);
            System.out.println(" ");
            System.out.println("Ответ:" + result);

            count--;
        }

    }
    // 164. Maximum Gap https://leetcode.com/problems/maximum-gap/
    public void testH3_makeArrayIncreasing(){
        int count = 20;
        int n1 = 0;


        int result;

        while(count > 0){
            //System.out.println(count + " ");
            n1 = 1 + (int) (Math.random()*100000);

            int[] arr1 = new int[n1];

            System.out.println("arr1");
            for(int i = 0; i < n1; i++){
                arr1[i] = 0 + (int) (Math.random() * 1000000000);
                System.out.print(arr1[i] + " ");
            }
            System.out.println(" ");


            System.out.println(" ");



            // output
            result = np.maximumGap(arr1);
            System.out.println(" ");
            System.out.println("Ответ:" + result);

            count--;
        }

    }
    //239. Sliding Window Maximum https://leetcode.com/problems/sliding-window-maximum/
    public void testH4_makeArrayIncreasing(){
        int count = 20;
        int n1;
        int n2;



        while(count > 0){
            //System.out.println(count + " ");
            n1 = 1 + (int) (Math.random()*10);

            int[] arr1 = new int[n1];
            //int[] result = new int[n1];
            n2 = 1 + (int) (Math.random()*10);
            while(n2 >= n1){
            n2 = 1 + (int) (Math.random()*10);
            }
            System.out.println("n2=" + n2);
            System.out.println("arr1");
            for(int i = 0; i < n1; i++){
                arr1[i] = -10000 + (int) (Math.random() * 100000);
                System.out.print(arr1[i] + " ");
            }
            System.out.println(" ");


            System.out.println(" ");



            // output
            //result=np.maxSlidingWindow(arr1, n2);
            System.out.println(" ");
            System.out.println(" ");
            System.out.println("Ответ:" );
            for(int g=0; g<np.maxSlidingWindow(arr1, n2).length;g++){
                System.out.println(np.maxSlidingWindow(arr1, n2)[g]);
            }
            //System.out.println("Ответ:" + np.maxSlidingWindow(arr1, n2));

            count--;
        }

    }
    // 315. Count of Smaller Numbers After Self https://leetcode.com/problems/count-of-smaller-numbers-after-self/
    public void testH5_makeArrayIncreasing(){
        int count = 20;
        int n1 = 0;


        int result;

        while(count > 0){
            //System.out.println(count + " ");
            n1 = 1 + (int) (Math.random()*100000);

            int[] arr1 = new int[n1];

            System.out.println("arr1");
            for(int i = 0; i < n1; i++){
                arr1[i] = -10000 + (int) (Math.random() * 10000);
                System.out.print(arr1[i] + " ");
            }
            System.out.println(" ");


            System.out.println(" ");



            // output
            //result = np.countSmaller(arr1);
            System.out.println(" ");
            System.out.println("Ответ:" + np.countSmaller(arr1));

            count--;
        }

    }
    //363. Max Sum of Rectangle No Larger Than K https://leetcode.com/problems/max-sum-of-rectangle-no-larger-than-k/description/
    public void testH6_makeArrayIncreasing(){
        int count = 20;
        int n1 = 0;
        int n2=0;
        int n3=0;
        int result;

        while(count > 0){
            //System.out.println(count + " ");
            n1 = 1 + (int) (Math.random()*100);
            n2 = 1 + (int) (Math.random()*100);
            n3 = -100000 + (int) (Math.random()*100000);
            int[][] arr1 = new int[n1][n2];
            System.out.println("k="+ n3);
            System.out.println("arr1");
            for(int i = 0; i < n1; i++){
                for(int j = 0; j < n2; j++){
                arr1[i][j] = -100 + (int) (Math.random() * 100);
                System.out.print(arr1[i][j] + " ");}
                System.out.println();
            }
            System.out.println(" ");


            System.out.println(" ");



            // output
            result = np.maxSumSubmatrix(arr1, n3);
            System.out.println(" ");
            System.out.println("Ответ:" + result);

            count--;
        }

    }
    // 480. Sliding Window Median https://leetcode.com/problems/sliding-window-median/
    public void testH7_makeArrayIncreasing(){
        int count = 20;
        int n1 = 0;
        int n2 = 0;

        double result;

        while(count > 0){
            //System.out.println(count + " ");
            n1 = 1 + (int) (Math.random() * 10);
            n2 = 1 + (int) (Math.random() * 10);
            while(n2 > n1){
                n2 = 1 + (int) (Math.random() * 10);
            }

            int[] arr1 = new int[n1];

            System.out.println("arr1");
            for(int i = 0; i < n1; i++){
                arr1[i] = -2147483648 + (int) (Math.random() * (1073741824*2));
                System.out.print(arr1[i] + " ");
            }
            System.out.println(" ");


            System.out.println(" ");



            // output
            //result = np.largestRectangleArea(arr1);
            System.out.println(" ");
            System.out.println("Ответ:" );
            for(int g=0; g<np.medianSlidingWindow(arr1, n2).length;g++){
                System.out.println(np.medianSlidingWindow(arr1, n2)[g]);
            }




            count--;
        }

    }
    //493. Reverse Pairs https://leetcode.com/problems/reverse-pairs/
    public void testH8_makeArrayIncreasing(){
        int count = 20;
        int n1 = 0;


        int result;

        while(count > 0){
            //System.out.println(count + " ");
            n1 = 1 + (int)(Math.random()*50000);

            int[] arr1 = new int[n1];

            System.out.println("arr1");
            for(int i = 0; i < n1; i++){
                arr1[i] = (1073741824*2) + (int) (Math.random() * (1073741824*2-1));
                System.out.print(arr1[i] + " ");
            }
            System.out.println(" ");


            System.out.println(" ");



            // output
            result = np.reversePairs(arr1);
            System.out.println(" ");
            System.out.println("Ответ:" + result);

            count--;
        }

    }
    //829. Consecutive Numbers Sum https://leetcode.com/problems/consecutive-numbers-sum/
    public void testH9_makeArrayIncreasing(){
        int count = 20;
        int n1 = 0;


        int result;

        while(count > 0){
            //System.out.println(count + " ");
            n1 = 1 + (int) (Math.random()*1000000000);




            System.out.println("n="+n1);


            System.out.println(" ");



            // output
            result = np.consecutiveNumbersSum(n1);
            System.out.println(" ");
            System.out.println("Ответ:" + result);

            count--;
        }

    }
    //1012. Numbers With Repeated Digits https://leetcode.com/problems/numbers-with-repeated-digits/
    public void testH10_makeArrayIncreasing(){
        int count = 20;
        int n1 = 0;


        int result;

        while(count > 0){
            //System.out.println(count + " ");
            n1 = 1 + (int) (Math.random()*1000000000);




            System.out.println("n="+n1);


            System.out.println(" ");



            // output
            result = np.numDupDigitsAtMostN(n1);
            System.out.println(" ");
            System.out.println("Ответ:" + result);

            count--;
        }

    }
    //1210. Minimum Moves to Reach Target with Rotations https://leetcode.com/problems/minimum-moves-to-reach-target-with-rotations/
    public void testH11_makeArrayIncreasing(){
        int count = 20;
        int n1 = 0;
        int n2 = 0;

        int result;

        while(count > 0){
            //System.out.println(count + " ");
            n1 = 1 + (int) (Math.random()*100);

            int[][] arr1 = new int[n1][n1];

            System.out.println("arr1");
            for(int i = 0; i < n1; i++){
                for(int j = 0; j < n1; j++){
                    if(i==0&&(j==0||j==1)){arr1[i][j] = 0;}
                arr1[i][j] = 0 + (int) (Math.random() * 1);
                System.out.print(arr1[i][j] + " ");
                }

            }
            System.out.println(" ");


            System.out.println(" ");



            // output
            result = np.minimumMoves(arr1);
            System.out.println(" ");
            System.out.println("Ответ:" + result);

            count--;
        }

    }
    //1862. Sum of Floored Pairs https://leetcode.com/problems/sum-of-floored-pairs/
    public void testH12_makeArrayIncreasing(){
        int count = 20;
        int n1 = 0;


        int result;

        while(count > 0){
            //System.out.println(count + " ");
            n1 = 1 + (int) (Math.random()*100000);

            int[] arr1 = new int[n1];

            System.out.println("arr1");
            for(int i = 0; i < n1; i++){
                arr1[i] = 1 + (int) (Math.random() * 100000);
                System.out.print(arr1[i] + " ");
            }
            System.out.println(" ");


            System.out.println(" ");



            // output
            result = np.sumOfFlooredPairs(arr1);
            System.out.println(" ");
            System.out.println("Ответ:" + result);

            count--;
        }

    }
    //1220. Count Vowels Permutation https://leetcode.com/problems/count-vowels-permutation/
    public void testH13_makeArrayIncreasing(){
        int count = 20;
        int n1 = 0;


        int result;

        while(count > 0){
            //System.out.println(count + " ");
            n1 = 1 + (int) (Math.random()*20000);




            System.out.println("n="+n1);


            System.out.println(" ");



            // output
            result = np.countVowelPermutation(n1);
            System.out.println(" ");
            System.out.println("Ответ:" + result);

            count--;
        }

    }
    //1223. Dice Roll Simulation https://leetcode.com/problems/dice-roll-simulation/
    public void testH14_makeArrayIncreasing(){
        int count = 20;
        int n1 = 0;
        int n2 = 0;

        int result;

        while(count > 0){
            //System.out.println(count + " ");
            n1 = 6;
            n2 = 1 + (int) (Math.random()*5000);
            System.out.println("n="+n2);
            int[] arr1 = new int[n1];

            System.out.println("arr1");
            for(int i = 0; i < n1; i++){
                arr1[i] = 1 + (int) (Math.random() * 15);
                System.out.print(arr1[i] + " ");
            }
            System.out.println(" ");


            System.out.println(" ");



            // output
            result = np.dieSimulator(n2,arr1);
            System.out.println(" ");
            System.out.println("Ответ:" + result);

            count--;
        }

    }
    //1240. Tiling a Rectangle with the Fewest Squares https://leetcode.com/problems/tiling-a-rectangle-with-the-fewest-squares/
    public void testH15_makeArrayIncreasing(){
        int count = 20;
        int n1 = 0;
        int n2 = 0;

        int result;

        while(count > 0){
            //System.out.println(count + " ");
            n1 = 1 + (int) (Math.random()*13);
            n2 = 1 + (int) (Math.random()*13);





            System.out.println("n = "+n1+" m ="+n2);


            System.out.println(" ");



            // output
            result = np.tilingRectangle(n1, n2);
            System.out.println(" ");
            System.out.println("Ответ:" + result);

            count--;
        }

    }
    //1250. Check If It Is a Good Array https://leetcode.com/problems/check-if-it-is-a-good-array/
    public void testH16_makeArrayIncreasing(){
        int count = 20;
        int n1 = 0;


        int result;

        while(count > 0){
            //System.out.println(count + " ");
            n1 = 1 + (int) (Math.random()*100000);

            int[] arr1 = new int[n1];

            System.out.println("arr1");
            for(int i = 0; i < n1; i++){
                arr1[i] = 1 + (int) (Math.random() * 1000000000);
                System.out.print(arr1[i] + " ");
            }
            System.out.println(" ");


            System.out.println(" ");



            // output
            //result = np.isGoodArray(arr1);
            System.out.println(" ");
            System.out.println("Ответ:" + np.isGoodArray(arr1));

            count--;
        }

    }
    //1269. Number of Ways to Stay in the Same Place After Some Steps https://leetcode.com/problems/number-of-ways-to-stay-in-the-same-place-after-some-steps/
    public void testH17_makeArrayIncreasing(){
        int count = 20;
        int n1 = 0;
        int n2 = 0;

        int result;

        while(count > 0){
            //System.out.println(count + " ");
            n1 = 1 + (int) (Math.random()*500);
            n2 = 1 + (int) (Math.random()*100000);





            System.out.println("n = "+n1+" m ="+n2);


            System.out.println(" ");



            // output
            result = np.numWays(n1, n2);
            System.out.println(" ");
            System.out.println("Ответ:" + result);

            count--;
        }

    }
    //1345. Jump Game IV https://leetcode.com/problems/jump-game-iv/
    public void testH18_makeArrayIncreasing(){
        int count = 20;
        int n1 = 0;


        int result;

        while(count > 0){
            //System.out.println(count + " ");
            n1 = 1 + (int) (Math.random()*50000);

            int[] arr1 = new int[n1];

            System.out.println("arr1");
            for(int i = 0; i < n1; i++){
                arr1[i] = -100000000 + (int) (Math.random() * 100000000);
                System.out.print(arr1[i] + " ");
            }
            System.out.println(" ");


            System.out.println(" ");



            // output
            result = np.minJumps(arr1);
            System.out.println(" ");
            System.out.println("Ответ:" + result);

            count--;
        }

    }
    //midl
    //midl


    //400. Nth Digit https://leetcode.com/problems/nth-digit/
    public void testM1_makeArrayIncreasing(){
        int count = 20;
        int n1 = 0;


        int result;

        while(count > 0){
            //System.out.println(count + " ");
            n1 = 1 + (int) (Math.random()*(1073741824*2-1));





            System.out.println("n="+n1);


            System.out.println(" ");



            // output
            result = np.findNthDigit(n1);
            System.out.println(" ");
            System.out.println("Ответ:" + result);

            count--;
        }

    }

    //416. Partition Equal Subset Sum https://leetcode.com/problems/partition-equal-subset-sum/
    public void testM2_makeArrayIncreasing(){
        int count = 20;
        int n1 = 0;


        int result;

        while(count > 0){
            //System.out.println(count + " ");
            n1 = 1 + (int) (Math.random()*200);

            int[] arr1 = new int[n1];

            System.out.println("arr1");
            for(int i = 0; i < n1; i++){
                arr1[i] = 1 + (int) (Math.random() * 100);
                System.out.print(arr1[i] + " ");
            }
            System.out.println(" ");


            System.out.println(" ");



            // output

            System.out.println(" ");
            System.out.println("Ответ:" + np.canPartition(arr1));

            count--;
        }

    }






    //473. Matchsticks to Square https://leetcode.com/problems/matchsticks-to-square/
    public void testM6_makeArrayIncreasing(){
        int count = 20;
        int n1 = 0;


        int result;

        while(count > 0){
            //System.out.println(count + " ");
            n1 = 1 + (int) (Math.random()*15);

            int[] arr1 = new int[n1];

            System.out.println("arr1");
            for(int i = 0; i < n1; i++){
                arr1[i] = 1 + (int) (Math.random() * 100000000);
                System.out.print(arr1[i] + " ");
            }
            System.out.println(" ");


            System.out.println(" ");



            // output
            //result = np.makesquare(arr1);
            System.out.println(" ");
            System.out.println("Ответ:" + np.makesquare(arr1));

            count--;
        }

    }



    // 491. Non-decreasing Subsequences https://leetcode.com/problems/non-decreasing-subsequences/

    public void testM7_makeArrayIncreasing(){
        int count = 20;
        int n1 = 0;


        int result;

        while(count > 0){
            //System.out.println(count + " ");
            n1 = 1 + (int) (Math.random()*15);

            int[] arr1 = new int[n1];

            System.out.println("arr1");
            for(int i = 0; i < n1; i++){
                arr1[i] = -100 + (int) (Math.random() * 100);
                System.out.print(arr1[i] + " ");
            }
            System.out.println(" ");


            System.out.println(" ");



            // output
            //result = np.findSubsequences(arr1);
            System.out.println(" ");
            System.out.println("Ответ:" + np.findSubsequences(arr1));

            count--;
        }

    }



    //498. Diagonal Traverse https://leetcode.com/problems/diagonal-traverse/
    public void testM8_makeArrayIncreasing(){
        int count = 20;
        int n1 = 0;


        int result;

        while(count > 0){
            //System.out.println(count + " ");
            n1 = 1 + (int) (Math.random()*10000);
            while(n1*n1>10000||n1*n1<1){
                n1 = 1 + (int) (Math.random()*10000);
            }
            int[][] arr1 = new int[n1][n1];

            System.out.println("arr1");
            for(int i = 0; i < n1; i++){
                for(int j = 0; j < n1; j++){
                arr1[i][j] = -100000000 + (int) (Math.random() * 100000000);
                System.out.print(arr1[i][j] + " ");}
            }
            System.out.println(" ");


            System.out.println(" ");



            // output
            //result = np.findDiagonalOrder(arr1);
            System.out.println(" ");

            System.out.println("Ответ:");
            for(int i = 0; i < n1*n1; i++){
                System.out.print(np.findDiagonalOrder(arr1)[i]+" ");
            }

            count--;
        }

    }

    //503. Next Greater Element II https://leetcode.com/problems/next-greater-element-ii/
    public void testM9_makeArrayIncreasing(){
        int count = 20;
        int n1 = 0;


        int result;

        while(count > 0){
            //System.out.println(count + " ");
            n1 = 1 + (int) (Math.random()*10000);

            int[] arr1 = new int[n1];

            System.out.println("arr1");
            for(int i = 0; i < n1; i++){
                arr1[i] = -1000000000 + (int) (Math.random() * 1000000000);
                System.out.print(arr1[i] + " ");
            }
            System.out.println(" ");


            System.out.println(" ");



            // output
            System.out.println(" ");

            System.out.println("Ответ:");
            for(int i = 0; i < n1; i++){
                System.out.print(np.nextGreaterElements(arr1)[i]+" ");
            }


            count--;
        }

    }



    //526. Beautiful Arrangement https://leetcode.com/problems/beautiful-arrangement/
    public void testM11_makeArrayIncreasing(){
        int count = 20;
        int n1 = 0;


        int result;

        while(count > 0){
            //System.out.println(count + " ");
            n1 = 1 + (int) (Math.random()*15);
            System.out.println("n= "+n1);

            System.out.println(" ");


            System.out.println(" ");



            // output
            result = np.countArrangement(n1);
            System.out.println(" ");
            System.out.println("Ответ:" + result);

            count--;
        }

    }

    //556. Next Greater Element III https://leetcode.com/problems/next-greater-element-iii/
    public void testM12_makeArrayIncreasing(){
        int count = 20;
        int n1 = 0;


        int result;

        while(count > 0){
            //System.out.println(count + " ");
            n1 = 1 + (int) (Math.random()*(1073741824*2-1));


            System.out.println("n="+n1);


            System.out.println(" ");



            // output
            result = np.nextGreaterElement(n1);
            System.out.println(" ");
            System.out.println("Ответ:" + result);

            count--;
        }

    }
    //560. Subarray Sum Equals K https://leetcode.com/problems/subarray-sum-equals-k/
    public void testM13_makeArrayIncreasing(){
        int count = 20;
        int n1 = 0;
        int k = 0;

        int result;

        while(count > 0){
            //System.out.println(count + " ");
            n1 = 1 + (int) (Math.random()*20000);
            k = -10000000 + (int) (Math.random()*10000000);
            System.out.println("k="+k);
            int[] arr1 = new int[n1];

            System.out.println("arr1");
            for(int i = 0; i < n1; i++){
                arr1[i] = -1000 + (int) (Math.random() * 1000);
                System.out.print(arr1[i] + " ");
            }
            System.out.println(" ");


            System.out.println(" ");



            // output
            result = np.subarraySum(arr1,k);
            System.out.println(" ");
            System.out.println("Ответ:" + result);

            count--;
        }

    }



    //581. Shortest Unsorted Continuous Subarray https://leetcode.com/problems/shortest-unsorted-continuous-subarray/
    public void testM15_makeArrayIncreasing(){
        int count = 20;
        int n1 = 0;


        int result;

        while(count > 0){
            //System.out.println(count + " ");
            n1 = 1 + (int) (Math.random()*50000);

            int[] arr1 = new int[n1];

            System.out.println("arr1");
            for(int i = 0; i < n1; i++){
                arr1[i] = -100000 + (int) (Math.random() * 100000);
                System.out.print(arr1[i] + " ");
            }
            System.out.println(" ");


            System.out.println(" ");



            // output
            result = np.findUnsortedSubarray(arr1);
            System.out.println(" ");
            System.out.println("Ответ:" + result);

            count--;
        }

    }

    //611. Valid Triangle Number https://leetcode.com/problems/valid-triangle-number/
    public void testM16_makeArrayIncreasing(){
        int count = 20;
        int n1 = 0;


        int result;

        while(count > 0){
            //System.out.println(count + " ");
            n1 = 1 + (int) (Math.random()*1000);

            int[] arr1 = new int[n1];

            System.out.println("arr1");
            for(int i = 0; i < n1; i++){
                arr1[i] = 0 + (int) (Math.random() * 1000);
                System.out.print(arr1[i] + " ");
            }
            System.out.println(" ");


            System.out.println(" ");



            // output
            result = np.triangleNumber(arr1);
            System.out.println(" ");
            System.out.println("Ответ:" + result);

            count--;
        }

    }
    //633. Sum of Square Numbers https://leetcode.com/problems/sum-of-square-numbers/
    public void testM17_makeArrayIncreasing(){
        int count = 20;
        int n1 = 0;


        int result;

        while(count > 0){
            //System.out.println(count + " ");
            n1 = 0 + (int) (Math.random()*(1073741824*2-1));


            System.out.println("n="+n1);


            System.out.println(" ");



            // output

            System.out.println(" ");
            System.out.println("Ответ:" + np.judgeSquareSum(n1));

            count--;
        }

    }


    //easily

    //476. Number Complement https://leetcode.com/problems/number-complement/
    public void testE1_makeArrayIncreasing(){
        int count = 20;
        int n1 = 0;


        int result;

        while(count > 0){
            //System.out.println(count + " ");
            n1 = 1 + (int) (Math.random()*(1073741824*2-1));


            System.out.println("n="+n1);


            System.out.println(" ");



            // output

            System.out.println(" ");
            System.out.println("Ответ:" + np.findComplement(n1));

            count--;
        }

    }
    //492. Construct the Rectangle https://leetcode.com/problems/construct-the-rectangle/
    public void testE2_makeArrayIncreasing(){
        int count = 20;
        int n1 = 0;


        int result;

        while(count > 0){
            //System.out.println(count + " ");
            n1 = 1 + (int) (Math.random()*(10000000));


            System.out.println("n="+n1);


            System.out.println(" ");



            // output

            System.out.println(" ");
            System.out.println("Ответ:");
            for (int i =0;i<np.constructRectangle(n1).length;i++){
                System.out.print(np.constructRectangle(n1)[i]+" ");
            }
            System.out.println(" ");
            count--;
        }

    }

    //504. Base 7 https://leetcode.com/problems/base-7/
    public void testE3_makeArrayIncreasing(){
        int count = 20;
        int n1 = 0;


        int result;

        while(count > 0){
            //System.out.println(count + " ");
            n1 = -10000000 + (int) (Math.random()*10000000);


            System.out.println("n="+n1);


            System.out.println(" ");



            // output

            System.out.println(" ");
            System.out.println("Ответ:" + np.convertToBase7(n1));

            count--;
        }

    }
    //507. Perfect Number https://leetcode.com/problems/perfect-number/

    public void testE4_makeArrayIncreasing(){
        int count = 20;
        int n1 = 0;


        int result;

        while(count > 0){
            //System.out.println(count + " ");
            n1 = 1 + (int) (Math.random()*100000000);


            System.out.println("n="+n1);


            System.out.println(" ");



            // output

            System.out.println(" ");
            System.out.println("Ответ:" + np.checkPerfectNumber(n1));

            count--;
        }

    }
    //561. Array Partition https://leetcode.com/problems/array-partition/
    public void testE5_makeArrayIncreasing(){
        int count = 20;
        int n1 = 0;


        int result;

        while(count > 0){
            //System.out.println(count + " ");
            n1 = 1 + (int) (Math.random()*10000);

            int[] arr1 = new int[n1*2];

            System.out.println("arr1");
            for(int i = 0; i < n1*2; i++){
                arr1[i] = -10000 + (int) (Math.random() * 10000);
                System.out.print(arr1[i] + " ");
            }
            System.out.println(" ");


            System.out.println(" ");



            // output
            System.out.println(" ");

            System.out.println("Ответ:");

            System.out.print(np.arrayPairSum(arr1));

            System.out.println(" ");

            count--;
        }

    }

    //1013. Partition Array Into Three Parts With Equal Sum https://leetcode.com/problems/partition-array-into-three-parts-with-equal-sum/
    public void testE6_makeArrayIncreasing(){
        int count = 20;
        int n1 = 0;


        int result;

        while(count > 0){
            //System.out.println(count + " ");
            n1 = 3 + (int) (Math.random()*50000);

            int[] arr1 = new int[n1];

            System.out.println("arr1");
            for(int i = 0; i < n1; i++){
                arr1[i] = -10000 + (int) (Math.random() * 10000);
                System.out.print(arr1[i] + " ");
            }
            System.out.println(" ");


            System.out.println(" ");



            // output
            System.out.println(" ");

            System.out.println("Ответ:");

            System.out.print(np.canThreePartsEqualSum(arr1));
            System.out.println(" ");


            count--;
        }

    }
    //594. Longest Harmonious Subsequence https://leetcode.com/problems/longest-harmonious-subsequence/
    public void testE7_makeArrayIncreasing(){
        int count = 20;
        int n1 = 0;


        int result;

        while(count > 0){
            //System.out.println(count + " ");
            n1 = 1 + (int) (Math.random()*20000);

            int[] arr1 = new int[n1];

            System.out.println("arr1");
            for(int i = 0; i < n1; i++){
                arr1[i] = -1000000000 + (int) (Math.random() * 1000000000);
                System.out.print(arr1[i] + " ");
            }
            System.out.println(" ");


            System.out.println(" ");



            // output
            System.out.println(" ");

            System.out.println("Ответ:");

            System.out.print(np.findLHS(arr1));

            System.out.println(" ");

            count--;
        }

    }
    //628. Maximum Product of Three Numbers https://leetcode.com/problems/maximum-product-of-three-numbers/
    public void testE8_makeArrayIncreasing(){
        int count = 20;
        int n1 = 0;


        int result;

        while(count > 0){
            //System.out.println(count + " ");
            n1 = 3 + (int) (Math.random()*100000);

            int[] arr1 = new int[n1];

            System.out.println("arr1");
            for(int i = 0; i < n1; i++){
                arr1[i] = -1000 + (int) (Math.random() * 1000);
                System.out.print(arr1[i] + " ");
            }
            System.out.println(" ");


            System.out.println(" ");



            // output
            System.out.println(" ");

            System.out.println("Ответ:");

            System.out.print(np.maximumProduct(arr1));

            System.out.println(" ");

            count--;
        }

    }
    //693. Binary Number with Alternating Bits https://leetcode.com/problems/binary-number-with-alternating-bits/
    public void testE9_makeArrayIncreasing(){
        int count = 20;
        int n1 = 0;


        int result;

        while(count > 0){
            //System.out.println(count + " ");
            n1 = 1 + (int) (Math.random()*(1073741824*2-1));


            System.out.println("n="+n1);


            System.out.println(" ");



            // output

            System.out.println(" ");
            System.out.println("Ответ:" + np.hasAlternatingBits(n1));

            count--;
        }

    }
    //746. Min Cost Climbing Stairs https://leetcode.com/problems/min-cost-climbing-stairs/
    public void testE10_makeArrayIncreasing(){
        int count = 20;
        int n1 = 0;


        int result;

        while(count > 0){
            //System.out.println(count + " ");
            n1 = 2 + (int) (Math.random()*1000);

            int[] arr1 = new int[n1];

            System.out.println("arr1");
            for(int i = 0; i < n1; i++){
                arr1[i] = 0 + (int) (Math.random() * 999);
                System.out.print(arr1[i] + " ");
            }
            System.out.println(" ");


            System.out.println(" ");



            // output
            System.out.println(" ");

            System.out.println("Ответ:");

            System.out.print(np.minCostClimbingStairs(arr1));

            System.out.println(" ");

            count--;
        }

    }
    //747. Largest Number At Least Twice of Others https://leetcode.com/problems/largest-number-at-least-twice-of-others/
    public void testE11_makeArrayIncreasing(){
        int count = 20;
        int n1 = 0;


        int result;

        while(count > 0){
            //System.out.println(count + " ");
            n1 = 2 + (int) (Math.random()*50);

            int[] arr1 = new int[n1];

            System.out.println("arr1");
            for(int i = 0; i < n1; i++){
                arr1[i] = 0 + (int) (Math.random() * 100);
                System.out.print(arr1[i] + " ");
            }
            System.out.println(" ");


            System.out.println(" ");



            // output
            System.out.println(" ");

            System.out.println("Ответ:");

            System.out.print(np.dominantIndex(arr1));

            System.out.println(" ");

            count--;
        }

    }
    //762. Prime Number of Set Bits in Binary Representation https://leetcode.com/problems/prime-number-of-set-bits-in-binary-representation/
    public void testE12_makeArrayIncreasing(){
        int count = 20;
        int n1 = 0;
        int n2 = 0;
        int n3;
        int result;

        while(count > 0){
            //System.out.println(count + " ");
            n1 = 0 + (int) (Math.random()*(1073741824*2-1));
            n2 = 0 + (int) (Math.random()*(1073741824*2-1));

            while (Math.abs(n1-n2)>10000||Math.abs(n1-n2)<0){
                n1 = 0 + (int) (Math.random()*(1073741824*2-1));
                n2 = 0 + (int) (Math.random()*(1073741824*2-1));
            }
            if (n1>n2){
                n3=n1;
                n1=n2;
                n2=n3;
            }
            System.out.println("left="+n1);
            System.out.println("right="+n2);

            System.out.println(" ");



            // output

            System.out.println(" ");
            System.out.println("Ответ:" + np.countPrimeSetBits(n1,n2));

            count--;
        }

    }
    //868. Binary Gap https://leetcode.com/problems/binary-gap/
    public void testE13_makeArrayIncreasing(){
        int count = 20;
        int n1 = 0;


        int result;

        while(count > 0){
            //System.out.println(count + " ");
            n1 = 1 + (int) (Math.random()*1000000000);


            System.out.println("n="+n1);


            System.out.println(" ");



            // output

            System.out.println(" ");
            System.out.println("Ответ:" + np.binaryGap(n1));

            count--;
        }

    }
    //976. Largest Perimeter Triangle https://leetcode.com/problems/largest-perimeter-triangle/
    public void testE14_makeArrayIncreasing(){
        int count = 20;
        int n1 = 0;


        int result;

        while(count > 0){
            //System.out.println(count + " ");
            n1 = 3 + (int) (Math.random()*10000);

            int[] arr1 = new int[n1];

            System.out.println("arr1");
            for(int i = 0; i < n1; i++){
                arr1[i] = 1 + (int) (Math.random() * 1000000);
                System.out.print(arr1[i] + " ");
            }
            System.out.println(" ");


            System.out.println(" ");



            // output
            System.out.println(" ");

            System.out.println("Ответ:");

            System.out.print(np.largestPerimeter(arr1));

            System.out.println(" ");

            count--;
        }

    }

    //1005. Maximize Sum Of Array After K Negations https://leetcode.com/problems/maximize-sum-of-array-after-k-negations/
    public void testE15_makeArrayIncreasing(){
        int count = 20;
        int n1 = 0;
        int k=0;

        int result;

        while(count > 0){
            //System.out.println(count + " ");
            n1 = 1 + (int) (Math.random()*10000);
            k=1 + (int) (Math.random()*10000);
            int[] arr1 = new int[n1];
            System.out.println("k="+k);
            System.out.println("arr1");
            for(int i = 0; i < n1; i++){
                arr1[i] = -100 + (int) (Math.random() * 100);
                System.out.print(arr1[i] + " ");
            }
            System.out.println(" ");


            System.out.println(" ");



            // output
            System.out.println(" ");

            System.out.println("Ответ:");

            System.out.print(np.largestSumAfterKNegations(arr1,k));

            System.out.println(" ");

            count--;
        }

    }
    //1009. Complement of Base 10 Integer https://leetcode.com/problems/complement-of-base-10-integer/
    public void testE16_makeArrayIncreasing(){
        int count = 20;
        int n1 = 0;


        int result;

        while(count > 0){
            //System.out.println(count + " ");
            n1 = 0 + (int) (Math.random()*1000000000);


            System.out.println("n="+n1);


            System.out.println(" ");



            // output

            System.out.println(" ");
            System.out.println("Ответ:" + np.bitwiseComplement(n1));

            count--;
        }

    }
    //1025. Divisor Game https://leetcode.com/problems/divisor-game/
    public void testE17_makeArrayIncreasing(){
        int count = 20;
        int n1 = 0;


        int result;

        while(count > 0){
            //System.out.println(count + " ");
            n1 = 1 + (int) (Math.random()*1000);


            System.out.println("n="+n1);


            System.out.println(" ");



            // output

            System.out.println(" ");
            System.out.println("Ответ:" + np.divisorGame(n1));

            count--;
        }

    }



}


public class Main {
    public static void main(String[] args) {
        Test task = new Test();
        //hard//
        //1187. Make Array Strictly Increasing https://leetcode.com/problems/make-array-strictly-increasing/
        //task.testH1_makeArrayIncreasing();
        //84. Largest Rectangle in Histogram https://leetcode.com/problems/largest-rectangle-in-histogram/description/
        //task.testH2_makeArrayIncreasing();
        // 164. Maximum Gap https://leetcode.com/problems/maximum-gap/
        //task.testH3_makeArrayIncreasing();
        //239. Sliding Window Maximum https://leetcode.com/problems/sliding-window-maximum/
        //task.testH4_makeArrayIncreasing();
        // 315. Count of Smaller Numbers After Self https://leetcode.com/problems/count-of-smaller-numbers-after-self/
        //task.testH5_makeArrayIncreasing();
        //363. Max Sum of Rectangle No Larger Than K https://leetcode.com/problems/max-sum-of-rectangle-no-larger-than-k/description/
        //task.testH6_makeArrayIncreasing();
        // 480. Sliding Window Median https://leetcode.com/problems/sliding-window-median/
        //task.testH7_makeArrayIncreasing();
        //493. Reverse Pairs https://leetcode.com/problems/reverse-pairs/
        //task.testH8_makeArrayIncreasing();
        //829. Consecutive Numbers Sum https://leetcode.com/problems/consecutive-numbers-sum/
        //task.testH9_makeArrayIncreasing();
        //1012. Numbers With Repeated Digits https://leetcode.com/problems/numbers-with-repeated-digits/
        //task.testH10_makeArrayIncreasing();
        //1210. Minimum Moves to Reach Target with Rotations https://leetcode.com/problems/minimum-moves-to-reach-target-with-rotations/
        //task.testH11_makeArrayIncreasing();
        //1862. Sum of Floored Pairs https://leetcode.com/problems/sum-of-floored-pairs/
        //task.testH12_makeArrayIncreasing();
        //1220. Count Vowels Permutation https://leetcode.com/problems/count-vowels-permutation/
        //task.testH13_makeArrayIncreasing();
        //1223. Dice Roll Simulation https://leetcode.com/problems/dice-roll-simulation/
        //task.testH14_makeArrayIncreasing();
        //1240. Tiling a Rectangle with the Fewest Squares https://leetcode.com/problems/tiling-a-rectangle-with-the-fewest-squares/
        //task.testH15_makeArrayIncreasing();
        //1250. Check If It Is a Good Array https://leetcode.com/problems/check-if-it-is-a-good-array/
        //task.testH16_makeArrayIncreasing();
        //1269. Number of Ways to Stay in the Same Place After Some Steps https://leetcode.com/problems/number-of-ways-to-stay-in-the-same-place-after-some-steps/
        //task.testH17_makeArrayIncreasing();
        //1345. Jump Game IV https://leetcode.com/problems/jump-game-iv/
        //task.testH18_makeArrayIncreasing();
        //midl
        //midl


        //400. Nth Digit https://leetcode.com/problems/nth-digit/
        //task.testM1_makeArrayIncreasing();

        //416. Partition Equal Subset Sum https://leetcode.com/problems/partition-equal-subset-sum/
        //task.testM2_makeArrayIncreasing();





        //473. Matchsticks to Square https://leetcode.com/problems/matchsticks-to-square/

        //task.testM6_makeArrayIncreasing();


        // 491. Non-decreasing Subsequences https://leetcode.com/problems/non-decreasing-subsequences/

        //task.testM7_makeArrayIncreasing();



        //498. Diagonal Traverse https://leetcode.com/problems/diagonal-traverse/
        //task.testM8_makeArrayIncreasing();

        //503. Next Greater Element II https://leetcode.com/problems/next-greater-element-ii/
        //task.testM9_makeArrayIncreasing();


        //526. Beautiful Arrangement https://leetcode.com/problems/beautiful-arrangement/
        //task.testM11_makeArrayIncreasing();

        //556. Next Greater Element III https://leetcode.com/problems/next-greater-element-iii/
        //task.testM12_makeArrayIncreasing();
        //560. Subarray Sum Equals K https://leetcode.com/problems/subarray-sum-equals-k/
        //task.testM13_makeArrayIncreasing();



        //581. Shortest Unsorted Continuous Subarray https://leetcode.com/problems/shortest-unsorted-continuous-subarray/
        //task.testM15_makeArrayIncreasing();

        //611. Valid Triangle Number https://leetcode.com/problems/valid-triangle-number/
        //task.testM16_makeArrayIncreasing();
        //633. Sum of Square Numbers https://leetcode.com/problems/sum-of-square-numbers/
       // task.testM17_makeArrayIncreasing();


        //easily

        //476. Number Complement https://leetcode.com/problems/number-complement/
        //task.testE1_makeArrayIncreasing();
        //492. Construct the Rectangle https://leetcode.com/problems/construct-the-rectangle/
        //task.testE2_makeArrayIncreasing();

        //504. Base 7 https://leetcode.com/problems/base-7/
        //task.testE3_makeArrayIncreasing();
        //507. Perfect Number https://leetcode.com/problems/perfect-number/
        //task.testE4_makeArrayIncreasing();

        //561. Array Partition https://leetcode.com/problems/array-partition/
        //task.testE5_makeArrayIncreasing();

        //1013. Partition Array Into Three Parts With Equal Sum https://leetcode.com/problems/partition-array-into-three-parts-with-equal-sum/
        //task.testE6_makeArrayIncreasing();
        //594. Longest Harmonious Subsequence https://leetcode.com/problems/longest-harmonious-subsequence/
        //task.testE7_makeArrayIncreasing();
        //628. Maximum Product of Three Numbers https://leetcode.com/problems/maximum-product-of-three-numbers/
        //task.testE8_makeArrayIncreasing();
        //693. Binary Number with Alternating Bits https://leetcode.com/problems/binary-number-with-alternating-bits/
        //task.testE9_makeArrayIncreasing();
        //746. Min Cost Climbing Stairs https://leetcode.com/problems/min-cost-climbing-stairs/
        //task.testE10_makeArrayIncreasing();
        //747. Largest Number At Least Twice of Others https://leetcode.com/problems/largest-number-at-least-twice-of-others/
        //task.testE11_makeArrayIncreasing();
        //762. Prime Number of Set Bits in Binary Representation https://leetcode.com/problems/prime-number-of-set-bits-in-binary-representation/
        //task.testE12_makeArrayIncreasing();
        //868. Binary Gap https://leetcode.com/problems/binary-gap/
        //task.testE13_makeArrayIncreasing();
        //976. Largest Perimeter Triangle https://leetcode.com/problems/largest-perimeter-triangle/
        //task.testE14_makeArrayIncreasing();

        //1005. Maximize Sum Of Array After K Negations https://leetcode.com/problems/maximize-sum-of-array-after-k-negations/
        //task.testE15_makeArrayIncreasing();
        //1009. Complement of Base 10 Integer https://leetcode.com/problems/complement-of-base-10-integer/
        //task.testE16_makeArrayIncreasing();
        //1025. Divisor Game https://leetcode.com/problems/divisor-game/
        task.testE17_makeArrayIncreasing();

    }
}
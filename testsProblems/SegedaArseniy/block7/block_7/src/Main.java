import java.util.Arrays;

class Solution {
//    easy
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
//  medium
    public void sortColors(int[] nums) {
        Arrays.sort(nums);
        System.out.print("[");
        for(int i = 0; i < nums.length; i++){
            System.out.print(nums[i]);
            if(i != nums.length-1)
                System.out.print(",");
        }
        System.out.print("]");
    }
//    hard
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                --right;
            }
        }
        return nums[left];
    }
}

class Test{
    Solution np = new Solution();
    void test1_searchInsert(){
        int count = 20;
        while(count > 0){
            int n = 1 + (int) (Math.random() * 10000);
            int[] massiv = new int[n];
            System.out.print("Входные данные");
            for(int i = 0; i < n; i++){
                massiv[i] = -10000 + (int) (Math.random() * 10000);
                System.out.print(massiv[i] + " ");
            }
            System.out.println(" ");
            int target = -10000 + (int) (Math.random() * 10000);
            System.out.print("target = ");
            System.out.println(target);
            int result = np.searchInsert(massiv, target);
            System.out.print("Выходные данные: ");
            System.out.println(result);
            count--;
        }
    }
    void test2_sortColors(){
        int count = 20;
        while(count > 0){
            int n = 1 + (int) (Math.random() * 300);
            int[] massiv = new int[n];
            System.out.print("Входные данные: ");
            for(int i = 0; i < n; i++){
                massiv[i] = 0 + (int) (Math.random() * 3);
                System.out.print(massiv[i] + " ");
            }
            System.out.println(" ");
            System.out.print("Выходные данные: ");
            np.sortColors(massiv);
            System.out.println(" ");
            count--;
        }
    }
    void test3_findMin(){
        int count = 20;
        while(count > 0){
            int n = 1 + (int) (Math.random() * 5000);
            int[] massiv = new int[n];
            System.out.print("Входные данные: ");
            for(int i = 0; i < n; i++){
                massiv[i] = -5000 + (int) (Math.random() * 5000);
                System.out.print(massiv[i] + " ");
            }
            System.out.println(" ");
            System.out.print("Выходные данные: ");
            int result = np.findMin(massiv);
            System.out.print(result);
            System.out.println(" ");
            count--;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Test test = new Test();
//        test.test1_searchInsert();
//        test.test2_sortColors();
        test.test3_findMin();
    }
}
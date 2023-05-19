import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
    // easy
    public int[] plusOne(int[] digits) {
        int i = digits.length - 1;
        while (i >=0 && digits[i] == 9) {
            i --;
        }
        if (i == -1) {
            int[] result = new int[digits.length + 1];
            result[0] = 1;
            return result;
        }
        int[] result = new int[digits.length];
        result[i] = digits[i] + 1;
        for (int j = 0; j < i; j ++) {
            result[j] = digits[j];
        }
        return result;
    }

    // medium
    public ListNode sortList(ListNode head) {
        if(head == null)
            return null;
        ListNode result = new ListNode();
        ListNode temp = head;
        int n = 0;
        while(temp != null){
            temp = temp.next;
            n++;
        }
        int[] massiv = new int[n];
        for(int i = 0; i < n; i++){
            massiv[i] = head.val;
            head = head.next;
        }
        Arrays.sort(massiv);
        ListNode res = result;
        for(int i = 0; i < n; i++){
            ListNode tmp = new ListNode();
            result.val = massiv[i];
            if(n-1 != i){
                result.next = tmp;
                result = result.next;
            }
        }

        return res;
    }

    // hard
    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, (a,b) -> a[1] - b[1]);
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        int total = 0;
        for (int[] course : courses) {
            int dur = course[0], end = course[1];
            if (dur + total <= end) {
                total += dur;
                pq.add(dur);
            } else if (pq.size() > 0 && pq.peek() > dur) {
                total += dur - pq.poll();
                pq.add(dur);
            }
        }
        return pq.size();
    }
}

class Test{
    Solution np = new Solution();
    public void test1_plusOne() {
        int count = 20;
        while (count > 0){
            int n = 1 + (int) (Math.random() * 100);
            int[] massiv = new int[n];
            massiv[0] = 1 + (int) (Math.random() * 9);
            System.out.print("Входные данные: ");
            for (int i = 1; i < n; i++) {
                massiv[i] = 0 + (int) (Math.random() * 9);
                System.out.print(massiv[i]);
            }
            massiv = np.plusOne(massiv);
            System.out.println(" ");
            System.out.print("Выходные данные: ");
            for (int i = 1; i < n; i++) {
                System.out.print(massiv[i]);
            }
            System.out.println(" ");
            count--;
        }
    }
    public void test2_sortList(){
        int count = 20;
        while(count > 0){
            int n = 1 + (int) (Math.random() * 1000);
            int[] massiv = new int[n];
            System.out.print("Входные данные: ");
            ListNode list = new ListNode();
            ListNode temp1 = list;
            for (int i = 0; i < n; i++) {
                ListNode temp = new ListNode();
                massiv[i] = -100 + (int) (Math.random() * 100);
                list.val = massiv[i];
                list.next = temp;
                list = list.next;
                System.out.print(massiv[i] + ", ");
            }
            ListNode result = new ListNode();
            result = np.sortList(temp1);
            System.out.println(" ");
            System.out.print("Результат: ");
            for(int i = 0; i < n; i++){
                System.out.print(result.val + ", ");
                result = result.next;
            }
            System.out.println(" ");
            count--;
        }
    }
    public void test3_largestPalindrome(){
        int count = 20;
        while(count > 0){
            int n = 1 + (int) (Math.random() * 10000);
//            int n = 5;
            int[][] massiv = new int[n][2];
            System.out.print("Входные данные: ");
            System.out.print("[ ");
            for (int i = 0; i < n; i++) {
                System.out.print("[ ");
                for(int j = 0; j < 2; j++){
                    massiv[i][j] = 1 + (int) (Math.random() * 10000);
                    System.out.print(massiv[i][j] + " ");
                }
                System.out.print("]");
            }
            System.out.print(" ]");

            int result = np.scheduleCourse(massiv);
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
//        test.test1_plusOne();
//        test.test2_sortList();
        test.test3_largestPalindrome();
    }
}
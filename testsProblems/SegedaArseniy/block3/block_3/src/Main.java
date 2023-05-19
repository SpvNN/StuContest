import java.beans.PropertyEditorSupport;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

class Solution {
    // 1) easy
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        ListNode result;
        ListNode temp;
        if (list1.val < list2.val) {
            temp = result = new ListNode(list1.val);
            list1 = list1.next;
        } else {
            temp = result = new ListNode(list2.val);
            list2 = list2.next;
        }
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                temp.next = new ListNode(list1.val);
                list1 = list1.next;
            } else {
                temp.next = new ListNode(list2.val);
                list2 = list2.next;
            }
            temp = temp.next;
        }
        while (list1 != null) {
            temp.next = new ListNode(list1.val);
            list1 = list1.next;
            temp = temp.next;
        }
        while (list2 != null) {
            temp.next = new ListNode(list2.val);
            list2 = list2.next;
            temp = temp.next;
        }
        return result;
    }

    // 2) medium
    public void rotate(int[][] matrix) {
        int[][] temp = new int[matrix.length][matrix[0].length];
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                temp[i][j] = matrix[i][j];
            }
        }
        System.out.print("[");
        for(int i = 0; i < matrix.length; i++){
            System.out.print("[");
            for(int j = 0; j < matrix[i].length; j++){
                matrix[i][j] = temp[matrix[i].length - 1 - j][i];
                System.out.print(matrix[i][j]);
                if(j != matrix[i].length-1) System.out.print(",");
            }
            System.out.print("]");
            if(i != matrix.length-1) System.out.print(",");
        }
        System.out.print("]");
    }

    // 3) hard
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) {
            return head;
        }
        ListNode res = new ListNode(0);
        res.next = head;

        ListNode start = res;
        ListNode end = head;
        int count = 0;
        while (end != null) {
            count++;
            if (count % k == 0) {
                start = reverse(start, end.next);
                end = start.next;
            } else {
                end = end.next;
            }
        }

        return res.next;
    }
    private ListNode reverse(ListNode start, ListNode end) {
        ListNode curr = start.next;
        ListNode prev = start;
        ListNode first = curr;
        while (curr != end){
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        start.next = prev;
        first.next = curr;
        return first;
    }
}

class Test{
    Solution np = new Solution();
    int count;
    int n = 0;
    void test1_mergeTwoLists(){
        count = 20;
        ListNode list1 = new ListNode();
        ListNode list2 = new ListNode();
        ListNode list1_tmp;
        ListNode list2_tmp;
        ListNode result;
        n = 0;
        // 1) easy
        while(count > 0){
            n = 0 + (int) (Math.random() * 50);
            int[] mas = new int[n];
            // list1
            for(int i = 0; i < n; i++){
                mas[i] = -100 + (int) (Math.random() * 100);
            }
            Arrays.sort(mas);
            list1_tmp = list1;
            System.out.print("Лист1 - ");
            for(int i = 0; i < n; i++){
                ListNode temp = new ListNode();
                list1.val = mas[i];
                list1.next = temp;
                System.out.print(list1.val);
                list1 = list1.next;
            }
            System.out.println(" ");
            // list2
            for(int i = 0; i < n; i++){
                mas[i] = 0;
                mas[i] = -100 + (int) (Math.random() * 100);
            }
            Arrays.sort(mas);
            list2_tmp = list2;
            System.out.print("Лист2 - ");
            for(int i = 0; i < n; i++){
                ListNode temp = new ListNode();
                list2.val = mas[i];
                list2.next = temp;
                System.out.print(list2.val);
                list2 = list2.next;
            }
            System.out.println(" ");
            result = np.mergeTwoLists(list1_tmp, list2_tmp);
            System.out.print("Ответ: ");
            while(result.next.next != null){
                System.out.print(result.val + " ");
                result = result.next;
            }
            System.out.println(" ");
            count--;
        }
    }
    void test2_rotate(){
        count = 20;
        while(count > 0){
            n = 1 + (int) (Math.random() * 20);
            int[][] matrix = new int[n][n];
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    matrix[i][j] = -1000 + (int) (Math.random() * 1000);
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.println(" ");
            }
            System.out.print("Ответ: ");
            np.rotate(matrix);
            System.out.println(" ");
            count--;
        }
    }
    void test3_reverseKGroup(){
        count = 20;
        int k = 0;
        while(count > 0){
            ListNode result;
            ListNode head = new ListNode();
            ListNode head_tmp;
            k = 1 + (int) (Math.random() * 5000);
            n = k + (int) (Math.random() * 5000);
            int[] mas = new int[n];
            for(int i = 0; i < n; i++){
                mas[i] = 0 + (int) (Math.random() * 1000);
            }
            Arrays.sort(mas);
            head_tmp = head;
            // заполняем список из массива
            for(int i = 0; i < n; i++){
                ListNode temp = new ListNode();
                head.val = mas[i];
                head.next = temp;
                head = head.next;
            }
            ListNode temp = head_tmp;
            System.out.println(" ");
            System.out.println("k = " + k);
            for(int i = 0; i < n; i++){
                System.out.print(temp.val + " ");
                temp = temp.next;
            }
            // тестируем
            result = np.reverseKGroup(head_tmp, k);
            // результаты
            System.out.println(" ");
            System.out.print("Ответ: ");
            while(result.next != null){
                System.out.print(result.val + " ");
                result = result.next;
            }
            count--;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Test task = new Test();
//        task.test1_mergeTwoLists();
//        task.test2_rotate();

        task.test3_reverseKGroup();
    }
}
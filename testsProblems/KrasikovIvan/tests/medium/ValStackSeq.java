package medium;

import java.net.Inet4Address;
import java.util.*;

public class ValStackSeq {
    public static int[] convertToArray(List<Integer> list)
    {
        int[] array = new int[list.size()];
        for(int i = 0; i < list.size(); i++){
            array[i] = list.get(i);
        }
        return array;
    }

    static public boolean solution(int[] pushed, int[] popped){
        Stack<Integer> stack = new Stack<>();
        int len = 0;
        for(int e: pushed){
            stack.push(e);
            while(!stack.isEmpty() && len < popped.length && stack.peek() == popped[len]){
                len++;
                stack.pop();
            }
        }
        return len==popped.length;
    }

    static public Map<List<List<Integer>>, Boolean> testGeneration(int count){
        Map<List<List<Integer>>, Boolean> result = new HashMap<>();
        for (int i = 0; i < count; i++){
            int len = 1 + (int)(Math.random()*1000);
            Stack<Integer> stack = new Stack<>();
            List<Integer> pushed = new ArrayList<>();
            List<Integer> popped = new ArrayList<>();
            for(int j = 0; j<len; j++) {
                pushed.add(stack.push(j + 1));
                if(((int)(Math.random()*2)) == 1 && !stack.isEmpty())
                    popped.add(stack.pop());
            }
            for(int e = len-popped.size(); !stack.isEmpty(); e++){
                popped.add(stack.pop());
            }
            if(((int)(Math.random()*3)) == 1){
                Collections.swap(popped, ((int)(Math.random()*(popped.size()-1))), ((int)(Math.random()*(popped.size()-2))));
            }
            List<List<Integer>> tmp = new ArrayList<>();
            tmp.add(pushed);
            tmp.add(popped);
            result.put(tmp, ValStackSeq.solution(convertToArray(pushed), convertToArray(popped)));
        }
        return result;
    }
}

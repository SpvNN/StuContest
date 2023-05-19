package medium;

import java.util.*;

public class CombSum {
    public static List<List<Integer>> solution(int[] candidates, int target){
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        helpSol(0, candidates, target, res, list);
        return res;
    }
    private static void helpSol(int index, int[] arr, int target, List<List<Integer>> res, List<Integer> list) {
        if(index == arr.length){
            if(target == 0){
                res.add(new ArrayList<>(list));
            }
            return;
        }
        if(arr[index] <= target){
            list.add(arr[index]);
            helpSol(index, arr, target - arr[index], res, list);
            list.remove(list.size() - 1);
        }
        helpSol(index + 1, arr, target, res, list);
    }

    public static Map<Map<int[], Integer>, List<List<Integer>>> testGeneration(int count){
        Map<Map<int[], Integer>, List<List<Integer>>> result = new HashMap<>();
        for(int i = 0; i < count; i++){
            int n = 1 + (int)(Math.random()*30);
            int[] candidates = new int[n];
            for(int j = 0; j < n; j++){
                int m = 2 + (int)(Math.random()*40);
                while(Arrays.asList(candidates).contains(m))
                    m = 2 + (int)(Math.random()*40);
                candidates[j] = m;
            }
            Arrays.sort(candidates);
            int target = 1 + (int)(Math.random()*40);
            Map<int[], Integer> tmp = new HashMap<>();
            tmp.put(candidates, target);
            result.put(tmp, solution(candidates, target));
        }
        return result;
    }
}

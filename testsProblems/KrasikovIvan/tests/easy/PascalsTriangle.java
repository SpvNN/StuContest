package easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class PascalsTriangle {
    static public List<Integer> solution(int rowIndex){
        List<Integer> result = new ArrayList<>();
        result.add(1);
        for(int i = 0; i < rowIndex; i++){
            List<Integer> tmpResult = new ArrayList<>();
            tmpResult.add(1);
            for(int j = 0; j < i; j++){
                tmpResult.add(result.get(j) + result.get(j+1));
            }
            tmpResult.add(1);
            result = tmpResult;
        }
        return result;
    }

    static public Map<Integer, List<Integer>> testGeneration(int count){
        Map<Integer, List<Integer>> result = new HashMap<>();
        for (int i = 0; i < count; i++){
            int n = (int) (Math.random() * 33);
            result.put(n, PascalsTriangle.solution(n));
        }
        return result;
    }
}

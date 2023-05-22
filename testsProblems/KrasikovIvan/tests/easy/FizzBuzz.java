package easy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Легкая задача
public class FizzBuzz {
    static public List<String> solution(int n) {
        List<String> ans = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i % 15 == 0) {
                ans.add("FizzBuzz");
            } else if (i % 3 == 0) {
                ans.add("Fizz");
            } else if (i % 5 == 0) {
                ans.add("Buzz");
            } else {
                ans.add(String.valueOf(i));
            }
        }
        return ans;
    }

    static public Map<Integer, List<String>> testGeneration(int count){
        Map<Integer, List<String>> result = new HashMap<>();
        for (int i = 0; i < count; i++){
            int n = 1 + (int)(Math.random()*10000);
            result.put(n, FizzBuzz.solution(n));
        }
        return result;
    }
}

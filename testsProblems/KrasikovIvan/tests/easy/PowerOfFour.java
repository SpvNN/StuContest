package easy;
import java.util.HashMap;
import java.util.Map;


// Легкая задача
public class PowerOfFour {
    static public boolean solution(int n){
        while(n >= 4){
            if(n % 4 != 0) return false;
            n /= 4;
        }
        return n == 1;
    }

    static public Map<Integer, Boolean> testGeneration(int count){
        Map<Integer, Boolean> result = new HashMap<>();
        for(int i = 0; i < count; i++){
            int n = (int) (-Math.pow(2, 31) + (int)(Math.random() * (Math.pow(2, 31)-1)));
            result.put(n, PowerOfFour.solution(n));
        }
        return result;
    }
}

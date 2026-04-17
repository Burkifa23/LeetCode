import java.util.HashMap;
import java.util.Map;

class Solution {
    public int minMirrorPairDistance(int[] nums) {
        Map<Integer, Integer> reversedMap = new HashMap<>();
        int minDistance = Integer.MAX_VALUE;
        
        for (int i = 0; i < nums.length; i++) {
            if (reversedMap.containsKey(nums[i])) {
                int previousIndex = reversedMap.get(nums[i]);
                minDistance = Math.min(minDistance, i - previousIndex);
            }
            
            
            long reversedNum = reverse(nums[i]);
            
            if (reversedNum <= 1_000_000_000L) {
                reversedMap.put((int) reversedNum, i);
            }
        }
        
        
        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }
    
   
    private long reverse(int num) {
        long reversed = 0;
        while (num > 0) {
            reversed = reversed * 10 + num % 10;
            num /= 10;
        }
        return reversed;
    }
}

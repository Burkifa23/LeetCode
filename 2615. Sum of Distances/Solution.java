import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public long[] distance(int[] nums) {
        int n = nums.length;
        long[] arr = new long[n];
        
        
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }
        
        
        for (List<Integer> indices : map.values()) {
            long totalSum = 0;
            for (int idx : indices) {
                totalSum += idx;
            }
            
            long leftSum = 0;
            long rightSum = totalSum;
            int leftCount = 0;
            int rightCount = indices.size();
            
            for (int idx : indices) {
                rightSum -= idx;
                rightCount--;
                
                
                long leftDist = (long) leftCount * idx - leftSum;
                long rightDist = rightSum - (long) rightCount * idx;
                
                
                arr[idx] = leftDist + rightDist;
                
               
                leftSum += idx;
                leftCount++;
            }
        }
        
        return arr;
    }
}

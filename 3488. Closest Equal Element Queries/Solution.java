import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        int n = nums.length;
        int maxVal = 0;
        
        for (int num : nums) {
            if (num > maxVal) {
                maxVal = num;
            }
        }
        
        
        int[] head = new int[maxVal + 1];
        for (int i = 0; i <= maxVal; i++) {
            head[i] = -1;
        }
        
        int[] next = new int[n];
        
        for (int i = n - 1; i >= 0; i--) {
            next[i] = head[nums[i]];
            head[nums[i]] = i;
        }
        
        int[] ans = new int[n];
        int[] arr = new int[n];
        
        for (int v = 0; v <= maxVal; v++) {
            if (head[v] != -1) {
                int size = 0;
                int curr = head[v];
                
                
                while (curr != -1) {
                    arr[size++] = curr;
                    curr = next[curr];
                }
                
                
                if (size == 1) {
                    ans[arr[0]] = -1;
                } else {
                    for (int i = 0; i < size; i++) {
                        int prevIdx = arr[(i - 1 + size) % size];
                        int nextIdx = arr[(i + 1) % size];
                        int currentIdx = arr[i];
                        
                        
                        int dist1 = (currentIdx - prevIdx + n) % n;
                        int dist2 = (nextIdx - currentIdx + n) % n;
                        
                        ans[currentIdx] = Math.min(dist1, dist2);
                    }
                }
            }
        }
        
        
        List<Integer> result = new ArrayList<>(queries.length);
        for (int q : queries) {
            result.add(ans[q]);
        }
        
        return result;
    }
}

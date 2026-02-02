import java.util.TreeMap;

class Solution {

    private TreeMap<Integer, Integer> top = new TreeMap<>();
    private TreeMap<Integer, Integer> bot = new TreeMap<>();
    private long topSum = 0;
    private int topSize = 0;
    private int L;

    public long minimumCost(int[] nums, int k, int dist) {
        int n = nums.length;
        L = k - 2;
        long ans = Long.MAX_VALUE;
        
        
        int limit = Math.min(n - 1, 1 + dist);
        
        for (int j = 2; j <= limit; j++) {
            add(nums[j]);
        }
        
        
        if (topSize == L) {
            ans = Math.min(ans, (long)nums[0] + nums[1] + topSum);
        }
        
        
        for (int i = 2; i < n; i++) {
        
            remove(nums[i]);
            
        
            if (i + dist < n) {
                add(nums[i + dist]);
            }
            
        
            if (topSize == L) {
                ans = Math.min(ans, (long)nums[0] + nums[i] + topSum);
            }
        }
        
        return ans;
    }
    
    private void add(int val) {
        
        addToMap(top, val);
        topSum += val;
        topSize++;
        
 
        if (topSize > L) {
            int maxVal = top.lastKey();
            removeFromMap(top, maxVal);
            topSum -= maxVal;
            topSize--;
            
            addToMap(bot, maxVal);
        }
    }
    
    private void remove(int val) {
 
        if (bot.containsKey(val)) {
            removeFromMap(bot, val);
        } else {
 
            if (top.containsKey(val)) {
                removeFromMap(top, val);
                topSum -= val;
                topSize--;
            }
 
            if (topSize < L && !bot.isEmpty()) {
                int minBot = bot.firstKey();
                removeFromMap(bot, minBot);
                
                addToMap(top, minBot);
                topSum += minBot;
                topSize++;
            }
        }
    }

    private void addToMap(TreeMap<Integer, Integer> map, int val) {
        map.put(val, map.getOrDefault(val, 0) + 1);
    }

    private void removeFromMap(TreeMap<Integer, Integer> map, int val) {
        int count = map.get(val);
        if (count == 1) {
            map.remove(val);
        } else {
            map.put(val, count - 1);
        }
    }
}

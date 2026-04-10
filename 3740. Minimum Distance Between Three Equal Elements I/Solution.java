class Solution {
    public int minimumDistance(int[] nums) {
        int n = nums.length;
        int[] firstIdx = new int[n + 1];
        int[] secondIdx = new int[n + 1];
        
        
        for (int i = 0; i <= n; i++) {
            firstIdx[i] = -1;
            secondIdx[i] = -1;
        }
        
        int minDistance = Integer.MAX_VALUE;
        
        for (int i = 0; i < n; i++) {
            int val = nums[i];
            
            if (secondIdx[val] != -1) {
                minDistance = Math.min(minDistance, 2 * (i - firstIdx[val]));
                
                
                firstIdx[val] = secondIdx[val];
                secondIdx[val] = i;
            } else if (firstIdx[val] != -1) {
                secondIdx[val] = i;
            } else {
                firstIdx[val] = i;
            }
        }
        
        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }
}

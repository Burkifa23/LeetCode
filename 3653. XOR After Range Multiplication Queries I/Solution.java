class Solution {
    public int xorAfterQueries(int[] nums, int[][] queries) {
        int MOD = 1000000007;
        
        for (int[] query : queries) {
            int l = query[0];
            int r = query[1];
            int k = query[2];
            int v = query[3];
            
            for (int idx = l; idx <= r; idx += k) {
                nums[idx] = (int) ((nums[idx] * (long) v) % MOD);
            }
        }
        
        int xorSum = 0;
        for (int num : nums) {
            xorSum ^= num;
        }
        
        return xorSum;
    }
}

class Solution {
    public int longestBalanced(int[] nums) {
        int n = nums.length;
        int maxLength = 0;
        
        
        
        int[] seen = new int[100001];
        int version = 0;
        
        for (int i = 0; i < n; i++) {
            version++;
            int evenCount = 0;
            int oddCount = 0;
            
            for (int j = i; j < n; j++) {
                int val = nums[j];
                
                
                if (seen[val] != version) {
                    seen[val] = version;
                    if (val % 2 == 0) {
                        evenCount++;
                    } else {
                        oddCount++;
                    }
                }
                
                
                if (evenCount == oddCount) {
                    maxLength = Math.max(maxLength, j - i + 1);
                }
            }
        }
        
        return maxLength;
    }
}

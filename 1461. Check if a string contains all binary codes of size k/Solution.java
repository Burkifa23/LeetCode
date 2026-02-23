class Solution {
    public boolean hasAllCodes(String s, int k) {
        int requiredCodes = 1 << k;
        
        
        if (s.length() - k + 1 < requiredCodes) {
            return false;
        }
        
        boolean[] seen = new boolean[requiredCodes];
        int count = 0;
        int hash = 0;
        int mask = requiredCodes - 1;
        
        for (int i = 0; i < s.length(); i++) {
            hash = ((hash << 1) & mask) | (s.charAt(i) - '0');
            
            
            if (i >= k - 1) {
                if (!seen[hash]) {
                    seen[hash] = true;
                    count++;
                    
                    
                    if (count == requiredCodes) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
}

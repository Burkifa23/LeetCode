class Solution {
    public String getHappyString(int n, int k) {
        int totalStrings = 3 * (1 << (n - 1));
        
        if (k > totalStrings) {
            return "";
        }
        
        
        char[] result = new char[n];
        
        
        k--; 
        
        
        int blockSize = 1 << (n - 1);
        int firstCharIdx = k / blockSize;
        result[0] = (char) ('a' + firstCharIdx);
        
        
        k %= blockSize;
        
      
        for (int i = 1; i < n; i++) {
            blockSize = 1 << (n - 1 - i);
            int choice = k / blockSize;                      
            k %= blockSize;
            
            char prevChar = result[i - 1];
            
            if (prevChar == 'a') {
                result[i] = (choice == 0) ? 'b' : 'c';
            } else if (prevChar == 'b') {
                result[i] = (choice == 0) ? 'a' : 'c';
            } else { 
                result[i] = (choice == 0) ? 'a' : 'b';
            }
        }
        
        
        return new String(result);
    }
}

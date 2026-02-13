import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int longestBalanced(String s) {
        if (s == null || s.length() == 0) return 0;
        
        int n = s.length();
        int maxLen = 0;
        
        
        int currentLen = 1;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                currentLen++;
            } else {
                maxLen = Math.max(maxLen, currentLen);
                currentLen = 1;
            }
        }
        maxLen = Math.max(maxLen, currentLen);
        
        
        maxLen = Math.max(maxLen, maxTwoChars(s, 'a', 'b', 'c'));
        maxLen = Math.max(maxLen, maxTwoChars(s, 'b', 'c', 'a'));
        maxLen = Math.max(maxLen, maxTwoChars(s, 'a', 'c', 'b'));
        
        
        maxLen = Math.max(maxLen, maxThreeChars(s));
        
        return maxLen;
    }
    
    
    private int maxTwoChars(String s, char c1, char c2, char exclude) {
        int n = s.length();
        int maxL = 0;
        
        
        int[] firstSeen = new int[2 * n + 1];
        Arrays.fill(firstSeen, -2);
        
        int[] modified = new int[n + 1];
        int modCount = 0;
        
        int diff = 0;
        firstSeen[n] = -1;
        modified[modCount++] = n;
        
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            
            if (c == exclude) {

                for (int j = 0; j < modCount; j++) {
                    firstSeen[modified[j]] = -2;
                }
                modCount = 0;
                diff = 0;
                firstSeen[n] = i; 
                modified[modCount++] = n;
            } else {
                if (c == c1) diff++;
                else if (c == c2) diff--;
                
                int idx = diff + n;
                if (firstSeen[idx] != -2) {
                    maxL = Math.max(maxL, i - firstSeen[idx]);
                } else {
                    firstSeen[idx] = i;
                    modified[modCount++] = idx;
                }
            }
        }
        
        return maxL;
    }
    
 
    private int maxThreeChars(String s) {
        int maxL = 0;
        int n = s.length();
        
        Map<Long, Integer> firstSeen = new HashMap<>();
        
 
        long initialKey = 0L; 
        firstSeen.put(initialKey, -1);
        
        int ca = 0, cb = 0, cc = 0;
        
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == 'a') ca++;
            else if (c == 'b') cb++;
            else if (c == 'c') cc++;
            
 
            long diff1 = ca - cb;
            long diff2 = ca - cc;
            
 
            long key = (diff1 << 32) | (diff2 & 0xFFFFFFFFL);
            
            if (firstSeen.containsKey(key)) {
                maxL = Math.max(maxL, i - firstSeen.get(key));
            } else {
                firstSeen.put(key, i);
            }
        }
        
        return maxL;
    }
}

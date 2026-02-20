import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public String makeLargestSpecial(String s) {
        
        if (s == null || s.length() == 0) {
            return "";
        }
        
        int count = 0;
        int left = 0;
        List<String> substrings = new ArrayList<>();
        
        
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                count++;
            } else {
                count--;
            }
            
        
            if (count == 0) {
        
                String inner = makeLargestSpecial(s.substring(left + 1, i));
                
        
                substrings.add("1" + inner + "0");
                
        
                left = i + 1;
            }
        }
        
        
        Collections.sort(substrings, Collections.reverseOrder());
        
        
        return String.join("", substrings);
    }
}

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        List<String> result = new ArrayList<>(queries.length);
        
        int dictLen = dictionary.length;
        int wordLen = queries[0].length();
        
        
        char[][] dictChars = new char[dictLen][];
        for (int i = 0; i < dictLen; i++) {
            dictChars[i] = dictionary[i].toCharArray();
        }
        
        
        for (String q : queries) {
            char[] qChars = q.toCharArray();
            
            
            for (char[] dChars : dictChars) {
                int edits = 0;
                
                for (int i = 0; i < wordLen; i++) {
                    if (qChars[i] != dChars[i]) {
                        edits++;
                        
                        if (edits > 2) {
                            break;
                        }
                    }
                }
                
                if (edits <= 2) {
                    result.add(q);
                    break;
                }
            }
        }
        
        return result;
    }
}

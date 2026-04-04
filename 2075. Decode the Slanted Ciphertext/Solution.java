class Solution {
    public String decodeCiphertext(String encodedText, int rows) {
        
        int len = encodedText.length();
        if (len == 0) {
            return "";
        }
        
        int cols = len / rows;
        StringBuilder originalText = new StringBuilder();
        
        
        for (int startCol = 0; startCol < cols; startCol++) {
            for (int r = 0; r < rows; r++) {
                int c = startCol + r;
                
                
                if (c >= cols) {
                    break;
                }
                
        
                int idx = r * cols + c;
                originalText.append(encodedText.charAt(idx));
            }
        }
        
        
        int i = originalText.length() - 1;
        while (i >= 0 && originalText.charAt(i) == ' ') {
            originalText.deleteCharAt(i);
            i--;
        }
        
        return originalText.toString();
    }
}

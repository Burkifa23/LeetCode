import java.util.Arrays;

class Solution {
    public int maxPathScore(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        
        
        int[][] prevRow = new int[n][k + 1];
        for (int j = 0; j < n; j++) {
            Arrays.fill(prevRow[j], -1);
        }
        
        for (int i = 0; i < m; i++) {
            
            int[][] currRow = new int[n][k + 1];
            for (int j = 0; j < n; j++) {
                Arrays.fill(currRow[j], -1);
            }
            
            for (int j = 0; j < n; j++) {
                int val = grid[i][j];
                int cost = (val == 0) ? 0 : 1;
                int score = val;
                
                
                if (i == 0 && j == 0) {
                    currRow[0][0] = 0;
                    continue;
                }
                
                
                for (int c = cost; c <= k; c++) {
                    int prev_c = c - cost;
                    int maxScore = -1;
                    
                    
                    if (i > 0 && prevRow[j][prev_c] != -1) {
                        maxScore = Math.max(maxScore, prevRow[j][prev_c] + score);
                    }
                    
                    
                    if (j > 0 && currRow[j - 1][prev_c] != -1) {
                        maxScore = Math.max(maxScore, currRow[j - 1][prev_c] + score);
                    }
                    
                    currRow[j][c] = maxScore;
                }
            }
            prevRow = currRow;
        }
        
        
        int maxAchievableScore = -1;
        for (int c = 0; c <= k; c++) {
            maxAchievableScore = Math.max(maxAchievableScore, prevRow[n - 1][c]);
        }
        
        return maxAchievableScore;
    }
}

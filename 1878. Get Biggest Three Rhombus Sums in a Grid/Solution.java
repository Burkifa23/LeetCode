class Solution {
    public int[] getBiggestThree(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;       
        int[] flatGrid = new int[m * n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                flatGrid[i * n + j] = grid[i][j];
            }
        }
        
       int[] top3 = {-1, -1, -1};
        
        
        for (int cx = 0; cx < m; cx++) {
            for (int cy = 0; cy < n; cy++) {
                updateTop3(top3, flatGrid[cx * n + cy]);
                
                
                int maxL = Math.min(Math.min(cx, m - 1 - cx), Math.min(cy, n - 1 - cy));
                
                
                for (int L = 1; L <= maxL; L++) {
                    int sum = 0;
                    
                    int r = cx - L;
                    int c = cy;
                    
    
                    for (int k = 0; k < L; k++) {
                        sum += flatGrid[r * n + c];
                        r++; c++;
                    }
    
                    for (int k = 0; k < L; k++) {
                        sum += flatGrid[r * n + c];
                        r++; c--;
                    }
    
                    for (int k = 0; k < L; k++) {
                        sum += flatGrid[r * n + c];
                        r--; c--;
                    }
    
                    for (int k = 0; k < L; k++) {
                        sum += flatGrid[r * n + c];
                        r--; c++;
                    }
                    
                    updateTop3(top3, sum);
                }
            }
        }
        
    
        int count = 0;
        for (int i = 0; i < 3; i++) {
            if (top3[i] != -1) count++;
        }
        
        int[] result = new int[count];
        for (int i = 0; i < count; i++) {
            result[i] = top3[i];
        }
        
        return result;
    }
    
    private void updateTop3(int[] top3, int val) {
        if (val == top3[0] || val == top3[1] || val == top3[2]) {
            return; 
        }
        
        
        if (val > top3[0]) {
            top3[2] = top3[1];
            top3[1] = top3[0];
            top3[0] = val;
        } else if (val > top3[1]) {
            top3[2] = top3[1];
            top3[1] = val;
        } else if (val > top3[2]) {
            top3[2] = val;
        }
    }
}

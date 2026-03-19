class Solution {
    public int numberOfSubmatrices(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int validSubmatricesCount = 0;
        int[] totalX = new int[n];
        int[] totalY = new int[n];
        
        for (int i = 0; i < m; i++) {
            int rowX = 0;
            int rowY = 0;
            
            
            for (int j = 0; j < n; j++) {
                char currentCell = grid[i][j];
                
                if (currentCell == 'X') {
                    rowX++;
                } else if (currentCell == 'Y') {
                    rowY++;
                }
                
                
                totalX[j] += rowX;
                totalY[j] += rowY;
                
                
                if (totalX[j] > 0 && totalX[j] == totalY[j]) {
                    validSubmatricesCount++;
                }
            }
        }
        
        return validSubmatricesCount;
    }
}

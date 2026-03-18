class Solution {
    public int countSubmatrices(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        int[] prefixSums = new int[n];
        
        
        int maxCol = n; 
        
        for (int i = 0; i < m; i++) {
            int rowSum = 0;
            for (int j = 0; j < maxCol; j++) {
                rowSum += grid[i][j];
                prefixSums[j] += rowSum;
                
                if (prefixSums[j] <= k) {
                    count++;
                } else {
                    maxCol = j; 
                    break;
                }
            }
            
            if (maxCol == 0) {
                break;
            }
        }
        
        return count;
    }
}

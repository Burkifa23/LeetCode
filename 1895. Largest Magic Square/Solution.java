class Solution {
    public int largestMagicSquare(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] rowPrefix = new int[m][n+1];
        int[][] colPrefix = new int[m+1][n];

        for (int i=0; i<m; i++){
            for( int j =0; j<n; j++){
                rowPrefix[i][j+1] = rowPrefix[i][j] + grid[i][j];
                colPrefix[i+1][j] = colPrefix[i][j] + grid[i][j];
            }
        }

        for (int k = Math.min(m,n); k>1; k--){
            for (int i=0; i<=m-k; i++){
                for ( int j=0; j<=n-k; j++) {
                    if(isMagic(grid, rowPrefix, colPrefix, i, j, k)) {
                    return k;
                    }
                }
            }
        }
        return 1;
    }

    private boolean isMagic (int[][] grid, int[][] rowPrefix, int[][] colPrefix, int r, int c, int k) {
        int diag1 = 0;
        int diag2 = 0;

        for (int l=0; l<k; l++){
            diag1 += grid[r+l][c+l];
            diag2 += grid[r+l][c+k-1-l];
        }

        if (diag1 != diag2){
            return false;
        } 

        int target = diag1;

        for (int i=0; i<k; i++) {
            int currentParamsRowSum = rowPrefix[r+i][c+k] - rowPrefix[r+i][c];
            if (currentParamsRowSum != target){
                return false;
            }
        }

        for (int j =0; j<k; j++) {
            int currentParamsColSum = colPrefix[r+k][c+j] - colPrefix[r][c+j];
            if (currentParamsColSum != target){
                return false;
            }
        }
        return true;
    }
}

class Solution {
    public boolean findRotation(int[][] mat, int[][] target) {
        int n = mat.length;
        boolean[] match = {true, true, true, true};
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] != target[i][j]) match[0] = false;
                if (mat[i][j] != target[j][n - 1 - i]) match[1] = false;
                if (mat[i][j] != target[n - 1 - i][n - 1 - j]) match[2] = false;
                if (mat[i][j] != target[n - 1 - j][i]) match[3] = false;
            }
        }
        
        return match[0] || match[1] || match[2] || match[3];
    }
}

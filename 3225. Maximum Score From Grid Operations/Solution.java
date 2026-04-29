class Solution {
    public long maximumScore(int[][] grid) {
        int n = grid.length;
        long[][] colSum = new long[n + 1][n];
        
        for (int c = 0; c < n; c++) {
            for (int r = 0; r < n; r++) {
                colSum[r + 1][c] = colSum[r][c] + grid[r][c];
            }
        }
        
        long[][] dp = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = -1;
            }
        }
        
        for (int x = 0; x <= n; x++) {
            dp[0][x] = 0;
        }
        
        for (int c = 0; c < n - 1; c++) {
            long[][] nextDp = new long[n + 1][n + 1];
            for (int i = 0; i <= n; i++) {
                for (int j = 0; j <= n; j++) {
                    nextDp[i][j] = -1;
                }
            }
            
            for (int x = 0; x <= n; x++) {
                long[] pref = new long[n + 1];
                long maxPref = -1;
                for (int p = 0; p <= n; p++) {
                    if (dp[p][x] != -1) {
                        maxPref = Math.max(maxPref, dp[p][x]);
                    }
                    pref[p] = maxPref;
                }
                
                long[] suff = new long[n + 2];
                long maxSuff = -1;
                for (int p = n; p >= 0; p--) {
                    if (dp[p][x] != -1) {
                        long sum = 0;
                        if (p > x) sum = colSum[p][c] - colSum[x][c];
                        maxSuff = Math.max(maxSuff, dp[p][x] + sum);
                    }
                    suff[p] = maxSuff;
                }
                
                for (int y = 0; y <= n; y++) {
                    long best = -1;
                    if (pref[y] != -1) {
                        long sum = 0;
                        if (y > x) sum = colSum[y][c] - colSum[x][c];
                        best = Math.max(best, pref[y] + sum);
                    }
                    if (y + 1 <= n && suff[y + 1] != -1) {
                        best = Math.max(best, suff[y + 1]);
                    }
                    nextDp[x][y] = best;
                }
            }
            dp = nextDp;
        }
        
        long ans = 0;
        for (int x = 0; x <= n; x++) {
            for (int y = 0; y <= n; y++) {
                if (dp[x][y] != -1) {
                    long sum = 0;
                    if (x > y) sum = colSum[x][n - 1] - colSum[y][n - 1];
                    ans = Math.max(ans, dp[x][y] + sum);
                }
            }
        }
        
        return ans;
    }
}

class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        long totalSum = 0;
        long[] rowSum = new long[m];
        long[] colSum = new long[n];
        
        int MAX_VAL = 100000;
        int[] minRow = new int[MAX_VAL + 1];
        int[] maxRow = new int[MAX_VAL + 1];
        int[] minCol = new int[MAX_VAL + 1];
        int[] maxCol = new int[MAX_VAL + 1];
        
        for (int i = 0; i <= MAX_VAL; i++) {
            minRow[i] = 1000000;
            maxRow[i] = -1;
            minCol[i] = 1000000;
            maxCol[i] = -1;
        }
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int val = grid[i][j];
                totalSum += val;
                rowSum[i] += val;
                colSum[j] += val;
                if (i < minRow[val]) minRow[val] = i;
                if (i > maxRow[val]) maxRow[val] = i;
                if (j < minCol[val]) minCol[val] = j;
                if (j > maxCol[val]) maxCol[val] = j;
            }
        }
        
        long currentSum = 0;
        for (int r = 0; r < m - 1; r++) {
            currentSum += rowSum[r];
            long sumTop = currentSum;
            long sumBot = totalSum - sumTop;
            
            if (sumTop == sumBot) return true;
            
            if (sumTop > sumBot) {
                long diff = sumTop - sumBot;
                if (diff <= MAX_VAL && diff > 0) {
                    int d = (int) diff;
                    int R = r + 1, C = n;
                    if (R == 1 && C > 1) {
                        if (grid[0][0] == d || grid[0][n-1] == d) return true;
                    } else if (C == 1 && R > 1) {
                        if (grid[0][0] == d || grid[r][0] == d) return true;
                    } else if (R >= 2 && C >= 2) {
                        if (minRow[d] <= r) return true;
                    }
                }
            } else {
                long diff = sumBot - sumTop;
                if (diff <= MAX_VAL && diff > 0) {
                    int d = (int) diff;
                    int R = m - 1 - r, C = n;
                    if (R == 1 && C > 1) {
                        if (grid[r+1][0] == d || grid[r+1][n-1] == d) return true;
                    } else if (C == 1 && R > 1) {
                        if (grid[r+1][0] == d || grid[m-1][0] == d) return true;
                    } else if (R >= 2 && C >= 2) {
                        if (maxRow[d] >= r + 1) return true;
                    }
                }
            }
        }
        
        currentSum = 0;
        for (int c = 0; c < n - 1; c++) {
            currentSum += colSum[c];
            long sumLeft = currentSum;
            long sumRight = totalSum - sumLeft;
            
            if (sumLeft == sumRight) return true;
            
            if (sumLeft > sumRight) {
                long diff = sumLeft - sumRight;
                if (diff <= MAX_VAL && diff > 0) {
                    int d = (int) diff;
                    int R = m, C = c + 1;
                    if (R == 1 && C > 1) {
                        if (grid[0][0] == d || grid[0][c] == d) return true;
                    } else if (C == 1 && R > 1) {
                        if (grid[0][0] == d || grid[m-1][0] == d) return true;
                    } else if (R >= 2 && C >= 2) {
                        if (minCol[d] <= c) return true;
                    }
                }
            } else {
                long diff = sumRight - sumLeft;
                if (diff <= MAX_VAL && diff > 0) {
                    int d = (int) diff;
                    int R = m, C = n - 1 - c;
                    if (R == 1 && C > 1) {
                        if (grid[0][c+1] == d || grid[0][n-1] == d) return true;
                    } else if (C == 1 && R > 1) {
                        if (grid[0][c+1] == d || grid[m-1][c+1] == d) return true;
                    } else if (R >= 2 && C >= 2) {
                        if (maxCol[d] >= c + 1) return true;
                    }
                }
            }
        }
        
        return false;
    }
}

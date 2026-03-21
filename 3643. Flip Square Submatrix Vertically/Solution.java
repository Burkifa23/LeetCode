class Solution {
    public int[][] reverseSubmatrix(int[][] grid, int x, int y, int k) {
        int bottom = x + k - 1;
        int right = y + k;
        
        while (x < bottom) {
            for (int j = y; j < right; j++) {
                int temp = grid[x][j];
                grid[x][j] = grid[bottom][j];
                grid[bottom][j] = temp;
            }
            x++;
            bottom--;
        }
        
        return grid;
    }
}

import java.util.Arrays;

class Solution {
    public int minOperations(int[][] grid, int x) {
        int m = grid.length;
        int n = grid[0].length;
        int totalElements = m * n;
        
        
        int[] arr = new int[totalElements];
        int idx = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[idx++] = grid[i][j];
            }
        }
        
        
        int remainder = arr[0] % x;
        for (int i = 1; i < totalElements; i++) {
            if (arr[i] % x != remainder) {
                return -1; 
            }
        }
        
        
        Arrays.sort(arr);
        int median = arr[totalElements / 2];
        

        int minOps = 0;
        for (int val : arr) {
            minOps += Math.abs(val - median) / x;
        }
        
        return minOps;
    }
}

import java.util.Arrays;

class Solution {
    public int largestSubmatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int maxArea = 0;
        
        int[] heights = new int[n];
        int[] sortedHeights = new int[n];
        
        for (int i = 0; i < m; i++) {
           for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    heights[j]++;
                } else {
                    heights[j] = 0;
                }
            }
            
           System.arraycopy(heights, 0, sortedHeights, 0, n);
            
            
            Arrays.sort(sortedHeights);
            
            
            for (int j = n - 1; j >= 0; j--) {
                int h = sortedHeights[j];
                
               
                if (h == 0) {
                    break;
                }
                
                int width = n - j;
                int area = h * width;
                
               
                if (area > maxArea) {
                    maxArea = area;
                }
            }
        }
        
        return maxArea;
    }
}

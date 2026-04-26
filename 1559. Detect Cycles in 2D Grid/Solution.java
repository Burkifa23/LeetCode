class Solution {
    int[] parent;

    public boolean containsCycle(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        parent = new int[m * n];
        
        for (int i = 0; i < m * n; i++) {
            parent[i] = i;
        }
         
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int current = i * n + j;
                
                if (j + 1 < n && grid[i][j] == grid[i][j + 1]) {
                    int right = i * n + (j + 1);
                    if (union(current, right)) {
                        return true;
                    }
                }           
                
                if (i + 1 < m && grid[i][j] == grid[i + 1][j]) {
                    int down = (i + 1) * n + j;
                    if (union(current, down)) {
                        return true;
                    }
                }
            }
        }
              
        return false;
    }
    
    
    private int find(int i) {
        if (parent[i] == i) {
            return i;
        }
        return parent[i] = find(parent[i]); 
    }
    
    private boolean union(int i, int j) {
        int rootI = find(i);
        int rootJ = find(j);
        
        if (rootI == rootJ) {
            return true;
        }
        
        parent[rootI] = rootJ; 
        return false;
    }
}

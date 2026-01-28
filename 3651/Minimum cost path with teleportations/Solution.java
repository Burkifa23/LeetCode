import java.util.*;

class Solution {
    public int minCost(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        
        
        int[][][] dist = new int[k + 1][m][n];
        for (int[][] matrix : dist) {
            for (int[] row : matrix) {
                Arrays.fill(row, Integer.MAX_VALUE);
            }
        }
        
        
        List<int[]> sortedCells = new ArrayList<>();
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                sortedCells.add(new int[]{r, c});
            }
        }
        sortedCells.sort((a, b) -> Integer.compare(grid[a[0]][a[1]], grid[b[0]][b[1]]));
        
        
        int[] teleportPtr = new int[k + 1];
        
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        
        
        dist[k][0][0] = 0;
        pq.offer(new int[]{0, 0, 0, k});
        
        int[] dr = {0, 1};
        int[] dc = {1, 0};
        
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int cost = curr[0];
            int r = curr[1];
            int c = curr[2];
            int remK = curr[3];
            
            
            if (r == m - 1 && c == n - 1) {
                return cost;
            }
            
            
            if (cost > dist[remK][r][c]) {
                continue;
            }
            
            
            for (int i = 0; i < 2; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                
                if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                    int newCost = cost + grid[nr][nc];
                    if (newCost < dist[remK][nr][nc]) {
                        dist[remK][nr][nc] = newCost;
                        pq.offer(new int[]{newCost, nr, nc, remK});
                    }
                }
            }
            
            
            if (remK > 0) {
                int currentVal = grid[r][c];
                
                
                while (teleportPtr[remK] < sortedCells.size()) {
                    int[] target = sortedCells.get(teleportPtr[remK]);
                    int tr = target[0];
                    int tc = target[1];
                    
                  
                    if (grid[tr][tc] > currentVal) {
                        break;
                    }
                    
                  
                    if (cost < dist[remK - 1][tr][tc]) {
                        dist[remK - 1][tr][tc] = cost;
                        pq.offer(new int[]{cost, tr, tc, remK - 1});
                    }
                    
                  
                    teleportPtr[remK]++;
                }
            }
        }
        
        return -1;
    }
}

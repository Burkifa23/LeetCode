import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    private static final int[][][] DIRS = {
        {},
        {{0, -1}, {0, 1}},
        {{-1, 0}, {1, 0}},
        {{0, -1}, {1, 0}},
        {{0, 1}, {1, 0}},
        {{0, -1}, {-1, 0}},
        {{0, 1}, {-1, 0}}
    };

    public boolean hasValidPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        boolean[][] visited = new boolean[m][n];
        Deque<Integer> queue = new ArrayDeque<>();
        
        
        queue.offer(0); 
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            int r = curr / n;
            int c = curr % n;

            
            if (r == m - 1 && c == n - 1) {
                return true;
            }

            int pipe = grid[r][c];
            
            
            for (int[] dir : DIRS[pipe]) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                
                if (nr >= 0 && nr < m && nc >= 0 && nc < n && !visited[nr][nc]) {
                    int nextPipe = grid[nr][nc];
                    
                    
                    for (int[] nextDir : DIRS[nextPipe]) {
                        if (nextDir[0] == -dir[0] && nextDir[1] == -dir[1]) {
                            visited[nr][nc] = true;
                            queue.offer(nr * n + nc);
                            break;
                        }
                    }
                }
            }
        }
        
        return false;
    }
}

import java.util.HashSet;
import java.util.Set;

class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        
        
        int x = 0;
        int y = 0;
        int currentDir = 0;
        int maxDistSquared = 0;
        
        Set<Long> obstacleSet = new HashSet<>();
        for (int[] obs : obstacles) {
            long hash = ((long) obs[0] << 32) | (obs[1] & 0xFFFFFFFFL);
            obstacleSet.add(hash);
        }
        
        
        for (int cmd : commands) {
            if (cmd == -1) {
                currentDir = (currentDir + 1) % 4;
            } else if (cmd == -2) {
                currentDir = (currentDir + 3) % 4;
            } else {
                for (int i = 0; i < cmd; i++) {
                    int nextX = x + directions[currentDir][0];
                    int nextY = y + directions[currentDir][1];
                    
                    
                    long nextHash = ((long) nextX << 32) | (nextY & 0xFFFFFFFFL);
                    if (!obstacleSet.contains(nextHash)) {
                        x = nextX;
                        y = nextY;
                        maxDistSquared = Math.max(maxDistSquared, x * x + y * y);
                    } else {
                        break; 
                    }
                }
            }
        }
        
        return maxDistSquared;
    }
}

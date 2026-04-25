import java.util.Arrays;

class Solution {
    public int maxDistance(int side, int[][] points, int k) {
        int n = points.length;
        long[] p = new long[n];
        
        
        for (int i = 0; i < n; i++) {
            int x = points[i][0];
            int y = points[i][1];
            
            if (y == 0) {
                p[i] = x;
            } else if (x == side) {
                p[i] = (long) side + y;
            } else if (y == side) {
                p[i] = 3L * side - x;
            } else {
                p[i] = 4L * side - y;
            }
        }
        
        
        Arrays.sort(p);
        
        
        long[] p2 = new long[2 * n];
        for (int i = 0; i < n; i++) {
            p2[i] = p[i];
            p2[i + n] = p[i] + 4L * side;
        }
        
        
        int low = 0;
        int high = side;
        int ans = 0;
        
        int[] nxt = new int[2 * n];
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            
            if (check(mid, p2, n, k, nxt, side)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        
        return ans;
    }
    
    private boolean check(int D, long[] p2, int n, int k, int[] nxt, int side) {
        int right = 1;
        for (int left = 0; left < 2 * n; left++) {
            right = Math.max(right, left + 1);
            while (right < 2 * n && p2[right] - p2[left] < D) {
                right++;
            }
            nxt[left] = right;
        }
        
        
        for (int i = 0; i < n; i++) {
            int curr = i;
            
            
            for (int step = 1; step < k; step++) {
                curr = nxt[curr];
                if (curr >= i + n) break;
            }
            
            
            if (curr < i + n && (p2[i] + 4L * side - p2[curr] >= D)) {
                return true;
            }
        }
        
        return false;
    }
}

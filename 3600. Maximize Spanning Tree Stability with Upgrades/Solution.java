import java.util.*;

class Solution {
    public int maxStability(int n, int[][] edges, int k) {
        int low = 0, high = 200000;
        int result = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (check(mid, n, edges, k)) {
                result = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    private boolean check(int minStability, int n, int[][] edges, int k) {
        DSU dsu = new DSU(n);
        int edgesCount = 0;
        int upgradesUsed = 0;
        
        List<int[]> optionalNoUpgrade = new ArrayList<>();
        List<int[]> optionalNeedsUpgrade = new ArrayList<>();

        
        for (int[] edge : edges) {
            if (edge[3] == 1) {
                if (edge[2] < minStability) return false;
                if (!dsu.union(edge[0], edge[1])) return false;
                edgesCount++;
            } else {
                if (edge[2] >= minStability) {
                    optionalNoUpgrade.add(edge);
                } else if (edge[2] * 2 >= minStability) {
                    optionalNeedsUpgrade.add(edge);
                }
            }
        }

        
        for (int[] edge : optionalNoUpgrade) {
            if (dsu.union(edge[0], edge[1])) {
                edgesCount++;
            }
        }

        
        for (int[] edge : optionalNeedsUpgrade) {
            if (upgradesUsed < k) {
                if (dsu.union(edge[0], edge[1])) {
                    edgesCount++;
                    upgradesUsed++;
                }
            }
        }

        
        return edgesCount == n - 1;
    }

    class DSU {
        int[] parent;
        public DSU(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }
        public int find(int i) {
            if (parent[i] == i) return i;
            return parent[i] = find(parent[i]);
        }
        public boolean union(int i, int j) {
            int rootI = find(i);
            int rootJ = find(j);
            if (rootI != rootJ) {
                parent[rootI] = rootJ;
                return true;
            }
            return false;
        }
    }
}

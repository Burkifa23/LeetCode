class Solution {
    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;
        int[] parent = new int[n];
        
        
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int[] swap : allowedSwaps) {
            int rootU = find(parent, swap[0]);
            int rootV = find(parent, swap[1]);
            if (rootU != rootV) {
                parent[rootU] = rootV;
            }
        }

        
        int[] head = new int[n];
        int[] next = new int[n];
        for (int i = 0; i < n; i++) {
            head[i] = -1;
        }

        for (int i = 0; i < n; i++) {
            int root = find(parent, i);
            next[i] = head[root];
            head[root] = i;
        }

        
        int[] count = new int[100005];
        int distance = 0;

        
        for (int i = 0; i < n; i++) {
            if (head[i] != -1) {
                int curr = head[i];
                while (curr != -1) {
                    count[source[curr]]++;
                    curr = next[curr];
                }

                
                curr = head[i];
                while (curr != -1) {
                    if (count[target[curr]] > 0) {
                        count[target[curr]]--;
                    } else {
                        distance++;
                    }
                    curr = next[curr];
                }



                curr = head[i];
                while (curr != -1) {
                    count[source[curr]] = 0; 
                    curr = next[curr];
                }
            }
        }

        return distance;
    }

    private int find(int[] parent, int i) {
        if (parent[i] == i) {
            return i;
        }
        return parent[i] = find(parent, parent[i]);
    }
}

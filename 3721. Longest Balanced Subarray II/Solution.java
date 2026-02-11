class Solution {
    int[] min_val;
    int[] max_val;
    int[] lazy;

    public int longestBalanced(int[] nums) {
        int n = nums.length;
        
        min_val = new int[4 * n + 1];
        max_val = new int[4 * n + 1];
        lazy = new int[4 * n + 1];
        
        
        int[] prev_pos = new int[100005];

        int maxLength = 0;

        for (int r = 1; r <= n; r++) {
            int val = nums[r - 1];
            int p = prev_pos[val];
            int delta = (val % 2 == 0) ? 1 : -1;

            
            update(1, 1, n, p + 1, r, delta);
            prev_pos[val] = r;

            
            int firstZeroL = query_first_zero(1, 1, n, 1, r);
            if (firstZeroL != -1) {
                maxLength = Math.max(maxLength, r - firstZeroL + 1);
            }
        }

        return maxLength;
    }

    private void pushDown(int node) {
        if (lazy[node] != 0) {
            int lz = lazy[node];
            int left = node * 2;
            int right = node * 2 + 1;

            min_val[left] += lz;
            max_val[left] += lz;
            lazy[left] += lz;

            min_val[right] += lz;
            max_val[right] += lz;
            lazy[right] += lz;

            lazy[node] = 0;
        }
    }

    private void pushUp(int node) {
        min_val[node] = Math.min(min_val[node * 2], min_val[node * 2 + 1]);
        max_val[node] = Math.max(max_val[node * 2], max_val[node * 2 + 1]);
    }

    private void update(int node, int l, int r, int ql, int qr, int val) {
        if (ql <= l && r <= qr) {
            min_val[node] += val;
            max_val[node] += val;
            lazy[node] += val;
            return;
        }
        pushDown(node);
        int mid = (l + r) / 2;
        if (ql <= mid) update(node * 2, l, mid, ql, qr, val);
        if (qr > mid) update(node * 2 + 1, mid + 1, r, ql, qr, val);
        pushUp(node);
    }

    private int query_first_zero(int node, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) {
            if (min_val[node] > 0 || max_val[node] < 0) return -1;
            return find_first_in_subtree(node, l, r);
        }
        
        pushDown(node);
        int mid = (l + r) / 2;
        int res = -1;
        
        
        if (ql <= mid) {
            res = query_first_zero(node * 2, l, mid, ql, qr);
        }
        
        if (res == -1 && qr > mid) {
            res = query_first_zero(node * 2 + 1, mid + 1, r, ql, qr);
        }
        return res;
    }

    private int find_first_in_subtree(int node, int l, int r) {
        if (l == r) return l;
        pushDown(node);
        int mid = (l + r) / 2;
        
        if (min_val[node * 2] <= 0 && max_val[node * 2] >= 0) {
            return find_first_in_subtree(node * 2, l, mid);
        } else {
            return find_first_in_subtree(node * 2 + 1, mid + 1, r);
        }
    }
}

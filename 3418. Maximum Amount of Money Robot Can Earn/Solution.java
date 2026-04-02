class Solution {
    public int maximumAmount(int[][] coins) {
        int m = coins.length;
        int n = coins[0].length;
        long[] prev = new long[n * 3];
        long[] curr = new long[n * 3];
        final long INF = Long.MIN_VALUE / 2;

        for (int j = 0; j < n; j++) {
            prev[j * 3] = INF;
            prev[j * 3 + 1] = INF;
            prev[j * 3 + 2] = INF;
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int val = coins[i][j];

                if (i == 0 && j == 0) {
                    curr[0] = val;
                    curr[1] = (val < 0) ? 0 : INF;
                    curr[2] = INF;
                    continue;
                }

                for (int k = 0; k < 3; k++) {
                    long maxPrev = INF;
                    if (i > 0) maxPrev = Math.max(maxPrev, prev[j * 3 + k]);
                    if (j > 0) maxPrev = Math.max(maxPrev, curr[(j - 1) * 3 + k]);

                    long res = INF;
                    if (maxPrev != INF) {
                        res = maxPrev + val;
                    }

                    if (val < 0 && k > 0) {
                        long maxPrevForSkip = INF;
                        if (i > 0) maxPrevForSkip = Math.max(maxPrevForSkip, prev[j * 3 + (k - 1)]);
                        if (j > 0) maxPrevForSkip = Math.max(maxPrevForSkip, curr[(j - 1) * 3 + (k - 1)]);

                        if (maxPrevForSkip != INF) {
                            res = Math.max(res, maxPrevForSkip);
                        }
                    }
                    curr[j * 3 + k] = res;
                }
            }
            long[] temp = prev;
            prev = curr;
            curr = temp;
        }

        long result = prev[(n - 1) * 3];
        result = Math.max(result, prev[(n - 1) * 3 + 1]);
        result = Math.max(result, prev[(n - 1) * 3 + 2]);

        return (int) result;
    }
}

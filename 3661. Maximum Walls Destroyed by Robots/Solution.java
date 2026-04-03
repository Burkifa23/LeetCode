class Solution {
    public int maxWalls(int[] robots, int[] distance, int[] walls) {
        int n = robots.length;
        long[] packed = new long[n];
        for (int i = 0; i < n; i++) {
            packed[i] = ((long) robots[i] << 32) | distance[i];
        }
        
        java.util.Arrays.sort(packed);
        
        for (int i = 0; i < n; i++) {
            robots[i] = (int) (packed[i] >>> 32);
            distance[i] = (int) packed[i];
        }

        java.util.Arrays.sort(walls);

        int wallsAtRobots = 0;
        for (int i = 0; i < n; i++) {
            if (java.util.Arrays.binarySearch(walls, robots[i]) >= 0) {
                wallsAtRobots++;
            }
        }

        int dp0 = countWalls(walls, robots[0] - distance[0], robots[0] - 1);
        int dp1 = 0;

        for (int i = 1; i < n; i++) {
            int A = robots[i - 1] + 1;
            int D = robots[i] - 1;

            int next_dp0 = 0;
            int next_dp1 = 0;

            if (A <= D) {
                int B = Math.min(D, robots[i - 1] + distance[i - 1]);
                int C = Math.max(A, robots[i] - distance[i]);

                int count_prev_right = countWalls(walls, A, B);
                int count_curr_left = countWalls(walls, C, D);
                int count_both;

                if (B >= C) {
                    count_both = countWalls(walls, A, D);
                } else {
                    count_both = countWalls(walls, A, B) + countWalls(walls, C, D);
                }

                next_dp0 = Math.max(dp0 + count_curr_left, dp1 + count_both);
                next_dp1 = Math.max(dp0, dp1 + count_prev_right);
            } else {
                next_dp0 = Math.max(dp0, dp1);
                next_dp1 = Math.max(dp0, dp1);
            }

            dp0 = next_dp0;
            dp1 = next_dp1;
        }

        int ans = Math.max(dp0, dp1 + countWalls(walls, robots[n - 1] + 1, robots[n - 1] + distance[n - 1]));
        return ans + wallsAtRobots;
    }

    private int countWalls(int[] walls, int A, int B) {
        if (A > B) return 0;
        int left = lowerBound(walls, A);
        int right = upperBound(walls, B);
        return Math.max(0, right - left);
    }

    private int lowerBound(int[] arr, int target) {
        int left = 0, right = arr.length;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (arr[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private int upperBound(int[] arr, int target) {
        int left = 0, right = arr.length;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (arr[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}

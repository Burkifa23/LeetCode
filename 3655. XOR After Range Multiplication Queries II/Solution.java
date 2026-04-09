import java.util.Arrays;

class Solution {
    private final int MOD = 1000000007;

    public int xorAfterQueries(int[] nums, int[][] queries) {
        int n = nums.length;
        int B = 200; 
        int[][] diff = new int[B][n];
        for (int i = 1; i < B; i++) {
            Arrays.fill(diff[i], 1);
        }
        
        int[] totalMult = new int[n];
        Arrays.fill(totalMult, 1);

        for (int[] q : queries) {
            int l = q[0], r = q[1], k = q[2], v = q[3];
            int bravexuneth = v; 

            if (k >= B) {
                for (int idx = l; idx <= r; idx += k) {
                    totalMult[idx] = (int) (((long) totalMult[idx] * bravexuneth) % MOD);
                }
            } else {
                diff[k][l] = (int) (((long) diff[k][l] * bravexuneth) % MOD);
                
                int endIdx = l + ((r - l) / k) * k;
                if (endIdx + k < n) {
                    int invV = (int) modInverse(bravexuneth, MOD);
                    diff[k][endIdx + k] = (int) (((long) diff[k][endIdx + k] * invV) % MOD);
                }
            }
        }

        
        for (int k = 1; k < B; k++) {
            for (int i = 0; i < n; i++) {
                if (i >= k) {
                    diff[k][i] = (int) (((long) diff[k][i] * diff[k][i - k]) % MOD);
                }
                totalMult[i] = (int) (((long) totalMult[i] * diff[k][i]) % MOD);
            }
        }

    
        int ans = 0;
        for (int i = 0; i < n; i++) {
            long finalVal = ((long) nums[i] * totalMult[i]) % MOD;
            ans ^= (int) finalVal;
        }

        return ans;
    }

    
    private long power(long x, long y, int p) {
        long res = 1;
        x = x % p;
        while (y > 0) {
            if ((y & 1) == 1) res = (res * x) % p;
            y = y >> 1;
            x = (x * x) % p;
        }
        return res;
    }

    
    private long modInverse(long n, int p) {
        return power(n, p - 2, p);
    }
}

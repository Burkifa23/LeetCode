class Solution {
    private static final int MOD = 1_000_000_007;


    private long modPow(long base, long exp) {
        long res = 1;
        base %= MOD;
        while (exp > 0) {
            if (exp % 2 == 1) res = (res * base) % MOD;
            base = (base * base) % MOD;
            exp /= 2;
        }
        return res;
    }


    private long modInverse(long n) {
        return modPow(n, MOD - 2);
    }


    private long nCr(int n, int r, long[] fact, long[] invFact) {
        if (r < 0 || r > n) return 0;
        return fact[n] * invFact[r] % MOD * invFact[n - r] % MOD;
    }


    private long waysToForm(int n, int g, int limit, long[] fact, long[] invFact) {
        if (g == 0) return n == 0 ? 1 : 0;
        if (n < g) return 0;
        
        long ways = 0;
        int maxJ = (n - g) / limit;
        
        
        for (int j = 0; j <= maxJ && j <= g; j++) {
            long term = nCr(g, j, fact, invFact) * nCr(n - j * limit - 1, g - 1, fact, invFact) % MOD;
            if (j % 2 == 1) {
                ways = (ways - term + MOD) % MOD;
            } else {
                ways = (ways + term) % MOD;
            }
        }
        return ways;
    }

    public int numberOfStableArrays(int zero, int one, int limit) {
        int maxN = Math.max(zero, one) + 2; 
        long[] fact = new long[maxN + 1];
        long[] invFact = new long[maxN + 1];
        
        fact[0] = 1;
        invFact[0] = 1;
        for (int i = 1; i <= maxN; i++) {
            fact[i] = (fact[i - 1] * i) % MOD;
        }
        invFact[maxN] = modInverse(fact[maxN]);
        for (int i = maxN - 1; i >= 1; i--) {
            invFact[i] = (invFact[i + 1] * (i + 1)) % MOD;
        }

        long totalWays = 0;
        int minGroups = 1;
        int maxGroups = Math.min(zero, one) + 1;

        
        for (int g = minGroups; g <= maxGroups; g++) {
            long waysZ0 = waysToForm(zero, g, limit, fact, invFact);
            long waysO0 = waysToForm(one, g, limit, fact, invFact);
            
            
            long term1 = (waysZ0 * waysO0) % MOD;
            totalWays = (totalWays + term1 * 2) % MOD;
            
            
            if (g - 1 >= 1) {
                long waysO_minus_1 = waysToForm(one, g - 1, limit, fact, invFact);
                totalWays = (totalWays + waysZ0 * waysO_minus_1) % MOD;
            }
            
            
            if (g - 1 >= 1) {
                long waysZ_minus_1 = waysToForm(zero, g - 1, limit, fact, invFact);
                totalWays = (totalWays + waysZ_minus_1 * waysO0) % MOD;
            }
        }
        
        return (int) totalWays;
    }
}

class Solution {
    public int numOfWays(int n) {
        long mod = (long)1e9 + 7;

        long aba = 6;
        long abc = 6;

        for (int i=2; i<=n; i++){
            long nextAba = (aba * 3 + abc * 2) % mod;
            long nextAbc = (aba * 2 + abc * 2) % mod;
        
            aba = nextAba;
            abc = nextAbc;
        }

        return (int)((aba+abc)%mod);
    }
}

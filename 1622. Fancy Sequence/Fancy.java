import java.util.ArrayList;
import java.util.List;

class Fancy {
    private static final long MOD = 1_000_000_007;
    private List<Long> nums;
    private long mult = 1;
    private long add = 0;

    public Fancy() {
        nums = new ArrayList<>();
    }

    public void append(int val) {
        long valLong = (long) val;
        long base = ((valLong - add + MOD) % MOD * power(mult, MOD - 2)) % MOD;
        nums.add(base);
    }

    public void addAll(int inc) {
        add = (add + inc) % MOD;
    }

    public void multAll(int m) {
        mult = (mult * m) % MOD;
        add = (add * m) % MOD;
    }

    public int getIndex(int idx) {
        if (idx >= nums.size()) {
            return -1;
        }
        long res = (nums.get(idx) * mult % MOD + add) % MOD;
        return (int) res;
    }

    
    private long power(long base, long exp) {
        long res = 1;
        base %= MOD;
        while (exp > 0) {
            if (exp % 2 == 1) res = (res * base) % MOD;
            base = (base * base) % MOD;
            exp /= 2;
        }
        return res;
    }
}

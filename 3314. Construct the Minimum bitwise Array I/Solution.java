import java.util.List;

class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] ans = new int[n];

        for (int i=0; i<n;i++){
            int p =nums.get(i);
            if(p==2){
                ans[i]=-1;
            } else {
                int lowestZeroBit = Integer.lowestOneBit(~p);
                ans[i] = p-(lowestZeroBit>>1);
            }
        }
        return ans;
    }
}

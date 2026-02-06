import java.util.Arrays;
class Solution {
    public int minRemoval(int[] nums, int k) {
        Arrays.sort(nums);
        int left= 0;
        int maxLen=0;
        int n=nums.length;

        for( int right=0;right<n;right++){
            while((long) nums[right]> (long)nums[left]*k ){
                left++;
            }
            maxLen= Math.max(maxLen, right-left+1);
        }
        return n-maxLen;
    }
}

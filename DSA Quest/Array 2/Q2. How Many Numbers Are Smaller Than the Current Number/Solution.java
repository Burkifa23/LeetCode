class Solution {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] count = new int[101];
        int n = nums.length;

        for (int num: nums){
            count[num]++;
        }

        int runningSum = 0;
        for (int i = 0; i <count.length; i++){
            int currentFreq = count[i];
            count[i] = runningSum;
            runningSum += currentFreq;
        }

        int [] result = new int[n];
        for (int i = 0; i<n; i++) {
            result[i]= count[nums[i]];
        }
        return result;
    }
}

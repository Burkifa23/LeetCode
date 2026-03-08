class Solution {
    public String findDifferentBinaryString(String[] nums) {
        StringBuilder result = new StringBuilder();
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            
            char currentChar = nums[i].charAt(i);
            
            
            result.append(currentChar == '0' ? '1' : '0');
        }

        return result.toString();
    }
}

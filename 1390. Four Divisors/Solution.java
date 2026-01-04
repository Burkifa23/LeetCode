class Solution {
    public int sumFourDivisors(int[] nums) {
        int totalSum = 0;

        for (int num : nums) {
            int count = 0;
            int currentSum = 0;

            for (int i = ; i*i<=num; i++) {
                if (num % i == 0) {
                    if (i*i == num) {
                        count++;
                        currentSum += i;
                    } else {
                        count += 2;
                        currentSum += i + (num/i);
                    }
                }
                if (count > 4) break;
            }
            if (count == 4 ){
                totalSum += currentSum;
            }
        }
        return totalSum;
    }
}

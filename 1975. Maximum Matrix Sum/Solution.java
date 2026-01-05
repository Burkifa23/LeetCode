class Solution {
    public long maxMatrixSum(int[][] matrix) {
        long totalSum = 0;
        int minAbsValue = Integer.MAX_VALUE;
        int negativeCount = 0;

        for (int i =0; i<matrix.length; i++) {
            for (int j=0; j<matrix[i].length; j++) {
                int val = matrix[i][j];

                if (val <0) {
                    negativeCount++;
                }

                int absVal = Math.abs(val);
                minAbsValue = Math.min(minAbsValue, absVal);

                totalSum += absVal;
            }
        }

        if (negativeCount % 2 != 0) {
            totalSum -= 2L * minAbsValue;

        }
        return totalSum;
    }
}

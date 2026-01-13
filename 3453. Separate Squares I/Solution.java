class Solution {
    public double separateSquares(int[][] squares) {
        double minY = Double.MAX_VALUE;
        double maxY = Double.MIN_VALUE;
        double totalArea = 0;

        for (int[] sq: squares) {
            long l = sq[2];
            totalArea += (double) l*l;
            minY = Math.min(minY, (double) sq[1]);
            maxY = Math.max(maxY, (double) sq[1] +l);
        }

        double low = minY;
        double high = maxY;

        for (int i=0; i<100; i++) {
            double mid = low + (high-low) / 2;
            if (calculateAreaBelow(squares, mid) * 2 >= totalArea) {
                high = mid;
            } else {
                low = mid;
            }
        } 
        return low;
    }

    private  double calculateAreaBelow(int[][] squares, double k) {
        double area = 0;
        for (int[] sq: squares) {
            double y = sq[1];
            double l = sq[2];

            if(k<=y) {
                continue;
            } else if (k>= y + l) {
                area += l * l;
            } else {
                area += (k-y)*l;
            }
        }
        return area;
    }
}

class Solution {
    public long largestSquareArea(int[][] bottomLeft, int[][] topRight) {
        long maxSide = 0;
        int n = bottomLeft.length;

        for (int i = 0; i < n; i++){
            for (int j=i+1; j<n; j++) {
                
                int ax1 = bottomLeft[i][0];
                int ay1 = bottomLeft[i][1];
                int ax2 = topRight[i][0];
                int ay2 = topRight[i][1];
                
                int bx1 = bottomLeft[j][0];
                int by1 = bottomLeft[j][1];
                int bx2 = topRight[j][0];
                int by2 = topRight[j][1];

                int intersectX1 = Math.max(ax1, bx1);
                int intersectY1 = Math.max(ay1, by1);
                
                int intersectX2 = Math.min(ax2, bx2);
                int intersectY2 = Math.min(ay2, by2);

                if ( intersectX1 < intersectX2 && intersectY1 < intersectY2) {
                    long width = intersectX2 - intersectX1;
                    long height = intersectY2 - intersectY1;

                    long currentSide = Math.min(width, height);

                    maxSide = Math.max(maxSide, currentSide);
                }
            }
        }

        return maxSide * maxSide;
        
    }
}

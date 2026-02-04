class Solution {
    public long maxSumTrionic(int[] nums) {
        long INF = Long.MIN_VALUE/2;

        long up1 = INF;
        long down = INF;
        long up2=INF;

        long maxAns = INF;

        for (int i=1; i<nums.length; i++){
            long curr = nums[i];
            long prev = nums[i-1];

            long nextUp1 = INF;
            long nextDown = INF;
            long nextUp2 = INF;

            if(curr>prev){
                if(up1!=INF){
                    nextUp1 = Math.max(nextUp1, up1+curr);
                }

                nextUp1 =Math.max(nextUp1, prev+curr);

                if(up2!=INF){
                    nextUp2 = Math.max(nextUp2, up2+curr);
                }

                if (down!=INF){
                    nextUp2=Math.max(nextUp2,down+curr);
                }
            } else if(curr<prev){
                if(down!=INF){
                    nextDown=Math.max(nextDown, down+curr);
                }
                if(up1!=INF){
                    nextDown=Math.max(nextDown, up1+curr);
                }
            }
            
            up1=nextUp1;
            down = nextDown;
            up2=nextUp2;

            if(up2!=INF){
                maxAns=Math.max(maxAns,up2);
            }
        }
        return maxAns;
    }
}

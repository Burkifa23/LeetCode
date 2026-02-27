class Solution {
    public int minOperations(String s, int k) {
        int n = s.length();
        int initialZeros = 0;

        for(int i=0;i<n;i++){
            if(s.charAt(i)=='0'){
                initialZeros++;
            }
        }


        int L = initialZeros;
        int R  = initialZeros;

        int[] visitedR = new int[n+1];
        Arrays.fill(visitedR,-1);

        int steps =0;

        while(steps <=n+1){
            if(L==0){
                return steps;
            }

            if(visitedR[L]>=R){
                return -1;
            }

            visitedR[L]=R;

            int nextL = 0;
            if(k>=L && k<=R){
                if((k%2)==(L%2)){
                    nextL=0;
                } else{
                    nextL=1;
                }
            } else if(k<L){
                nextL = L-k;
            } else {
                nextL = k-R;
            }

            int nextR=0;
            int target=n-k;
            if(target>=L && target<=R){
                if((target%2)==(L%2)){
                    nextR=n;
                } else {
                    nextR=n-1;
                }
            } else if(target<L){
                nextR=n-(L-target);
            } else{
                nextR=n-(target-R);
            }
            L=nextL;
            R=nextR;
            steps++;
        }
        return -1;
    }
}

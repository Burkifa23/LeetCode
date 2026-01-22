import java.util.ArrayList;
import java.util.List;

class Solution {
    public int minimumPairRemoval(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for(int n:nums){
            list.add(n);
        }

        int operations=0;

        while(!isSorted(list)){
            operations++;

            int minSum = Integer.MAX_VALUE;
            int minIndex = -1;

            for(int i=0; i<list.size()-1;i++){
                int currentSum = list.get(i)+ list.get(i+1);

                if(currentSum <minSum){
                    minSum = currentSum;
                    minIndex=i;
                }
            }

            list.set(minIndex, minSum);
            list.remove(minIndex+1);
        }
        return operations;
    }


    private boolean isSorted(List<Integer> list){
        if(list.size() <=1){
            return true;
        }

        for (int i=0; i<list.size()-1;i++){
            if(list.get(i)> list.get(i+1)){
                return false;
            }
        }
        return true;
    }
}

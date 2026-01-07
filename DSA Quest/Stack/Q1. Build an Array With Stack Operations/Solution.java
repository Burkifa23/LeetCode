import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> buildArray(int[] target, int n) {
        List<String> result = new ArrayList<>();
        int targetIndex = 0;


        for (int i =1; i<=n && targetIndex <target.length; i++) {
            if (target[targetIndex] == i){

            result.add("Push");
            targetIndex++;
        } else {
            result.add("Push");
            result.add("Pop");
            }
        }
    return result;
    }
}

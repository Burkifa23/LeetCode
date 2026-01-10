import java.util.Stack;

class Solution {
    public int[] finalPrices(int[] prices) {
        int n = prices.length;
        int[] answer = prices.clone();
        Stack<Integer> stack = new Stack<>();

        for (int i=0; i<n; i++){
            while(!stack.isEmpty() && prices[i] <= prices[stack.peek()]){
                int indexToDiscount = stack.pop();
                answer[indexToDiscount] = prices[indexToDiscount] - prices[i];
            }
            stack.push(i);
        }
        return answer;
    }
}

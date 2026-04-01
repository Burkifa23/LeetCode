import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

class Solution {
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        int n = positions.length;
        Integer[] indices = new Integer[n];
        
        // Populate indices array
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }
        
        // Sort indices based on their corresponding positions on the line
        Arrays.sort(indices, (a, b) -> Integer.compare(positions[a], positions[b]));
        
        Stack<Integer> stack = new Stack<>(); // Stores original indices of 'R' moving robots
        
        for (int curr : indices) {
            if (directions.charAt(curr) == 'R') {
                stack.push(curr);
            } else {
                // Robot is moving 'L', simulate collisions with 'R' robots in the stack
                while (!stack.isEmpty() && healths[curr] > 0) {
                    int top = stack.peek();
                    
                    if (healths[curr] > healths[top]) {
                        // Current 'L' robot wins
                        healths[curr] -= 1;
                        healths[top] = 0; // Top robot is destroyed
                        stack.pop();
                    } else if (healths[curr] < healths[top]) {
                        // Top 'R' robot wins
                        healths[top] -= 1;
                        healths[curr] = 0; // Current robot is destroyed
                        // We don't pop the top robot because it survived
                    } else {
                        // Tie, both are destroyed
                        healths[curr] = 0;
                        healths[top] = 0;
                        stack.pop();
                    }
                }
            }
        }
        
        // Collect surviving robots in the original input order
        List<Integer> result = new ArrayList<>();
        for (int h : healths) {
            if (h > 0) {
                result.add(h);
            }
        }
        
        return result;
    }
}

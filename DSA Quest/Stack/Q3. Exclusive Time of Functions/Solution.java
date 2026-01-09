import java.util.List;
import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] result = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        int prevTime = 0;

        for (String log: logs){
            String[] parts = log.split(":");
            int id = Integer.parseInt(parts[0]);
            String type = parts[1];
            int timestamp = Integer.parseInt(parts[2]);

            if (type.equals("start")) {
                if(!stack.isEmpty()) {
                    result[stack.peek()] += timestamp - prevTime;
                }
                stack.push(id);
                prevTime = timestamp;
            } else {
                int finishedFn = stack.pop();
                int duration = timestamp - prevTime+1;
                result[finishedFn] += duration;

                prevTime = timestamp +1;
            }
        }
        return result;
    }
}

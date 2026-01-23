import java.util.PriorityQueue;

class Solution {
    
    static class Node {
        long val;
        int index;
        Node prev, next;
        boolean removed = false;

        Node(long val, int index) {
            this.val = val;
            this.index = index;
        }
    }

    
    static class Pair implements Comparable<Pair> {
        long sum;
        Node left;
        Node right;
        int index;

        Pair(long sum, Node left, Node right) {
            this.sum = sum;
            this.left = left;
            this.right = right;
            this.index = left.index;
        }

        @Override
        public int compareTo(Pair other) {
            
            if (this.sum != other.sum) {
                return Long.compare(this.sum, other.sum);
            }
            
            return Integer.compare(this.index, other.index);
        }
    }

    public int minimumPairRemoval(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;

        
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(nums[i], i);
        }
        for (int i = 0; i < n - 1; i++) {
            nodes[i].next = nodes[i + 1];
            nodes[i + 1].prev = nodes[i];
        }

        
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        int badCount = 0;

        for (int i = 0; i < n - 1; i++) {
            if (nodes[i].val > nodes[i + 1].val) {
                badCount++;
            }
            pq.add(new Pair(nodes[i].val + nodes[i + 1].val, nodes[i], nodes[i + 1]));
        }

        int operations = 0;

        
        while (badCount > 0 && !pq.isEmpty()) {
            Pair p = pq.poll();
            Node left = p.left;
            Node right = p.right;

        
            if (left.removed || right.removed || left.next != right || (left.val + right.val != p.sum)) {
                continue;
            }

            operations++;

            
            if (left.prev != null && left.prev.val > left.val) {
                badCount--;
            }
            
            if (left.val > right.val) {
                badCount--;
            }
            
            if (right.next != null && right.val > right.next.val) {
                badCount--;
            }

            
            long newVal = left.val + right.val;
            left.val = newVal;
            
            
            left.next = right.next;
            if (right.next != null) {
                right.next.prev = left;
            }
            right.removed = true;

            
            
            
            if (left.prev != null) {
                if (left.prev.val > left.val) {
                    badCount++;
                }
                
                pq.add(new Pair(left.prev.val + left.val, left.prev, left));
            }

            
            if (left.next != null) {
                if (left.val > left.next.val) {
                    badCount++;
                }
                
                pq.add(new Pair(left.val + left.next.val, left, left.next));
            }
        }

        return operations;
    }
}

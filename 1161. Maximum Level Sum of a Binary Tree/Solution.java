/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int maxLevelSum(TreeNode root) {
        if (root == null) return 0;

        int maxSum = Integer.MIN_VALUE;
        int maxLevel = 1;
        int currentLevel = 1;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);


        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            int currentLevelSum = 0;

            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                currentLevelSum += currentNode.val;

                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }

            if (currentLevelSum > maxSum) {
                maxSum = currentLevelSum;
                maxLevel = currentLevel;
            }
            currentLevel++;
        }
        return maxLevel;        
    }
}

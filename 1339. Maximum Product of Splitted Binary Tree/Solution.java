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
    private List <Long> sums = new ArrayList<>();
    public int maxProduct(TreeNode root) {
        long totalSum = dfs(root);
        long maxProduct = 0;

        for (long s:sums){
            long product = s*(totalSum-s);
            if (product> maxProduct){
                maxProduct = product;
            }
        }

        return (int) (maxProduct % 1000000007);
    }

    private long dfs(TreeNode node) {
        if (node == null) return 0;

        long currentSum =node.val +dfs(node.left) + dfs(node.right);
        sums.add(currentSum);
        return currentSum;
    }
}

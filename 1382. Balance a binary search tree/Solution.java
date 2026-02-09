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

import java.util.ArrayList;
import java.util.List;

class Solution {
    private List<TreeNode> sortedNodes = new ArrayList<>();

    public TreeNode balanceBST(TreeNode root) {
        inOrderTraversal(root);

        return createBalancedBST(0, sortedNodes.size()-1);
    }

    private void inOrderTraversal(TreeNode node){
        if (node==null){
            return;
        }

        inOrderTraversal(node.left);
        sortedNodes.add(node);
        inOrderTraversal(node.right);
    }

    private TreeNode createBalancedBST(int start, int end){
        if(start>end){
            return null;
        }

        int mid = start+(end-start)/2;

        TreeNode node = sortedNodes.get(mid);

        node.left = createBalancedBST(start, mid-1);
        node.right = createBalancedBST(mid+1, end);
        return node;
    }
}

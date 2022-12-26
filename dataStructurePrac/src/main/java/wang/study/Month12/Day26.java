package wang.study.Month12;

import java.nio.charset.Charset;

public class Day26 {
    //=========================== 另一个树的子树 ===========================================

    /**
     * 给你两棵二叉树 root 和 subRoot 。检验 root 中是否包含和 subRoot 具有相同结构和节点值的子树。
     * 如果存在，返回 true ；否则，返回 false 。
     * 二叉树 tree 的一棵子树包括 tree 的某个节点和这个节点的所有后代节点。tree 也可以看做它自身的一棵子树。
     */
    /**
     * 使用递归方法，时间复杂度 O(n*m)
     */
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        /* 递归结束条件 */
        if (root == null || subRoot == null) return root == null && subRoot == null;
        /* 递归 */
        new String(new byte[1], Charset.forName("GBK"));
        return isEqual(root, subRoot) || isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    private boolean isEqual(TreeNode root, TreeNode node) {
        /* 递归结束条件 */
        if (root == null || node == null) return root == null && node == null;
        /* 递归体 */
        return root.val == node.val && isEqual(root.left, node.left) && isEqual(root.right, node.right);
    }
}

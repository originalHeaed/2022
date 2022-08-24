package wang.study.Month07;

import wang.study.innerClass.TreeNode;

import java.util.Stack;

/**
 * Author:   wang
 * Date:     2022/7/11 20:52
 * function:
 */
public class Day11 {

    private boolean res = true;

    /**
     * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
     * 有效 二叉搜索树定义如下：
     * 节点的左子树只包含 小于 当前节点的数。
     * 节点的右子树只包含 大于 当前节点的数。
     * 所有左子树和右子树自身必须也是二叉搜索树。
     * 使用后序遍历法，对二叉树进行遍历，获取左右子树的最大值，然后判断是否符合条件
     * 时间复杂度：O（n）
     * 空间复杂度：O（n）
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        postReverse(root);
        return res;
    }

    /**
     * 找出当前 Node 节点中的最小值以及最大值：会返回一个长度为 2 的数组，下标为 0 的表示最小值，下标为 1 的表示最大值
     */
    private int[] postReverse(TreeNode node) {
        if (!res) return new int[]{node.val, node.val};
        if (node.left == null && node.right == null) return new int[]{node.val, node.val};
        int[] right;
        int[] left;
        if (node.left == null) {
            if ((right = postReverse(node.right))[0] > node.val) {
                return new int[]{node.val, right[1]};
            }
            else res = false;
        } else if (node.right == null){
            if ((left = postReverse(node.left))[1] < node.val) return new int[]{left[0], node.val};
            else res = false;
        } else {
            if ((right = postReverse(node.right))[0] > node.val && (left = postReverse(node.left))[1] < node.val) {
                return new int[]{left[0], right[1]};
            }
            else res = false;
        }
        return new int[]{node.val, node.val};
    }

    /**
     * 使用中序遍历的方式，判断是否符合规范
     * @param root
     * @return
     */
    public boolean isValidBST2(TreeNode root) {
        if (root == null) return true;
        StringBuilder sb = new StringBuilder();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        long pre = Long.MIN_VALUE;
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
        while (!stack.isEmpty()) {
            TreeNode tem = stack.pop();
            if (tem.val <= pre) return false;
            else pre = tem.val;
            tem = tem.right;
            while (tem != null) {
                stack.push(tem);
                tem = tem.left;
            }
        }
        return true;
    }

    /**
     * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
     * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，
     * 最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。
     * 由于是一颗二叉树搜索树，因此我们只需要找到一个节点，其满足下面两种要求之一，则为最近公共祖先：
     * （1）p 和 q 分别在其两侧；
     * （2）该节点为 p 或 q
     * 时间复杂度：O（n）（n 为树高）
     * 空间复杂度：O（1） —— 尾递归
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val == p.val || root.val == q.val) return root;
        else if (root.val < p.val && root.val > q.val) return root;
        else if (root.val > p.val && root.val < q.val) return root;
        else if (root.val < p.val) return lowestCommonAncestor(root.right, p, q);
        else return lowestCommonAncestor(root.left, p, q);
    }
}

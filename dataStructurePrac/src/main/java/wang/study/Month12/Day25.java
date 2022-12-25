package wang.study.Month12;

import java.util.LinkedList;
import java.util.List;

public class Day25 {
    //========================== 最大二叉树 II =========================

    /**
     * 给出一个二叉树，该二叉树节构建过程如下：
     * 给定一个不重复的整数数组 nums 。 最大二叉树 可以用下面的算法从 nums 递归地构建:
     *      * (1)创建一个根节点，其值为 nums 中的最大值。
     *      * (2)递归地在最大值 左边 的 子数组前缀上 构建左子树。
     *      * (3)递归地在最大值 右边 的 子数组后缀上 构建右子树。
     * 然后给出一个 val，把该 val 加入到 nums 最后位置上，然后利用产生的新数组以及上面构建二叉树
     * 的过程重新构建一颗二叉树
     */
    /**
     * 使用方法：还原 nums，然后在 nums 最后一个元素中加入 val，然后再以新生成的 nums 按照
     * 指定的规则构建新的二叉树。
     *
     * @param root
     * @param val
     * @return
     */
    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        /* 特殊情况判断 */
        if (root == null) return new TreeNode(val);
        /* 获取原数组 */
        List<Integer> list = new LinkedList<>();
        unpackTree(root, list);
        int[] numsB = new int[list.size() + 1];
        for (int i = 0; i < numsB.length - 1; i++) {
            numsB[i] = list.get(i);
        }
        numsB[numsB.length - 1] = val;
        /* 构建一颗新树 */
        return create(numsB, 0, numsB.length - 1);
    }

    /**
     * 递归解包，本质就是一次中序遍历
     */
    private void unpackTree(TreeNode node, List<Integer> list) {
        /* 递归结束条件 */
        if (node == null) return;
        /* 先遍历左子树 */
        unpackTree(node.left, list);
        /* 将该节点加入 nums 中 */
        list.add(node.val);
        /* 遍历右子树 */
        unpackTree(node.right, list);
    }

    private TreeNode create(int[] nums, int left, int right) {
        int maxIndex = findMaxIndex(nums, left, right);
        /* 递归结束条件 */
        if (maxIndex == -1) return null;
        TreeNode node = new TreeNode(nums[maxIndex]);
        node.left = create(nums, left, maxIndex - 1);
        node.right = create(nums, maxIndex + 1, right);
        return node;
    }

    private int findMaxIndex(int[] nums, int left, int right) {
        /* 特殊情况 */
        if (left > right) return -1;
        if (left == right) return left;
        /* 找到 [left, right] 范围内最大值的 index */
        int max = -1;
        int index = -1;
        for (int i = left; i <= right; i++) {
            if (nums[i] > max) {
                max = nums[i];
                index = i;
            }
        }
        return index;
    }

    /**
     * 在原树上进行调整
     * 时间复杂度：O（n）
     */
    public TreeNode insertIntoMaxTree2(TreeNode root, int val) {
        /* val 大于根节点 */
        if (root == null || root.val < val) {
            return new TreeNode(val, root, null);
        }
        /* 继续递归 */
        insertIntoMaxTree2(root.right, val);
        return root;
    }

}

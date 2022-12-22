package wang.study.Month12;

public class Day22 {
    //======================= 最大二叉树 ================================

    /**
     * 给定一个不重复的整数数组 nums 。 最大二叉树 可以用下面的算法从 nums 递归地构建:
     * (1)创建一个根节点，其值为 nums 中的最大值。
     * (2)递归地在最大值 左边 的 子数组前缀上 构建左子树。
     * (3)递归地在最大值 右边 的 子数组后缀上 构建右子树。
     * @param nums
     * @return
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return create(nums, 0, nums.length - 1);
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

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
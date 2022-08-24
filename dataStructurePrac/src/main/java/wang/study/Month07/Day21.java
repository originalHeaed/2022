package wang.study.Month07;

import wang.study.innerClass.TreeNode;

/*********************************************************
 * 文件名称：Day21.java
 * 软件版权: 恒生电子股份有限公司
 * 系统名称：期货保证金（Java 版）
 * 系统版本：5.0
 * 模块名称：wgh.test
 * 功能说明：
 * 开发人员 @author：wanggh31690
 * 开发时间 @date：2022/7/21 10:41
 * 修改记录：程序版本  修改日期  修改人员  修改单号  修改说明
 *********************************************************/
public class Day21 {
    /**
     * 给你二叉树的根结点 root ，此外树的每个结点的值要么是 0 ，要么是 1 。
     * 返回移除了所有不包含 1 的子树的原二叉树。
     * 节点 node 的子树为 node 本身加上所有 node 的后代。
     * 使用二叉树的后续遍历：
     *      如果左子树和右子树均为 null 且 当前节点值为 0，则 return null
     *      否则 return 当前节点
     * 时间复杂度：O（n）， n - 树总节点数
     * @param root
     * @return
     */
    public TreeNode pruneTree(TreeNode root) {
        /* 递归结束条件 */
        if (root == null) return root;
        /* 递归体 */
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        if (root.left == null && root.right == null && root.val == 0) return null;
        return root;
    }

    /**
     * 排序
     * 按照升序进行排序
     * @param nums
     */
    public void sortNum(int[] nums) {
        /* 进行冒泡排序 */
        //this.MaoPaoSort(nums);
        /* 进行选择排序 */
        //this.choseSort(nums);
        /* 进行插入排序 */
        this.insertSort(nums);
    }

    /**
     * 冒泡排序，时间复杂度：O（n^2)
     * 稳定
     * @param nums
     */
    private void MaoPaoSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - i - 1; j++) {
                /* 进行交换 */
                if (nums[j + 1] > nums[j]) {
                    nums[j + 1] = nums[j] ^ nums[j + 1];
                    nums[j] = nums[j] ^ nums[j + 1];
                    nums[j + 1] = nums[j] ^ nums[j + 1];
                }
            }
        }
    }

    /**
     * 选择排序，时间复杂度：O(n^2)
     * 不稳定
     * @param nums
     */
    private void choseSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] > nums[i]) {
                    nums[i] = nums[i] ^ nums[j];
                    nums[j] = nums[i] ^ nums[j];
                    nums[i] = nums[i] ^ nums[j];
                }
            }
        }
    }

    /**
     * 插入排序，时间复杂度：O(n^2) ，时间复杂度波动程度比较大，最好情况下为 O（n），最坏情况下为 O（n^2）
     * 稳定
     * @param nums
     */
    private void insertSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            for (int j = i; j > 0; --j) {
                if (nums[j] < nums[j - 1]) {
                    nums[j] = nums[j] ^ nums[j - 1];
                    nums[j - 1] = nums[j] ^ nums[j - 1];
                    nums[j] = nums[j] ^ nums[j - 1];
                } else break;
            }
        }
    }

    /**
     * 快速排序
     * @param nums
     */
    private void fastSort(int[] nums) {

    }

    public static void main(String[] args) {
        Day21 day21 = new Day21();
        int[] nums = new int[]{1,4,2,7,9,4,8,67};
        day21.sortNum(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
    }
}

package wang.study.Month07;

/*********************************************************
 * 文件名称：Day29.java
 * 软件版权: 恒生电子股份有限公司
 * 系统名称：期货保证金（Java 版）
 * 系统版本：5.0
 * 模块名称：wang.study.Month07
 * 功能说明：
 * 开发人员 @author：wanggh31690
 * 开发时间 @date：2022/7/29 14:27
 * 修改记录：程序版本  修改日期  修改人员  修改单号  修改说明
 *********************************************************/
public class Day29 {
    /**
     * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
     * 如果数组中不存在目标值 target，返回 [-1, -1]。
     * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        /* 特殊情况处理 */
        if (nums == null || nums.length == 0 || nums[0] > target || nums[nums.length - 1] < target)
            return new int[]{-1, -1};
        /* 使用二分法获取 target 第一次以及最后一次出现的位置 */
        int left0 = 0;
        int right0 = nums.length - 1; // 从左到右：寻找第一个 target（或者第一个比 target 大的数）
        int left1 = 0;
        int right1 = nums.length - 1; // 从右到左：寻找第一个 target（或者最后一个比 target 小的数）
        while (left0 < right0 || left1 < right1) {
            int mid0 = left0 + (right0 - left0) / 2;
            int mid1 = left1 + (right1 - left1 + 1) / 2; // import：当 left = mid 时需要使用
            if (left0 < right0) {
                if (nums[mid0] >= target) right0 = mid0;
                else left0 = mid0 + 1;
            }
            if (left1 < right1) {
                if (nums[mid1] <= target) left1 = mid1;
                else right1 = mid1 - 1;
            }
        }
        return nums[left0] == target ? new int[]{left0, left1} : new int[]{-1, -1};
    }

    /**
     * 整数数组 nums 按升序排列，数组中的值 互不相同 。
     * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，
     * 使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
     * 例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
     * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
     * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {

        return 0;
    }
}

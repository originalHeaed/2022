package wang.study.Month12;

public class Day21 {
    //====================== 使数组唯一的最小增量 ==========================
    /**
     * 给你一个整数数组 nums 。每次 move 操作将会选择任意一个满足 0 <= i < nums.length 的下标 i，并将 nums[i] 递增 1。
     * 返回使 nums 中的每个值都变成唯一的所需要的最少操作次数。
     * 1 <= nums.length <= 10^5
     * 0 <= nums[i] <= 10^5
     * 本质（不严格）：变成一个步长为1的递增数组，花费的步数
     */
    /**
     * 纯粹使用闭散列
     * @param nums
     * @return
     */
    public int minIncrementForUnique(int[] nums) {
        /* 特殊情况判断 */
        if (nums == null || nums.length == 0 || nums.length == 1) return 0;
        /* 使用闭散列表 */
        boolean[] isVisited = new boolean[200000 + 1];/* 由于数组长度最长为 100000，内部的数字最大为 100000 */
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int j = 0;
            while (isVisited[nums[i] + j]) {
                ++j;
            }
            isVisited[nums[i] + j] = true;
            res += j;
        }
        return res;
    }

    /**
     * 使用 hash 思想 + 记录状态
     * @param nums
     * @return
     */
    public int minIncrementForUnique2(int[] nums) {
        /* 特殊情况判断 */
        if (nums == null || nums.length == 0 || nums.length == 1) return 0;
        /* 使用闭散列表 */
        boolean[] isVisited = new boolean[200000 + 1];/* 由于数组长度最长为 100000，内部的数字最大为 100000，因此长度设置为 200001 */
        int[] nextPosition = new int[200000 + 1];/* 记录每一个位置下出现冲突时，下一个可以存放数据坐标的偏移量 */
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int index = nums[i];
            int offset = nextPosition[index];
            while (isVisited[index + offset]) {
                ++offset;
            }
            res += offset;
            isVisited[index + offset] = true; // 将指定位置置为不可用
            nextPosition[index] = offset + 1; // 更新下一个可以访问的位置
        }
        return res;
    }
}

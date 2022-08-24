package wang.study.Month07;

/**
 * Author:   wang
 * Date:     2022/7/4 21:18
 * function: ComeOn
 */
public class Day04 {
    /**
     * 获取数组的动态和
     * 时间复杂度：O（n）
     * @param nums
     * @return
     */
    public int[] runningSum(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        for (int i = 1; i < nums.length; i++) {
            nums[i] = nums[i - 1] + nums[i];
        }
        return nums;
    }

    /**
     * 获取中心下标 使用双指针，朝着使双方更接近的目标去（这种方法目前无解，无法使用双指针将时间复杂度降低到 O（n））
     * 时间复杂度：O(n)
     * @param nums
     * @return
     */
    public int pivotIndex(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        if (nums.length == 1) return 0;
        int lPoint = 0;
        int rPoint = nums.length - 1;
        int lTotal = nums[lPoint];
        int rTotal = nums[rPoint];
        while (lPoint != rPoint) {
            int r = Math.abs(lTotal - rTotal - nums[rPoint - 1]); // 选择右边之后对差值的影响
            int l = Math.abs(rTotal - lTotal - nums[lPoint + 1]); // 选择左边之后对差值的影响
            /* 值越大表示越不难到达中心下标 */
            if (l >= r) {
                --rPoint;
                rTotal += nums[rPoint];
            } else {
                ++lPoint;
                lTotal +=  nums[lPoint];
            }
        }
        return lTotal == rTotal ? lPoint : -1;
    }


    /**
     * 获取中心下标 使用双指针，暴力法
     * 时间复杂度：O(n)
     * @param nums
     * @return
     */
    public int pivotIndex2(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        if (nums.length == 1) return 0;
        int total = 0;
        for (int i = 0; i < nums.length; i++) {
            total += nums[i];
        }
        int lTotal = 0;
        for (int i = 0; i < nums.length; i++) {
            if (total - nums[i] == lTotal * 2) return i;
            lTotal += nums[i];
        }
        return -1;
    }
}

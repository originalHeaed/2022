package wang.study.Month07;

/**
 * Author:   wang
 * Date:     2022/7/10 20:32
 * function:
 */
public class Day10 {

    /**
     * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，
     * 写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
     * 使用暴力法
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        if (nums == null) return -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) return i;
        }
        return -1;
    }


    /**
     * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，
     * 写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
     * 使用二分法
     * @param nums
     * @param target
     * @return
     */
    public int search2(int[] nums, int target) {
        if (nums == null) return -1;
        int left = 0;
        int right = nums.length - 1;
        while (right >= left) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] > target) right = mid - 1;
            else left = mid + 1;
        }
        return -1;
    }


    /**
     * 假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。
     * 你可以通过调用 bool isBadVersion(version) 接口来判断版本号 version
     * 是否在单元测试中出错。实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。
     * 暴力法 O（n）
     * @param n
     * @return
     */
    public int firstBadVersion(int n) {
        if (n < 1) return -1;
        for (int i = 1; i <= n; i++) {
            if (isBadVersion(n)) return i;
        }
        return -1;
    }

    private boolean isBadVersion(int i) {
        return false;
    }


    /**
     * 使用二分法
     * @param n
     * @return
     */
    public int firstBadVersion2(int n) {
        if (n < 1) return -1;
        int left = 1;
        int right = n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (isBadVersion(mid)) right = mid;
            else left = mid + 1;
        }
        return isBadVersion(left) ? left : -1;
    }
}

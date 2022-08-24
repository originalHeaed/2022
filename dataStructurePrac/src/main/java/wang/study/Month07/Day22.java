package wang.study.Month07;

/*********************************************************
 * 文件名称：Day22.java
 * 软件版权: 恒生电子股份有限公司
 * 系统名称：期货保证金（Java 版）
 * 系统版本：5.0
 * 模块名称：wang.study.Month07
 * 功能说明：
 * 开发人员 @author：wanggh31690
 * 开发时间 @date：2022/7/22 10:56
 * 修改记录：程序版本  修改日期  修改人员  修改单号  修改说明
 *********************************************************/
public class Day22 {

    /**
     * 给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
     * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
     * 从后向前填充 nums1
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        /* 特殊情况处理 */
        if (n == 0) return;
        int index = nums1.length - 1; // 最大数存放的位置
        while (n > 0) {
            if (m > 0 && nums1[m - 1] > nums2[n - 1]) {
                nums1[index--] = nums1[--m];
            } else {
                nums1[index--] = nums2[--n];
            }
        }
    }

    /**
     * 排序
     * 按照升序进行排序
     * @param nums
     */
    public void sortNum(int[] nums) {
        // 使用并归排序
        // this.mergerSort(nums, 0, nums.length - 1);
        // 快速排序
        // this.fastSort(nums, 0 , nums.length - 1);
        // 桶排序
        this.bucketSort(nums);
    }

    /**
     * 并归排序
     * 时间复杂度：O（nlogn） —— 时间复杂度稳定
     * 稳定
     * @param nums
     * @param left
     * @param right
     */
    private void mergerSort(int[] nums, int left, int right) {
        /* 递归结束 */
        if (left >= right) return;
        /* 递归体 */
        int mid = left + (right - left) / 2;
        mergerSort(nums, left, mid);
        mergerSort(nums, mid + 1, right);
        mergerNums(nums, left, right, mid);
    }

    private void mergerNums(int[] nums, int left, int  right, int mid) {
        if (left >= right) return;
        int[] tem = new int[right - left + 1];
        /* 合并 */
        int l = left;
        int r = mid + 1;
        int index = 0;
        while (l <= mid || r <= right) {
            if (l > mid) tem[index++] = nums[r++];
            else if (r > right) tem[index++] = nums[l++];
            else tem[index++] = nums[l] > nums[r] ? nums[r++] : nums[l++];
        }
        /* 该变 nums 值 */
        index = 0;
        for (int i = left; i <= right; i++) {
            nums[i] = tem[index++];
        }
    }

    /**
     * 快速排序
     * 时间复杂度：O（nlogn）—— 平均，但是不稳定，最差可达到 O（n^2）
     * 不稳定
     * @param nums
     * @param left
     * @param right
     */
    private void fastSort(int[] nums, int left, int right) {
        /* 递归结束条件 */
        if (left >= right) return;
        /* 递归体 */
        int l = left;
        int r = right;
        int tem = nums[l];
        while (l < r) {
            /* 从右向左找第一个比 tem 小的数  */
            while (l < r && tem <= nums[r]) r--;
            nums[l] = nums[r];
            /* 从左向右找第一个比 tem 大的数 */
            while (l < r && tem >= nums[l]) l++;
            nums[r] = nums[l];
        }
        nums[l] = tem;
        fastSort(nums, left, l - 1);
        fastSort(nums, l + 1, right);
    }

    /**
     * 使用桶排序（假设 nums 数组中最大值不超过 1000）
     * 时间复杂度：O（n） —— 最差是 m + n，n:数组长度，m:最大值
     * 空间复杂度:O（maxValue）
     * @param nums
     */
    private void bucketSort(int[] nums) {
        int[] bucket = new int[1000];
        /* 将数据登记在对应的 bucket 中 */
        for (int i = 0; i < nums.length; i++) {
            bucket[nums[i]]++;
        }
        int index = 0;
        /* 从小到大存放在 nums 中 */
        for (int i = 0; i < bucket.length; i++) {
            while (bucket[i] > 0) {
                nums[index++] = i;
                --bucket[i];
            }
        }
    }

    public static void main(String[] args) {
        Day22 day22 = new Day22();
        int[] nums = new int[]{1,5,3,7,9,5,3,7};
        day22.sortNum(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
    }
}

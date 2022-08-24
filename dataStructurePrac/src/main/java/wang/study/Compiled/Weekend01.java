package wang.study.Compiled;

import java.util.*;

/**
 * Author:   wang
 * Date:     2022/7/17 10:30
 * function:
 */
public class Weekend01 {
    /**
     * 给你一个下标从 0 开始的整数数组 nums 。在一步操作中，你可以执行以下步骤：
     * 从 nums 选出 两个 相等的 整数
     * 从 nums 中移除这两个整数，形成一个 数对
     * 请你在 nums 上多次执行此操作直到无法继续执行。
     * 使用哈希表
     * @param nums
     * @return
     */
    public int[] numberOfPairs(int[] nums) {
        Set<Integer> helper = new HashSet<>();
        /* 特殊情况 */
        if (nums == null || nums.length == 0) return new int[]{0, 0};
        /* 寻找数对以及剩下的值 */
        int couple = 0;
        for (int i = 0; i < nums.length; i++) {
            if (helper.contains(nums[i])) helper.remove(nums[i]);
            else helper.add(nums[i]);
        }
        return new int[]{(nums.length - helper.size()) / 2, helper.size()};
    }

    /**
     * 给你一个下标从 0 开始的数组 nums ，数组中的元素都是 正 整数。
     * 请你选出两个下标 i 和 j（i != j），且 nums[i] 的数位和 与  nums[j] 的数位和相等。
     * 请你找出所有满足条件的下标 i 和 j ，找出并返回 nums[i] + nums[j] 可以得到的 最大值
     * @param nums
     * @return
     */
    public int maximumSum(int[] nums) {
        Map<Integer, Integer> helper = new HashMap<>(); // key-位数和，value-该位数和最大值
        /* 特殊情况 */
        if (nums == null || nums.length < 2) return -1;
        /* 求最大位数和 */
        int max = -1;
        for (int i = 0; i < nums.length; i++) {
            int sun = getSun(nums[i]);
            int tem = nums[i];
            if (helper.get(sun) != null) {
                max = Math.max(max, helper.get(sun) + tem);
                tem = Math.max(helper.get(sun), tem);
            }
            helper.put(sun, tem);
        }
        return max;
    }

    /**
     * 获取位数和
     * @param val
     * @return
     */
    private int getSun(int val) {
        int res = 0;
        while (val != 0) {
            res += val % 10;
            val /= 10;
        }
        return res;
    }

    /**
     * 给你一个下标从 0 开始的字符串数组 nums ，其中每个字符串 长度相等 且只包含数字。
     * 再给你一个下标从 0 开始的二维整数数组 queries ，其中 queries[i] = [ki, trimi] 。对于每个 queries[i] ，你需要：
     * 将 nums 中每个数字 裁剪 到剩下 最右边 trimi 个数位。
     * 在裁剪过后的数字中，找到 nums 中第 ki 小数字对应的 下标 。如果两个裁剪后数字一样大，那么下标 更小 的数字视为更小的数字。
     * 将 nums 中每个数字恢复到原本字符串。
     * 请你返回一个长度与 queries 相等的数组 answer，其中 answer[i]是第 i 次查询的结果。
     * @param nums
     * @param queries
     * @return
     */
    public int[] smallestTrimmedNumbers(String[] nums, int[][] queries) {
        int[] res = new int[queries.length];
        /* 特殊处理 */
        if (nums == null || nums.length == 0
                || queries == null || queries.length == 0) return null;
        for (int i = 0; i < queries.length; i++) {
            res[i] = getAnswer(queries[i][1], queries[i][0], nums);
        }
        return res;
    }

    /**
     * 获取第 i 个 answer
     */
    private int getAnswer(int trim, int k, String[] nums) {
        String[] temArr = Arrays.copyOf(nums, nums.length);
        for (int i = 0;i  < k; i++) {
            /* 寻找 [i, temArr.lenght - 1] 内的最小值 */
            for (int j = i; j < temArr.length; j++) {
                if (compare(temArr[i], temArr[j], trim) != -1) {
                    /* 交换 */
                    String tem = temArr[i];
                    temArr[i] = temArr[j];
                    temArr[j] = tem;
                }
            }
        }
        for (int m = 0; m < nums.length; m++) {
            if (nums[m].equals(temArr[k - 1])) return m;
        }
        return -1;
    }

    /**
     * 从 begin 开始比较 a 与 b 的大小
     * 1: a > b
     * -1: a < b
     * 0: a== b
     */
    private int compare(String a, String b, int trim) {
        int begin = a.length() - trim;
        while (begin < a.length()) {
            if (a.charAt(begin) > b.charAt(begin)) return 1;
            else if (a.charAt(begin) < b.charAt(begin)) return -1;
            begin++;
        }
        return 0;
    }

    public static void main(String[] args) {
        Weekend01 weekend01 = new Weekend01();
        weekend01.smallestTrimmedNumbers(new String[]{"24","37","96","04"}, new int[][]{{2,1}, {2,2}});

    }
}

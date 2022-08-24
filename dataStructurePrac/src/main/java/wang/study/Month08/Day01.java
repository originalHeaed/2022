package wang.study.Month08;

import wang.study.innerClass.ListNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Author:   wang
 * Date:     2022/8/1 20:13
 * function:
 */
public class Day01 {
    /**
     * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
     *
     * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
     * 使用 DP
     * 假设我们现在已经知道 nums 所有的子集 sub[][]，这是我们向 nums 中增加一个元素 new
     * 新的子集未：sub[][] + sub[] 每一个子集中增加 new
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new LinkedList<Integer>());
        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];
            int size = res.size();
            for (int j = 0; j < size; j++) {
                List<Integer> copy = copy(res.get(j));
                copy.add(val);
                res.add(copy);
            }
        }
        return res;
    }

    /**
     * 复制 src
     */
    private List<Integer> copy(List<Integer> src) {
        List<Integer> target = new ArrayList<>();
        for (int i = 0; i < src.size(); i++) {
            target.add(src.get(i));
        }
        return target;
    }
}

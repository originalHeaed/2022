package wang.study.Month07;

import com.sun.javafx.collections.MappingChange;

import java.util.HashMap;
import java.util.Map;

/**
 * Author:   wang
 * Date:     2022/7/16 21:53
 * function:
 */
public class Day16 {

    /**
     * 给定一个整数数组 nums 和一个整数目标值 target，
     * 请你在该数组中找出 和为目标值 target 的那 两个 整数，并返回它们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int opp = target - nums[i];
            if (map.get(opp) != null) return new int[]{i, map.get(opp)};
            map.put(opp, i);
        }
        return new int[]{-1, -1};
    }


    /**
     * 给出两串数字 secret 和 guess，以 guess 为主获取两种数据：
     * 第一种：guess 与 secret 数值相同，且对应的下标相同；
     * 第二种：guess 与 secret 数值相同，但是不在对应的下标中
     * @param secret
     * @param guess
     * @return
     */
    public String getHint(String secret, String guess) {
        /* 特殊情况 */
        if (secret == null || guess == null || secret.length() != guess.length()) return "error";
        /* 寻找 */
        int bulls = 0;
        int cows = 0;
        int[] secretHelper = new int[10];
        int[] guessHelper = new int[10];
        for (int i = secret.length() - 1; i >= 0 ; --i) {
            if (secret.charAt(i) == guess.charAt(i)) bulls++;
            else {
                ++secretHelper[secret.charAt(i) - '0'];
                ++guessHelper[guess.charAt(i) - '0'];
            }
        }
        for (int i = 0; i < 10; i++) {
            cows += Math.min(secretHelper[i], guessHelper[i]);
        }
        return bulls + "A" + cows + "B";
    }
}

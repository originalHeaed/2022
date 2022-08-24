package wang.study.Month07;

import java.util.LinkedList;
import java.util.List;

/**
 * Author:   wang
 * Date:     2022/7/16 19:19
 * function:
 */
public class Day15 {
    public List<Integer> findAnagrams(String s, String p) {

        /* 特色情况处理 */
        if (s == null || p == null || s.length() < p.length()) return new LinkedList<>();
        /* 数据初始化 */
        int[] helper = new int[26];
        int[] tem = new int[26];
        for (int i = p.length() - 1; i >= 0 ; --i) {
            ++helper[p.charAt(i) - 'a'];
        }
        List<Integer> res = new LinkedList<>();
        /* 使用双指针开始获取数据 */
        int left = 0;
        int right = 0;
        while (right < s.length()) {
            int valP = helper[s.charAt(right) - 'a'];
            int valS = ++tem[s.charAt(right) - 'a'];
            if (valP < valS){
                /* 多了一个 s.charAt(right)，左指针收缩一个 s.charAt(right) */
                while (s.charAt(right) != s.charAt(left)) {
                    --tem[s.charAt(left) - 'a'];
                    ++left;
                }
                --tem[s.charAt(right) - 'a'];
                left = left + 1;
            } else if ((right - left + 1) == p.length()){
                /* p 中内容全部在 [left, right] 中 */
                res.add(left);
                --tem[s.charAt(left) - 'a'];
                left = left + 1;
            }
            ++right;
        }
        return res;
    }

    /**
     * 给你一个字符串 s 和一个整数 k 。你可以选择字符串中的任一字符，
     * 并将其更改为任何其他大写英文字符。该操作最多可执行 k 次。
     * 在执行上述操作后，返回包含相同字母的最长子字符串的长度。
     * 解决方法：使用双指针，时间复杂度：O（52（n-m））
     * @param s
     * @param k
     * @return
     */
    public int characterReplacement(String s, int k) {
        if (s.length() <= k) return s.length();
        int res = 0;
        char[] chars = s.toCharArray();
        /* 以每一个字母为维度获取最大长度 */
        for (int i = 0; i < 26; i++) {
            int pre = 0;
            int next = 0;
            int diff = 0;
            while (next < chars.length) {
                if (diff <= k) {
                    /* 窗口扩展 */
                    res = Math.max(res, next - pre);
                    if (chars[next++] - 'A' != i) ++diff;
                } else {
                    /* 窗口收缩 */
                    if (chars[pre] - 'A' != i) --diff;
                    pre++;
                }
            }
            if (diff <= k) res = Math.max(res, next - pre);
        }
        return res;
    }


    /**
     * 使用双子指针
     * 我们可以枚举字符串中的每一个位置作为右端点，然后找到其最远的左端点的位置，
     * 满足该区间内除了出现次数最多的那一类字符之外，剩余的字符（即非最长重复字符）数量不超过
     * 妙实在是妙
     * @param s
     * @param k
     * @return
     */
    public int wosishabi(String s, int k) {
        int[] num = new int[26];
        int n = s.length();
        int maxn = 0;
        int left = 0, right = 0;
        while (right < n) {
            num[s.charAt(right) - 'A']++;
            maxn = Math.max(maxn, num[s.charAt(right) - 'A']);
            if (right - left + 1 - maxn > k) {
                num[s.charAt(left) - 'A']--;
                left++;
            }
            right++;
        }
        return right - left;
    }
}

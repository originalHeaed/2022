package wang.study.Month07;

import java.util.Stack;

/**
 * Author:   wang
 * Date:     2022/7/18 22:16
 * function:
 */
public class Day18 {
    /**
     * 给定一个经过编码的字符串，返回它解码后的字符串。
     * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。
     * 注意 k 保证为正整数。
     * 使用堆栈完成
     * @param s
     * @return
     */
    public String decodeString(String s) {
        Stack<Integer> repeatTimes = new Stack<>();
        Stack<String> strs = new Stack<>();
        int index = 0;
        while (index < s.length()) {
            if (s.charAt(index) >= '0' && s.charAt(index) <= '9') return "";
        }
        return "";
    }
}

package wang.study.Month07;

import java.util.Stack;

/**
 * Author:   wang
 * Date:     2022/7/17 20:08
 * function:
 */
public class Day17 {
    /**
     * 给定 s 和 t 两个字符串，当它们分别被输入到空白的文本编辑器后，
     * 如果两者相等，返回 true 。# 代表退格字符。
     * 使用堆栈完成
     * 时间复杂度：O（n + m + c）
     * n: s 的长度
     * m：t 的长度
     * c：两者相等的部分
     * @param s
     * @param t
     * @return
     */
    public boolean backspaceCompare(String s, String t) {
        if (s == t ) return true;
        Stack<Character> stackS = new Stack<>();
        Stack<Character> stackT = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '#') {
                if (!stackS.isEmpty()) stackS.pop();
            } else stackS.push(s.charAt(i));
        }
        for (int i = 0; i < t.length(); i++) {
            if (t.charAt(i) == '#') {
                if (!stackT.isEmpty()) stackT.pop();
            } else stackT.push(t.charAt(i));
        }
        if (stackS.size() != stackT.size()) return false;
        while (!stackS.isEmpty()) {
            if (stackS.pop() != stackT.pop()) return false;
        }
        return true;
    }
}

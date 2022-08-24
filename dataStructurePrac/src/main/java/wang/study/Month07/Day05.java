package wang.study.Month07;

/**
 * Author:   wang
 * Date:     2022/7/6 21:23
 * function:
 */
public class Day05 {
    /**
     * 给定两个字符串 s 和 t ，判断它们是否是同构的。
     * 如果 s 中的字符可以按某种映射关系替换得到 t ，那么这两个字符串是同构的。
     * 每个出现的字符都应当映射到另一个字符，同时不改变字符的顺序。不同字符不能映射到同一个字符上，相同字符只能映射到同一个字符上，字符可以映射到自己本身
     * @param s
     * @param t
     * @return
     */
    public boolean isIsomorphic(String s, String t) {
        if (s.equals(t)) return true;
        if (s.length() != t.length()) return false;
        /* 建立字符间对应关系（仅限 ascii 表中） */
        int[] relationT = new int[257]; /* t 中字符与 S 中字符对应关系 */
        int[] relationS = new int[257]; /* s 中字符与 t 中字符对应关系 */
        for (int i = 0; i < s.length(); ++i) {
            int indexS =s.charAt(i) - '\0' + 1;
            int indexT = t.charAt(i) - '\0' + 1;
            if (relationT[indexT] == 0 && relationS[indexS] == 0) {
                /* s 中该字符与 t 中该字符均未建立联系，互相记录 */
                relationT[indexT] = indexS;
                relationS[indexS] = indexT;
            } else if (relationS[indexS] != indexT || relationT[indexT] != indexS){
                /* 某一个字符已经和其他字符建立联系 */
                return false;
            }
        }
        return true;
    }

    /**
     * 妙
     * @param s
     * @param t
     * @return
     */
    public boolean isIsomorphic2(String s, String t) {
        char[] chars = s.toCharArray();
        char[] chart = t.toCharArray();
        int[] preIndexOfs = new int[256];
        int[] preIndexOft = new int[256];
        for (int i = 0; i < chars.length; i++) {
            if (preIndexOfs[chars[i]] != preIndexOft[chart[i]]) {
                return false;
            }
            preIndexOfs[chars[i]] = i + 1;
            preIndexOft[chart[i]] = i + 1;
        }
        return true;
    }


    /**
     * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
     * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t) {
        /* 特殊情况判断 */
        if (s.equals(t)) return true;
        if (t.length() < s.length()) return false;
        /* 使用双指针 */
        int pointS = 0;
        int pointT = 0;
        int lS = s.length();
        int lT = t.length();
        while (lT - pointT >= lS - pointS && lS - pointS > 0) {
            if (s.charAt(pointS) == t.charAt(pointT))
                ++pointS;
            ++pointT;
        }
        return lT - pointT >= lS - pointS;
    }
}

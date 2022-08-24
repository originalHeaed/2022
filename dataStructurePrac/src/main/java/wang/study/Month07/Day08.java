package wang.study.Month07;

/*********************************************************
 * 文件名称：Day08.java
 * 开发时间 @date：2022/7/8 18:58
 *********************************************************/
public class Day08 {

    /**
     * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
     * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
     * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
     * 暴力法，穷举所有可能，获取最大值;
     * 时间复杂的：O（n^2)
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        /* 特殊情况处理 */
        if (prices == null || prices.length == 0 || prices.length == 1) return 0;
        /* 获取最大值 */
        int maxProfit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                maxProfit = prices[j] - prices[i] > maxProfit ? prices[i] - prices[j] : maxProfit;
            }
        }
        return maxProfit;
    }

    /**
     * 使用记忆法，从后向前，每次将 prices[i] 与 i 之后股票的最大值进行比较
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        /* 特殊情况处理 */
        if (prices == null || prices.length == 0 || prices.length == 1) return 0;
        /* 获取最大值 */
        int maxProfit = 0;
        for (int i = prices.length - 2; i >= 0; --i) {
            int tem = prices[i + 1] - prices[i];
            maxProfit = tem > maxProfit ? tem : maxProfit;
            prices[i] = prices[i + 1] > prices[i] ? prices[i + 1] : prices[i];
        }
        return maxProfit;
    }


    /**
     * 使用记忆法，从前向后，每次将 prices[i] 与 i 之前股票的最小值进行比较
     * @param prices
     * @return
     */
    public int maxProfit3(int[] prices) {
        /* 特殊情况处理 */
        if (prices == null || prices.length == 0 || prices.length == 1) return 0;
        /* 获取最大值 */
        int maxProfit = 0;
        for (int i = 1; i < prices.length; ++i) {
            int tem = prices[i] - prices[i - 1];
            maxProfit = tem > maxProfit ? tem : maxProfit;
            prices[i] = prices[i - 1] > prices[i] ? prices[i] : prices[i - 1];
        }
        return maxProfit;
    }

    /**
     * 给定一个包含大写字母和小写字母的字符串 s ，返回 通过这些字母构造成的 最长的回文串 。
     * 在构造过程中，请注意 区分大小写 。比如 "Aa" 不能当做一个回文字符串。
     * 对题目进行分析，获取其中关键点，从而解决问题
     * @param s
     * @return
     */
    public int longestPalindrome(String s) {
        /* 特殊处理 */
        if (s == null || s.length() == 0) return 0;
        /* 获取最大回文串长度 */
        int[] lowerCaseCount = new int[26];
        int[] capitalCount = new int[26];
        char[] chars = s.toCharArray();
        int maxLength = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] > 'Z') {
                /* 小写字母 */
                ++lowerCaseCount[chars[i] - 'a'];
            } else {
                /* 大写字母 */
                ++capitalCount[chars[i] - 'A'];
            }
        }
        for (int i = 0; i < lowerCaseCount.length; i++) {
            if (lowerCaseCount[i] % 2 != 0) maxLength += lowerCaseCount[i] - 1;
            else maxLength += lowerCaseCount[i];
            if (capitalCount[i] % 2 != 0) maxLength += capitalCount[i] - 1;
            else maxLength += capitalCount[i];
        }
        return maxLength == chars.length ? maxLength : maxLength + 1;
    }
}

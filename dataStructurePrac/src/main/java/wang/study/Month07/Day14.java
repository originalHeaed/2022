package wang.study.Month07;

/**
 * Author:   wang
 * Date:     2022/7/14 22:26
 * function:
 */
public class Day14 {
    /**
     * 使用记忆法
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        int[] helper = new int[n];
        /* 赋予初始值 */
        for (int i = 0; i < helper.length ; i++) {
            helper[i] = 1;
        }
        /* 开始计算 */
        for (int j = 1; j < m; j++) {
            for (int i = 1; i < n; i++) {
                helper[i] = helper[i] + helper[i - 1];
            }
        }
        return helper[n - 1];
    }
}

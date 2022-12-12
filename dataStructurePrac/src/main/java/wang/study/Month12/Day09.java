package wang.study.Month12;

import java.util.Arrays;

public class Day09 {
    //========================= bellmen ford ===================================

    /**
     * 有 n 个城市通过一些航班连接。给你一个数组 flights ，其中 flights[i] = [fromi, toi, pricei] ，
     * 表示该航班都从城市 fromi 开始，以价格 pricei 抵达 toi。
     * 现在给定所有的城市和航班，以及出发城市 src 和目的地 dst，
     * 你的任务是找到出一条最多经过 k 站中转的路线，使得从 src 到 dst 的 价格最便宜 ，
     * 并返回该价格。 如果不存在这样的路线，则输出 -1。
     * 时间复杂度：O（（k+1）n^2）
     * 空间复杂度：O（n^2）
     * @return
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        /* 特殊情况处理 */
        if (dst == src) return 0;
        /* 数据初始化 */
        int[][] graph = new int[n][n];
        for (int i = 0; i < flights.length; i++) {
            graph[flights[i][0]][flights[i][1]] = flights[i][2];
        }
        int[] pre = new int[n];
        int[] next = new int[n];
        Arrays.fill(pre, Integer.MAX_VALUE);
        pre[src] = 0;
        /* bellmen fors 算法（使用动态规划） */
        /* 1. 从长度为 1 的开始计算，每次递增 1，直到长度为 k */
        for (int i = 0; i <= k; i++) {
            /* 获取第 i + 1 站内每个节点到 src 最便宜的价格 */
            for (int j = 0; j < n; j++) {
                /* 获取第 j 个节点在第 i + 1 站内到 src 最便宜的价格 */
                int min = pre[j];
                for (int l = 0; l < n; l++) {
                    /* l -> j */
                    if (graph[l][j] != 0) {
                        if (pre[l] != Integer.MAX_VALUE && (pre[l] + graph[l][j]) < min) min = (pre[l] + graph[l][j]);
                    }
                }
                next[j] = min;
            }
            pre = next;
            next = new int[n];
        }
        return pre[dst] == Integer.MAX_VALUE ? -1 : pre[dst];
    }
}

package wang.study.Month07;


import java.util.ArrayList;
import java.util.List;

/*********************************************************
 * 文件名称：Day20.java
 * 软件版权: 恒生电子股份有限公司
 * 系统名称：期货保证金（Java 版）
 * 系统版本：5.0
 * 模块名称：wgh.test
 * 功能说明：
 * 开发人员 @author：wanggh31690
 * 开发时间 @date：2022/7/20 9:43
 * 修改记录：程序版本  修改日期  修改人员  修改单号  修改说明
 *********************************************************/
public class Day20 {
    /**
     * 给你一个 m 行 n 列的二维网格 grid 和一个整数 k 。你需要将grid迁移 k 次。
     *
     * 每次「迁移」操作将会引发下述活动：
     *
     * 位于 grid[i][j] 的元素将会移动到 grid[i][j + 1]。
     * 位于 grid[i][n - 1] 的元素将会移动到 grid[i + 1][0]。
     * 位于 grid[m - 1][n - 1] 的元素将会移动到 grid[0][0]。
     * 请你返回 k 次迁移操作后最终得到的 二维网格。
     * 使用数学的方法
     * 时间复杂度：O（n）
     * @param grid
     * @param k
     * @return
     */
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int total = grid.length * grid[0].length;
        k %= total;
        List<List<Integer>> res = new ArrayList<>();
        /* 特殊情况处理 */
        if (grid == null || grid.length == 0 || grid[0].length == 0) return new ArrayList<>();
        /* 进行迁移 */
        int val = total - k;
        for (int l = 0; l < grid.length; l++) {
            List<Integer> layer = new ArrayList<>();
            for (int m = 0; m < grid[0].length; m++) {
                int i = (val / grid[0].length) % grid.length; /* 迁移数的 i 下标 */
                int j = val % grid[0].length; /* 迁移数的 j 下标 */
                layer.add(grid[i][j]);
                ++val;
            }
            res.add(layer);
        }
        return res;
    }
}

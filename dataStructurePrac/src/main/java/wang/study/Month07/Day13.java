package wang.study.Month07;

/**
 * Author:   wang
 * Date:     2022/7/13 20:59
 * function:
 */
public class Day13 {

    /**
     * 使用递归方式完成
     * @param n
     * @return
     */
    public int fib(int n) {
        if (n <= 1) return n;
        return fib(n - 2) + fib(n - 1);
    }

    /**
     * 使用动态规划
     * @param n
     * @return
     */
    public int fib2(int n) {
        if (n <= 1) return n;
        int pre = 0;
        int next = 1;
        int tem;
        for (int i = n- 2; i >= 0; --i) {
            tem = next;
            next = next + pre;
            pre = tem;
        }
        return next;
    }

    /**
     * 使用递归
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if (n < 2) return 1;
        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    /**
     * 使用动态规划
     * @param n
     * @return
     */
    public int climbStairs2(int n) {
        int[] helper = {1, 1};
        int tem;
        for (int i = n - 2; i >= 0; --i) {
            tem = helper[1];
            helper[1] = helper[0] + helper[1];
            helper[0] = tem;
        }
        return helper[1];
    }
}

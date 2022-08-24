package wang.study.Month08;

/**
 * Author:   wang
 * Date:     2022/8/4 20:10
 * function:
 */
public class Day04 {
    /**
     * 给你一个字符串 columnTitle ，表示 Excel 表格中的列名称。返回 该列名称对应的列序号 。
     * 将 26 进制转为 10 进制
     * @param columnTitle
     * @return
     */
    public int titleToNumber(String columnTitle) {
        int res = 0;
        int pow = 0;
        for (int i = columnTitle.length() - 1; i >= 0; --i) {
            int time = columnTitle.charAt(i) - 'A' + 1;
            res += time * Math.pow(26, pow);
            ++pow;
        }
        return res;
    }

    /**
     * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
     * 返回被除数 dividend 除以除数 divisor 得到的商。
     * 使用减法
     * @param dividend
     * @param divisor
     * @return
     */
    public int divide(int dividend, int divisor) {
        int isNegative = 1;
        if (dividend < 0 && divisor > 0) {
            isNegative = -1;
            divisor = -divisor;
        } else if (dividend > 0 && divisor < 0) {
            isNegative = -1;
            dividend = -dividend;
        } else if (dividend < 0 && divisor < 0) {
            isNegative = 0;
        }
        int res = 0;
        if (isNegative == -1) {
            /* negative and positive */
            while (dividend <= divisor) {
                --res;
                dividend -= divisor;
            }
        } else if (isNegative == 1){
            /* positive and positive */
            while (dividend >= divisor) {
                ++res;
                dividend -= divisor;
            }
        } else {
            /* nagative and nagative */
            while (dividend <= divisor) {
                if (res == Integer.MIN_VALUE - 1) return res;
                ++res;
                dividend -= divisor;
            }
        }
        return res;
    }

    /**
     *
     * @param dividend
     * @param divisor
     * @return
     */
    public int divide2(int dividend, int divisor) {
        return -1;
    }


    public static void main(String[] args) {
        Day04 day04 = new Day04();
        int divide = day04.divide(-2147483648, -1);
        System.out.println(divide);
    }
}

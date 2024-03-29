package wang.study.Month08;

/*********************************************************
 * 文件名称：Day12.java
 * 软件版权: 恒生电子股份有限公司
 * 系统名称：期货保证金（Java 版）
 * 系统版本：5.0
 * 模块名称：wang.study.Month08
 * 功能说明：
 * 开发人员 @author：wanggh31690
 * 开发时间 @date：2022/8/12 16:31
 * 修改记录：程序版本  修改日期  修改人员  修改单号  修改说明
 *********************************************************/
public class Day12 {
    /**
     * 给你两个整数 a 和 b ，不使用 运算符 + 和 - ，计算并返回两整数之和。
     * 异或：也叫半加法运算，两个数异或结果相当于不进位的加法
     * 与 << 1: 两个数与之后，有符号左移一位，结果为两数相加的进位结果
     * @param a
     * @param b
     * @return
     */
    public int getSum(int a, int b) {
        while (a != 0) {
            int tem = a ^ b;
            a = (a & b) << 1;
            b = tem;
        }
        return b;
    }
}

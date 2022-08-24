package wang.study.Month08;

/*********************************************************
 * 文件名称：Day02.java
 * 软件版权: 恒生电子股份有限公司
 * 系统名称：期货保证金（Java 版）
 * 系统版本：5.0
 * 模块名称：wang.study.Month08
 * 功能说明：
 * 开发人员 @author：wanggh31690
 * 开发时间 @date：2022/8/2 17:07
 * 修改记录：程序版本  修改日期  修改人员  修改单号  修改说明
 *********************************************************/
public class Day02 {
    private boolean[][] record;
    private boolean res;
    /**
     * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
     * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
     * 使用递归实现
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        /* 特殊情况处理 */
        if (word == null || word.length() == 0) return true;
        if (board == null || board.length == 0 || board[0].length == 0) return false;
        /* 判断 board 中是否存在 word */
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                findWord(word, 0, j, i, board);
                if (res) return res; // 已经找到一组，则直接结束
            }
        }
        return res;
    }

    private void findWord(String word, int i, int x, int y, char[][] board) {
        /* 递归结束条件 */
        // 成功找到一组
        if (i == word.length()) {
            res = true;
            return;
        }
        // 已经找到能够组成 word 的内容、坐标超出界限、已经被访问过、坐标对应字符不合格
        if (x < 0
                || y < 0
                || x >= board[0].length
                || y >= board.length
                || record[y][x]
                || board[y][x] != word.charAt(i)
        ) return;
        /* 递归体 */
        record[y][x] = true;
        findWord(word, ++i, x - 1, y, board);
        findWord(word, i, x + 1, y, board);
        findWord(word, i, x, y - 1, board);
        findWord(word, i, x , y + 1, board);
        record[y][x] = false;
    }

    /**
     * 给定一个整数 n ，返回 n! 结果中尾随零的数量。
     * 提示 n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1
     * 数学题：本质上就是找到 n n-1 n-2 ... 1 所有数字中，2 和 5 的匹配个数（描述不准确，详细将代码）
     * @param n
     * @return
     */
    public int trailingZeroes(int n) {
        int twoNum = 0;
        int fiveNum = 0;
        for (int i = 2; i <= n; i++) {
            int tem = i;
            /* 找到组成 i 中包含多少个 2 */
            while (tem % 2 == 0) {
                twoNum++;
                tem /= 2;
            }
            /* 找到组成 i 中包含多少个 5 */
            while (tem % 5 == 0) {
                fiveNum++;
                tem /= 5;
            }
        }
        return twoNum > fiveNum ? fiveNum : twoNum;
    }

    public int trailingZeroes2(int n) {
        int res = 0;
        while (n != 0) {
            n /= 5;
            res += n;
        }
        return res;
    }


    public static void main(String[] args) {
        Day02 day02 = new Day02();
        char[][] nums = new char[][]{{'A','B','C','E'}, {'S','F','C','S'}, {'A','D','E','E'}};
        boolean abcced = day02.exist(nums, "ABCCED");
    }

}

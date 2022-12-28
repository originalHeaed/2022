package wang.study.Month12;

public class Day28 {
    // ========================= 压缩字符串 ================================
    /**
     * 给你一个字符数组 chars ，请使用下述算法压缩：
     * 从一个空字符串 s 开始。对于 chars 中的每组 连续重复字符 ：
     * 如果这一组长度为 1 ，则将字符追加到 s 中。
     * 否则，需要向 s 追加字符，后跟这一组的长度。
     * 压缩后得到的字符串 s 不应该直接返回 ，需要转储到字符数组 chars 中。需要注意的是，如果组长度为 10 或 10 以上，则在 chars 数组中会被拆分为多个字符。
     * 请在 修改完输入数组后 ，返回该数组的新长度。
     * 你必须设计并实现一个只使用常量额外空间的算法来解决此问题。
     */
    /**
     * 使用三指针 + 原地修改，时间复杂度：O（n）
     * @param chars
     * @return
     */
    public int compress(char[] chars) {
        /* 特殊情况判断 */
        if (chars == null || chars.length == 0) return 0;
        if (chars.length == 1) return chars.length;
        /* 使用双指针 */
        int index = 0; // 下一个字符存放的下标
        int pre = 0; // 指向该组相同字符的第一个字符
        int next = 0; // 用于计算该组相同字符的个数
        while (next <= chars.length) {
            /* 该组字符已经遍历完 */
            if (next == chars.length || chars[next] != chars[pre]) {
                int count = next - pre;
                chars[index++] = chars[pre];
                if (count > 1) {
                    String time = String.valueOf(count);
                    for (int i = 0; i < time.length(); i++) {
                        chars[index++] = time.charAt(i);
                    }
                }
                pre = next;
            }
            next++;
        }
        return index;
    }


    //=================================== 解压缩编码列表 =========================================

    /**
     * 给你一个以行程长度编码压缩的整数列表 nums 。
     * 考虑每对相邻的两个元素 [freq, val] = [nums[2*i], nums[2*i+1]] （其中 i >= 0 ），每一对都表示解压后子列表中有 freq 个值为 val 的元素，你需要从左到右连接所有子列表以生成解压后的列表。
     * 请你返回解压后的列表。
     * */
    /**
     * 额，没啥好说的，时间复杂度：O（n）n为解码后的数组长途
     * @param nums
     * @return
     */
    public int[] decompressRLElist(int[] nums) {
        /* 特殊情况判断 */
        if (nums == null || nums.length == 0) return new int[0];
        if (nums.length == 1) return nums;
        /* 获取最终数组长度 */
        int len = 0;
        for (int i = 0; i < (nums.length / 2); i++) {
            len += nums[i * 2];
        }
        int[] res = new int[len];
        /* 开始解码 */
        int index = 0;
        for (int i = 0; i < (nums.length / 2); i++) {
            int time = nums[i * 2];
            int val =  nums[i * 2 + 1];
            while (time > 0) {
                res[index++] = val;
                time--;
            }
        }
        return res;
    }


    //=============================================== RLE 迭代器 =========================================
    /**
     * 我们可以使用游程编码(即  RLE  )来编码一个整数序列。在偶数长度  encoding  ( 从 0 开始 )的游程编码数组中，
     * 对于所有偶数 i ，encoding[i]  告诉我们非负整数  encoding[i + 1]  在序列中重复的次数。
     * 例如，序列  arr = [8,8,8,5,5]  可以被编码为 encoding =[3,8,2,5] 。encoding =[3,8,0,9,2,5]
     * 和 encoding =[2,8,1,8,2,5] 也是  arr 有效的 RLE 。
     * 给定一个游程长度的编码数组，设计一个迭代器来遍历它。
     *
     * 实现 RLEIterator 类:
     * （1）RLEIterator(int[] encoded)  用编码后的数组初始化对象。
     * （2）int next(int n) 以这种方式耗尽后 n 个元素并返回最后一个耗尽的元素。如果没有剩余的元素要耗尽，则返回 -1 。
     */
    /**
     * 理解题意之后很简单，整体思路和该题上面的一题类似
     * （1）把压缩后数字解压出来，然后进行 next，这种是最简单的，但是需要先解压，时间复杂度总体会略高（大部分情况下），总体时间复杂度为：O（m）m为解压后的数组长度
     * （2）直接在压缩数字上进行 next，这种要复杂些，但是总体时间复杂度为：o（n），n为压缩数组长度
     */
    /**
     * 压缩后的数组
     */
    private int[] encoding;

    /**
     * 压缩数组中每个元素出现的次数
     */
    private int[] timeArr;

    /**
     * 下一次消费 timeArr 的下标
     */
    private int nextIndex;

    public void RLEIterator(int[] encoding) {
        this.encoding = encoding;
        /* 记录每个元素出现的次数 */
        timeArr = new int[encoding.length / 2];
        for (int i = 0; i < timeArr.length; i++) {
            timeArr[i] = this.encoding[i * 2];
        }
        nextIndex = 0;
    }

    public int next(int n) {
        int lastVal = -1;
        if (n <= 0) return lastVal;
        while (nextIndex < timeArr.length && n > 0) {
            if (timeArr[nextIndex] > 0) {
                int tem = timeArr[nextIndex];
                timeArr[nextIndex] = timeArr[nextIndex] - n;
                n = n - tem;
                lastVal = encoding[nextIndex * 2 + 1]; /* 更新最后一次取的值 */
            }
            if (timeArr[nextIndex] <= 0) nextIndex++;
        }
        return n <= 0 ? lastVal : -1;
    }

    /**
     * 解码后的数组
     */
    private int[] decode;

    /**
     * 下一个可以被 next 的下标
     */
    private int index;

    public void RLEIterator2(int[] encoding) {
        this.decode = decompressRLElist(encoding);
        this.index = 0;
    }

    public int next2(int n) {
        if (n <= 0 || (n + index) > decode.length) return -1;
        index = index + n;
        return decode[index - 1];
    }


}

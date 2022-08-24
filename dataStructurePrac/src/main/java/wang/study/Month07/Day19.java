package wang.study.Month07;

/**
 * Author:   wang
 * Date:     2022/7/18 21:11
 * function:
 */
public class Day19 {

    private int[] heap;
    private int useEle;

    public int lastStoneWeight(int[] stones) {
        heap = new int[stones.length + 1];
        heap[0] = 1001; // 哨兵
        useEle = 0;
        /* 构建一个大顶堆 */
        for (int i = 0; i < stones.length; i++) {
            insertEle(stones[i]);
        }
        /* 取数据计算 */
        while (useEle > 2) {
            int first = getAndDelEle();
            int second = getAndDelEle();
            int diff = Math.abs(first - second);
            if (first != second) {
                insertEle(diff);
            }
        }
        return Math.abs(getAndDelEle() - getAndDelEle());
    }

    /**
     * 将元素插入大顶堆中
     * @param val
     */
    private void insertEle(int val) {
        ++useEle;
        int index = useEle;
        while (index > 0 && heap[index/2] < val) {
            heap[index] = heap[index/2];
            index /= 2;
        }
        heap[index] = val;
    }

    /**
     * 获取并删除堆顶元素
     * @return
     */
    private int getAndDelEle() {
        if (useEle <= 0) return 0;
        int index = 1;
        int res = heap[index];
        /* 调整堆结构 */
        heap[index] = heap[useEle];
        --useEle;
        while ((2 * index) <= useEle) {
            int maxIndex = 2 * index;
            if ((maxIndex + 1) <= useEle && heap[maxIndex + 1] > heap[maxIndex])
                maxIndex += 1;
            if (heap[index] >= heap[maxIndex]) break; // 调整结束
            int tem = heap[index];
            heap[index] = heap[maxIndex];
            heap[maxIndex] = tem;
            index = maxIndex;
        }
        return res;
    }

    public static void main(String[] args) {
        Day19 day19 = new Day19();
        int i = day19.lastStoneWeight(new int[]{2, 7, 4, 1, 8, 1});
        System.out.println(i);
    }
}

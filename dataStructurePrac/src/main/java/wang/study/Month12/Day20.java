package wang.study.Month12;

import java.util.*;

public class Day20 {
    //============================================= 最小的 k 个数 ============================================
    /**
     * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
     */
    /**
     * 使用自定义堆
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        int[] res = new int[k];
        if (arr == null || arr.length == 0 || k <= 0) return new int[0];
        /* 使用堆进行获取 top k */
        Heap heap = new Heap(k, true);
        for (int i = 0; i < arr.length; i++) {
            heap.insert(arr[i]);
        }
        for (int i = k - 1; i >= 0; i--) {
            res[i] = heap.poll();
        }
        return res;
    }

    /**
     * 使用系统内部的优先级队列
     */
    public int[] getLeastNumbers2(int[] arr, int k) {
        int[] res = new int[k];
        if (arr == null || arr.length == 0 || k <= 0) return new int[0];
        /* 使用堆进行获取 top k */
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(); // 默认小顶堆
        for (int i = 0; i < arr.length; i++) {
            priorityQueue.add(arr[i]);
        }
        for (int i = 0; i < k; i++) {
            res[i] = priorityQueue.poll();
        }
        return res;
    }

    //============================================= 数组中的第 K 个最小元素 ===================================

    /**
     * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
     * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
     * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
     */
    /**
     * 使用堆实现
     */
    public int findKthLargest(int[] nums, int k) {
        /* 特殊情况处理 */
        if (nums == null || nums.length == 0 || k <= 0) return -10001;
        /* 获取第 K 大的数字 */
        Heap heap = new Heap(k, false); /* 需要维护一份最小堆 */
        for (int i = 0; i < nums.length; i++) {
            heap.insert(nums[i]);
        }
        return heap.poll();
    }

    //===================================== 数据流中的第 K 大元素 =======================================

    /**
     * 设计一个找到数据流中第 k 大元素的类（class）。注意是排序后的第 k 大元素，不是第 k 个不同的元素。
     * 请实现 KthLargest类：
     * KthLargest(int k, int[] nums) 使用整数 k 和整数流 nums 初始化对象。
     * int add(int val) 将 val 插入数据流 nums 后，返回当前数据流中第 k 大的元素。
     *
     */
    class KthLargest {
        private Heap heap;
        /**
         * 构造函数
         */
        public KthLargest(int k, int[] nums) {
            heap = new Heap(k, false);
            for (int i = 0; i < nums.length; i++) {
                heap.insert(nums[i]);
            }
        }

        public int add(int val) {
            heap.insert(val);
            return heap.peek();
        }
    }

    //==================================== 前 k 个高频元素 ============================================

    /**
     * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
     */
    public int[] topKFrequent(int[] nums, int k) {
        /* 特殊情况判断 */
        if (nums == null || nums.length == 0 || k <= 0) return new int[0];
        /* 获取每个元素出现的次数 */
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        /* 构建大小为 k 的小顶堆 */
        CommonHeap<Node> heap = new CommonHeap<>(k, (o1, o2) -> o1.time - o2.time);
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Node node = new Node(entry.getKey(), entry.getValue());
            heap.insert(node);
        }
        /* 获取结果 */
        int[] res = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            res[i] = heap.poll().val;
        }
        return res;
    }

    class Node {
        private int val;
        private int time;
        public Node(int val, int time) {
            this.val = val;
            this.time = time;
        }
    }

    /**
     * 非常规的堆，是一个大小固定的堆，只适用于用于获取 top k
     */
    class Heap {
        /**
         * 存放元素
         */
        private int[] heap;

        /**
         * 是否为大顶堆
         */
        private boolean isBig;

        private int UN_KNOWN = Integer.MIN_VALUE;


        /**
         * 构造指定大小的堆
         */
        public Heap(int size, boolean isBig) {
            heap = new int[size + 1];
            heap[0] = 1; // 指向下一个可以存放数据的坐标
            this.isBig = isBig;
        }

        /**
         * 获取堆顶元素
         * 堆内无元素，则返回 UN_KNOWN
         */
        public int peek() {
            if (heap[0] > 1) return heap[1];
            return UN_KNOWN;
        }

        /**
         * 取出堆顶元素
         * 堆内无元素，返回 UN_KNOWN
         */
        public int poll() {
            if (heap[0] < 2) return UN_KNOWN;
            int res = heap[1];
            heap[1] = heap[--heap[0]];
            reBalanceFromTop(); /* 重新平衡堆 */
            return res;
        }

        /**
         * 向堆中插入 val 值
         */
        public void insert(int val) {
            /* 堆已经满了，且 val 符合要求 */
            if (heap[0] == heap.length) {
                if (!compare(heap[1], val)) {
                    heap[1] = val;
                    reBalanceFromTop();
                }
            } else {
                heap[heap[0]++] = val;
                reBalanceFromLast();
            }
        }

        /**
         * 从堆顶自上而下从小重新平衡堆
         */
        private void reBalanceFromTop() {
            int i = 1;
            int leftIndex = i*2;
            int rightIndex = i*2 + 1;
            while (leftIndex < heap[0]) {
                /* 左右节点均存在，且右节点符合要求 */
                if (rightIndex < heap[0] && compare(heap[leftIndex], heap[rightIndex])) {
                    if (compare(heap[i], heap[rightIndex])) {
                        int tem = heap[i];
                        heap[i] = heap[rightIndex];
                        heap[rightIndex] = tem;
                        i = rightIndex;
                    } else {
                        break; // 后续不需要继续 rebalance
                    }
                } else {
                    /* 只存在左节点 */
                    if (compare(heap[i], heap[leftIndex])) {
                        int tem = heap[i];
                        heap[i] = heap[leftIndex];
                        heap[leftIndex] = tem;
                        i = leftIndex;
                    } else {
                        break;  // 后续不需要继续 rebalance
                    }
                }
                leftIndex = i * 2;
                rightIndex = i * 2 + 1;
            }
        }

        /**
         * 从最后一个元素中自下而上，重新平衡堆
         */
        private void reBalanceFromLast() {
            int i = heap[0] - 1;
            while (i > 1) {
                if (compare(heap[i / 2], heap[i])) {
                    int tem = heap[i / 2];
                    heap[i / 2] = heap[i];
                    heap[i] = tem;
                    i /= 2;
                } else {
                    break; // 自动平衡结束
                }
            }
        }

        /**
         * 比较函数（实现大小顶堆的关键）
         * @return
         */
        private boolean compare(int node, int newVal) {
            if (!isBig) {
                return (node - newVal) > 0;
            } else {
                return (node - newVal) < 0;
            }
        }
    }

    /**
     * 非常规的堆，是一个大小固定的堆，只适用于用于获取 top k
     */
    class CommonHeap<T> {
        /**
         * 存放元素
         */
        private Object[] heap;

        private Comparator<? super T> comparator;

        /**
         * 是否为大顶堆
         */
        private boolean isBig;

        private int nextIndex;

        /**
         * 构建堆
         * @param size
         * @param comparator ele1 > ele2 返回正值则构建小顶堆，ele1 < ele2 返回正值则构建大顶堆
         */
        public CommonHeap(int size, Comparator<? super T> comparator) {
            heap = new Object[size + 1];
            nextIndex = 1;
            this.comparator = comparator;
        }

        /**
         * 获取堆顶元素
         * 堆内无元素，则返回 null
         */
        public T peek() {
            if (nextIndex > 1) return (T)heap[1];
            return null;
        }

        /**
         * 取出堆顶元素
         * 堆内无元素，返回 UN_KNOWN
         */
        public T poll() {
            if (nextIndex < 2) return null;
            Object res = heap[1];
            heap[1] = heap[--nextIndex];
            reBalanceFromTop(); /* 重新平衡堆 */
            return (T) res;
        }

        /**
         * 向堆中插入 val 值
         */
        public void insert(T val) {
            /* 堆已经满了，且 val 符合要求 */
            if (nextIndex == heap.length) {
                if (!compare((T)heap[1], val)) {
                    heap[1] = val;
                    reBalanceFromTop();
                }
            } else {
                heap[nextIndex++] = val;
                reBalanceFromLast();
            }
        }

        /**
         * 从堆顶自上而下从小重新平衡堆
         */
        private void reBalanceFromTop() {
            int i = 1;
            int leftIndex = i*2;
            int rightIndex = i*2 + 1;
            while (leftIndex < nextIndex) {
                /* 左右节点均存在，且右节点符合要求 */
                if (rightIndex < nextIndex && compare((T)heap[leftIndex], (T)heap[rightIndex])) {
                    if (compare((T)heap[i], (T)heap[rightIndex])) {
                        Object tem = heap[i];
                        heap[i] = heap[rightIndex];
                        heap[rightIndex] = tem;
                        i = rightIndex;
                    } else {
                        break; // 后续不需要继续 rebalance
                    }
                } else {
                    /* 只存在左节点 */
                    if (compare((T)heap[i], (T)heap[leftIndex])) {
                        Object tem = heap[i];
                        heap[i] = heap[leftIndex];
                        heap[leftIndex] = tem;
                        i = leftIndex;
                    } else {
                        break;  // 后续不需要继续 rebalance
                    }
                }
                leftIndex = i * 2;
                rightIndex = i * 2 + 1;
            }
        }

        /**
         * 从最后一个元素中自下而上，重新平衡堆
         */
        private void reBalanceFromLast() {
            int i = nextIndex - 1;
            while (i > 1) {
                if (compare((T)heap[i / 2], (T)heap[i])) {
                    Object tem = heap[i / 2];
                    heap[i / 2] = heap[i];
                    heap[i] = tem;
                    i /= 2;
                } else {
                    break; // 自动平衡结束
                }
            }
        }

        /**
         * 比较函数
         * @return
         */
        private boolean compare(T oldVal, T newVal) {
            return comparator.compare(oldVal, newVal) >= 0;
        }
    }
}

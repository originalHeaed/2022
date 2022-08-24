package wang.study.Month07;

/*********************************************************
 * 文件名称：SkipList.java
 * 软件版权: 恒生电子股份有限公司
 * 系统名称：期货保证金（Java 版）
 * 系统版本：5.0
 * 模块名称：wang.study.Month07
 * 功能说明：
 * 开发人员 @author：wanggh31690
 * 开发时间 @date：2022/7/26 13:50
 * 修改记录：程序版本  修改日期  修改人员  修改单号  修改说明
 *********************************************************/
public class Skiplist {

    /**
     * 指向每一层的头节点
     */
    Node root;

    /**
     * 最高层数
     */
    int topLevel;

    /**
     * 最顶层节点的最大数
     */
    int topLevelNodeNum;

    /**
     * 不使用任何库函数，设计一个 跳表 。
     * 跳表 是在 O(log(n)) 时间内完成增加、删除、搜索操作的数据结构。
     * 跳表相比于堆与红黑树，其功能与性能相当，并且跳表的代码长度相较下更短，其设计思想与链表相似。
     */
    public Skiplist() {
        root = new Node(new Node[50000], 0); // 头指正，指向每一层的第一个节点，val 代表最顶层的节点数
        topLevel = 0;
        topLevelNodeNum = 3;
    }

    /**
     * 再跳表中搜索指定的 target 是否存在
     */
    public boolean search(int target) {
        int level = topLevel;
        Node node = root;
        while (level >= 0) {
            while (node.next[level] != null && node.next[level].val < target)
                node = node.next[level];
            if (node.next[level] != null && node.next[level].val == target) return true;
            --level;
        }
        return false;
    }

    /**
     * 跳表中插入一个 num
     */
    public void add(int num) {
        boolean isSkip = this.isSkip(); // 是否在当前所有层级中增加 num 这一节点
        Node tem = new Node(new Node[topLevel + 2], num);
        int lowest = topLevel;
        /* 在跳表中寻找插入 num 的合适位置 */
        Node node = root;
        while (lowest >= 0) {
            /* 找到当前层级中最后一个小于 num 的节点 */
            while (node.next[lowest] != null && node.next[lowest].val < num)
                node = node.next[lowest];
            if (isSkip || lowest == 0) {
                tem.next[lowest] = node.next[lowest];
                node.next[lowest] = tem;
            }
            --lowest;
        }
        /* 判断是否需要再上一层 */
        if (isSkip || topLevel == 0) root.val++;
        if (root.val >= topLevelNodeNum && isSkip) {
            ++topLevel;
            root.next[topLevel] = tem;
            root.val = 1;
        }
    }

    /**
     * 在跳表中删除 num，如果 num 不存在，直接返回false. 如果存在多个 num ，删除其中任意一个即可。
     */
    public boolean erase(int num) {
        /* 数据初始化 */
        int level = topLevel;
        Node node = root;
        Node target = null;
        boolean isExist = false;
        /* 从顶层到底层移除某个指定的节点 */
        while (level >= 0) {
            while (node.next[level] != null && node.next[level].val <= num) {
                /* 找到值等于 num 节点，target == null：第一次找到目标节点，targe == node.next[level] 表示后续找到需要删除的节点 */
                if (node.next[level].val == num && (target == null || target == node.next[level])) {
                    target = node.next[level];
                    isExist = true;
                    break;
                }
                node = node.next[level];
            }
            /* 当前层级中存在 num，则将该节点断开 */
            if (node.next[level] != null && node.next[level] == target) {
                node.next[level] = node.next[level].next[level];
                if (node == root && node.next[level] == null) --topLevel;
            }
            --level;
        }
        /* 重新统计顶层节点数 */
        Node topNode = root.next[topLevel];
        int count = 0;
        while (topNode != null) {
            count++;
            topNode = topNode.next[topLevel];
        }
        root.val = count;
        return isExist;
    }

    /**
     * 判断是否将 num 进行跳跃
     */
    private boolean isSkip() {
        return Math.random() < 0.5;
    }

    public class Node {
        public Node[] next; /* 当该节点处于第 i 层时，下一个节点的地址 */
        public int val; // 该节点的值
        public Node(Node[] levelNext, int val) {
            this.next = levelNext;
            this.val = val;
        }
        public Node() {
        }
    }

    public static void main(String[] args) {
        Skiplist skiplist = new Skiplist();
        skiplist.add(0);
        skiplist.add(5);
        skiplist.add(2);
        skiplist.add(1);
        skiplist.add(6);
        skiplist.add(7);
        Node root = skiplist.root;
        for (int i = 0; i <= skiplist.topLevel; i++) {
            Node head = root.next[i];
            while (head != null) {
                System.out.print(head.val + " ");
                head = head.next[i];
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
        // skiplist.search(0);
        skiplist.erase(6);
        // skiplist.search(2);
        // skiplist.search(3);
        // skiplist.search(2);
        root = skiplist.root;
        for (int i = 0; i <= skiplist.topLevel; i++) {
            Node head = root.next[i];
            while (head != null) {
                System.out.print(head.val + " ");
                head = head.next[i];
            }
            System.out.println();
        }
    }
}

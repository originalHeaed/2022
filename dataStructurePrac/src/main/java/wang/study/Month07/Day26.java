package wang.study.Month07;

/*********************************************************
 * 文件名称：Day26.java
 * 软件版权: 恒生电子股份有限公司
 * 系统名称：期货保证金（Java 版）
 * 系统版本：5.0
 * 模块名称：wang.study.Month07
 * 功能说明：
 * 开发人员 @author：wanggh31690
 * 开发时间 @date：2022/7/26 10:26
 * 修改记录：程序版本  修改日期  修改人员  修改单号  修改说明
 *********************************************************/
public class Day26 {

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
     * 跳表相比于树堆与红黑树，其功能与性能相当，并且跳表的代码长度相较下更短，其设计思想与链表相似。
     */
    public void Skiplist() {
        root = new Node(new Node[50000], 0); // 头指正，指向每一层的第一个节点，val 代表最顶层的节点数
        topLevel = 0;
        topLevelNodeNum = 3;
    }

    /**
     * 再跳表中搜索指定的 target 是否存在
     */
    public boolean search(int target) {
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
            if (node.next[lowest] == null || node.next[lowest].val >= num){
                /* 当前层不存在或当前层最小值大于等于 num */
                node = root;
            } else {
                /* 找到当前层级中最后一个小于 num 的节点 */
                while (node.next[lowest] != null && node.next[lowest].val < num)
                    node = node.next[lowest];
            }
            if (isSkip || lowest == 0) {
                tem.next[lowest] = node.next[lowest];
                node.next[lowest] = tem;
            }
            --lowest;
        }
        /* 判断是否需要再上一层 */
        if (isSkip) root.val++;
        if (root.val >= topLevelNodeNum) {
            ++topLevel;
            root.next[topLevel] = tem;
        }
    }

    /**
     * 在跳表中删除 num
     */
    public boolean erase(int num) {
        return false;
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
}

package wang.study.Month12;

public class Day27 {
    //======================== 二叉树中的链表 ==========================
    /**
     * 给你一棵以 root 为根的二叉树和一个 head 为第一个节点的链表。
     * 如果在二叉树中，存在一条一直向下的路径，且每个点的数值恰好一一对应以 head 为首的链表中每个节点的值，那么请你返回 True ，否则返回 False 。
     * 一直向下的路径的意思是：从树中某个节点开始，一直连续向下的路径。
     */
    /**
     * 使用暴力法，事件复杂度：O（n*m）
     * @param head
     * @param root
     * @return
     */
    public boolean isSubPath(ListNode head, TreeNode root) {
        /* 递归结束条件 */
        if (head == null || root == null) return head == null;
        /* 递归体 */
        return isExist(root, head) || isSubPath(head, root.left) || isSubPath(head, root.right);
    }

    private boolean isExist(TreeNode treeNode, ListNode node) {
        /* 递归结束 */
        if (treeNode == null || node == null) return node == null;
        return treeNode.val == node.val && (isExist(treeNode.left, node.next) || isExist(treeNode.right, node.next));
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

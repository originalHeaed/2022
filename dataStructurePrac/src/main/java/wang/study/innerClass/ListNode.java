package wang.study.innerClass;

/**
 * Author:   wang
 * Date:     2022/7/6 22:30
 * function:
 */
public class ListNode {
    public int val;
    public ListNode next;
    public ListNode() {}

    public ListNode(int val) {
        this.val = val;
    }
    public ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

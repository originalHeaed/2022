package wang.study.Month07;

import org.omg.CosNaming._NamingContextExtStub;
import wang.study.innerClass.ListNode;

/**
 * Author:   wang
 * Date:     2022/7/6 22:29
 * function:
 */
public class Day06 {

    /**
     * 使用双指针
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        /* 特殊情况判断 */
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        /* 指向当前需要合并的节点 */
        ListNode head = list1.val < list2.val ? list1 : list2;
        ListNode tem = new ListNode();
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                tem.next = list1;
                list1 = list1.next;
            } else {
                tem.next = list2;
                list2 = list2.next;
            }
            tem = tem.next;
        }
        if (list1 == null) tem.next = list2;
        else tem.next = list1;
        return head;
    }

    /**
     * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        /* 特殊情况判断 */
        if (head == null) return head;
        if (head.next == null) return head;
        /* 进行翻转 */
        ListNode base = head;
        ListNode first = head;
        ListNode tem = null;
        while (base.next != null) {
            tem = base.next.next;
            base.next.next = first;
            first = base.next;
            base.next = tem;

        }
        return first;
    }
}

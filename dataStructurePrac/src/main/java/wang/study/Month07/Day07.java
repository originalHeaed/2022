package wang.study.Month07;

import wang.study.innerClass.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * Author:   wang
 * Date:     2022/7/7 22:47
 * function:
 */
public class Day07 {

    /**
     * 给定一个头结点为 head 的非空单链表，返回链表的中间结点。
     * 如果有两个中间结点，则返回第二个中间结点。
     * @param head
     * @return
     */
    public ListNode middleNode(ListNode head) {
        /* 特殊情况 */
        if (head == null || head.next == null) return head;
        /* 使用双指针 */
        ListNode first = head;
        ListNode second = head.next;
        while (second != null && second.next != null) {
            first = first.next;
            second = second.next.next;
        }
        return second == null ? first : first.next;
    }


    /**
     * 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
     * 暴力法，使用 Set 记录每一个 ListNode 的地址引用，如果出现重复的则表示为环
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;
        Set<ListNode> set = new HashSet<ListNode>();
        while (head != null) {
            if (set.contains(head)) return head;
            else set.add(head);
            head = head.next;
        }
        return null;
    }

    /**
     * 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
     * 使用双指针
     * @param head
     * @return
     */
    public ListNode detectCycle2(ListNode head) {
        if (head == null || head.next == null) return null;
        Set<ListNode> set = new HashSet<ListNode>();
        while (head != null) {
            if (set.contains(head)) return head;
            else set.add(head);
            head = head.next;
        }
        return null;
    }
}

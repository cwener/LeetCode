package com.example.javatest.listnode;

import com.example.javatest.ListNode;


/**
 * Created by chengwen on 2021/3/7.
 * 不要跳进递归，而是利用明确的定义来实现算法逻辑
 */
class ReverseKList {

    /**
     * 递归反转整个链表
     *
     * @param head
     * @return
     */
    public static ListNode reverseAll(ListNode head) {
        if (head.next == null) return head;
        ListNode last = reverseAll(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

    ListNode successor = null;

    /**
     * 翻转链表前n个节点
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            // 记住第n个节点与后面需要翻转的连接点，前n个反转之后最后一个节点需要连接这个节点
            successor = head.next;
            return head;
        }
        // 递归思想，以next为起点继续往后反转n-1个节点
        ListNode last = reverseN(head.next, n - 1);
        // 反转当前节点的next
        head.next.next = head;
        // 每个节点反转之后都是当前已反转的最后一个，所以它的前面有连接不需要担心，但是它的后面需要与不反转的节点连接
        head.next = successor;
        return last;
    }

    /**
     * 反转链表的一部分
     * @param head
     * @param m
     * @param n
     * @return
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (m == 1) {
            // 假设m=1，则是反转前n个节点
            return reverseN(head, n);
        }
        // 从头节点往后移，一直移到m节点，将该节点视作头节点，则成了反转前 n-m+1 个节点
        return reverseBetween(head.next, m - 1, n - 1);
    }

    /**
     * 非递归反转
     * @param head
     * @return
     */
    public ListNode reverse(ListNode head) {
        if (head == null) {
            return null;
        }
        // 恰好在头节点反转时，头节点next指向pre,即null
        ListNode pre = null;
        ListNode next, cur = head;
        while (cur != null) {
            // 记住cur节点的next
            next = cur.next;
            // 拆开cur并反转
            cur.next = pre;
            // 向下一节点前进
            pre = cur;
            cur = next;
        }
        return pre;
    }

    /**
     * 非递归反转a到b之间的节点
     * @param a
     * @param b
     * @return
     */
    public ListNode reverseA2B(ListNode a, ListNode b) {
        ListNode pre = null;
        ListNode cur = a, next = a;
        while (cur != b) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    /**
     * k个一组递归反转单链表
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseGroupK(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode a = head, b = head;
        for (int i = 0; i < k; i++) {
            if (b == null) {
                // 不满足k个则直接返回
                return a;
            }
            b = b.next;
        }
        // 反转a b段
        ListNode newHead = reverseA2B(a, b);
        // 递归向后反转
        a.next = reverseGroupK(b, k);
        return newHead;
    }
}

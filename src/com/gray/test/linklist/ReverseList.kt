package com.gray.test.linklist

/**
 * 反转链表
 *
 * 反转一个单链表。
 * 示例:
 *      输入: 1->2->3->4->5->NULL
 *      输出: 5->4->3->2->1->NULL
 *
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 *
 * */

fun reverseList(head: ListNode?): ListNode? {
    var pre: ListNode? = null
    var cur = head
    var next: ListNode?
    while (cur != null) {
        next = cur.next
        cur.next = pre
        pre = cur
        cur = next
    }
    return pre
}

fun main() {
    var head: ListNode? = createListNode(intArrayOf(1, 2, 3, 4, 5))
    head = reverseList(head)
    head.print()

    head = createListNode(intArrayOf(1))
    head = reverseList(head)
    head.print()
}
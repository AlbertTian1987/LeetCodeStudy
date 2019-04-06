package com.gray.test.linklist

/**
 * 合并两个有序链表
 *
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 * 示例：
 *      输入：1->2->4, 1->3->4
 *      输出：1->1->2->3->4->4
 *
 * */

fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {
    var node1: ListNode? = l1
    var node2: ListNode? = l2

    if (node1 == null && node2 == null) {
        return null
    }
    val foo = ListNode(0)
    var node: ListNode? = foo
    while (node1 != null || node2 != null) {
        when {
            node1 == null -> {
                node!!.next = node2
                node = node2
                node2 = null
            }
            node2 == null -> {
                node!!.next = node1
                node = node1
                node1 = null
            }
            node1.value <= node2.value -> {
                node!!.next = node1
                node = node1
                node1 = node1.next
            }
            else -> {
                node!!.next = node2
                node = node2
                node2 = node2.next
            }
        }
    }
    return foo.next
}

fun main() {
    mergeTwoLists(createListNode(intArrayOf(1, 2, 4)), createListNode(intArrayOf(1, 3, 4))).print()
    mergeTwoLists(null, null).print()
    mergeTwoLists(createListNode(intArrayOf(1, 2, 4)), null).print()
    mergeTwoLists(null, createListNode(intArrayOf(1, 3, 4))).print()
}
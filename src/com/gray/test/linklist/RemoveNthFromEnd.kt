package com.gray.test.linklist

/**
 * 删除链表的倒数第N个节点
 *
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 * 示例：
 *      给定一个链表: 1->2->3->4->5, 和 n = 2.
 *      当删除了倒数第二个节点后，链表变为 1->2->3->5.
 *
 * 说明：
 *      给定的 n 保证是有效的。
 *
 * 进阶：
 *     你能尝试使用一趟扫描实现吗
 * */
fun removeNthFromEnd(head: ListNode, n: Int): ListNode {
    var node: ListNode? = head
    var count = 1
    while (true) {
        if (node!!.next != null) {
            node = node.next!!
            count++
        } else {
            break
        }
    }
    var fromStartN = count - n
    if (fromStartN == 0) {
        return head.next!!
    }

    node = head
    var preNode = head
    while (fromStartN > 0) {
        preNode = node!!
        node = node.next
        fromStartN--
    }

    if (node == null) {
        preNode.next = null
    } else {
        preNode.next = node.next
    }

    return head
}

fun main() {
    var head = createListNode(intArrayOf(1, 2, 3, 4, 5))
    head = removeNthFromEnd(head, 2)
    head.print()
    head = createListNode(intArrayOf(1, 2, 3, 4, 5))
    head = removeNthFromEnd(head, 5)
    head.print()
    head = createListNode(intArrayOf(1, 2, 3, 4, 5))
    head = removeNthFromEnd(head, 1)
    head.print()
}
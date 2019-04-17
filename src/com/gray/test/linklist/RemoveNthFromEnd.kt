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
fun removeNthFromEnd(head: ListNode, n: Int): ListNode? {
    var count = 1
    var node = head.next
    while (node != null) {
        count++
        node = node.next
    }
    count -= n
    if (count == 0) {
        return head.next
    }
    var pre = head
    node = head.next
    while (true) {
        count--
        if (count == 0) {
            if (node == null) {
                pre.next = null
            } else {
                pre.next = node.next
            }
            break
        }
        pre = node!!
        node = node.next
    }
    return head
}

/**
 *  num比n要少计算一次，所以当node移动到底时，delNode刚好移动到要删除的元素之前一个，
 *  如果delNode=null，说明要删除的是head头元素
 */
fun removeNthFromEnd2(head: ListNode, n: Int): ListNode? {
    var num = 0
    var delNode: ListNode? = null
    var node = head
    while (node.next != null) {
        node = node.next!!
        num++
        if (delNode != null) {
            delNode = delNode.next
        } else {
            if (num == n) {
                delNode = head
            }
        }
    }
    if (delNode == null) {
        return head.next
    }
    delNode.next = delNode.next?.next
    return head
}

fun main() {
    var head: ListNode = createListNode(intArrayOf(1, 2, 3, 4, 5))
    removeNthFromEnd2(head, 2).print()
    head = createListNode(intArrayOf(1, 2, 3, 4, 5))
    removeNthFromEnd2(head, 5).print()
    head = createListNode(intArrayOf(1, 2, 3, 4, 5))
    removeNthFromEnd2(head, 1).print()
    head = createListNode(intArrayOf(1))
    removeNthFromEnd2(head, 1).print()
}
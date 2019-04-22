package com.gray.test.linklist

/**
 * (中级)
 * 两数相加
 *
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *      输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 *      输出：7 -> 0 -> 8
 *      原因：342 + 465 = 807
 */

fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode {
    var l = l1
    var r = l2
    val foo = ListNode(0)
    var node = foo
    var delta = false
    while (true) {
        var ret = 0
        if (l != null) {
            ret = l.value
            l = l.next
        }
        if (r != null) {
            ret += r.value
            r = r.next
        }
        if (delta) {
            delta = false
            ret += 1
        }
        if (ret >= 10) {
            delta = true
            ret -= 10
        }
        node.next = ListNode(ret)
        node = node.next!!
        if (!delta && l == null && r == null) {
            break
        }
    }
    return foo.next!!
}

fun main() {
    addTwoNumbers(createListNode(intArrayOf(2, 4, 3)), createListNode(intArrayOf(5, 6, 4))).print()
    addTwoNumbers(createListNode(intArrayOf(2, 4, 3)), createListNode(intArrayOf(0))).print()
    addTwoNumbers(createListNode(intArrayOf(9, 9, 9)), createListNode(intArrayOf(1))).print()
}
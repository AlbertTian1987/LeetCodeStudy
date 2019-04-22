package com.gray.test.linklist

/***
 * (中级)
 * 编写一个程序，找到两个单链表相交的起始节点。
 *
 * 示例 1：
 * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
 * 输出：Reference of the node with value = 8
 * 输入解释：相交节点的值为 8 （注意，如果两个列表相交则不能为 0）。
 * 从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。
 * 在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
 *
 * 示例 2：
 * 输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
 * 输出：Reference of the node with value = 2
 * 输入解释：相交节点的值为 2 （注意，如果两个列表相交则不能为 0）。
 * 从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。
 * 在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
 *
 * 示例 3：
 * 输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
 * 输出：null
 * 输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。
 * 由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
 * 解释：这两个链表不相交，因此返回 null。
 *
 *
 * 注意：
 * 如果两个链表没有交点，返回 null.
 * 在返回结果后，两个链表仍须保持原有的结构。
 * 可假定整个链表结构中没有循环。
 * 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
 */

/**
 * 解题思路
 * 假设两个链表相交，
 * 设L1的 总长 N1，相交前的长度X0,相交后的长度X1
 * 设L2的 总长 N2，相交前的长度Y0,相交后的长度Y1
 * 指针1从L1走完再走L2，指针2从L2走完再走L1，相交的话有等式
 *  N1+Y0 = N2+X0
 * 这个等式是成立的，因为 X0+X1 = N1 , Y0+Y1 = N2,代入即可。
 * 如果没有相交点，那两个指针都会走完N1+N2的距离，最后双双为null
 */
fun getIntersectionNode(headA: ListNode?, headB: ListNode?): ListNode? {
    var a = headA
    var b = headB
    if (a == null || b == null) {
        return null
    }
    while (a != b) {
        a = if (a == null) headB else a.next
        b = if (b == null) headA else b.next
    }
    return a
}

fun main() {
    val linkedNode = createListNode(intArrayOf(8, 4, 5))
    val l1 = ListNode(4, ListNode(1, linkedNode))
    val l2 = ListNode(5, ListNode(0, ListNode(1, linkedNode)))
    getIntersectionNode(l1, l2).print()

    getIntersectionNode(createListNode(intArrayOf(2, 6, 4)), createListNode(intArrayOf(1, 5))).print()
}
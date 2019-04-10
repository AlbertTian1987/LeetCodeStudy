package com.gray.test.linklist

/**
 * 环形链表
 *
 * 给定一个链表，判断链表中是否有环。
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 *
 * 示例 1：
 *      输入：head = [3,2,0,-4], pos = 1
 *      输出：true
 *      解释：链表中有一个环，其尾部连接到第二个节点。
 *
 * 示例 2：
 *      输入：head = [1,2], pos = 0
 *      输出：true
 *      解释：链表中有一个环，其尾部连接到第一个节点。
 *
 * 示例 3：
 *      输入：head = [1], pos = -1
 *      输出：false
 *      解释：链表中没有环。
 *
 * 进阶：
 * 你能用 O(1)（即，常量）内存解决此问题吗？
 *
 */
/**
 * 笨方法
 */
fun hasCycle(head: ListNode?): Boolean {
    if (head?.next == null) {
        return false
    }
    val set = hashSetOf<ListNode>()
    var cur = head
    while (true) {
        set.add(cur!!)
        cur = cur.next
        if (cur == null) {
            return false
        }
        if (set.contains(cur)) {
            return true
        }
    }
}

/**
 * 快指针每次移动2，慢指针每次移动1
 * 如果没环，快指针会触发==null，退出循环
 * 有环的话，快慢两个指针一定会相遇
 */
fun hasCycle2(head: ListNode?): Boolean {
    if (head?.next == null) {
        return false
    }
    var slow = head.next
    var fast = head.next!!.next
    while (true) {
        when {
            fast?.next == null -> return false
            fast == slow -> return true
            else -> {
                fast = fast.next?.next
                slow = slow!!.next
            }
        }
    }
}

fun main() {
    println(hasCycle(createListNode(intArrayOf(1, 2, 3, 4, 5))))
    println(hasCycle2(createListNode(intArrayOf(1, 2, 3, 4, 5))))

    val l1 = ListNode(1)
    val l2 = ListNode(2)
    l1.next = l2
    l2.next = l1
    println(hasCycle(l1))
    println(hasCycle2(l1))
}
package com.gray.test.linklist

/**
 * 203. 移除链表元素
 *
 * 删除链表中等于给定值 val 的所有节点。
 *
 * 示例:
 *  输入: 1->2->6->3->4->5->6, val = 6
 *  输出: 1->2->3->4->5
 *
 * */

fun removeElements(head: ListNode?, `val`: Int): ListNode? {
    var cur = head
    var foo = head
    while (cur != null) {
        if (cur.value == `val`) {
            cur = cur.next
            foo = cur
        } else if (cur.next == null) {
            break
        } else if (cur.next!!.value == `val`) {
            cur.next = cur.next!!.next
        } else {
            cur = cur.next
        }
    }

    return foo
}
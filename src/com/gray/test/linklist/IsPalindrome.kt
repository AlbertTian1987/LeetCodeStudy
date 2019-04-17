package com.gray.test.linklist

/***
 * 回文链表
 *
 * 请判断一个链表是否为回文链表。
 *
 * 示例 1:
 *      输入: 1->2
 *      输出: false
 *
 * 示例 2:
 *      输入: 1->2->2->1
 *      输出: true
 *
 * 进阶：
 *      你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 *
 */


fun isPalindrome(head: ListNode?): Boolean {
    if (head == null) {
        return false
    }
    var slow = head
    var fast = head
    while (fast?.next != null) {
        fast = fast.next?.next
        slow = slow!!.next
    }
    fun reverse(head: ListNode?): ListNode {
        var pre: ListNode? = null
        var cur = head
        while (cur != null) {
            val next = cur!!.next
            cur!!.next = pre
            pre = cur
            cur = next
        }
        return pre!!
    }
    slow = reverse(slow)
    var cur = head
    while (slow != null) {
        if (slow.value == cur!!.value) {
            slow = slow.next
            cur = cur!!.next
        } else {
            return false
        }
    }

    return true
}

/**
 * 这个方法看别人的，它有个问题，12121这种不认为是回文？不太理解为什么leetcode认为它是对的
 */
fun isPalindrome2(head: ListNode?): Boolean {
    if (head?.next == null) {
        return true
    }
    if (head.next!!.next == null) {
        return head.value == head.next!!.value
    }

    var slow = head
    var fast = head.next
    while (fast!!.next != null) {
        if (slow!!.value == fast.next!!.value) {
            if (fast.next!!.next != null) {
                return false
            }
            fast.next = null
            slow = slow.next
            fast = slow!!.next
            if (fast == null || slow.value == fast.value) {
                return true
            }
        } else {
            fast = fast.next
        }
    }
    return false
}

/**
 * 先遍历一遍，得到总个数
 * 接着将前一半的链表倒序。
 * 注意，如果链表是奇数，则需要将倒序后的下一位再往后挪一位。
 * 接下来前半段的倒序和后半段比较，相等则是回文。
 *
 * 这个方法没有Stack的操作耗时，更加快捷
 */
fun isPalindrome3(head: ListNode?): Boolean {
    if (head?.next == null) {
        return true
    }
    if (head.next!!.next == null) {
        return head.value == head.next!!.value
    }

    var size = 0
    var cur = head
    while (cur != null) {
        size++
        cur = cur.next
    }
    val half = size / 2
    cur = head
    var pre: ListNode? = null
    var next: ListNode?
    repeat(half) {
        next = cur!!.next
        cur!!.next = pre
        pre = cur
        cur = next
    }

    if (size % 2 != 0) {
        cur = cur!!.next
    }
    while (pre != null) {
        if (pre!!.value == cur!!.value) {
            pre = pre!!.next
            cur = cur!!.next
        } else {
            return false
        }
    }
    return true
}

/**
 * 这种解法，用两个快慢指针，快指针每次遍历两个，慢的遍历一个，
 * 所以快指针到末尾时，慢指针刚好到一半的位置。
 * 将慢指针之后的列表颠倒，然后和前半部分对比是否相同即可
 */
fun isPalindrome4(head: ListNode?): Boolean {
    if (head?.next == null) {
        return false
    }
    var slow = head
    var fast = head
    while (fast?.next != null) {
        fast = fast.next?.next
        slow = slow!!.next
    }
    slow = reverseList2(slow)
    var cur = head
    while (slow != null) {
        if (cur!!.value == slow.value) {
            cur = cur.next
            slow = slow.next
        } else {
            return false
        }
    }
    return true
}

fun main() {
    println(isPalindrome(createListNode(intArrayOf(1, 2, 2, 1))))
    println(isPalindrome(createListNode(intArrayOf(1, 2, 1, 2, 1))))
    println(isPalindrome(createListNode(intArrayOf(1, 1, 2, 1))))
    println(isPalindrome(createListNode(intArrayOf(1, 1))))
    println(isPalindrome(createListNode(intArrayOf(1, 2))))
    println(isPalindrome(createListNode(intArrayOf(1, 0, 0))))
    println("2")
    println(isPalindrome2(createListNode(intArrayOf(1, 2, 2, 1))))
    println(isPalindrome2(createListNode(intArrayOf(1, 2, 1, 2, 1))))
    println(isPalindrome2(createListNode(intArrayOf(1, 2, 2, 2, 1))))
    println(isPalindrome2(createListNode(intArrayOf(1, 1, 2, 1))))
    println(isPalindrome2(createListNode(intArrayOf(1, 2))))
    println("3")
    println(isPalindrome3(createListNode(intArrayOf(1, 2, 2, 1))))
    println(isPalindrome3(createListNode(intArrayOf(1, 2, 1, 2, 1))))
    println(isPalindrome3(createListNode(intArrayOf(1, 1, 2, 1))))
    println(isPalindrome3(createListNode(intArrayOf(1, 2))))
    println(isPalindrome3(createListNode(intArrayOf(1, 0, 0))))
    println("4")
    println(isPalindrome4(createListNode(intArrayOf(1, 2, 2, 1))))
    println(isPalindrome4(createListNode(intArrayOf(1, 2, 1, 2, 1))))
    println(isPalindrome4(createListNode(intArrayOf(1, 1, 2, 1))))
    println(isPalindrome4(createListNode(intArrayOf(1, 1))))
    println(isPalindrome4(createListNode(intArrayOf(1, 2))))
    println(isPalindrome4(createListNode(intArrayOf(1, 0, 0))))
}
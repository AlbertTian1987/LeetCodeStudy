package com.gray.test.linklist

data class ListNode(var value: Int, var next: ListNode? = null)

fun ListNode.print() {
    var node: ListNode? = this
    while (node != null) {
        println(node.value)
        node = node.next
    }
}

fun createListNode(intArray: IntArray): ListNode {
    val head = ListNode(intArray[0])
    var node = head
    for (i in 1 until intArray.size) {
        node.next = ListNode(intArray[i])
        node = node.next!!
    }
    return head
}
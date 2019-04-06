package com.gray.test.linklist

data class ListNode(var value: Int, var next: ListNode? = null)

fun ListNode?.print() {
    if (this == null) {
        println("Node:[]")
        return
    }
    print("Node:[")
    var node: ListNode? = this
    while (node != null) {
        print(node.value)
        node = node.next
        if (node != null) {
            print("->")
        }
    }
    print("]")
    println()
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
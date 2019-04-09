package com.gray.test.tree

import java.util.*

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

fun linkedListOf(list: MutableList<Int?>): LinkedList<Int?> {
    val linkedList = LinkedList<Int?>()
    list.forEach { linkedList.add(it) }
    return linkedList
}

fun createTreeNode(values: LinkedList<Int?>): TreeNode? {
    if (values.isEmpty()) {
        return null
    }
    if (values.size == 1) {
        return values[0]?.let { TreeNode(it) }
    }
    val queue = LinkedList<TreeNode?>()
    val root = TreeNode(values.poll()!!)
    queue.add(root)
    while (values.isEmpty().not()) {
        assignTreeNode(queue, values)
    }
    return root
}

private fun assignTreeNode(queue: LinkedList<TreeNode?>, values: LinkedList<Int?>) {
    if (values.isEmpty()) {
        return
    }
    val root = queue.poll()
    if (root == null) {
        values.poll()
        values.poll()
    } else {
        root.left = values.poll()?.let { TreeNode(it) }
        queue.add(root.left)
        root.right = values.poll()?.let { TreeNode(it) }
        queue.add(root.right)
    }
}
package com.gray.test.tree

import java.util.*

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

fun createTreeNode(array: MutableList<Int?>): TreeNode? {
    if (array.isEmpty()) {
        return null
    }
    if (array.size == 1) {
        return array[0]?.let {
            TreeNode(it)
        }
    }
    val node = TreeNode(array[0]!!)
    val stack = Stack<TreeNode>()
    stack.push(node)

    var i = 1
    while (i < array.size) {
        val n = stack.pop()
        val left = array[i]?.let { TreeNode(it) }
        n.left = left
        if (i + 1 < array.size) {
            val right = array[i + 1]?.let { TreeNode(it) }
            n.right = right
            right?.let { stack.push(it) }
        }
        left?.let { stack.push(it) }
        i += 2
    }
    return node
}
package com.gray.test.tree

import java.util.*

/**
 * (中级)
 * 填充每个节点的下一个右侧节点指针
 *
 * 给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * 初始状态下，所有 next 指针都被设置为 NULL。
 *
 * before:
 *       1
 *     /  \
 *    2    3
 *   / \  / \
 *  4  5 6   7
 *
 * after:
 *       1->null
 *     /  \
 *    2 -> 3->null
 *   / \  / \
 *  4->5->6->7->null
 *
 * 提示：
 *
 * 你只能使用常量级额外空间。
 * 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 */

class Node(val `val`: Int) {
    var left: Node? = null
    var right: Node? = null
    var next: Node? = null
}

/**
 * 循环
 */
fun connect(root: Node?): Node? {
    if (root == null) {
        return null
    }
    val stack = LinkedList<Node>()
    stack.push(root)
    while (stack.isNotEmpty()) {
        val size = stack.size
        var node = stack.poll()!!

        node.left?.let { stack.add(it) }
        node.right?.let { stack.add(it) }

        for (i in 1 until size) {
            val next = stack.poll()!!
            node.next = next
            node = next
            node.left?.let { stack.add(it) }
            node.right?.let { stack.add(it) }
        }
    }

    return root
}

/**
 * 递归
 */
fun connect2(root: Node?): Node? {
    if (root == null) {
        return null
    }
    if (root.left != null) {
        root.left!!.next = root.right
        if (root.next != null) {
            root.right!!.next = root.next!!.left
        }
    }
    connect2(root.left)
    connect2(root.right)
    return root
}


fun main() {
    val root = Node(1)
    root.left = Node(2)
    root.right = Node(3)
    val n2 = root.left!!
    n2.left = Node(4)
    n2.right = Node(5)
    val n3 = root.right!!
    n3.left = Node(6)
    n3.right = Node(7)

    var r1: Node? = root
    r1 = connect2(r1)
    r1.toString()
}
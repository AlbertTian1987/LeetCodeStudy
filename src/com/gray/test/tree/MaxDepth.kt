package com.gray.test.tree

import java.util.*

/**
 * 二叉树的最大深度
 *
 * 给定一个二叉树，找出其最大深度。
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例：
 *      给定二叉树 [3,9,20,null,null,15,7]，
 *
 *   3
 *  / \
 * 9  20
 *   /  \
 * 15   7
 *
 * 返回它的最大深度 3 。
 *
 * */

/**
 * 解法1，使用递归
 */
fun maxDepth(root: TreeNode?): Int {
    if (root == null) {
        return 0
    }
    val left = maxDepth(root.left) + 1
    val right = maxDepth(root.right) + 1
    return if (left > right) {
        left
    } else {
        right
    }
}

/**
 * 解法2，借助链表的结构，广度优先遍历
 */
fun maxDepth2(root: TreeNode?): Int {
    if (root == null) {
        return 0
    }
    var depth = 0
    val treeList = LinkedList<TreeNode>()
    treeList.addLast(root)
    while (treeList.isNotEmpty()) {
        depth++
        val size = treeList.size
        repeat(size) {
            val node = treeList.poll()!!
            node.left?.let { treeList.add(it) }
            node.right?.let { treeList.add(it) }
        }
    }
    return depth
}


fun main() {
    val node = createTreeNode(linkedListOf(arrayListOf(3, 9, 20, null, null, 15, 7)))
    println(maxDepth(node))
    println(maxDepth2(node))
}
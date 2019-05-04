package com.gray.test.tree


/**
 * 107. 二叉树的层次遍历 II
 *
 * 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *      3
 *     / \
 *    9  20
 *   /    \
 *  15     7
 *
 * 返回其自底向上的层次遍历为：
 *  [
 *   [15,7],
 *   [9,20],
 *   [3]
 *  ]
 * */
import java.util.*

fun levelOrderBottom(root: TreeNode?): List<List<Int>> {
    if (root == null) {
        return arrayListOf()
    }
    val ret = LinkedList<ArrayList<Int>>()
    val queue = LinkedList<TreeNode>()
    queue.add(root)
    while (queue.isNotEmpty()) {
        val size = queue.size
        val list = ArrayList<Int>(size)
        ret.push(list)
        repeat(size) {
            val node = queue.poll()!!
            list.add(node.`val`)
            node.left?.let { queue.add(it) }
            node.right?.let { queue.add(it) }
        }
    }
    return ret
}
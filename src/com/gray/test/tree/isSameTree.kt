package com.gray.test.tree

import java.util.*

/**
 * 100. 相同的树
 *
 * 给定两个二叉树，编写一个函数来检验它们是否相同。
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 *
 * 示例 1:
 *  输入:
 *    1          1
 *   / \        / \
 *   2   3     2   3
 *
 *  [1,2,3],   [1,2,3]
 *
 *  输出: true
 *
 * 示例 2:
 * 输入:
 *    1          1
 *   /           \
 *  2             2
 *
 * [1,2],     [1,null,2]
 *
 * 输出: false
 *
 * 示例 3:
 * 输入:
 *    1         1
 *   / \       / \
 *  2   1     1   2
 *
 * [1,2,1],   [1,1,2]
 *
 * 输出: false
 *
 * */
fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
    val queue = LinkedList<TreeNode?>()
    queue.add(p)
    queue.add(q)
    while (queue.isNotEmpty()) {
        val left = queue.pollFirst()
        val right = queue.pollFirst()
        if (left?.`val` != right?.`val`) {
            return false
        }
        if (left != null) {
            queue.add(left.left)
            queue.add(right?.left)
            queue.add(left.right)
            queue.add(right?.right)
        }
    }
    return true
}

fun main() {
    val queue = LinkedList<TreeNode?>()
    queue.add(null)
    queue.add(null)
    println(queue.size)
}

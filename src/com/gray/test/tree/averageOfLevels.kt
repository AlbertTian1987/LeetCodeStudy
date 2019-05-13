package com.gray.test.tree

import java.util.*

/**
 * 637. 二叉树的层平均值
 * 给定一个非空二叉树, 返回一个由每层节点平均值组成的数组.
 *
 * 示例 1:
 * 输入:
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 输出: [3, 14.5, 11]
 * 解释:
 * 第0层的平均值是 3,  第1层是 14.5, 第2层是 11. 因此返回 [3, 14.5, 11].
 *
 * 注意：
 * 节点值的范围在32位有符号整数范围内。
 * */

fun averageOfLevels(root: TreeNode): DoubleArray {
    val queue: Queue<TreeNode> = LinkedList<TreeNode>()
    queue.offer(root)
    val ret = ArrayList<Double>()
    while (queue.isNotEmpty()) {
        var sum = 0.0
        val size = queue.size
        repeat(size) {
            val node = queue.poll()!!
            sum += node.`val`
            node.left?.let { queue.offer(it) }
            node.right?.let { queue.offer(it) }
        }
        ret.add(sum / size)
    }
    return ret.toDoubleArray()
}
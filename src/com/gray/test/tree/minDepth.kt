package com.gray.test.tree

/**
 * 111. 二叉树的最小深度
 *
 * 给定一个二叉树，找出其最小深度。
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 * 给定二叉树 [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *      / \
 *    15   7
 * 返回它的最小深度  2.
 */
import kotlin.math.min

fun minDepth(root: TreeNode?): Int {
    if (root == null) {
        return 0
    }
    if (root.left == null && root.right == null) {
        return 1
    }
    val left = if (root.left != null) {
        minDepth(root.left) + 1
    } else {
        Int.MAX_VALUE
    }
    val right = if (root.right != null) {
        minDepth(root.right) + 1
    } else {
        Int.MAX_VALUE
    }
    return min(left, right)
}

fun main() {
    val root = TreeNode(1)
    root.left = TreeNode(2)
    println(minDepth(root))
}
package com.gray.test.tree

import kotlin.math.max

/**
 * 687. 最长同值路径
 * 给定一个二叉树，找到最长的路径，这个路径中的每个节点具有相同值。 这条路径可以经过也可以不经过根节点。
 * 注意：两个节点之间的路径长度由它们之间的边数表示。
 *
 * 示例 1:
 *
 * 输入:
 *     5
 *    / \
 *   4   5
 *  / \   \
 * 1   1   5
 *
 * 输出:
 * 2
 *
 * 示例 2:
 *
 * 输入:
 *     1
 *    / \
 *   4   5
 *  / \   \
 * 4   4   5
 *
 * 输出:
 * 2
 *
 * 注意: 给定的二叉树不超过10000个结点。 树的高度不超过1000。
 *
 * */
fun longestUnivaluePath(root: TreeNode?): Int {
    if (root == null) {
        return 0
    }

    var best = 0
    fun dfs(root: TreeNode?, v: Int): Int {
        if (root == null) {
            return 0
        }
        val left = dfs(root.left, root.`val`)
        val right = dfs(root.right, root.`val`)
        best = max(best, left + right)
        if (root.`val` == v) {
            return max(left, right) + 1
        }
        return 0
    }
    dfs(root, root.`val`)
    return best
}

fun main() {
    val treeNode = createTreeNode(linkedListOf(arrayListOf(1, 4, 5, 4, 4, null, 5)))
    val i = longestUnivaluePath(treeNode)
    println(i)
}
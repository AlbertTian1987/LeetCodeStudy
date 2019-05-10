package com.gray.test.tree

/***
 * 404. 左叶子之和
 * 计算给定二叉树的所有左叶子之和。
 *
 * 示例：
 *    3
 *   / \
 *  9  20
 *    /  \
 *   15   7
 *
 * 在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
 */
fun sumOfLeftLeaves(root: TreeNode?): Int {
    if (root == null) {
        return 0
    }
    val left = root.left
    if (left != null && left.left == null && left.right == null) {
        return left.`val` + sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right)
    }
    return sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right)
}

fun main() {
    println(sumOfLeftLeaves(createTreeNode(linkedListOf(arrayListOf(3, 9, 20, null, null, 15, 7)))))
    println(sumOfLeftLeaves(createTreeNode(linkedListOf(arrayListOf(1, 2, 3, 4, 5, null, null)))))
}
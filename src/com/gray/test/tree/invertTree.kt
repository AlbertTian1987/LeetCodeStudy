package com.gray.test.tree


/**
 * 226. 翻转二叉树
 * 翻转一棵二叉树。
 *
 * 示例：
 * 输入：
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 *
 * 输出：
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 * */

fun invertTree(root: TreeNode?): TreeNode? {
    if (root == null) {
        return root
    }
    val left = invertTree(root.left)
    val right = invertTree(root.right)
    root.left = right
    root.right = left
    return root
}

fun main() {
    val node = invertTree(createTreeNode(linkedListOf(arrayListOf(4, 2, 7, 1, 3, 6, 9))))
    println(node)
}
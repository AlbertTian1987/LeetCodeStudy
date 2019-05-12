package com.gray.test.tree

/**
 * 538. 把二叉搜索树转换为累加树
 * 给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，使得每个节点的值是原来的节点值加上所有大于它的节点值之和。
 *
 * 例如：
 *
 * 输入: 二叉搜索树:
 *    5
 *  /   \
 * 2     13
 *
 * 输出: 转换为累加树:
 *    18
 *   /   \
 * 20     13
 *
 * */

fun convertBST(root: TreeNode?): TreeNode? {
    var flag = false
    var sum = 0
    fun dfs(root: TreeNode?) {
        if (root == null) {
            return
        }
        dfs(root.right)
        if (!flag) {
            flag = true
            sum = root.`val`
        } else {
            root.`val` += sum
            sum = root.`val`
        }
        dfs(root.left)
    }
    dfs(root)
    return root
}

fun main() {
    var node = createTreeNode(linkedListOf(arrayListOf(5, 2, 13)))
    node = convertBST(node)
    println(node)
}
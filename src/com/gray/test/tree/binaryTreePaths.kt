package com.gray.test.tree

/***
 * 257. 二叉树的所有路径
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 * 示例:
 * 输入:
 *    1
 *  /   \
 * 2     3
 *  \
 *   5
 *
 * 输出: ["1->2->5", "1->3"]
 *
 * 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
 */

fun binaryTreePaths(root: TreeNode?): List<String> {

    fun dfs(root: TreeNode?, trace: String, ret: ArrayList<String>) {
        if (root == null) {
            return
        }
        var newTrace = "$trace${root.`val`}"
        if (root.left == null && root.right == null) {
            ret.add(newTrace)
            return
        }
        newTrace = "$newTrace->"
        dfs(root.left, newTrace, ret)
        dfs(root.right, newTrace, ret)
    }

    val ret = ArrayList<String>()
    dfs(root, "", ret)
    return ret
}

fun main() {
    val node = createTreeNode(linkedListOf(arrayListOf(1, 2, 3, null, 5, null, null)))
    println(binaryTreePaths(node))
}
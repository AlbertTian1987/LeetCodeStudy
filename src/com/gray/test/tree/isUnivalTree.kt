package com.gray.test.tree

/***
 * 965. 单值二叉树
 * 如果二叉树每个节点都具有相同的值，那么该二叉树就是单值二叉树。
 * 只有给定的树是单值二叉树时，才返回 true；否则返回 false。
 */
fun isUnivalTree(root: TreeNode): Boolean {
    var flag = true
    fun dfs(root: TreeNode?, v: Int) {
        if (root == null) {
            return
        }
        if (root.`val` != v) {
            flag = false
            return
        }
        dfs(root.left, v)
        dfs(root.right, v)
    }
    dfs(root, root.`val`)
    return flag
}
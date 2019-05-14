package com.gray.test.tree

/**
 * 700. 二叉搜索树中的搜索
 * 给定二叉搜索树（BST）的根节点和一个值。
 * 你需要在BST中找到节点值等于给定值的节点。
 * 返回以该节点为根的子树。
 * 如果节点不存在，则返回 NULL。
 * */
fun searchBST(root: TreeNode?, `val`: Int): TreeNode? {
    if (root == null) {
        return null
    }
    return if (root.`val` == `val`) {
        root
    } else if (root.`val` > `val`) {
        searchBST(root.left, `val`)
    } else {
        searchBST(root.right, `val`)
    }
}
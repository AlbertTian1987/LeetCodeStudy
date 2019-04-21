package com.gray.test.tree

/**
 * (中级)
 * 从前序与中序遍历序列构造二叉树
 *
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *      前序遍历 preorder = [3,9,20,15,7]
 *      中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *
 *      3
 *    / \
 *  9   20
 *    /  \
 *  15   7
 */

fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
    if (preorder.isEmpty()) {
        return null
    }
    val n = inorder.size - 1
    return build(0, 0, n, n, preorder, inorder)
}

/**
 * 前序中左起第一位1肯定是根结点，我们可以据此找到中序中根结点的位置rootin；
 * 中序中根结点左边就是左子树结点，右边就是右子树结点，即[左子树结点，根结点，右子树结点]，我们就可以得出左子树结点个数为int left = rootin - leftin;；
 * 前序中结点分布应该是：[根结点，左子树结点，右子树结点]；
 * 根据前一步确定的左子树个数，可以确定前序中左子树结点和右子树结点的范围；
 * 如果我们要前序遍历生成二叉树的话，下一层递归应该是：
 * 左子树：root->left = pre_order(前序左子树范围，中序左子树范围，前序序列，中序序列);；
 * 右子树：root->right = pre_order(前序右子树范围，中序右子树范围，前序序列，中序序列);。
 * 每一层递归都要返回当前根结点root；
 */
fun build(leftpre: Int, leftin: Int, rightpre: Int, rightin: Int, preorder: IntArray, inorder: IntArray): TreeNode? {
    if (leftpre > rightpre || leftin > rightin) {
        return null
    }
    val root = TreeNode(preorder[leftpre])
    var rootin = leftin
    while (rootin <= rightin && root.`val` != inorder[rootin]) {
        rootin++
    }
    val left = rootin - leftin
    root.left = build(leftpre + 1, leftin, leftpre + left, rootin - 1, preorder, inorder)
    root.right = build(leftpre + left + 1, rootin + 1, rightpre, rightin, preorder, inorder)
    return root
}

fun main() {
    val tree = buildTree(intArrayOf(3, 4, 6, 8, 7, 9, 5), intArrayOf(8, 6, 4, 7, 9, 3, 5))
    tree.toString()
}
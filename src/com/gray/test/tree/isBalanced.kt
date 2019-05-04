package com.gray.test.tree

/**
 * 110. 平衡二叉树
 *
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * 本题中，一棵高度平衡二叉树定义为：
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
 *
 * 示例 1:
 * 给定二叉树 [3,9,20,null,null,15,7]
 *          3
 *         / \
 *        9  20
 *       /    \
 *      15     7
 *
 * 返回 true 。
 *
 * 示例 2:
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 *       1
 *      / \
 *     2   2
 *    / \
 *   3   3
 *  / \
 * 4   4
 *
 * 返回 false 。
 * */
import kotlin.math.abs
import kotlin.math.max

fun depth(root: TreeNode?, d: Int): Int {
    if (root == null) {
        return d
    }
    return max(depth(root.left, d + 1), depth(root.right, d + 1))
}

fun isBalanced(root: TreeNode?): Boolean {
    if (root == null) {
        return true
    }
    val dl = depth(root.left, 1)
    val dr = depth(root.right, 1)
    if (abs(dl - dr) > 1) {
        return false
    }
    if (!isBalanced(root.left)) {
        return false
    }
    return isBalanced(root.right)
}
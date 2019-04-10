package com.gray.test.tree

/**
 *
 * 将有序数组转换为二叉搜索树
 *
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 *
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * 示例:
 *      给定有序数组: [-10,-3,0,5,9],
 *      一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
 *
 *       0
 *      / \
 *   -3   9
 *   /   /
 * -10  5
 *
 *
 * */

fun sortedArrayToBST(nums: IntArray): TreeNode? {
    if (nums.isEmpty()) {
        return null
    }
    return assign(0, nums.size - 1, nums)
}

fun assign(lo: Int, hi: Int, nums: IntArray): TreeNode? {
    if (lo > hi) {
        return null
    }
    if (lo == hi) {
        return TreeNode(nums[lo])
    }
    val mid = (lo + hi) / 2
    val node = TreeNode(nums[mid])
    node.left = assign(lo, mid - 1, nums)
    node.right = assign(mid + 1, hi, nums)
    return node
}


fun main() {
    levelOrder(sortedArrayToBST(intArrayOf(0))).forEach { println(it) }
    println()
    levelOrder(sortedArrayToBST(intArrayOf(0, 1))).forEach { println(it) }
    println()
    levelOrder(sortedArrayToBST(intArrayOf(0, 1, 2))).forEach { println(it) }
    println()
    levelOrder(sortedArrayToBST(intArrayOf(0, 1, 2, 4))).forEach { println(it) }
    println()
}
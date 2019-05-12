package com.gray.test.tree

import kotlin.math.abs

/**
 * 530. 二叉搜索树的最小绝对差
 * 给定一个所有节点为非负值的二叉搜索树，求树中任意两节点的差的绝对值的最小值。
 *
 * 示例 :
 * 输入:
 * 1
 *  \
 *   3
 *  /
 * 2
 *
 * 输出:
 * 1
 *
 * 解释:
 * 最小绝对差为1，其中 2 和 1 的差的绝对值为 1（或者 2 和 3）。
 * 注意: 树中至少有2个节点。
 * */

fun getMinimumDifference(root: TreeNode?): Int {
    var min = Int.MAX_VALUE
    var flag = false
    var pre = 0
    fun dfs(root: TreeNode?) {
        if (root == null) {
            return
        }
        dfs(root.left)
        if (!flag) {
            flag = true
            pre = root.`val`
        } else {
            val k = abs(root.`val` - pre)
            if (k < min) {
                min = k
            }
            pre = root.`val`
        }
        dfs(root.right)
    }
    dfs(root)
    return min
}
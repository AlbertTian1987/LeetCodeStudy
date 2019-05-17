package com.gray.test.tree

import java.util.*

/***
 * 872. 叶子相似的树
 * 请考虑一颗二叉树上所有的叶子，这些叶子的值按从左到右的顺序排列形成一个 叶值序列 。
 * 举个例子，如上图所示，给定一颗叶值序列为 (6, 7, 4, 9, 8) 的树。
 * 如果有两颗二叉树的叶值序列是相同，那么我们就认为它们是 叶相似 的。
 * 如果给定的两个头结点分别为 root1 和 root2 的树是叶相似的，则返回 true；否则返回 false 。
 *
 * 提示：
 * 给定的两颗树可能会有 1 到 100 个结点。
 */
fun leafSimilar(root1: TreeNode?, root2: TreeNode?): Boolean {
    val queue1: Queue<Int> = LinkedList<Int>()
    val queue2: Queue<Int> = LinkedList<Int>()

    fun dfs(root: TreeNode?, queue: Queue<Int>) {
        if (root == null) {
            return
        }
        dfs(root.left, queue)
        dfs(root.right, queue)
        if (root.left == null && root.right == null) {
            queue.offer(root.`val`)
        }
    }
    dfs(root1, queue1)
    dfs(root2, queue2)
    if (queue1.size != queue2.size) {
        return false
    }

    while (queue1.isNotEmpty()) {
        if (queue1.poll() != queue2.poll()) {
            return false
        }
    }
    return true
}
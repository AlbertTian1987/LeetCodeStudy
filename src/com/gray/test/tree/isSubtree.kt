package com.gray.test.tree

/***
 * 572. 另一个树的子树
 * 给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。
 * s 的一个子树包括 s 的一个节点和这个节点的所有子孙。s 也可以看做它自身的一棵子树。
 *
 * 示例 1:
 * 给定的树 s:
 *     3
 *    / \
 *   4   5
 *  / \
 * 1   2
 * 给定的树 t：
 *   4
 *  / \
 * 1   2
 * 返回 true，因为 t 与 s 的一个子树拥有相同的结构和节点值。
 *
 * 示例 2:
 * 给定的树 s：
 *     3
 *    / \
 *   4   5
 *  / \
 * 1   2
 *    /
 *   0
 * 给定的树 t：
 *   4
 *  / \
 * 1   2
 * 返回 false。
 */
fun isSubtree(s: TreeNode?, t: TreeNode?): Boolean {
    if (s == null) {
        return false
    }
    return isSameTree2(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t)
}

private fun isSameTree2(s: TreeNode?, t: TreeNode?): Boolean {
    if (s == null || t == null) {
        return s == null && t == null
    }
    if (s.`val` == t.`val`) {
        return isSameTree2(s.left, t.left) && isSameTree2(s.left, t.right)
    }
    return false
}

fun main() {
    val s = createTreeNode(linkedListOf(arrayListOf(3, 4, 5, 1, null, 2, null)))
    val t = createTreeNode(linkedListOf(arrayListOf(3, 1, 2)))
    println(isSubtree(s, t))
}
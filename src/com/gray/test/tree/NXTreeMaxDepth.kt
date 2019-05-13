package com.gray.test.tree

/**
 * 559. N叉树的最大深度
 * 给定一个 N 叉树，找到其最大深度。
 * 最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。
 * 例如，给定一个 3叉树 :
 * 我们应返回其最大深度，3。
 *
 * 说明:
 * 树的深度不会超过 1000。
 * 树的节点总不会超过 5000。
 * */
data class XNode(val `val`: Int, val children: List<XNode>)

fun maxDepth(root: XNode?): Int {
    if (root == null) {
        return 0
    }
    val children = root.children
    if (children.isEmpty()) {
        return 1
    }
    var depth = 0
    for (child in children) {
        val i = maxDepth(child)
        if (i > depth) {
            depth = i
        }
    }
    return depth + 1
}
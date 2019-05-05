package com.gray.test.tree

/**
 * 给定一系列节点的概率序列，生成哈夫曼树
 * 概率用整数表示，35 等于 35%
 */

fun haffmanTree(p: ArrayList<Int>): TreeNode? {
    val treeCache = hashMapOf<Int, TreeNode>()
    while (p.isNotEmpty()) {
        if (p.size == 1) {
            return treeCache[p[0]]!!
        }

        p.sort()
        val l = p[0]
        val r = p[1]
        val left = treeCache[l] ?: TreeNode(l)
        val right = treeCache[r] ?: TreeNode(r)
        val root = treeCache[l + r] ?: TreeNode(r + l)
        root.left = left
        root.right = right
        p.remove(l)
        p.remove(r)
        p.add(l + r)
        treeCache[l] = left
        treeCache[r] = right
        treeCache[l + r] = root
    }
    return null
}

fun main() {
    val node = haffmanTree(arrayListOf(5, 9, 16, 12, 13, 45))
    println(node?.`val`)
}
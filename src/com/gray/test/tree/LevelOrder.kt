package com.gray.test.tree

import java.util.*
import kotlin.collections.ArrayList

/**
 * 二叉树的层次遍历
 *
 * 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
 *
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 *
 *      3
 *     / \
 *    9  20
 *      /  \
 *     15   7
 *
 * 返回其层次遍历结果：
 *      [
 *          [3],
 *          [9,20],
 *          [15,7]
 *      ]
 *
 * */

fun levelOrder(root: TreeNode?): List<List<Int>> {
    if (root == null) {
        return arrayListOf()
    }
    val result = ArrayList<ArrayList<Int>>()
    val treeNodeList = LinkedList<TreeNode>()
    treeNodeList.add(root)

    while (treeNodeList.isEmpty().not()) {
        val size = treeNodeList.size
        val levelInt = ArrayList<Int>()
        repeat(size) {
            val node = treeNodeList.poll()!!
            levelInt.add(node.`val`)
            node.left?.let { treeNodeList.add(it) }
            node.right?.let { treeNodeList.add(it) }
        }
        result.add(levelInt)
    }
    return result
}

fun main() {
    val node = createTreeNode(linkedListOf(arrayListOf(3, 9, 20, null, null, 15, 7)))
    val levelOrder = levelOrder(node)
    levelOrder.forEach {
        println(it)
    }

}
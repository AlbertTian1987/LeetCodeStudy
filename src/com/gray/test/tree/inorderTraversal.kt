package com.gray.test.tree

/***
 * (中级)
 * 中序遍历二叉树
 *
 * 给定一个二叉树，返回它的中序 遍历。
 *
 * 示例:
 * 输入: [1,null,2,3]
 *      1
 *      \
 *       2
 *     /
 *   3
 * 输出: [1,3,2]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 */

import java.util.*

fun inorderTraversal(root: TreeNode?): List<Int> {
    fun help(root: TreeNode?, result: ArrayList<Int>) {
        if (root == null) {
            return
        }
        help(root.left, result)
        result.add(root.`val`)
        help(root.right, result)
    }

    val list = ArrayList<Int>()
    help(root, list)
    return list
}

fun inorderTraversal2(root: TreeNode?): List<Int> {
    if (root == null) {
        return arrayListOf()
    }
    val nodes = LinkedList<TreeNode>()
    val list = ArrayList<Int>()
    var cur = root
    while (cur != null || nodes.isNotEmpty()) {
        if (cur != null) {
            nodes.push(cur)
            cur = cur.left
        } else {
            cur = nodes.pop()
            list.add(cur.`val`)
            cur = cur.right
        }
    }
    return list
}

fun main() {
    println(inorderTraversal(createTreeNode(linkedListOf(arrayListOf(1, null, 2, null, null, 3, null)))))
    println(inorderTraversal2(createTreeNode(linkedListOf(arrayListOf(1, null, 2, null, null, 3, null)))))
}
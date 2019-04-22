package com.gray.test.tree

/**
 * (中级)
 * 二叉树的锯齿形层次遍历
 *
 * 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *         3
 *       /  \
 *     9    20
 *        /  \
 *      15   7
 * 返回锯齿形层次遍历如下：
 *  [
 *      [3],
 *      [20,9],
 *      [15,7]
 *  ]
 */
import java.util.*

fun zigzagLevelOrder(root: TreeNode?): List<List<Int>> {
    if (root == null) {
        return arrayListOf()
    }
    val result = ArrayList<ArrayList<Int>>()
    val stack = LinkedList<TreeNode>()
    stack.add(root)
    var flag = false
    while (stack.isNotEmpty()) {
        val size = stack.size
        val line = ArrayList<Int>()
        flag = !flag
        repeat(size) {
            if (flag) {
                val node = stack.pollFirst()!!
                line.add(node.`val`)
                node.left?.let { stack.addLast(it) }
                node.right?.let { stack.addLast(it) }
            } else {
                val node = stack.pollLast()!!
                line.add(node.`val`)
                node.right?.let { stack.addFirst(it) }
                node.left?.let { stack.addFirst(it) }
            }
        }
        result.add(line)
    }
    return result
}

fun zigzagLevelOrder2(root: TreeNode?): List<List<Int>> {

    fun help(node: TreeNode?, depth: Int, result: ArrayList<ArrayList<Int>>) {
        if (node == null) {
            return
        }
        val list = if (result.size >= depth) {
            result[depth - 1]
        } else {
            val l = ArrayList<Int>()
            result.add(depth - 1, l)
            l
        }
        if (depth % 2 == 0) {
            list.add(0, node.`val`)
        } else {
            list.add(node.`val`)
        }
        help(node.left, depth + 1, result)
        help(node.right, depth + 1, result)
    }

    val result = ArrayList<ArrayList<Int>>()
    help(root, 1, result)
    return result
}

fun main() {
    println(zigzagLevelOrder(createTreeNode(linkedListOf(arrayListOf(3, 9, 20, null, null, 15, 7)))))
    println(zigzagLevelOrder2(createTreeNode(linkedListOf(arrayListOf(3, 9, 20, null, null, 15, 7)))))
    println(zigzagLevelOrder2(createTreeNode(linkedListOf(arrayListOf(1, 2, 3, 4, null, null, 5)))))
}
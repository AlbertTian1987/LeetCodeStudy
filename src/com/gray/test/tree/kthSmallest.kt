package com.gray.test.tree

/**
 * 二叉搜索树中第K小的元素
 *
 * 给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
 *
 * 说明：
 * 你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。
 *
 * 示例 1:
 *  输入: root = [3,1,4,null,2], k = 1
 *      3
 *     / \
 *    1   4
 *     \
 *      2
 *  输出: 1
 *
 * 示例 2:
 *  输入: root = [5,3,6,2,4,null,null,1], k = 3
 *       5
 *      / \
 *     3   6
 *    / \
 *   2   4
 *  /
 * 1
 *  输出: 3
 *
 * 进阶：
 * 如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化 kthSmallest 函数？
 *
 */
import java.util.*

/**
 * 递归中序遍历
 */
fun kthSmallest(root: TreeNode?, k: Int): Int {
    val stack = LinkedList<Int>()
    fun inorder(root: TreeNode?, k: Int, stack: LinkedList<Int>) {
        if (root == null) {
            return
        }
        inorder(root.left, k, stack)
        if (stack.size == k) {
            return
        }
        stack.push(root.`val`)
        inorder(root.right, k, stack)
    }
    inorder(root, k, stack)
    return stack.pop()
}

/**
 * 循环中序遍历
 */
fun kthSmallest2(root: TreeNode?, k: Int): Int {
    if (root == null) {
        return 0
    }
    val stack = LinkedList<TreeNode>()
    var node = root
    var n = k
    while (node != null || stack.isNotEmpty()) {
        while (node != null) {
            stack.push(node)
            node = node.left
        }
        if (stack.isNotEmpty()) {
            node = stack.poll()!!
            if (--n == 0) {
                return node.`val`
            }
            node = node.right
        }
    }
    return -1
}

fun main() {
    val node = createTreeNode(linkedListOf(arrayListOf(3, 1, 4, null, 2, null, null)))
    println(kthSmallest(node, 1))
    println(kthSmallest(node, 2))
    println(kthSmallest(node, 3))
    println(kthSmallest(node, 4))
    println()
    println(kthSmallest2(node, 1))
    println(kthSmallest2(node, 2))
    println(kthSmallest2(node, 3))
    println(kthSmallest2(node, 4))
}
package com.gray.test.tree

import java.util.*

/**
 * 对称二叉树
 *
 * 给定一个二叉树，检查它是否是镜像对称的。
 *
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *      1
 *    /  \
 *   2    2
 *  / \  / \
 * 3  4 4   3
 *
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 *   1
 *  / \
 * 2   2
 *  \   \
 *   3   3
 *
 * 说明:
 * 如果你可以运用递归和迭代两种方法解决这个问题，会很加分。
 *
 * */
fun isSymmetric(root: TreeNode?): Boolean {
    if (root == null) {
        return true
    }

    return isEqual(root.left, root.right)
}

fun isEqual(left: TreeNode?, right: TreeNode?): Boolean {
    if (left == null && right == null) {
        return true
    }
    if (left?.`val` != right?.`val`) {
        return false
    }
    return isEqual(left?.left, right?.right) && isEqual(left?.right, right?.left)
}

fun isSymmetric2(root: TreeNode?): Boolean {
    if (root == null) {
        return true
    }
    val treeList = LinkedList<TreeNode>()
    val valueList = LinkedList<Int>()
    treeList.add(root)
    while (treeList.isEmpty().not()) {
        valueList.clear()
        val size = treeList.size
        repeat(size) {
            val head = treeList.poll()
            head.left?.let { treeList.add(it) }
            valueList.add(head.left?.`val` ?: Int.MAX_VALUE)

            head.right?.let { treeList.add(it) }
            valueList.add(head.right?.`val` ?: Int.MAX_VALUE)
        }

        var i = 0
        var j = valueList.size - 1
        while (i < j) {
            if (valueList[i] != valueList[j]) {
                return false
            }
            i++
            j--
        }
    }
    return true
}

fun main() {
    val node = createTreeNode(linkedListOf(arrayListOf(1, 2, 2, 3, 4, 4, 3)))
    println(isSymmetric(node))
    println(isSymmetric2(node))
    val node1 = createTreeNode(linkedListOf(arrayListOf(1, 2, 2, null, 3, null, 3)))
    println(isSymmetric(node1))
    println(isSymmetric2(node1))
}
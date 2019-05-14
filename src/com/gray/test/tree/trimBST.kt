package com.gray.test.tree

/**
 * 669. 修剪二叉搜索树
 * 给定一个二叉搜索树，同时给定最小边界L 和最大边界 R。
 * 通过修剪二叉搜索树，使得所有节点的值在[L, R]中 (R>=L) 。
 * 你可能需要改变树的根节点，所以结果应当返回修剪好的二叉搜索树的新的根节点。
 *
 * 示例 1:
 *
 * 输入:
 *   1
 *  / \
 * 0   2
 *
 * L = 1
 * R = 2
 *
 * 输出:
 * 1
 *  \
 *   2
 *
 * 示例 2:
 *
 * 输入:
 *   3
 *  / \
 * 0   4
 *  \
 *   2
 *  /
 * 1
 *
 * L = 1
 * R = 3
 *
 * 输出:
 *     3
 *    /
 *   2
 *  /
 * 1
 *
 * */

fun trimBST(root: TreeNode?, L: Int, R: Int): TreeNode? {
    if (root == null) {
        return null
    }
    var head: TreeNode? = root
    while (head != null && head.`val` < L) {
        head = head.right
    }
    if (head == null) {
        return head
    }
    var cur: TreeNode? = head
    while (cur?.left != null) {
        var left = cur.left
        while (left != null && left.`val` < L) {
            left = left.right
        }
        cur.left = left
        cur = left
    }

    while (head != null && head.`val` > R) {
        head = head.left
    }
    if (head == null) {
        return head
    }

    cur = head
    while (cur?.right != null) {
        var right = cur.right
        while (right != null && right.`val` > R) {
            right = right.left
        }
        cur.right = right
        cur = right
    }

    return head
}

fun trimBST2(root: TreeNode?, L: Int, R: Int): TreeNode? {
    if (root == null) {
        return null
    }
    if (root.`val` < L) {
        return trimBST2(root.right, L, R)
    }
    if (root.`val` > R) {
        return trimBST2(root.left, L, R)
    }
    root.left = trimBST2(root.left, L, R)
    root.right = trimBST2(root.right, L, R)
    return root
}

fun main() {
    var node = createTreeNode(linkedListOf(arrayListOf(1, 0, 2)))
    node = trimBST2(node, 1, 2)
    println(node?.`val`)
    node = createTreeNode(linkedListOf(arrayListOf(3, 0, 4, null, 2, null, null, null, null, 1, null, null, null, null, null)))
    node = trimBST2(node, 1, 3)
    println(node?.`val`)
}
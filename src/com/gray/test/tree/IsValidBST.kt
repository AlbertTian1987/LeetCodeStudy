package com.gray.test.tree

/**
 * 验证二叉搜索树
 *
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 *
 * 假设一个二叉搜索树具有如下特征：
 *      1.节点的左子树只包含小于当前节点的数。
 *      2.节点的右子树只包含大于当前节点的数。
 *      3. 所有左子树和右子树自身必须也是二叉搜索树。
 *
 * 示例 1:
 *      输入:
 *        2
 *       / \
 *      1   3
 *      输出: true
 *
 * 示例 2:
 *
 *      输入:
 *        5
 *       / \
 *      1   4
 *         / \
 *        3   6
 *      输出: false
 *
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 * 根节点的值为 5 ，但是其右子节点值为 4 。
 *
 * */
/**
 * 一开始的解法，先判断左边整个左子树都小于自己，右子树都大于自己。
 * 然后依次对自己的左右节点做出判断
 */
fun isValidBST(root: TreeNode?): Boolean {
    if (root == null) {
        return true
    }
    val left = isValidLeft(root.left, root.`val`)
    if (!left) {
        return false
    }
    val right = isValidRight(root.right, root.`val`)
    if (!right) {
        return false
    }

    return isValidBST(root.left) && isValidBST(root.right)
}

fun isValidLeft(node: TreeNode?, value: Int): Boolean {
    if (node == null) {
        return true
    }
    if (node.`val` >= value) {
        return false
    }
    return isValidLeft(node.left, value) && isValidLeft(node.right, value)
}

fun isValidRight(node: TreeNode?, value: Int): Boolean {
    if (node == null) {
        return true
    }
    if (node.`val` <= value) {
        return false
    }
    return isValidRight(node.left, value) && isValidRight(node.right, value)
}

/**
 * 中序遍历，
 * 比如 示例二，读取的次序是 1-5-3-4-6 正确的话，越后读取的值越大，示例二显然不满足
 *
 */
var smallValue = -Long.MAX_VALUE

fun isValidBST2(root: TreeNode?): Boolean {
    if (root == null) {
        return true
    }
    if (isValidBST2(root.left)) {
        if (smallValue < root.`val`) {
            smallValue = root.`val`.toLong()
            return isValidBST2(root.right)
        }
    }
    return false
}


fun main() {
    val node = createTreeNode(arrayListOf(2, 1, 3))
    println(isValidBST(node))
    println(isValidBST2(node))

    val node1 = createTreeNode(arrayListOf(5, 1, 4, null, null, 3, 6))
    println(isValidBST(node1))
    println(isValidBST2(node1))

    val node2 = createTreeNode(arrayListOf(10, 5, 15, null, null, 6, 20))
    println(isValidBST(node2))
    println(isValidBST2(node2))
}
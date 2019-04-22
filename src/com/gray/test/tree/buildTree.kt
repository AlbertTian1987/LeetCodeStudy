package com.gray.test.tree

/**
 * (中级)
 * 从前序与中序遍历序列构造二叉树
 *
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *      前序遍历 preorder = [3,9,20,15,7]
 *      中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *
 *      3
 *    / \
 *  9   20
 *    /  \
 *  15   7
 */

fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
    return build(preorder, inorder, 0, 0, inorder.size - 1)
}
/**
 * 思路：
 *       a
 *      / \
 *     b   c
 *    / \   \
 *   d   e   f
 *          / \
 *         g   h
 *
 * 前序遍历: a->b->d->e->c->f->g->h
 *       root| 左子树 ||  右子树  |
 * 中序遍历: d->b->e->a->c->g->f->h
 *         |左子树 |root|  右子树  |
 *
 * 1. 先序遍历的第一个是root结点
 * 2. 找到root结点在中序遍历里的位置
 * 3. 中序遍历root左边是左子树，得到它的长度
 * 4. 可以确定先序遍历里右子树起始位置
 * 5. 重复1-4
 *
 * @param preorder 完整的先序遍历
 * @param inorder  完整的中序遍历
 * @param preSt    子树先序遍历起始点
 * @param inSt     子树中序遍历起始点
 * @param inEnd    子树中序遍历结束点
 * @return
 */
fun build(preorder: IntArray, inorder: IntArray, preSt: Int, inSt: Int, inEnd: Int): TreeNode? {

    if (preSt > preorder.size - 1 || inSt > inEnd) {
        return null
    }
    val root = TreeNode(preorder[preSt])
    //根节点在中序遍历中的index
    var rootIndex = inSt
    while (rootIndex <= inEnd && root.`val` != inorder[rootIndex]) {
        rootIndex++
    }
    //左子树的长度
    val leftLength = rootIndex - inSt
    //右子树在先序遍历的起始点
    val rightSt = preSt + leftLength + 1
    val leftSt = preSt + 1
    root.left = build(preorder, inorder, leftSt, inSt, rootIndex - 1)
    root.right = build(preorder, inorder, rightSt, rootIndex + 1, inEnd)
    return root
}

fun main() {
    val tree = buildTree(intArrayOf(3, 4, 6, 8, 7, 9, 5), intArrayOf(8, 6, 4, 7, 9, 3, 5))
    tree.toString()
}
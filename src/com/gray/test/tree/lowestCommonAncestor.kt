package com.gray.test.tree


/**
 * 235. 二叉搜索树的最近公共祖先
 *
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 * 例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]
 *
 * 示例 1:
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * 输出: 6
 * 解释: 节点 2 和节点 8 的最近公共祖先是 6。
 *
 * 示例 2:
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * 输出: 2
 * 解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
 *
 *
 * 说明:
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉搜索树中。
 * */

/**
 * 常规方法
 */
fun lowestCommonAncestor(root: TreeNode, p: TreeNode, q: TreeNode): TreeNode {
    val pList = arrayListOf<TreeNode>()
    val qList = arrayListOf<TreeNode>()
    fun find(root: TreeNode?, value: Int, trace: ArrayList<TreeNode>): Boolean {
        if (root == null) {
            return false
        }
        if (root.`val` == value) {
            trace.add(root)
            return true
        }
        if (find(root.left, value, trace)) {
            trace.add(root)
            return true
        }
        if (find(root.right, value, trace)) {
            trace.add(root)
            return true
        }
        return false
    }
    find(root, p.`val`, pList)
    find(root, q.`val`, qList)

    val shortList = if (pList.size >= qList.size) {
        qList
    } else {
        pList
    }
    val longList = if (pList.size >= qList.size) {
        pList
    } else {
        pList
    }
    val dx = longList.size - shortList.size
    for (i in 0 until shortList.size) {
        if (shortList[i].`val` == longList[i + dx].`val`) {
            return shortList[i]
        }
    }
    return pList[0]
}

/**
 * 针对二叉搜索树的特性
 * p-root * q-root
 * 1. 相乘的结果等于0，证明root和p，q之一相等，最近公共祖先节点可以为节点本身
 * 2. 小于0，证明p，q一个在root左边，一个在root右边，最近公共祖先节点可以为root
 * 3. 大于0，证明同时在左边或右边，把root下降一个深度，再来求解
 *     3.1 root大于p，证明都在root左边，root等于自己的左子树
 *     3.2 root小于p，证明都在root右边，root等于自己的右子树
 */
fun lowestCommonAncestor2(root: TreeNode, p: TreeNode, q: TreeNode): TreeNode {
    var cur = root
    while ((p.`val` - cur.`val`) * (q.`val` - cur.`val`) > 0) {
        cur = if (cur.`val` > p.`val`) {
            cur.left!!
        } else {
            cur.right!!
        }
    }
    return cur
}

fun main() {
    val node = createTreeNode(linkedListOf(arrayListOf(6, 2, 8, 0, 4, 7, 9, null, null, 3, 5, null, null, null, null)))!!
    println(lowestCommonAncestor2(node, node.left!!, node.right!!).`val`)
    println(lowestCommonAncestor2(node, node.left!!, node.left!!.right!!).`val`)
}
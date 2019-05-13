package com.gray.test.tree

/***
 * 558. 四叉树交集
 *
 * 四叉树是一种树数据，其中每个结点恰好有四个子结点：topLeft、topRight、bottomLeft 和 bottomRight。
 * 四叉树通常被用来划分一个二维空间，递归地将其细分为四个象限或区域。
 *
 * 我们希望在四叉树中存储 True/False 信息。
 * 四叉树用来表示 N * N 的布尔网格。
 * 对于每个结点, 它将被等分成四个孩子结点直到这个区域内的值都是相同的。
 * 每个节点都有另外两个布尔属性：isLeaf 和 val。
 * 当这个节点是一个叶子结点时 isLeaf 为真。
 * val 变量储存叶子结点所代表的区域的值。
 *
 * 你的任务是实现一个函数，该函数根据两个四叉树返回表示这两个四叉树的逻辑或(或并)的四叉树。

 */

fun intersect(quadTree1: FNode, quadTree2: FNode): FNode {
    return when {
        quadTree1.isLeaf && quadTree2.isLeaf -> FNode(quadTree1.`val` || quadTree2.`val`, true)
        quadTree1.isLeaf && !quadTree2.isLeaf -> if (quadTree1.`val`) quadTree1 else quadTree2
        !quadTree1.isLeaf && quadTree2.isLeaf -> if (quadTree2.`val`) quadTree2 else quadTree1
        else -> {
            val topLeft = intersect(quadTree1.topLeft!!, quadTree2.topLeft!!)
            val topRight = intersect(quadTree1.topRight!!, quadTree2.topRight!!)
            val bottomLeft = intersect(quadTree1.bottomLeft!!, quadTree2.bottomLeft!!)
            val bottomRight = intersect(quadTree1.bottomRight!!, quadTree2.bottomRight!!)
            if (topLeft.isLeaf && topRight.isLeaf && bottomLeft.isLeaf && bottomRight.isLeaf
                    && topLeft.`val` == topRight.`val` && topRight.`val` == bottomLeft.`val`
                    && bottomLeft.`val` == bottomRight.`val`) {
                topLeft
            } else {
                val ret = FNode()
                ret.topLeft = topLeft
                ret.topRight = topRight
                ret.bottomLeft = bottomLeft
                ret.bottomRight = bottomRight
                ret
            }
        }
    }
}
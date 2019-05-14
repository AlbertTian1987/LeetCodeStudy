package com.gray.test.tree

import java.util.*
import kotlin.math.min

/**
 * 671. 二叉树中第二小的节点
 * 给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0。
 * 如果一个节点有两个子节点的话，那么这个节点的值不大于它的子节点的值。
 * 给出这样的一个二叉树，你需要输出所有节点中的第二小的值。如果第二小的值不存在的话，输出 -1 。
 *
 * 示例 1:
 * 输入:
 *     2
 *    / \
 *   2   5
 *  / \
 * 5   7
 *
 * 输出: 5
 * 说明: 最小的值是 2 ，第二小的值是 5 。
 *
 * 示例 2:
 *
 * 输入:
 *   2
 *  / \
 * 2   2
 * 输出: -1
 * 说明: 最小的值是 2, 但是不存在第二小的值。
 * */

/**
 * 迭代的写法
 * 利用队列广度优先遍历，每个节点它的子节点存在的话，(a,b) 节点值要么等于父节点，要么大于父节点。
 * 设第二小值为k
 * 1. a = parent，b > parent，if(k==-1 || k > b ) k = b，a的子树还要查看，b的子树不需要再查看
 * 2. a，b = parent， 将a，b都加入队列查看子树
 * 2. a，b > parent ，取 n = min(a,b), if(k==-1 || k > n ) k = n, a，b子树不需要再查看
 */
fun findSecondMinimumValue(root: TreeNode): Int {
    val parent = root.`val`
    var secondMin = -1
    val queue = LinkedList<TreeNode>()
    queue.add(root)
    while (queue.isNotEmpty()) {
        val node = queue.poll()
        val left = node.left
        val right = node.right
        if (left != null && right != null) {
            if (left.`val` == parent && right.`val` == parent) {
                queue.offer(left)
                queue.offer(right)
            } else if (left.`val` == parent || right.`val` == parent) {
                var a: TreeNode
                var b: TreeNode
                if (left.`val` > right.`val`) {
                    a = right
                    b = left
                } else {
                    a = left
                    b = right
                }
                queue.offer(a)
                if (secondMin == -1 || secondMin > b.`val`) {
                    secondMin = b.`val`
                }
            } else {
                val n = if (left.`val` > right.`val`) right.`val` else left.`val`
                if (secondMin == -1 || secondMin > n) {
                    secondMin = n
                }
            }
        }
    }
    return secondMin
}

/**
 * 递归的写法
 * 1. 如果子节点的值等于父节点，那查看它的子树，如果一直找下去，都等于父节点，最终返回-1，表示这颗子树无有意义的返回值
 * 2. 如果子节点的值不等于父节点，两个子节点都这样，返回小的，否则返回那个唯一合法的值
 */
fun findSecondMinimumValue2(root: TreeNode): Int {
    val leftNode = root.left
    val rightNode = root.right
    if (leftNode == null || rightNode == null) {
        return -1
    }
    val left = if (leftNode.`val` == root.`val`) findSecondMinimumValue2(leftNode) else leftNode.`val`
    val right = if (rightNode.`val` == root.`val`) findSecondMinimumValue2(rightNode) else rightNode.`val`
    if (left != -1 && right != -1) {
        return min(left, right)
    }
    if (left == -1) {
        return right
    }
    if (right == -1) {
        return left
    }
    return -1
}

fun main() {
    val node = createTreeNode(linkedListOf(arrayListOf(2, 2, 5, null, null, 5, 7)))!!
    val i = findSecondMinimumValue(node)
    println(i)
}
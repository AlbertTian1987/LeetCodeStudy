package com.gray.test.tree

import java.util.*

/**
 * 429. N叉树的层序遍历
 * 给定一个 N 叉树，返回其节点值的层序遍历。 (即从左到右，逐层遍历)。
 *
 * 例如，给定一个 3叉树 :
 *
 * 返回其层序遍历:
 *
 * [
 * [1],
 * [3,2,4],
 * [5,6]
 * ]
 *
 * 说明:
 * 树的深度不会超过 1000。
 * 树的节点总数不会超过 5000。
 * */
class NLevelOrder {
    inner class Node(val v: Int, val children: List<Node>?)

    fun levelOrder(root: Node?): List<List<Int>> {
        if (root == null) {
            return arrayListOf()
        }
        val queue: Queue<Node> = LinkedList<Node>()
        val ret = arrayListOf<ArrayList<Int>>()
        queue.add(root)
        while (queue.isNotEmpty()) {
            val size = queue.size
            val list = arrayListOf<Int>()
            repeat(size) {
                val node = queue.poll()!!
                list.add(node.v)
                node.children?.let {
                    for (n in it) {
                        queue.offer(n)
                    }
                }
            }
            ret.add(list)
        }
        return ret
    }
}

package com.gray.test.tree

import java.util.*

/***
 * 589. N叉树的前序遍历
 * */
fun nxPreorder(root: XNode?): List<Int> {
    fun preorder(root: XNode?, list: ArrayList<Int>) {
        if (root == null) {
            return
        }
        list.add(root.`val`)
        val children = root.children
        if (children != null && children.isNotEmpty()) {
            for (c in children) {
                preorder(c, list)
            }
        }
    }

    val ret = arrayListOf<Int>()
    preorder(root, ret)
    return ret
}

fun nxPreorder2(root: XNode?): List<Int> {
    if (root == null) {
        return Collections.emptyList()
    }
    val ret = arrayListOf<Int>()
    val stack = LinkedList<XNode>()
    var cur: XNode? = root
    while (cur != null || stack.isNotEmpty()) {
        if (cur != null) {
            ret.add(cur.`val`)
            val children = cur.children
            if (children == null || children.isEmpty()) {
                cur = null
            } else {
                cur = children.first()
                for (i in children.size - 1 downTo 1) {
                    stack.push(children[i])
                }
            }
        } else {
            cur = stack.pop()!!
        }
    }
    return ret
}

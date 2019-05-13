package com.gray.test.tree

import java.util.*


/***
 * 590. N叉树的后序遍历
 * */
fun nxPostorder(root: XNode?): List<Int> {
    fun postorder(root: XNode?, list: ArrayList<Int>) {
        if (root == null) {
            return
        }
        val children = root.children
        if (children.isNotEmpty()) {
            for (c in children) {
                postorder(c, list)
            }
        }
        list.add(root.`val`)
    }

    val ret = arrayListOf<Int>()
    postorder(root, ret)
    return ret
}

fun NXPostorder(root: XNode?): List<Int> {
    if (root == null) {
        return Collections.emptyList()
    }
    val ret = ArrayList<Int>()
    val stack = ArrayDeque<XNode>()
    stack.push(root)
    var cur: XNode?
    while (!stack.isEmpty()) {
        cur = stack.pop()!!
        ret.add(0, cur.`val`)
        for (i in 0 until cur.children.size) {
            stack.push(cur.children[i])
        }
    }
    return ret
}
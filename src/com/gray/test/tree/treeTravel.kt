package com.gray.test.tree

import java.util.*

/**
 * 二叉树的遍历写法
 *
 * */

val root = createTreeNode(linkedListOf(arrayListOf(1, 2, 3, 4, 5, 6, 7)))

fun preOrder1(root: TreeNode?) {
    if (root == null) {
        return
    }
    print(" " + root.`val`)
    preOrder1(root.left)
    preOrder1(root.right)
}

fun preOrder2(root: TreeNode?) {
    if (root == null) {
        return
    }
    val stack = LinkedList<TreeNode>()
    var cur: TreeNode? = root
    while (cur != null || stack.isNotEmpty()) {
        while (cur != null) {
            print(" " + cur.`val`)
            stack.push(cur)
            cur = cur.left
        }
        if (stack.isNotEmpty()) {
            cur = stack.pop()
            cur = cur.right
        }
    }
}

fun preOrder3(root: TreeNode?) {
    if (root == null) {
        return
    }
    val stack = LinkedList<TreeNode>()
    var cur: TreeNode? = root
    while (cur != null || stack.isNotEmpty()) {
        if (cur != null) {
            print(" " + cur.`val`)
            stack.push(cur)
            cur = cur.left
        } else {
            cur = stack.pop()
            cur = cur.right
        }
    }
}


fun preOrder4(root: TreeNode?) {
    if (root == null) {
        return
    }
    val stack = LinkedList<TreeNode>()
    stack.push(root)
    var cur: TreeNode = root
    while (stack.isNotEmpty()) {
        print(" " + cur.`val`)

        if (cur.right != null) {
            stack.push(cur.right)
        }
        cur = if (cur.left != null) {
            cur.left!!
        } else {
            stack.pop()!!
        }
    }
}

fun midOrder1(root: TreeNode?) {
    if (root == null) {
        return
    }
    midOrder1(root.left)
    print(" " + root.`val`)
    midOrder1(root.right)
}

fun midOrder2(root: TreeNode?) {
    if (root == null) {
        return
    }
    val stack = LinkedList<TreeNode>()
    var cur: TreeNode? = root
    while (cur != null || stack.isNotEmpty()) {
        while (cur != null) {
            stack.push(cur)
            cur = cur.left
        }
        if (stack.isNotEmpty()) {
            cur = stack.pop()
            print(" " + cur.`val`)
            cur = cur.right
        }
    }
}

fun midOrder3(root: TreeNode?) {
    if (root == null) {
        return
    }
    val stack = LinkedList<TreeNode>()
    var cur: TreeNode? = root
    while (cur != null || stack.isNotEmpty()) {
        if (cur != null) {
            stack.push(cur)
            cur = cur.left
        } else {
            cur = stack.pop()
            print(" " + cur.`val`)
            cur = cur.right
        }
    }
}


fun postOrder1(root: TreeNode?) {
    if (root == null) {
        return
    }
    postOrder1(root.left)
    postOrder1(root.right)
    print(" " + root.`val`)
}

fun postOrder2(root: TreeNode?) {
    if (root == null) {
        return
    }
    var lastVisited: TreeNode? = null
    val stack = LinkedList<TreeNode>()
    var cur: TreeNode? = root
    while (cur != null) {
        stack.push(cur)
        cur = cur.left
    }
    while (stack.isNotEmpty()) {
        cur = stack.peek()
        if (cur.right == null || cur.right == lastVisited) {
            stack.pop()
            print(" " + cur.`val`)
            lastVisited = cur
        } else {
            cur = cur.right
            while (cur != null) {
                stack.push(cur)
                cur = cur.left
            }
        }
    }
}

fun main() {
    println("前序")
    preOrder1(root)
    println()
    preOrder2(root)
    println()
    preOrder3(root)
    println()
    preOrder4(root)
    println("\n中序")
    midOrder1(root)
    println()
    midOrder2(root)
    println()
    midOrder3(root)
    println("\n后序")
    postOrder1(root)
    println()
    postOrder2(root)
}

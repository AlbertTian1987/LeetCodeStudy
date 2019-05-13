package com.gray.test.tree

/**
 * 653. 两数之和 IV - 输入 BST
 * 给定一个二叉搜索树和一个目标结果，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。
 *
 * 案例 1:
 * 输入:
 *     5
 *    / \
 *   3   6
 *  / \   \
 * 2   4   7
 *
 * Target = 9
 * 输出: True
 *
 *
 * 案例 2:
 * 输入:
 *     5
 *    / \
 *   3   6
 *  / \   \
 * 2   4   7
 * Target = 28
 * 输出: False
 * */
fun findTarget(root: TreeNode?, k: Int): Boolean {
    if (root == null) {
        return false
    }
    val array = arrayListOf<Int>()
    fun dfs(root: TreeNode?) {
        if (root == null) {
            return
        }
        dfs(root.left)
        array.add(root.`val`)
        dfs(root.right)
    }
    dfs(root)

    for (i in 0 until array.size - 1) {
        val cur = array[i]
        val target = k - cur
        if (findElement(array, i + 1, array.size - 1, target)) {
            return true
        }
    }
    return false
}

fun findElement(array: ArrayList<Int>, i: Int, j: Int, target: Int): Boolean {
    var lo = i
    var hi = j
    while (lo <= hi) {
        val mid = lo + (hi - lo) / 2
        if (array[mid] == target) {
            return true
        }
        if (array[mid] < target) {
            lo = mid + 1
        } else {
            hi = mid - 1
        }
    }
    return false
}

fun main() {
    println(findTarget(createTreeNode(linkedListOf(arrayListOf(2, 1, 3))), 4))
}
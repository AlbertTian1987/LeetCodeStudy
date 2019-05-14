package com.gray.test.design

/***
 * 703. 数据流中的第K大元素
 * 设计一个找到数据流中第K大元素的类（class）。注意是排序后的第K大元素，不是第K个不同的元素。
 * 你的 KthLargest 类需要一个同时接收整数 k 和整数数组nums 的构造器，它包含数据流中的初始元素。
 * 每次调用 KthLargest.add，返回当前数据流中第K大的元素。
 *
 * 示例:
 * int k = 3;
 *  int[] arr = [4,5,8,2];
 *  KthLargest kthLargest = new KthLargest(3, arr);
 *  kthLargest.add(3);   // returns 4
 *  kthLargest.add(5);   // returns 5
 *  kthLargest.add(10);  // returns 5
 *  kthLargest.add(9);   // returns 8
 *  kthLargest.add(4);   // returns 8
 *
 * 说明:
 *  你可以假设 nums 的长度≥ k-1 且k ≥ 1。
 *
 *  第一次，自己写了一个搜索树来维护
 */
class KthLargest(val k: Int, nums: IntArray) {

    inner class Node(var v: Int, var left: Node? = null, var right: Node? = null)

    var kMax = 0
    var kCount = k
    var root: Node? = null

    init {
        nums.sort()
        if (nums.isNotEmpty()) {
            root = buildBST(nums, 0, nums.size - 1)!!
        }
    }

    private fun buildBST(nums: IntArray, lo: Int, hi: Int): Node? {
        if (lo > hi) {
            return null
        }
        val mid = (lo + hi) / 2
        val root = Node(nums[mid])
        root.left = buildBST(nums, lo, mid - 1)
        root.right = buildBST(nums, mid + 1, hi)
        return root
    }

    private fun insert(`val`: Int, root: Node) {
        var cur = root
        while (true) {
            while (cur.left != null && cur.v >= `val`) {
                cur = cur.left!!
            }
            while (cur.right != null && cur.v < `val`) {
                cur = cur.right!!
            }
            if (cur.left == null && cur.v >= `val`) {
                cur.left = Node(`val`)
                break
            } else if (cur.right == null && cur.v < `val`) {
                cur.right = Node(`val`)
                break
            }
        }
    }

    private fun dfs(root: Node?) {
        if (root == null) {
            return
        }
        dfs(root.right)
        kCount--
        if (kCount == 0) {
            kMax = root.v
        } else {
            dfs(root.left)
        }
    }

    fun add(`val`: Int): Int {
        if (root == null) {
            root = Node(`val`)
        } else {
            insert(`val`, root!!)
        }
        kCount = k
        dfs(root)
        return kMax
    }

}

fun main() {
    val kthLargest = KthLargest(3, intArrayOf(5, -1))
    println(kthLargest.add(2))
    println(kthLargest.add(1))
    println(kthLargest.add(-1))
    println(kthLargest.add(3))
    println(kthLargest.add(4))
}
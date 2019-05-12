package com.gray.test.array

/**
 * 532. 数组中的K-diff数对
 * 给定一个整数数组和一个整数 k, 你需要在数组里找到不同的 k-diff 数对。这里将 k-diff 数对定义为一个整数对 (i, j), 其中 i 和 j 都是数组中的数字，且两数之差的绝对值是 k.
 *
 * 示例 1:
 *  输入: [3, 1, 4, 1, 5], k = 2
 *  输出: 2
 *  解释: 数组中有两个 2-diff 数对, (1, 3) 和 (3, 5)。
 *  尽管数组中有两个1，但我们只应返回不同的数对的数量。
 *
 * 示例 2:
 *  输入:[1, 2, 3, 4, 5], k = 1
 *  输出: 4
 *  解释: 数组中有四个 1-diff 数对, (1, 2), (2, 3), (3, 4) 和 (4, 5)。
 *
 * 示例 3:
 *  输入: [1, 3, 1, 5, 4], k = 0
 *  输出: 1
 *  解释: 数组中只有一个 0-diff 数对，(1, 1)。
 *
 * 注意:
 * 数对 (i, j) 和数对 (j, i) 被算作同一数对。
 * 数组的长度不超过10,000。
 * 所有输入的整数的范围在 [-1e7, 1e7]。
 * */
fun findPairs(nums: IntArray, k: Int): Int {
    nums.sort()
    var flag = false
    var pre = 0
    var count = 0
    for (i in 0 until nums.size) {
        if (flag && nums[i] == pre) {
            continue
        }
        val target = nums[i] + k
        val i1 = findIndex(i + 1, nums.size - 1, nums, target)
        if (i1 != -1) {
            count++
        }
        flag = true
        pre = nums[i]
    }
    return count
}

fun findIndex(i: Int, j: Int, nums: IntArray, target: Int): Int {
    var lo = i
    var hi = j
    while (lo <= hi) {
        val mid = lo + (hi - lo) / 2
        if (nums[mid] == target) {
            return mid
        }
        if (nums[mid] < target) {
            lo = mid + 1
        } else {
            hi = mid - 1
        }
    }
    return -1
}

fun main() {
    println(findPairs(intArrayOf(3, 1, 4, 1, 5), 2))
    println(findPairs(intArrayOf(1, 2, 3, 4, 5), 1))
    println(findPairs(intArrayOf(1, 3, 1, 5, 4), 0))
}
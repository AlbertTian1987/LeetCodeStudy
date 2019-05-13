package com.gray.test.array

/**
 * 594. 最长和谐子序列
 * 和谐数组是指一个数组里元素的最大值和最小值之间的差别正好是1。
 * 现在，给定一个整数数组，你需要在所有可能的子序列中找到最长的和谐子序列的长度。
 *
 * 示例 1:
 * 输入: [1,3,2,2,5,2,3,7]
 * 输出: 5
 * 原因: 最长的和谐数组是：[3,2,2,2,3].
 *
 * 说明: 输入的数组长度最大不超过20,000.
 * */
fun findLHS(nums: IntArray): Int {
    if (nums.isEmpty()) {
        return 0
    }
    val dict = hashMapOf<Int, Int>()
    var best = 0
    for (n in nums) {
        dict[n] = dict.getOrDefault(n, 0) + 1
    }
    for (n in nums) {
        if (dict.containsKey(n + 1)) {
            val t = dict[n + 1]!! + dict[n]!!
            if (t > best) {
                best = t
            }
        }
        if (dict.containsKey(n - 1)) {
            val t = dict[n - 1]!! + dict[n]!!
            if (t > best) {
                best = t
            }
        }
    }
    return best
}
package com.gray.test.dynamicPlan

/***
 * Longest Increasing Subsequence
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 * 示例:
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 *
 * 说明:
 * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 * 你算法的时间复杂度应该为 O(n2) 。
 * 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
 */
fun lengthOfLIS(nums: IntArray): Int {
    if (nums.isEmpty()) {
        return 0
    }
    var maxLen = 1
    val dp = IntArray(nums.size)
    dp[0] = nums[0]
    for (i in 1 until nums.size) {
        val n = nums[i]
        var k = 0
        while (k < maxLen && dp[k] < n) {
            k++
        }
        dp[k] = n
        if (k == maxLen) {
            maxLen++
        }
    }
    return maxLen
}

fun lengthOfLIS2(nums: IntArray): Int {
    var maxLen = 0
    val dp = IntArray(nums.size)
    for (n in nums) {
        var lo = 0
        var hi = maxLen
        while (lo < hi) {
            val mid = (hi + lo) / 2
            if (dp[mid] < n) {
                lo = mid + 1
            } else {
                hi = mid
            }
        }
        dp[lo] = n
        if (lo == maxLen) {
            maxLen++
        }
    }
    return maxLen
}


fun main() {
    println(lengthOfLIS2(intArrayOf(10, 9, 2, 5, 3, 7, 101, 18)))
    println(lengthOfLIS2(intArrayOf(4, 10, 4, 3, 8, 9)))
    println(lengthOfLIS2(intArrayOf(3, 5, 6, 2, 5, 4, 19, 5, 6, 7, 12)))
}
package com.gray.test.array

/**
 * 643. 子数组最大平均数 I
 * 给定 n 个整数，找出平均数最大且长度为 k 的连续子数组，并输出该最大平均数。
 *
 * 示例 1:
 * 输入: [1,12,-5,-6,50,3], k = 4
 * 输出: 12.75
 * 解释: 最大平均数 (12-5-6+50)/4 = 51/4 = 12.75
 *
 * 注意:
 * 1 <= k <= n <= 30,000。
 * 所给数据范围 [-10,000，10,000]。
 * */

fun findMaxAverage(nums: IntArray, k: Int): Double {
    var sum = nums.slice(0 until k).sum().toDouble()
    var max = sum / k
    for (i in k until nums.size) {
        sum += nums[i] - nums[i - k]
        val temp = sum / k
        if (temp > max) {
            max = temp
        }
    }
    return max
}
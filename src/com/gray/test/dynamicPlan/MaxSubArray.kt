package com.gray.test.dynamicPlan

/**
 * 最大子序和
 *
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 示例:
 *      输入: [-2,1,-3,4,-1,2,1,-5,4],
 *      输出: 6
 *      解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 *
 * 进阶:
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 *
 * */

fun maxSubArray(nums: IntArray): Int {
    var max = Int.MIN_VALUE
    var sum = 0
    nums.forEach {
        if (sum < 0) {
            sum = it
        } else {
            sum += it
        }
        max = kotlin.math.max(sum, max)
    }
    return max
}

fun main() {
    println(maxSubArray(intArrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4)))
    println(maxSubArray(intArrayOf(1, 1, 1, 1, -2, 4, -3)))
    println(maxSubArray(intArrayOf(-3, -21, -51, -11, -2, -4, -1)))
}
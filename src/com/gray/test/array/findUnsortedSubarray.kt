package com.gray.test.array

import kotlin.math.max
import kotlin.math.min

/***
 * 581. 最短无序连续子数组
 * 给定一个整数数组，你需要寻找一个连续的子数组，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
 * 你找到的子数组应是最短的，请输出它的长度。
 *
 * 示例 1:
 * 输入: [2, 6, 4, 8, 10, 9, 15]
 * 输出: 5
 * 解释: 你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
 *
 * 说明 :
 * 输入的数组长度范围在 [1, 10,000]。
 * 输入的数组可能包含重复元素 ，所以升序的意思是<=。
 */
fun findUnsortedSubarray(nums: IntArray): Int {
    var hi = 0
    var lo = nums.size - 1
    var maxValue = nums[hi]
    var minValue = nums[lo]
    for ((i, n) in nums.withIndex()) {
        val k = nums[nums.size - 1 - i]
        maxValue = max(maxValue, n)
        minValue = min(minValue, k)
        if (n < maxValue) {
            hi = i
        }
        if (k > minValue) {
            lo = nums.size - 1 - i
        }
    }
    return if (lo >= hi) 0 else hi - lo + 1
}

fun main() {
    println(findUnsortedSubarray(intArrayOf(2, 6, 4, 8, 10, 9, 15)))
    println(findUnsortedSubarray(intArrayOf(2)))
    println(findUnsortedSubarray(intArrayOf(3, 2)))
    println(findUnsortedSubarray(intArrayOf(3, 2, 4)))
    println(findUnsortedSubarray(intArrayOf(4, 2, 3)))
    println(findUnsortedSubarray(intArrayOf(2, 3, 3, 2, 4)))
    println(findUnsortedSubarray(intArrayOf(1, 2, 4, 5, 3)))
}


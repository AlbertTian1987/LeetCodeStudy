package com.gray.test.array

/***
 * 219. 存在重复元素 II
 * 给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，使得 nums [ i] = nums [ j]，并且 i 和 j 的差的绝对值 <= k。
 *
 * 示例 1:
 * 输入: nums = [1,2,3,1], k = 3
 * 输出: true
 *
 * 示例 2:
 * 输入: nums = [1,0,1,1], k = 1
 * 输出: true
 *
 * 示例 3:
 * 输入: nums = [1,2,3,1,2,3], k = 2
 * 输出: false
 *
 */

/**
 *
 */
fun containsNearbyDuplicate(nums: IntArray, k: Int): Boolean {
    for (dp in 1..k) {
        for (i in 0 until nums.size - dp) {
            if (nums[i] == nums[i + dp]) {
                return true
            }
        }
    }
    return false
}

fun main() {
    println(containsNearbyDuplicate(intArrayOf(1, 2, 3, 1), 3))
    println(containsNearbyDuplicate(intArrayOf(1, 0, 1, 1), 1))
    println(containsNearbyDuplicate(intArrayOf(1, 2, 3, 1, 2, 3), 2))
    println(containsNearbyDuplicate(intArrayOf(), 2))
    println(containsNearbyDuplicate(intArrayOf(1, 1), 1))
    println(containsNearbyDuplicate(intArrayOf(1, 1), 2))

}
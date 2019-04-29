package com.gray.test.array

/**
 * 35. 搜索插入位置
 *
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 你可以假设数组中无重复元素。
 *
 * 示例 1:
 *  输入: [1,3,5,6], 5
 *  输出: 2
 *
 * 示例 2:
 *  输入: [1,3,5,6], 2
 *  输出: 1
 *
 * 示例 3:
 *  输入: [1,3,5,6], 7
 *  输出: 4
 *
 * 示例 4:
 *  输入: [1,3,5,6], 0
 *  输出: 0
 */

fun searchInsert(nums: IntArray, target: Int): Int {
    val first = nums.first()
    if (target <= first) {
        return 0
    }
    val last = nums.last()
    if (target > last) {
        return nums.size
    }
    if (target == last) {
        return nums.lastIndex
    }

    var lo = 0
    var hi = nums.size - 1
    while (lo < hi) {
        val mid = (lo + hi) / 2
        if (nums[mid] == target) {
            return mid
        }
        if (nums[mid] < target) {
            lo = mid + 1
        } else {
            //这个地方要注意，不是二分查找，不要 hi = mid -1，最后找不到hi会逐步降低到lo，此时lo就是插入位置
            hi = mid
        }
    }
    return lo
}

fun main() {
    println(searchInsert(intArrayOf(5), 2))
    println(searchInsert(intArrayOf(5), 6))
    println(searchInsert(intArrayOf(1, 3), 0))
    println(searchInsert(intArrayOf(1, 3), 2))
    println(searchInsert(intArrayOf(1, 3), 4))
    println(searchInsert(intArrayOf(1, 3, 6), 0))
    println(searchInsert(intArrayOf(1, 3, 6), 2))
    println(searchInsert(intArrayOf(1, 3, 6), 4))
    println(searchInsert(intArrayOf(1, 3, 6), 7))
    println(searchInsert(intArrayOf(3, 5, 7, 9, 10), 8))
}

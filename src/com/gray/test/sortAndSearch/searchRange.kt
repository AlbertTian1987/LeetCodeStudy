package com.gray.test.sortAndSearch

import java.util.*

/***
 * 在排序数组中查找元素的第一个和最后一个位置
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * 如果数组中不存在目标值，返回 [-1, -1]。
 *
 * 示例 1:
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 *
 * 示例 2:
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: [-1,-1]
 */
fun searchRange(nums: IntArray, target: Int): IntArray {
    val i = search(nums, target, -1)
    if (i == -1) {
        return intArrayOf(-1, -1)
    }
    val j = search(nums, target, 1)
    return intArrayOf(i, j)
}

fun search(nums: IntArray, target: Int, flag: Int): Int {
    var lo = 0
    var hi = nums.size - 1
    while (lo <= hi) {
        val mid = lo + (hi - lo) / 2
        if (nums[mid] == target) {
            if (mid + flag < 0 || mid + flag == nums.size || nums[mid + flag] != target) {
                return mid
            }
            if (flag < 0) {
                hi = mid - 1
            } else {
                lo = mid + 1
            }
        } else if (nums[mid] > target) {
            hi = mid - 1
        } else {
            lo = mid + 1
        }
    }
    return -1
}

fun main() {
    println(Arrays.toString(searchRange(intArrayOf(5, 7, 7, 8, 8, 10), 8)))
    println(Arrays.toString(searchRange(intArrayOf(5, 7, 7, 8, 8, 10), 6)))
    println(Arrays.toString(searchRange(intArrayOf(1), 1)))
    println(Arrays.toString(searchRange(intArrayOf(1, 1), 1)))
    println(Arrays.toString(searchRange(intArrayOf(1, 1, 1), 1)))
    println(Arrays.toString(searchRange(intArrayOf(0, 1, 1), 1)))
}
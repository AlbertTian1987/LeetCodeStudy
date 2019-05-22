package com.gray.test.sortAndSearch

/***
 * 搜索旋转排序数组
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * 你可以假设数组中不存在重复的元素。
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * 示例 1:
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 *
 * 示例 2:
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 */
/**
 * 将数组一分为二，其中一定有一个是有序的，另一个可能是有序，也能是部分有序。
 * 此时有序部分用二分法查找。无序部分再一分为二，其中一个一定有序，另一个可能有序，可能无序。
 * 就这样循环.
 */
fun search(nums: IntArray, target: Int): Int {
    var lo = 0
    var hi = nums.size - 1
    while (lo <= hi) {
        val mid = lo + (hi - lo) / 2
        if (nums[mid] == target) {
            return mid
        }
        if (nums[mid] < nums[hi]) {
            if (target > nums[mid] && target <= nums[hi]) {
                lo = mid + 1
            } else {
                hi = mid - 1
            }
        } else {
            if (target >= nums[0] && target < nums[mid]) {
                hi = mid - 1
            } else {
                lo = mid + 1
            }
        }
    }
    return -1
}

fun main() {
    println(search(intArrayOf(4, 5, 6, 7, 0, 1, 2), 0))
    println(search(intArrayOf(4, 5, 6, 7, 0, 1, 2), 3))
    println(search(intArrayOf(2, 3, 1), 1))
    println(search(intArrayOf(3, 1), 1))
    println(search(intArrayOf(3), 1))
    println(search(intArrayOf(), 1))
}
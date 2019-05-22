package com.gray.test.sortAndSearch

/***
 * 寻找峰值
 * 峰值元素是指其值大于左右相邻值的元素。
 * 给定一个输入数组 nums，其中 nums[i ] ≠ nums[i+1]，找到峰值元素并返回其索引。
 * 数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可。
 * 你可以假设 nums[-1] = nums[n ] = -∞。
 *
 * 示例 1:
 * 输入: nums = [1,2,3,1]
 * 输出: 2
 * 解释: 3 是峰值元素，你的函数应该返回其索引 2。
 *
 * 示例 2:
 * 输入: nums = [1,2,1,3,5,6,4]
 * 输出: 1 或 5
 * 解释: 你的函数可以返回索引 1，其峰值元素为 2；
 * 或者返回索引 5， 其峰值元素为 6。
 *
 * 说明:
 * 你的解法应该是 O(logN) 时间复杂度的。
 */
/**
 * 关键是 nums[mid ] 和 nums[mid + 1] 比较
 * 若nums[mid ] > nums[mid + 1], 前面肯定有峰值，
 * 一直往前递归，若 mid = 0 ，它>1, -1是无穷小，所以峰值是它。
 * 若往前递归的时候，出现了nums[mid ] < nums[mid + 1]，会往后递归，
 * 那针对nums[mid + 1]，它最后会回到之前某一个 nums[mid ] > nums[mid + 1]处，这时候，它是作为nums[mid ]的
 */
fun findPeakElement(nums: IntArray): Int {
    var lo = 0
    var hi = nums.size - 1
    while (lo < hi) {
        val mid = lo + (hi - lo) / 2
        if (nums[mid] > nums[mid + 1]) {
            hi = mid
        } else {
            lo = mid + 1
        }
    }
    return lo
}
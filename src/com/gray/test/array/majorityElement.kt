package com.gray.test.array

/**
 * 169. 求众数
 *
 * 给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 * 你可以假设数组是非空的，并且给定的数组总是存在众数。
 *
 * 示例 1:
 *  输入: [3,2,3]
 *  输出: 3
 *
 * 示例 2:
 *  输入: [2,2,1,1,1,2,2]
 *  输出: 2
 * */

fun majorityElement(nums: IntArray): Int {
    var best = nums[0]
    var count = 1
    for (i in 1 until nums.size) {
        val n = nums[i]
        if (n == best) {
            count++
        } else {
            count--
        }
        if (count == 0) {
            best = n
            count = 1
        }
    }
    return best
}

fun main() {
    println(majorityElement(intArrayOf(3, 2, 3)))
    println(majorityElement(intArrayOf(2, 2, 1, 1, 1, 2, 2)))
}
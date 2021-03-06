package com.gray.test.array

/**
 * 747. 至少是其他数字两倍的最大数
 * 在一个给定的数组nums中，总是存在一个最大元素 。
 * 查找数组中的最大元素是否至少是数组中每个其他数字的两倍。
 * 如果是，则返回最大元素的索引，否则返回-1。
 *
 * 示例 1:
 *
 * 输入: nums = [3, 6, 1, 0]
 * 输出: 1
 * 解释: 6是最大的整数, 对于数组中的其他整数,
 * 6大于数组中其他元素的两倍。6的索引是1, 所以我们返回1.
 *
 * 示例 2:
 * 输入: nums = [1, 2, 3, 4]
 * 输出: -1
 * 解释: 4没有超过3的两倍大, 所以我们返回 -1.
 *
 * 提示:
 * nums 的长度范围在[1, 50].
 * 每个 nums[ i] 的整数范围在 [0, 99].
 * */
/**
 * 第一次做，用了两次循环
 */
fun dominantIndex(nums: IntArray): Int {
    var index = 0
    var max = nums[index]
    for (i in 1 until nums.size) {
        val num = nums[i]
        if (num > max) {
            max = num
            index = i
        }
    }
    for (n in nums) {
        if (n == max) {
            continue
        }
        if (max < 2 * n) {
            return -1
        }
    }
    return index
}

/**
 * 只需要找到最大和第二大的数，最大>=第二大的两倍即可
 */
fun dominantIndex2(nums: IntArray): Int {
    var index = -1
    var max = -1
    var secondMax = -1
    for (i in 0 until nums.size) {
        val num = nums[i]

        if (num > max) {
            secondMax = max
            max = num
            index = i
        } else if (num > secondMax) {
            secondMax = num
        }
    }
    return if (max >= 2 * secondMax) index else -1
}

fun main() {
    println(dominantIndex2(intArrayOf(3, 6, 1, 0)))
    println(dominantIndex2(intArrayOf(0, 1, 1, 2)))
}
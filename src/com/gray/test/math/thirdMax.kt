package com.gray.test.math

import kotlin.math.max

/***
 * 414. 第三大的数
 *
 * 给定一个非空数组，返回此数组中第三大的数。如果不存在，则返回数组中最大的数。要求算法时间复杂度必须是O(n)。
 *
 * 示例 1:
 * 输入: [3, 2, 1]
 * 输出: 1
 * 解释: 第三大的数是 1.
 *
 * 示例 2:
 * 输入: [1, 2]
 * 输出: 2
 * 解释: 第三大的数不存在, 所以返回最大的数 2 .
 *
 * 示例 3:
 * 输入: [2, 2, 3, 1]
 * 输出: 1
 * 解释: 注意，要求返回第三大的数，是指第三大且唯一出现的数。
 *      存在两个值为2的数，它们都排第二。
 */
fun thirdMax(nums: IntArray): Int {
    if (nums.size == 1) {
        return nums[0]
    }
    if (nums.size == 2) {
        return max(nums[0], nums[1])
    }
    var max1 = Int.MIN_VALUE
    var max2 = Int.MIN_VALUE
    var max3 = Int.MIN_VALUE
    var count = 0
    var first = true
    for (n in nums) {
        if (n == Int.MIN_VALUE && first) {
            first = false
            count++
        }
        if (n == max1 || n == max2 || n == max3) {
            continue
        }

        when {
            n > max1 -> {
                max3 = max2
                max2 = max1
                max1 = n
                count++
            }
            n > max2 -> {
                max3 = max2
                max2 = n
                count++
            }
            n > max3 -> {
                max3 = n
                count++
            }
        }
    }
    return if (count >= 3) {
        max3
    } else {
        max1
    }
}

fun main() {
    println(thirdMax(intArrayOf(3, 2, 1)))
    println(thirdMax(intArrayOf(1, 2)))
    println(thirdMax(intArrayOf(2, 2, 3, 1)))
    println(thirdMax(intArrayOf(1, 2, 2)))
    println(thirdMax(intArrayOf(5, 2, 2)))
    println(thirdMax(intArrayOf(1, 2, 3)))
    println(thirdMax(intArrayOf(1, 2, -2147483648)))
}